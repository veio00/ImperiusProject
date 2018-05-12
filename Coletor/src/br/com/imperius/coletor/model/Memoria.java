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
public class Memoria {
    private int idMemoria;
    private int Qtd;
    private String Geracao;
    private int Maquina_Memoria;

    /**
     * @return the idMemoria
     */
    public int getIdMemoria() {
        return idMemoria;
    }

    /**
     * @param idMemoria the idMemoria to set
     */
    public void setIdMemoria(int idMemoria) {
        this.idMemoria = idMemoria;
    }

    /**
     * @return the Qtd
     */
    public int getQtd() {
        return Qtd;
    }

    /**
     * @param Qtd the Qtd to set
     */
    public void setQtd(int Qtd) {
        this.Qtd = Qtd;
    }

    /**
     * @return the Geracao
     */
    public String getGeracao() {
        return Geracao;
    }

    /**
     * @param Geracao the Geracao to set
     */
    public void setGeracao(String Geracao) {
        this.Geracao = Geracao;
    }

    /**
     * @return the Maquina_Memoria
     */
    public int getMaquina_Memoria() {
        return Maquina_Memoria;
    }

    /**
     * @param Maquina_Memoria the Maquina_Memoria to set
     */
    public void setMaquina_Memoria(int Maquina_Memoria) {
        this.Maquina_Memoria = Maquina_Memoria;
    }
}
