/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imperius.coletor.view;

import br.com.imperius.coletor.controller.Bandeja;
import static br.com.imperius.coletor.controller.Bandeja.startBandeja;
import com.google.gson.Gson;
import br.com.imperius.coletor.controller.Envio;
import br.com.imperius.coletor.controller.InfoMaquina;
import br.com.imperius.coletor.model.Padrao;
import java.awt.Color;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.util.logging.*;
import org.hyperic.sigar.*;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Will
 */
public class CadastroMaquina extends javax.swing.JFrame {

    /**
     * Creates new form CadastroMaquina
     */
    public CadastroMaquina() {
        Color minhaCor = new Color(0, 0, 0);
        getContentPane().setBackground(minhaCor);
        setLocationRelativeTo(null);
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSair = new javax.swing.JButton();
        btnEntrar = new javax.swing.JButton();
        txtEmail = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblErro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro Maquina"); // NOI18N
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(java.awt.Color.white);
        setIconImage(createImage()
        );
        setName("fmCadastro"); // NOI18N
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        btnSair.setBackground(new java.awt.Color(153, 153, 153));
        btnSair.setForeground(new java.awt.Color(255, 255, 255));
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnEntrar.setBackground(new java.awt.Color(153, 153, 153));
        btnEntrar.setForeground(new java.awt.Color(255, 255, 255));
        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bem vindo ao Imperius Monitor.");
        jLabel1.setToolTipText("");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText(" Por favor entrar com E-mail cadastrado no site.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(btnEntrar)
                                .addGap(68, 68, 68)
                                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(lblErro)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(13, 13, 13)
                .addComponent(lblErro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSair)
                    .addComponent(btnEntrar))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        String email = txtEmail.getText();
        Gson g = new Gson();
        String WebServer = "";

        try {
            WebServer = Padrao.getWebServer();
        } catch (IOException ex) {
            Logger.getLogger(CadastroMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }

        if ("".equals(email)) {
            lblErro.setText("Preencha o email");
            lblErro.setForeground(Color.red);
        } else {
            try {
                int cod = Integer.parseInt(Envio.envioColeta(g.toJson(""), WebServer + "PesquisaCadastro?email=" + email + ""));
                if (cod > 0) {
                    InfoMaquina.cadastro(cod);
                } else {
                    lblErro.setText("E-mail não cadastrado ou errado");
                    lblErro.setForeground(Color.red);
                    openURL("http://imperius.azurewebsites.net/");
                }
            } catch (IOException | SigarException ex) {
                lblErro.setText("E-mail não cadastrado ou errado");
                lblErro.setForeground(Color.red);
                openURL("http://imperius.azurewebsites.net/");
            }
        }

        SystemTray tray = SystemTray.getSystemTray();

        for (TrayIcon icon : tray.getTrayIcons()) {
            tray.remove(icon);

        }

        startBandeja();
        this.dispose();
    }//GEN-LAST:event_btnEntrarActionPerformed

    protected static Image createImage() {
        URL imageURL = Bandeja.class.getResource("/logo.png");

        if (imageURL == null) {
            System.err.println("Resource not found: " + "/logo.png");
            return null;
        } else {
            return (new ImageIcon(imageURL, "tray icon")).getImage();
        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroMaquina().setVisible(true);
            }
        });
    }

    private static final String errMsg = "Erro ao tentar abrir o browser";

    public static void openURL(String url) {
        String osName = System.getProperty("os.name");
        try {
            if (osName.startsWith("Windows")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else { //assume Unix or Linux   
                String[] browsers = {
                    "firefox", "mozilla"};
                String browser = null;
                for (int count = 0; count < browsers.length && browser == null; count++) {
                    if (Runtime.getRuntime().exec(
                            new String[]{"which", browsers[count]}).waitFor() == 0) {
                        browser = browsers[count];
                    }
                }
                if (browser == null) {
                    throw new Exception("Navegador não encontrado!");
                } else {
                    Runtime.getRuntime().exec(new String[]{browser, url});
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, errMsg + ":\n" + e.getLocalizedMessage());
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblErro;
    private javax.swing.JTextField txtEmail;
    // End of variables declaration//GEN-END:variables
}
