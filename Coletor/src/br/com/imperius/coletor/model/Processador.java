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
public class Processador {
    private int idCpu;
    private String Modelo;
    private int Maquina_Cpu;

    /**
     * @return the idCpu
     */
    public int getIdCpu() {
        return idCpu;
    }

    /**
     * @param idCpu the idCpu to set
     */
    public void setIdCpu(int idCpu) {
        this.idCpu = idCpu;
    }

    /**
     * @return the Modelo
     */
    public String getModelo() {
        return Modelo;
    }

    /**
     * @param Modelo the Modelo to set
     */
    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    /**
     * @return the Maquina_Cpu
     */
    public int getMaquina_Cpu() {
        return Maquina_Cpu;
    }

    /**
     * @param Maquina_Cpu the Maquina_Cpu to set
     */
    public void setMaquina_Cpu(int Maquina_Cpu) {
        this.Maquina_Cpu = Maquina_Cpu;
    }
}
