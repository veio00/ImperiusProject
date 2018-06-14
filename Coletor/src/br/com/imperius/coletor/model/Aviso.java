/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imperius.coletor.model;

import static br.com.imperius.coletor.configuracao.Config.getProp;
import static br.com.imperius.coletor.configuracao.Config.setProp;
import br.com.imperius.coletor.controller.Envio;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Will
 */
public class Aviso {

    private int idAviso;
    private String NomeAviso;
    private int AvisoI1;
    private int AvisoI2;
    private int AvisoI3;
    private int AvisoF1;
    private int AvisoF2;
    private int AvisoF3;
    private int Maquina_Aviso;

    public Aviso() throws IOException {
        Properties props = getProp(); //pega propriedades atuais    
        this.idAviso = Integer.parseInt(props.getProperty("idAviso"));
        this.AvisoI1 = Integer.parseInt(props.getProperty("AvisoI1"));
        this.AvisoI2 = Integer.parseInt(props.getProperty("AvisoI2"));
        this.AvisoI3 = Integer.parseInt(props.getProperty("AvisoI3"));
        this.AvisoF1 = Integer.parseInt(props.getProperty("AvisoF1"));
        this.AvisoF2 = Integer.parseInt(props.getProperty("AvisoF2"));
        this.AvisoF3 = Integer.parseInt(props.getProperty("AvisoF3"));
        this.NomeAviso = props.getProperty("NomeAviso");
    }

    public static ArrayList<Aviso> carregaAviso() throws IOException {
        Properties props = getProp(); //pega propriedades atuais 

        String dt = Envio.envioColeta("", props.getProperty("WebServer") + "CarregaAviso?maquina=" + props.getProperty("idMaquina"));

        Gson gson = new Gson();
        Type Aviso = new TypeToken<ArrayList<Aviso>>() {
        }.getType();
        ArrayList<Aviso> a = gson.fromJson(dt, Aviso);
        return a;

    }

    public static boolean salvaAviso(Aviso a) throws IOException {
        Properties props = getProp(); //pega propriedades atuais 
        Gson g = new Gson();
        return Envio.envioColeta(g.toJson(a), props.getProperty("WebServer") + "SalvaAviso",boolean.class);

    }

    public int getIdAviso() {
        return idAviso;
    }

    public void setIdAviso(int idAviso) {
        this.idAviso = idAviso;
    }

    public String getNomeAviso() {
        return NomeAviso;
    }

    public void setNomeAviso(String NomeAviso) {
        this.NomeAviso = NomeAviso;
    }

    public int getAvisoI1() {
        return AvisoI1;
    }

    public void setAvisoI1(int AvisoI1) {
        this.AvisoI1 = AvisoI1;
    }

    public int getAvisoI2() {
        return AvisoI2;
    }

    public void setAvisoI2(int AvisoI2) {
        this.AvisoI2 = AvisoI2;
    }

    public int getAvisoI3() {
        return AvisoI3;
    }

    public void setAvisoI3(int AvisoI3) {
        this.AvisoI3 = AvisoI3;
    }

    public int getAvisoF1() {
        return AvisoF1;
    }

    public void setAvisoF1(int AvisoF1) {
        this.AvisoF1 = AvisoF1;
    }

    public int getAvisoF2() {
        return AvisoF2;
    }

    public void setAvisoF2(int AvisoF2) {
        this.AvisoF2 = AvisoF2;
    }

    public int getAvisoF3() {
        return AvisoF3;
    }

    public void setAvisoF3(int AvisoF3) {
        this.AvisoF3 = AvisoF3;
    }

    public int getMaquina_Aviso() {
        return Maquina_Aviso;
    }

    public void setMaquina_Aviso(int Maquina_Aviso) {
        this.Maquina_Aviso = Maquina_Aviso;
    }

}
