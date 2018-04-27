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
public class Maquina {
    private int idMaquina;
    private String Responsavel;
    private String Nome_Maquina;
    private String Adiquirida;
    private String Sistema;
    private int Keep_Alive;
    private int Grupo_Cliente;
    /**
     * @return the Sistema
     */
    public String getSistema() {
        return Sistema;
    }

    /**
     * @param Sistema the Sistema to set
     */
    public void setSistema(String Sistema) {
        this.Sistema = Sistema;
    }

    /**
     * @return the idMaquina
     */
    public int getIdMaquina() {
        return idMaquina;
    }

    /**
     * @param idMaquina the idMaquina to set
     */
    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    /**
     * @return the Responsavel
     */
    public String getResponsavel() {
        return Responsavel;
    }

    /**
     * @param Responsavel the Responsavel to set
     */
    public void setResponsavel(String Responsavel) {
        this.Responsavel = Responsavel;
    }

    /**
     * @return the Nome_Maquina
     */
    public String getNome_Maquina() {
        return Nome_Maquina;
    }

    /**
     * @param Nome_Maquina the Nome_Maquina to set
     */
    public void setNome_Maquina(String Nome_Maquina) {
        this.Nome_Maquina = Nome_Maquina;
    }

    /**
     * @return the Adiquirida
     */
    public String getAdiquirida() {
        return Adiquirida;
    }

    /**
     * @param Adiquirida the Adiquirida to set
     */
    public void setAdiquirida(String Adiquirida) {
        this.Adiquirida = Adiquirida;
    }

    /**
     * @return the Keep_Alive
     */
    public int getKeep_Alive() {
        return Keep_Alive;
    }

    /**
     * @param Keep_Alive the Keep_Alive to set
     */
    public void setKeep_Alive(int Keep_Alive) {
        this.Keep_Alive = Keep_Alive;
    }

    /**
     * @return the Grupo_Cliente
     */
    public int getGrupo_Cliente() {
        return Grupo_Cliente;
    }

    /**
     * @param Grupo_Cliente the Grupo_Cliente to set
     */
    public void setGrupo_Cliente(int Grupo_Cliente) {
        this.Grupo_Cliente = Grupo_Cliente;
    }
}
