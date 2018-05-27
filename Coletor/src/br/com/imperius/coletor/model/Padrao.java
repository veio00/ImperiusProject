/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imperius.coletor.model;

import static br.com.imperius.coletor.configuracao.Config.getProp;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Will
 */
public class Padrao {

    private static int id;
    private static int Grupo;
    private static String WebServer;
    
    public static int getGrupo() throws IOException {
        if(Grupo == 0){
            Properties props =  getProp();
            int grupo =Integer.parseInt(props.getProperty("idGrupo"));
            return Grupo = grupo;
        } else {
                    return Grupo;
        }
    }

    public static void setGrupo(int aGrupo) throws IOException {
        Grupo = aGrupo;
        br.com.imperius.coletor.configuracao.Config.setProp("idGrupo",aGrupo+"","Codigo da Empresa");
    }

    public int getId() {
        return id;
    }

    public static void setId(int Id) throws IOException {
        id = Id;
        br.com.imperius.coletor.configuracao.Config.setProp("idMaquina",Id+"","Codigo da maquina");
    }
    
    public static String getWebServer() throws IOException {
        Properties props =  getProp();
        return WebServer = props.getProperty("WebServer");
        
    }
    
    
    
    
    
    
    
}
