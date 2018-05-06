/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import java.io.IOException;
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
    
    private static int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public static Maquina infoMaquina() throws SigarException, UnknownHostException{
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
        //zm.setResponsavel("");
        m.setSistema(sys.getVendor());
        m.setGrupo_Cliente(id);
        m.setResponsavel(".");
        return m; 
        
    }
    
    public static Processador InfoCpu(int id) throws SigarException{
        Sigar sigar = new Sigar();
        CpuPerc cpuperc = null;

        try {
            cpuperc = sigar.getCpuPerc();
        } catch (SigarException se) {
            se.printStackTrace();
        }
        Processador p = new Processador();
        CpuInfo[] infos = sigar.getCpuInfoList();
        CpuPerc[] cpus = sigar.getCpuPercList();
        CpuInfo info = infos[0];
        
        p.setModelo(info.getModel());
        p.setMaquina_Cpu(id);
        return p;
    }
    
    public static Memoria InfoMemo(int id) throws SigarException{
        Sigar sigar = new Sigar();
        Mem mem = null;

        try {
            mem = sigar.getMem();
        } catch (SigarException se) {
            se.printStackTrace();
        }
        Memoria me = new Memoria();
        me.setQtd((int) mem.getRam());
        me.setMaquina_Memoria(id);
        return me;
    
    }
    
    public static Disco InfoHd(int id) throws SigarException{
        Disco d = new Disco();
        
        Sigar sigar = new Sigar();
        FileSystemUsage disk = null;

        try {
            disk = sigar.getFileSystemUsage("C:");     
        } catch (SigarException se) {
            se.printStackTrace();
        }
        
        d.setEspaco((int) disk.getTotal()/1024);
        d.setMaquina_Disco(id);
        return d;
    
    }
    
    public static void Cadastro(int grupo) throws SigarException, IOException{
        Gson g = new Gson();
        id = grupo;
        Maquina m = InfoMaquina.infoMaquina();
        try{
            int codigo=Integer.parseInt(Envio.envioColeta(g.toJson(m),"http://imperius.azurewebsites.net/api/Coleta/InfoMaquina"));
            if(codigo > 0){
                boolean a=false,b=false,c=false;
                while(a == false){
                    Processador p = InfoMaquina.InfoCpu(codigo);
                    System.out.println(g.toJson(InfoMaquina.InfoCpu(codigo)));
                    a = Envio.envioColeta(g.toJson(p),"http://imperius.azurewebsites.net/api/Coleta/InfoProcessador", Boolean.class);
                }
                while(b == false){
                    Memoria me = InfoMaquina.InfoMemo(codigo);
                    System.out.println(g.toJson(InfoMaquina.InfoMemo(codigo)));
                    b = Envio.envioColeta(g.toJson(me),"http://imperius.azurewebsites.net/api/Coleta/InfoMemoria", Boolean.class);
                }
                while(c == false){
                    Disco hd = InfoMaquina.InfoHd(codigo);
                    System.out.println(g.toJson(InfoMaquina.InfoHd(codigo)));
                    c = Envio.envioColeta(g.toJson(hd),"http://imperius.azurewebsites.net/api/Coleta/InfoDisco", Boolean.class);
                }
            }
        }catch(Exception e){
            throw e;
        }
        
    }

}
