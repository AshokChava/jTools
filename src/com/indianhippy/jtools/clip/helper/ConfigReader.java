/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.indianhippy.jtools.clip.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author achava
 */
public class ConfigReader {
    public Properties loadProperties(String fileName){
        Properties prop = new Properties();
	try {
		// load a properties file
		prop.load(getClass().getClassLoader().getResourceAsStream(fileName));
		// get the property value and print it out
                return(prop);
	} catch (IOException ex) {
		ex.printStackTrace();
	} 
        return null;
    }
}
