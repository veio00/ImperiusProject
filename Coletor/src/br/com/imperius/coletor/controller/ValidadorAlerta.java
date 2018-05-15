package br.com.imperius.coletor.controller;

import static br.com.imperius.coletor.configuracao.Config.getProp;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

public class ValidadorAlerta {

    public static void main(String args[]) throws IOException {
        GregorianCalendar gcalendar = new GregorianCalendar();
        int cont = 0;

        String timeStamp = new SimpleDateFormat("dd/MM").format(Calendar.getInstance().getTime());
        String mm = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
        int min = Integer.parseInt(mm);

        Properties props = getProp();
        String ultimaHora = props.getProperty("ultimaHora");
    }
    public static void p3() {
        
    }
}