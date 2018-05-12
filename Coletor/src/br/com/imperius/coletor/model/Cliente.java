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
public class Cliente {
    private int idCliente;
    private String Nome;
    private String Email;
    private int Acesso_Cliente;
    private int Grupo_Cliente;

    /**
     * @return the idCliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the Nome
     */
    public String getNome() {
        return Nome;
    }

    /**
     * @param Nome the Nome to set
     */
    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * @return the Acesso_Cliente
     */
    public int getAcesso_Cliente() {
        return Acesso_Cliente;
    }

    /**
     * @param Acesso_Cliente the Acesso_Cliente to set
     */
    public void setAcesso_Cliente(int Acesso_Cliente) {
        this.Acesso_Cliente = Acesso_Cliente;
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
