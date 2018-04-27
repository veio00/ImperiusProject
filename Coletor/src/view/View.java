/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.google.gson.Gson;
import controller.Alerta;
import controller.InfoMaquina;
import controller.Envio;
import controller.LeituraMaquina;
import java.io.*;
import java.net.*;
import model.Leitura;
import org.hyperic.sigar.*;
import org.*;

/**
 *
 * @author Will
 */
public class View {

    public static void main(String[] args) throws SigarException, Exception {
        Gson g = new Gson();
        System.out.println(g.toJson(LeituraMaquina.ColetaUso()));
        System.out.println(g.toJson(InfoMaquina.getDados()));
        InfoMaquina.getDados();
        Leitura u;
        u = LeituraMaquina.ColetaUso();
        boolean verificador = false;
        while(verificador == false){
            verificador = Envio.envioColeta(g.toJson(u),"http://imperius.azurewebsites.net/api/Coleta/LeituraAgora", Boolean.class);
            Alerta.KeepAlive("true",Boolean.class);
            System.out.println("foi");
        }
        

    }
    
    
}
