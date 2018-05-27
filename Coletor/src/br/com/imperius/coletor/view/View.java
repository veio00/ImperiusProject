package br.com.imperius.coletor.view;

import static br.com.imperius.coletor.configuracao.Config.getProp;
import com.google.gson.Gson;
import br.com.imperius.coletor.controller.*;
import static br.com.imperius.coletor.controller.Envio.envioColeta;
import java.io.*;
import java.net.*;
import br.com.imperius.coletor.model.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hyperic.sigar.*;
import org.*;

/**
 *
 * @author Will
 */
public class View {

    public static void start() throws IOException, SigarException {
        Timer timer = new Timer();
        Gson g = new Gson();
        Leitura u = LeituraMaquina.ColetaUso();
        String WebServer = Padrao.getWebServer();

        System.out.println(g.toJson(LeituraMaquina.ColetaUso()));
        System.out.println(g.toJson(InfoMaquina.infoMaquina()));

        timer.schedule(
                new TimerTask() {
            @Override
            public void run() {

                boolean verificador = false;
                try {
                    int validar = Integer.parseInt(Envio.envioColeta(g.toJson(u), WebServer + "LeituraAgora"));
                    new ValidadorAlerta(u, validar);
                    System.out.println("foi ");
                } catch (IOException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }, new Date(), 100);//rada de 5 em 5 minutos

    }

    public static void main(String[] args) throws Exception {
        CadastroMaquina cm = new CadastroMaquina();
        Properties prop = getProp();
        String idMaquina = prop.getProperty("idMaquina"); //Variavel que guardará o id da maquina.
        String idEmpresa = prop.getProperty("idGrupo");//Variavel que guardará o empresa da maquina

        if ("0".equals(idEmpresa)) {
            cm.setVisible(true);
        } else if ("0".equals(idMaquina)) {
            cm.setVisible(true);
        } else {
            start();
        }

    }
}
