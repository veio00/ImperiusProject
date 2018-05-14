package br.com.imperius.coletor.view;

import static br.com.imperius.coletor.configuracao.Config.getProp;
import com.google.gson.Gson;
import br.com.imperius.coletor.controller.*;
import java.io.*;
import java.net.*;
import java.util.Properties;
import br.com.imperius.coletor.model.*;
import org.hyperic.sigar.*;
import org.*;

/**
 *
 * @author Will
 */
public class View {
    
    public static void start() throws IOException, SigarException{
        Gson g = new Gson();
        while(1>0){            
            System.out.println(g.toJson(LeituraMaquina.ColetaUso()));
            System.out.println(g.toJson(InfoMaquina.infoMaquina()));
            Leitura u = LeituraMaquina.ColetaUso();
            
            boolean verificador = false;
            while(!verificador){
                verificador = Envio.envioColeta(g.toJson(u),"http://imperius.azurewebsites.net/api/Coleta/LeituraAgora", Boolean.class);

                System.out.println(verificador);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        Properties prop = getProp();
        String idMaquina = prop.getProperty("idMaquina"); //Variavel que guardará o id da maquina.
        String idEmpresa = prop.getProperty("idGrupo");//Variavel que guardará o empresa da maquina
        if("0".equals(idMaquina) && "0".equals(idEmpresa)){
            CadastroMaquina cm = new CadastroMaquina();
            cm.setVisible(true);
        }
                   
    }    
    
}
