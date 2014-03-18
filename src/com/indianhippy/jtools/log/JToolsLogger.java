/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.indianhippy.jtools.log;

import java.io.IOException;
import java.util.Formatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author achava
 */

public class JToolsLogger {
  static private FileHandler fileTxt;
  static private SimpleFormatter formatterTxt;
static public void setup() throws IOException {
    // Get the global logger to configure it
    Logger logger = Logger.getLogger("JTools");
 
    FileHandler fh;  

    try {  

        // This block configure the logger with handler and formatter  
         String userHomeDir = System.getProperty("user.home", ".");
    String systemDir = userHomeDir + "\\.clipHistory";
        fh = new FileHandler(systemDir+"\\jtools.log");  
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);  

        // the following statement is used to log any messages  
     
    } catch (SecurityException e) {  
        e.printStackTrace();  
    } catch (IOException e) {  
        e.printStackTrace();  
    }  
    logger.setLevel(Level.INFO);
   
   
  }
}
 