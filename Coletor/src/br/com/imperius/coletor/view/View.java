/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imperius.coletor.view;

import static br.com.imperius.coletor.configuracao.Config.getProp;
import com.google.gson.Gson;
import br.com.imperius.coletor.controller.Alerta;
import br.com.imperius.coletor.controller.InfoMaquina;
import br.com.imperius.coletor.controller.Envio;
import br.com.imperius.coletor.controller.LeituraMaquina;
import java.io.*;
import java.net.*;
import java.util.Properties;
import br.com.imperius.coletor.model.Disco;
import br.com.imperius.coletor.model.Leitura;
import br.com.imperius.coletor.model.Maquina;
import br.com.imperius.coletor.model.Memoria;
import br.com.imperius.coletor.model.Processador;
import org.hyperic.sigar.*;
import org.*;

/**
 *
 * @author Will
 */
public class View {

    public static void main(String[] args) throws SigarException, Exception {
        Gson g = new Gson();
        Properties prop = getProp();
        String idMaquina = prop.getProperty("idMaquina"); //Variavel que guardará o id da maquina.
        String idEmpresa; //Variavel que guardará o empresa da maquina
        idEmpresa = prop.getProperty("idGrupo");
        //if(idMaquina ==  null && idEmpresa == null){
            
            
        //}
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
    
    
    
}
