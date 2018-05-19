package br.com.imperius.coletor.controller;

import static br.com.imperius.coletor.configuracao.Config.getProp;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import model.Leitura;

public class ValidadorAlerta {

    private Leitura leitura;

    public Leitura getLeitura() {
        return leitura;
    }

    public void setLeitura(Leitura leitura) {
        this.leitura = leitura;
    }

    public static void main(String args[]) throws IOException {
        GregorianCalendar gcalendar = new GregorianCalendar();
        int cont = 0;

        String timeStamp = new SimpleDateFormat("dd/MM").format(Calendar.getInstance().getTime());
        String mm = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
        int min = Integer.parseInt(mm);

        Properties props = getProp();
        String ultimaHora = props.getProperty("ultimaHora");
    }

    public static void validacao(Leitura uso) {
        int cpu = uso.getCpu();
        int hd = uso.getHd();
        int mram = uso.getMram();
    }

    public static void cpuPorcentagem(int cpu) {

        if (cpu > 74 && cpu <= 84) {
            //P3
        } else if (cpu >= 85 && cpu <= 94) {
            //P2
        } else if (cpu >= 95) {
            //P1
        } else {
            //qualquer coisa menor que 74 faz nada
        }
    }

    public static void hdPorcentagem(int hd) {
        if (hd > 79 && hd <= 80) {
            //P3
        } else if (hd >= 81 && hd <= 89) {
            //P2
        } else if (hd >= 90) {
            //P1
        } else {
            //qualquer coisa menor que 79 faz nada
        }
    }

    public static void mramPorcentagem(int mram) {
        if (mram > 69 && mram <= 79) {
            //P3
        } else if (mram >= 80 && mram <= 89) {
            //P2
        } else if (mram >= 90) {
            //P1
        } else {
            //qualquer coisa menor que 69 faz nada
        }
    }

    public static void p3() {

    }

    public static void p2() {

    }

    public static void p1() {

    }
}
