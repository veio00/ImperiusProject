/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import static ConfiguracaoMaquina.Config.getProp;
import com.google.gson.Gson;
import controller.Alerta;
import controller.InfoMaquina;
import controller.Envio;
import controller.LeituraMaquina;
import java.io.*;
import java.net.*;
import java.util.Properties;
import model.Disco;
import model.Leitura;
import model.Maquina;
import model.Memoria;
import model.Processador;
import org.hyperic.sigar.*;
import org.*;

/**
 *
 * @author Will
 */
public class View {

    public static void main(String[] args) throws SigarException, Exception {
        Gson g = new Gson();
        String idMaquina,idEmpresa; //Variavel que guardará o id do servidor.

	System.out.println("************Teste de leitura do arquivo de propriedades************");
	Properties prop = getProp();
	idMaquina = prop.getProperty("idMaquina");
	idEmpresa = prop.getProperty("idGrupo");
	System.out.println("idMaquina = " + idMaquina+","+ "idGrupo = " + idEmpresa);
        while(1>0){
            
            System.out.println(g.toJson(LeituraMaquina.ColetaUso()));
            System.out.println(g.toJson(InfoMaquina.infoMaquina()));
            Leitura u = LeituraMaquina.ColetaUso();
            
            boolean verificador = false;
            while(verificador == false){
                verificador = Envio.envioColeta(g.toJson(u),"http://imperius.azurewebsites.net/api/Coleta/LeituraAgora", Boolean.class);

                System.out.println(verificador);
            }
        }
           
    }
    
    
    
}
