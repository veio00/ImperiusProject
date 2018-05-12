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
public class Grupo {
    private int idGrupo;
    private String Nome_grupo;

    /**
     * @return the idGrupo
     */
    public int getIdGrupo() {
        return idGrupo;
    }

    /**
     * @param idGrupo the idGrupo to set
     */
    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    /**
     * @return the Nome_grupo
     */
    public String getNome_grupo() {
        return Nome_grupo;
    }

    /**
     * @param Nome_grupo the Nome_grupo to set
     */
    public void setNome_grupo(String Nome_grupo) {
        this.Nome_grupo = Nome_grupo;
    }
}
