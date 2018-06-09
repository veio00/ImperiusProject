/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imperius.coletor.model;

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

    public Aviso(int idAviso, String NomeAviso, int AvisoI1, int AvisoI2, int AvisoI3, int AvisoF1, int AvisoF2, int AvisoF3, int Maquina_Aviso) {
        this.idAviso = idAviso;
        this.NomeAviso = NomeAviso;
        this.AvisoI1 = AvisoI1;
        this.AvisoI2 = AvisoI2;
        this.AvisoI3 = AvisoI3;
        this.AvisoF1 = AvisoF1;
        this.AvisoF2 = AvisoF2;
        this.AvisoF3 = AvisoF3;
        this.Maquina_Aviso = Maquina_Aviso;
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
