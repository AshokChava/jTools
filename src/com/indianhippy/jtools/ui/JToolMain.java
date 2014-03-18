/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.indianhippy.jtools.ui;

import com.indianhippy.jtools.clip.helper.ConfigReader;
import com.indianhippy.jtools.jclip.ui.JClipPanel;
import com.indianhippy.jtools.log.JToolsLogger;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author achava
 */
public class JToolMain extends javax.swing.JFrame {

    /**
     * Creates new form JToolMain
     */
    private static Logger logger = Logger.getLogger("JTools");
    ConfigReader cReader=new ConfigReader();
    Properties config=cReader.loadProperties("properties/config.properties");
    JClipPanel jclip=new JClipPanel();
    public JToolMain() {
        initComponents();
        setLookAndFeel();
         this.setTitle(config.getProperty("versionString"));
      setAppIcon();
        createSystemTrayIcon();
        try {
            logger.setLevel(Level.INFO);
            JToolsLogger.setup();

        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
   
 public void createSystemTrayIcon() {

        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image image
                    = Toolkit.getDefaultToolkit()
                    .getImage(getClass().getClassLoader().getResource("images/icon.png"));

            PopupMenu popup = new PopupMenu();

            final MenuItem menuExit = new MenuItem("Quit");
            final MenuItem menuShow = new MenuItem("Show");
            final MenuItem menuAddClip = new MenuItem("Add Clip");

            MouseListener mouseListener
                    = new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                        }

                        public void mouseExited(MouseEvent e) {
                        }

                        public void mousePressed(MouseEvent e) {
                            if (e.getButton() == 1) {
                                setVisible(true);
                            }

                        }

                        public void mouseReleased(MouseEvent e) {
                        }
                    };

            ActionListener exitListener
                    = new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            Runtime r = Runtime.getRuntime();
                            logger.info("Exiting...");
                            r.exit(0);
                        }
                    };
            ActionListener showListener
                    = new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            setVisible(true);
                        }
                    };
            ActionListener addClipListener
                    = new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            jclip.addFromClipBoard();

                        }
                    };
            menuExit.addActionListener(exitListener);
            menuShow.addActionListener(showListener);
            menuAddClip.addActionListener(addClipListener);
            popup.add(menuExit);
            popup.add(menuShow);
            popup.add(menuAddClip);

            final TrayIcon trayIcon = new TrayIcon(image, "JDesktopLaunch", popup);

            ActionListener actionListener
                    = new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            trayIcon.displayMessage(config.getProperty("versionString"), "",
                                    TrayIcon.MessageType.INFO);
                        }
                    };

            trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(actionListener);
            trayIcon.addMouseListener(mouseListener);

            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("TrayIcon could not be added.");
            }

        } else {
            //  System Tray is not supported
        }
    }
    private void checkForUpdates() {
        BufferedReader in = null;
        try {
           
            URL version = new URL(config.getProperty("versionFile"));
            in = new BufferedReader(
                    new InputStreamReader(version.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (!inputLine.equalsIgnoreCase(this.getTitle())) {
                    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                        desktop.browse(new URL(config.getProperty("downloadLink")).toURI());
                    }
                }
            }
            in.close();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

    }

    private void setAppIcon() {
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/icon.png")));
            this.setIconImage(icon.getImage());
            this.setSize(100, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jClipPanel1 = new com.indianhippy.jtools.jclip.ui.JClipPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        checkForUpdateItem = new javax.swing.JMenuItem();
        aboutItem = new javax.swing.JMenuItem();

        getContentPane().setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(jClipPanel1);

        jMenu2.setText("About");

        checkForUpdateItem.setText("Check For Updates..");
        checkForUpdateItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkForUpdateItemActionPerformed(evt);
            }
        });
        jMenu2.add(checkForUpdateItem);

        aboutItem.setText("About");
        jMenu2.add(aboutItem);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkForUpdateItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkForUpdateItemActionPerformed
        checkForUpdates();
    }//GEN-LAST:event_checkForUpdateItemActionPerformed

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutItem;
    private javax.swing.JMenuItem checkForUpdateItem;
    private com.indianhippy.jtools.jclip.ui.JClipPanel jClipPanel1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu jPopupMenu1;
    // End of variables declaration//GEN-END:variables
}
