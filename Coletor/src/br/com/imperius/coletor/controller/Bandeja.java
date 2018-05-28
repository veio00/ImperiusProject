/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imperius.coletor.controller;

import static br.com.imperius.coletor.view.CadastroMaquina.openURL;
import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class Bandeja {

    public static void startBandeja() {

        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        //Image image = Toolkit.getDefaultToolkit().getImage("hanSolo.gif");
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon = new TrayIcon(createImage("/hanSolo.gif", "tray icon"));
        final SystemTray tray = SystemTray.getSystemTray();

        // Create a pop-up menu components
        MenuItem Configuracao = new MenuItem("Configuração");
        MenuItem site = new MenuItem("Site");
        //CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
        //CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
        //Menu displayMenu = new Menu("Display");
        //MenuItem errorItem = new MenuItem("Error");
        //MenuItem warningItem = new MenuItem("Warning");
        //MenuItem infoItem = new MenuItem("Info");
        //MenuItem noneItem = new MenuItem("None");
        MenuItem exitItem = new MenuItem("Exit");

        //Add components to pop-up menu
        popup.add(Configuracao);
        popup.add(site);
//        popup.addSeparator();
//        popup.add(cb1);
//        popup.add(cb2);
//        popup.addSeparator();
//        popup.add(displayMenu);
//        displayMenu.add(errorItem);
//        displayMenu.add(warningItem);
//        displayMenu.add(infoItem);
//        displayMenu.add(noneItem);
        popup.add(exitItem);

        trayIcon.setPopupMenu(popup);

        Configuracao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Em construção");
            }
        });
        
        site.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openURL("http://imperius.azurewebsites.net/");
            }
        });
        
        exitItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }

        });
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
    }
    //Obtain the image URL

    protected static Image createImage(String path, String description) {
        URL imageURL = Bandeja.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
}
