/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapps.golfprogram.golfprogram.properties;

import com.google.common.base.Preconditions;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Common application for retrieving properties. 
 *
 */
public class PropertiesReader {
    private final String propertyFileName;

    /**
     * Constructor of property reader accepts a property file name
     * @param propertyFileName 
     */
    public PropertiesReader(String propertyFileName) {
        Preconditions.checkNotNull(propertyFileName);
        this.propertyFileName = propertyFileName;
        
    }
    
    /**
     * The config file contains only one line in it at this time. I am sure as 
     * program develops , other properties will be useful. It is assumed when
     * a user buys the program and registers with the installer, this value can
     * be used to present friendly message like Hello FirstName + LastName. 
     * @return One String that matches the sole value in the config file. As the
     * properties grow, we must return an ArrayList of Name Value Pairs. 
     * @throws IOException 
     */
    public String getPropValues() throws IOException{
        final Properties prop = new Properties();
                
        final InputStream inputStream = getClass().getClassLoader().
                                          getResourceAsStream(propertyFileName);
        prop.load(inputStream);
        
        if(inputStream == null){
            throw new FileNotFoundException("property file " + propertyFileName
                + " not found in the classpath");
        }
        return prop.toString();
    }
}
