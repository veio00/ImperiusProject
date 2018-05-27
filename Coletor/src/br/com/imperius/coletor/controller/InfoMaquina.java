/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imperius.coletor.controller;

import br.com.imperius.coletor.configuracao.Config;
import static br.com.imperius.coletor.configuracao.Config.getProp;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import br.com.imperius.coletor.model.Disco;
import br.com.imperius.coletor.model.Maquina;
import br.com.imperius.coletor.model.Memoria;
import br.com.imperius.coletor.model.Processador;
import br.com.imperius.coletor.model.Leitura;
import br.com.imperius.coletor.model.Padrao;
import static br.com.imperius.coletor.view.View.start;
import java.util.Properties;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SysInfo;

/**
 *
 * @author Will
 */
public class InfoMaquina {

    public static Maquina infoMaquina() throws SigarException, UnknownHostException, IOException {
        Sigar sigar = new Sigar();
        Mem mem = null;
        CpuPerc cpuperc = null;
        FileSystemUsage disk = null;
        String osName = System.getProperty("os.name");

        try {
            mem = sigar.getMem();
            cpuperc = sigar.getCpuPerc();

            if (osName.startsWith("Windows")) {
                disk = sigar.getFileSystemUsage("C:");
            } else {
                disk = sigar.getFileSystemUsage("/");
            }

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

        //pega total de memoria em bytes e converte em megas(MB)
        memo.setQtd((int) mem.getTotal() / 1024 / 1024);
        //pega total de disco 
        hd.setEspaco((int) disk.getTotal() / 1024 / 1024);
        //hd.setMarca((int) disk.getAvail());
        m.setNome_Maquina(InetAddress.getLocalHost().getHostName());
        //zm.setResponsavel("");
        m.setSistema(sys.getVendor());
        m.setGrupo_Cliente(Padrao.getGrupo());
        m.setResponsavel(".");
        m.setKeep_Alive(1);
        
        return m;
    }

    public static Processador infoCpu(int cod) throws SigarException {
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
        p.setMaquina_Cpu(cod);
        
        return p;
    }

    public static Memoria infoMemo(int cod) throws SigarException {
        Sigar sigar = new Sigar();
        Mem mem = null;

        try {
            mem = sigar.getMem();
        } catch (SigarException se) {
            se.printStackTrace();
        }
        Memoria me = new Memoria();
        me.setQtd((int) mem.getRam());
        me.setMaquina_Memoria(cod);
        
        return me;
    }

    public static Disco infoHd(int cod) throws SigarException {
        Disco d = new Disco();
        String osName = System.getProperty("os.name");

        Sigar sigar = new Sigar();
        FileSystemUsage disk = null;

        try {
            if (osName.startsWith("Windows")) {
                disk = sigar.getFileSystemUsage("C:");
            } else {
                disk = sigar.getFileSystemUsage("/");
            }

        } catch (SigarException se) {
            se.printStackTrace();
        }

        d.setEspaco((int) disk.getTotal() / 1024);
        d.setMaquina_Disco(cod);
        
        return d;
    }

    public static void cadastro(int grupo) throws SigarException, IOException {
        Gson g = new Gson();
        Padrao.setGrupo((int)grupo);
        Maquina m = InfoMaquina.infoMaquina();
        String WebServer = Padrao.getWebServer();
        try {
            int codigo = Integer.parseInt(Envio.envioColeta(g.toJson(m), WebServer+"InfoMaquina"));
            Padrao.setId(codigo);
            
            if (codigo > 0) {
                //a,b e c são validadores
                boolean a = false, b = false, c = false;
                
                while (a == false) {
                    Processador p = InfoMaquina.infoCpu(codigo);
                    a = Envio.envioColeta(g.toJson(p), WebServer+"InfoProcessador", Boolean.class);
                }
                
                while (b == false) {
                    Memoria me = InfoMaquina.infoMemo(codigo);
                    b = Envio.envioColeta(g.toJson(me), WebServer+"InfoMemoria", Boolean.class);
                }
                
                while (c == false) {
                    Disco hd = InfoMaquina.infoHd(codigo);
                    c = Envio.envioColeta(g.toJson(hd), WebServer+"InfoDisco", Boolean.class);
                }
                
                start();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}