/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imperius.coletor.controller;

import br.com.imperius.coletor.configuracao.Config;
import static br.com.imperius.coletor.configuracao.Config.getProp;
import br.com.imperius.coletor.model.Leitura;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hyperic.sigar.*;

/**
 *
 * @author Will
 */
public class LeituraMaquina {

    // metodo que retorna uma classe model de Leitura para mandar a api
    public static Leitura ColetaUso() {
        
        Leitura uso = new Leitura();
        Sigar sigar = new Sigar();
        Mem mem = null;
        CpuPerc cpuperc = null;
        FileSystemUsage disk = null;
        
        try {
            mem = sigar.getMem();
            cpuperc = sigar.getCpuPerc();

            String osName = System.getProperty("os.name");

            if (osName.startsWith("Windows")) {
                disk = sigar.getFileSystemUsage("C:");
            } else {
                disk = sigar.getFileSystemUsage("/");
            }

        } catch (SigarException se) {
            se.printStackTrace();
        }

        uso.getIdUso();
        uso.setCpu((int) (cpuperc.getCombined() * 100));
        uso.setMram((int) mem.getUsedPercent());
        uso.setHd((int) (disk.getUsePercent() * 100));
        uso.setData(new SimpleDateFormat("dd/M/yyyy hh:mm").format(Calendar.getInstance().getTime()));
        try {

            Properties props = getProp();
            int codigo = Integer.parseInt(props.getProperty("idMaquina"));
            uso.setMaquina_Uso(codigo);

        } catch (IOException ex) {
            Logger.getLogger(LeituraMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }

        return uso;
    }
}
