/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Will
 */
public class Leitura {
    private int idUso;
    private int Hd;
    private int Mram;
    private int Cpu;
    private String Data;
    private int Maquina_Uso;

    /**
     * @return the idUso
     */
    public int getIdUso() {
        return idUso;
    }

    /**
     * @param idUso the idUso to set
     */
    public void setIdUso(int idUso) {
        this.idUso = idUso;
    }

    /**
     * @return the Hd
     */
    public int getHd() {
        return Hd;
    }

    /**
     * @param Hd the Hd to set
     */
    public void setHd(int Hd) {
        this.Hd = Hd;
    }

    /**
     * @return the Mram
     */
    public int getMram() {
        return Mram;
    }

    /**
     * @param Mram the Mram to set
     */
    public void setMram(int Mram) {
        this.Mram = Mram;
    }

    /**
     * @return the Cpu
     */
    public int getCpu() {
        return Cpu;
    }

    /**
     * @param Cpu the Cpu to set
     */
    public void setCpu(int Cpu) {
        this.Cpu = Cpu;
    }

    /**
     * @return the Maquina_Uso
     */
    public int getMaquina_Uso() {
        return Maquina_Uso;
    }

    /**
     * @param Maquina_Uso the Maquina_Uso to set
     */
    public void setMaquina_Uso(int Maquina_Uso) {
        this.Maquina_Uso = Maquina_Uso;
    }

    /**
     * @return the Data
     */
    public String getData() {
        return Data;
    }

    /**
     * @param Data the Data to set
     */
    public void setData(String Data) {
        this.Data = Data;
    }
}
