/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Leitura;
import org.hyperic.sigar.*;

/**
 *
 * @author Will
 */
public class LeituraMaquina {
    // metodo que retorna uma classe model de Leitura para mandar a api
    public static Leitura ColetaUso(){
        Sigar sigar = new Sigar();
        Mem mem = null;
        CpuPerc cpuperc = null;
        FileSystemUsage disk = null;
        try {
            mem = sigar.getMem();
            cpuperc = sigar.getCpuPerc();
            disk = sigar.getFileSystemUsage("C:");        
        } catch (SigarException se) {
            se.printStackTrace();
        }
        Leitura uso = new Leitura();
        uso.setIdUso(1);
        uso.setCpu((int) (cpuperc.getCombined()*100));
        uso.setMram((int) mem.getUsedPercent());
        uso.setHd((int) (disk.getUsePercent()*100));
        uso.setMaquina_Uso(1);
        return uso;
        
    }

}
