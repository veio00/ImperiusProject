/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import model.Disco;
import model.Maquina;
import model.Memoria;
import model.Processador;
import model.Leitura;
import org.hyperic.sigar.*;


/**
 *
 * @author Will
 */
public class InfoMaquina {
    public static Maquina getDados() throws SigarException, UnknownHostException{
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
        Maquina m = new Maquina();
        Processador cpu = new Processador();
        Memoria memo = new Memoria();
        Disco hd = new Disco();
        SysInfo sys = new SysInfo();
        sys.gather(sigar);   
        CpuInfo[] infos = sigar.getCpuInfoList();
        CpuPerc[] cpus = sigar.getCpuPercList();
        CpuInfo info = infos[0];
        //Pega informação do modelo do processador
        cpu.setModelo(info.getModel());
        //pega total de nucleos do processador
        //System.out.println("Total CPUs....." + info.getTotalCores());
        //pega total de memoria em bytes e converte em megas(MB)
        memo.setQtd((int) mem.getTotal()/1024/1024);
        //pega total de disco 
        hd.setEspaco((int) disk.getTotal()/1024/1024);
        //hd.setMarca((int) disk.getAvail());
        m.setNome_Maquina(InetAddress.getLocalHost().getHostName());
        m.setAdiquirida("23/12/1992");
        //zm.setResponsavel("");
        m.setSistema(sys.getVendor());
        return m; 
        
    }
}
