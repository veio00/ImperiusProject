/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ConfiguracaoMaquina.Config;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import model.Disco;
import model.Maquina;
import model.Memoria;
import model.Processador;
import model.Leitura;
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

    private static int id;
    private static int Grupo;
    
    public static int getGrupo() {
        return Grupo;
    }

    public static void setGrupo(int aGrupo) throws IOException {
        Grupo = aGrupo;
        ConfiguracaoMaquina.Config.setProp("idGrupo",aGrupo+"","Codigo da Empresa");
    }

    public int getId() {
        return id;
    }

    public static void setId(int Id) throws IOException {
        id = Id;
        ConfiguracaoMaquina.Config.setProp("idMaquina",Id+"","Codigo da maquina");
    }

    public static Maquina infoMaquina() throws SigarException, UnknownHostException {
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
        //pega total de nucleos do processador
        //System.out.println("Total CPUs....." + info.getTotalCores());
        //pega total de memoria em bytes e converte em megas(MB)
        memo.setQtd((int) mem.getTotal() / 1024 / 1024);
        //pega total de disco 
        hd.setEspaco((int) disk.getTotal() / 1024 / 1024);
        //hd.setMarca((int) disk.getAvail());
        m.setNome_Maquina(InetAddress.getLocalHost().getHostName());
        //zm.setResponsavel("");
        m.setSistema(sys.getVendor());
        m.setGrupo_Cliente(Grupo);
        m.setResponsavel(".");
        return m;

    }

    public static Processador InfoCpu(int cod) throws SigarException {
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

    public static Memoria InfoMemo(int cod) throws SigarException {
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

    public static Disco InfoHd(int cod) throws SigarException {
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

    public static void Cadastro(int grupo) throws SigarException, IOException {
        Gson g = new Gson();
        setGrupo((int)grupo);
        Maquina m = InfoMaquina.infoMaquina();
        try {
            int codigo = Integer.parseInt(Envio.envioColeta(g.toJson(m), "http://imperius.azurewebsites.net/api/Coleta/InfoMaquina"));
            setId(codigo);
            if (codigo > 0) {
                //a,b e c são validadores
                boolean a = false, b = false, c = false;
                while (a == false) {
                    Processador p = InfoMaquina.InfoCpu(codigo);
                    System.out.println(g.toJson(InfoMaquina.InfoCpu(codigo)));
                    a = Envio.envioColeta(g.toJson(p), "http://imperius.azurewebsites.net/api/Coleta/InfoProcessador", Boolean.class);
                }
                while (b == false) {
                    Memoria me = InfoMaquina.InfoMemo(codigo);
                    System.out.println(g.toJson(InfoMaquina.InfoMemo(codigo)));
                    b = Envio.envioColeta(g.toJson(me), "http://imperius.azurewebsites.net/api/Coleta/InfoMemoria", Boolean.class);
                }
                while (c == false) {
                    Disco hd = InfoMaquina.InfoHd(codigo);
                    System.out.println(g.toJson(InfoMaquina.InfoHd(codigo)));
                    c = Envio.envioColeta(g.toJson(hd), "http://imperius.azurewebsites.net/api/Coleta/InfoDisco", Boolean.class);
                }
            }
        } catch (Exception e) {
            throw e;
        }

    }

}
