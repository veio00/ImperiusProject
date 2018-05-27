/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imperius.coletor.model;

import java.util.Date;

/**
 *
 * @author Will
 */
public class Logs {

    private int idLogs;
    private String Data;
    private int Uso_Logs;
    private String Msg;
    private int Leitura_Logs;

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public int getLeitura_Logs() {
        return Leitura_Logs;
    }

    public void setLeitura_Logs(int Leitura_Logs) {
        this.Leitura_Logs = Leitura_Logs;
    }

    public int getIdLogs() {
        return idLogs;
    }

    public void setIdLogs(int idLogs) {
        this.idLogs = idLogs;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public int getUso_Logs() {
        return Uso_Logs;
    }

    public void setUso_Logs(int Uso_Logs) {
        this.Uso_Logs = Uso_Logs;
    }
}
