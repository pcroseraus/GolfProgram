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
 * @author roserp
 */
public class PropertiesReader {
    private String propertyFileName;

    /**
     * Constructor of property reader accepts a property file name
     * @param propertyFileName 
     */
    public PropertiesReader(String propertyFileName) {
        Preconditions.checkNotNull(propertyFileName);
        this.propertyFileName = propertyFileName;
        
    }
    
    public String getPropValues() throws IOException{
        String result = "";
        Properties prop = new Properties();
                
        InputStream inputStream = getClass().getClassLoader().
                                            getResourceAsStream(propertyFileName);
        prop.load(inputStream);
        
        if(inputStream == null){
            throw new FileNotFoundException("property file " + propertyFileName +
                " not found in the classpath");
        }
        return prop.toString();
    }
}
