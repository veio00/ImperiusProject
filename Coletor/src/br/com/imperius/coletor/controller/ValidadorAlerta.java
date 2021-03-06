package br.com.imperius.coletor.controller;

import br.com.imperius.coletor.configuracao.Config;
import static br.com.imperius.coletor.configuracao.Config.getProp;
import static br.com.imperius.coletor.configuracao.Config.setProp;

import br.com.imperius.coletor.model.Aviso;
import br.com.imperius.coletor.model.Leitura;
import br.com.imperius.coletor.model.Logs;
import br.com.imperius.coletor.model.Padrao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ValidadorAlerta {

    private static Timer tempo = new Timer(); // timer que esta excultando atualmente
    private static TimerTask tarefa; // timer que esta excultando atualmente

    //Pega data atual para tratativa de renovação de avisos 
    private static String DataAviso = new SimpleDateFormat("dd/M/yyyy hh:mm").format(Calendar.getInstance().getTime());
    private static int idLeitura; // server para pegar o id da leitura analisada
    private static String WebServer; //server para pegar link da api  
    private static String nivelAtual; //pega nivel de alerta atual 
    private static String disparado; // pega ultima data que foi acionado
    private static int AvisoI1;
    private static int AvisoI2;
    private static int AvisoI3;
    private static int AvisoF1;
    private static int AvisoF2;
    private static int AvisoF3;
    private static int NomeAviso;

    public static void validacao(Leitura uso, int id) throws IOException {

        idLeitura = id;
        Properties props = getProp(); //pega propriedades atuais 
        disparado = props.getProperty("Disparado");      //pegar propridade Disparo atual para tratativa;
        nivelAtual = props.getProperty("nivelAviso");    //pegar propridade o nivel de aviso atual para tratativa;
        WebServer = props.getProperty("WebServer"); //pega link padrão para envio do log;        

        String ultimoDisparo = disparado;
        String nivel = nivelAtual;
        String dataAtual = DataAviso;
        int cpu = uso.getCpu();     // pega a leitura da Cpu para tratativa 
        int hd = uso.getHd();       // pega a leitura da HD para tratativa 
        int mram = uso.getMram();   // pega a leitura da Memaria ram para tratativa 

        try {
            if ("0".equals(ultimoDisparo) || !ultimoDisparo.substring(0, 4).equals(dataAtual.substring(0, 4))) {
                //Cria ou atualiza propriedade que guarda a ultima data de disparo.
                Config.setProp("Disparado", DataAviso, "data do utimo avisa disparado");
                //Cria ou atualiza propridade que quarda o Nivel de aviso resetado.
                Config.setProp("nivelAviso", "0", "Nivel de aviso atual");
            }
        } catch (IOException e) {
            try {
                setProp("nivelAviso", "");
                setProp("Disparado", "");
            } catch (IOException f) {
                throw f;
            }
        }
        //verifica se ja foi disparado algum aviso hoje 
        //caso não tenha ele verifica se a necessidade,
        //caso tenho verifica se é necessario aumentar o nivel de aviso.
        String verificar = props.getProperty("NomeAviso");
        String padrao = "Padrão";
        if (!padrao.equals(verificar)) {
            int idAviso = Integer.parseInt(props.getProperty("idAviso"));
            int AvisoI1 = Integer.parseInt(props.getProperty("AvisoI1"));
            int AvisoI2 = Integer.parseInt(props.getProperty("AvisoI2"));
            int AvisoI3 = Integer.parseInt(props.getProperty("AvisoI3"));
            int AvisoF1 = Integer.parseInt(props.getProperty("AvisoF1"));
            int AvisoF2 = Integer.parseInt(props.getProperty("AvisoF2"));
            int AvisoF3 = Integer.parseInt(props.getProperty("AvisoF3"));

            if (cpu > AvisoI1 && cpu <= AvisoF1 || hd > AvisoI3 && hd <= AvisoF3 || mram > AvisoF2 && mram <= AvisoI2) {
                //Chama aviso de personalizado 
                personalizado();
            }
        } else {
            if ("".equals(nivel)) {
                if (cpu > 74 && cpu <= 84 || hd > 79 && hd <= 80 || mram > 69 && mram <= 79) {
                    //Chama aviso de nivel 3
                    p3();
                } else if (cpu >= 85 && cpu <= 94 || hd >= 81 && hd <= 89 || mram >= 80 && mram <= 89) {
                    //Chama aviso de nivel 2
                    p2();
                } else if (cpu >= 95 || hd >= 90 || mram >= 90) {
                    //Chama aviso de nivel 1
                    p1();
                } else {
                    //para o aviso do dia anterior caso esteja execultando e não tenha mais necessidade.
                    //tempo.cancel();
                    pause();
                    resume();
                }
            } else {
                //verifica qual nivel atual de aviso e se ah necessidade de aumenta-lo
                switch (nivel) {
                    case "p1":
                        //verifica a necessidade do aviso ainda estar avivo
                        if (cpu >= 85 && cpu <= 94 || hd >= 81 && hd <= 89 || mram >= 80 && mram <= 89) {
                            //para contagem anterior
                            //tempo.cancel();
                            //Chama aviso de nivel 2 caso tenha regredido um nivel de aviso;
                            pause();
                            resume();
                            p2();
                        } else if (cpu > 74 && cpu <= 84 || hd > 79 && hd <= 80 || mram > 69 && mram <= 79) {
                            //para contagem anterior
                            //tempo.cancel();
                            //Chama aviso de nivel 3 caso tenha regredido um nivel de aviso;
                            pause();
                            resume();
                            p3();
                        }
                        break;
                    case "p2":
                        if (cpu >= 95 || hd >= 90 || mram >= 90) {
                            pause();
                            resume();
                            //Chama aviso de nivel 1
                            p1();
                        } else if (cpu > 74 && cpu <= 84 || hd > 79 && hd <= 80 || mram > 69 && mram <= 79) {
                            pause();
                            resume();
                            //Chama aviso de nivel 3 caso tenha regredido um nivel de aviso;
                            p3();
                        }
                        break;
                    case "p3":
                        if (cpu >= 85 && cpu <= 94 || hd >= 81 && hd <= 89 || mram >= 80 && mram <= 89) {
                            //para contagem anterior
                            //tempo.cancel();
                            //Chama aviso de nivel 2
                            pause();
                            resume();
                            p2();
                        } else if (cpu >= 95 || hd >= 90 || mram >= 90) {
                            pause();
                            resume();
                            //Chama aviso de nivel 1
                            p1();
                        }
                        break;
                    default:
                        Config.setProp("nivelAviso", "", "Nivel de aviso atual");
                        break;

                }

            }
        }
    }

// metodo de aviso baixo aonde sera avisado 1 vez so por dia caso
    private static void p3() throws IOException {
        Config.setProp("nivelAviso", "p3", "Nivel de aviso atual");
        //cria contagem de tempo para enviar o proximo logs ou aviso
        try {
            tempo.schedule(
                    new TimerTask() {
                @Override
                public void run() {
                    //instancia um objeto de GSON para conversão se json 
                    Gson g = new Gson();
                    //onstancia da classe Logs 
                    Logs log = new Logs();
                    // guarda informaçoes dentro ca classe de get set 
                    log.setData(DataAviso);
                    log.setMsg("Seu Computador esta em risco por favor procurar um tecnico o mais rapido possivel");
                    log.setLeitura_Logs(idLeitura);
                    System.out.println(g.toJson(log) + WebServer + "SalvaLogs");

                    try {
                        //envia aviso para o web server 
                        Envio.envioColeta(g.toJson(log), WebServer + "SalvaLogs", Boolean.class
                        );

                    } catch (IOException ex) {
                        System.out.println(ex);
                    } finally {
                        System.out.println(" p3 foi enviado");
                    }

                }
            }, new Date(), 1440000);// roda em 24hs 
        } finally {
            System.out.println("Erro ao enviar");
        }

    }

    // metodo de aviso Medio aonde sera avisado de seis em seis horas 
    private static void p2() throws IOException {
        Config.setProp("nivelAviso", "p2", "Nivel de aviso atual");
        //cria contagem de tempo para enviar o proximo logs ou aviso
        try {
            tempo.schedule(
                    new TimerTask() {
                @Override
                public void run() {
                    //instancia um objeto de GSON para conversão se json 
                    Gson g = new Gson();
                    //onstancia da classe Logs 
                    Logs log = new Logs();
                    // guarda informaçoes dentro ca classe de get set 

                    log.setData(DataAviso);
                    log.setMsg("olha seu computador ta mal se vc não cuidar vai dar ruim ");
                    log.setLeitura_Logs(idLeitura);
                    System.out.println(g.toJson(log) + WebServer + "SalvaLogs");

                    try {
                        Envio.envioColeta(g.toJson(log), WebServer + "SalvaLogs", Boolean.class
                        );

                    } catch (IOException ex) {
                        System.out.println(ex);
                    } finally {
                        System.out.println(" p2 foi enviado");
                    }
                }
            }, new Date(), 360000);// roda de 6 em 6 horas
        } finally {
            System.out.println("Erro ao enviar");
        }
    }

    // metodo de aviso Alto aonde sera avisado de uma em uma hora 
    private static void p1() throws IOException {
        Config.setProp("nivelAviso", "p1", "Nivel de aviso atual");
        //cria contagem de tempo para enviar o proximo logs ou aviso
        try {
            tempo.schedule(
                    new TimerTask() {
                @Override
                public void run() {
                    //instancia um objeto de GSON para conversão se json 
                    Gson g = new Gson();
                    //onstancia da classe Logs 
                    Logs log = new Logs();
                    // guarda informaçoes dentro ca classe de get set 

                    log.setData(DataAviso);
                    log.setMsg("Seu computador vai explodir");
                    log.setLeitura_Logs(idLeitura);
                    System.out.println(g.toJson(log) + WebServer + "SalvaLogs");

                    try {
                        Envio.envioColeta(g.toJson(log), WebServer + "SalvaLogs", Boolean.class
                        );

                    } catch (IOException ex) {
                        System.out.println(ex);
                    } finally {
                        System.out.println(" p1 foi enviado");
                    }
                }
            }, new Date(), 60000);//toda de 1 em 1 hora
        } finally {
            System.out.println("Erro ao enviar");
        }
    }

    // metodo de aviso Alto aonde sera avisado de uma em uma hora 
    private static void personalizado() throws IOException {
        Config.setProp("nivelAviso", "p1", "Nivel de aviso atual");
        //cria contagem de tempo para enviar o proximo logs ou aviso
        try {
            tempo.schedule(
                    new TimerTask() {
                @Override
                public void run() {
                    //instancia um objeto de GSON para conversão se json 
                    Gson g = new Gson();
                    //onstancia da classe Logs 
                    Logs log = new Logs();
                    // guarda informaçoes dentro ca classe de get set

                    log.setData(DataAviso);
                    log.setMsg("Seu aviso personalizado foi acionado");
                    log.setLeitura_Logs(idLeitura);
                    System.out.println(g.toJson(log) + WebServer + "SalvaLogs");

                    try {
                        Envio.envioColeta(g.toJson(log), WebServer + "SalvaLogs", Boolean.class
                        );

                    } catch (IOException ex) {
                        System.out.println(ex);
                    } finally {
                        System.out.println(" personalizado foi enviado");
                    }
                }
            }, new Date(), 60000);//toda de 1 em 1 hora
        } finally {
            System.out.println("Erro ao enviar");
        }
    }

    public static void pause() {
        tempo.cancel();
    }

    public static void resume() {
        tempo = new Timer();
    }
}
