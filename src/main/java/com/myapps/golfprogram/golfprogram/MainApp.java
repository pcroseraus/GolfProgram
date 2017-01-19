/*
 * I created this App to demonstrate an proficiency in Spring. 
 */

package com.myapps.golfprogram.golfprogram;

import com.myapps.golfprogram.golfprogram.dataaccess.Contact;
import com.myapps.golfprogram.golfprogram.dataaccess.ContactDao;
import com.myapps.golfprogram.golfprogram.properties.PropertiesReader;
import java.io.IOException;
import java.util.List;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * This file gets everything going.  The Spring Context reads from a file in 
 * the classpath. app-context.xml defines the datasource which is postgress in 
 * this case, and the packages to scan that become candidates for autowiring and 
 * injection. Classpath is in src/main/resources for the purposes of this 
 * application. It is usually safe to place any files you want to include in
 * your application here. When maven creates the Jar file, it will  include this
 * file.
 * The ContactDao is a Data Access Object that will mine the contacts from the 
 * database which is postgresql at this time. Contacts have contact information
 * in the form of mobile, home, and work phones.
 * 
 * Contacts also have Scores.  Each score is associated with a Golf Course, a
 * date, and a Score.  TODO: This is assuming how many holes??
 * Finally it instantiates the GolfTool which creates its view. The manipulation
 * of the views size and visibility is here.  
 * 
 * Notice the public static void main method. It takes no arguments, and is 
 * considered the main method.  
 */
public class MainApp {
    
    public static void main(String[] args) throws IOException{
        PropertiesReader propReader = new PropertiesReader("config.properties");
        System.out.println("Program initiated by " + propReader.getPropValues());
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context.xml");
        ctx.refresh();
        
        /** 
         * The Main App should not need to know about getting information from 
         * the database.  This work should be moved to the Golf Tool.  TODO
         */
        ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);
        List<Contact> contacts = contactDao.findAll();
        GolfTool golfTool = new GolfTool(contacts, contactDao);
        
        /** 
         * The Main App should not need to know about setting views, the Tool
         * or Controller pattern should dictate who sets the view for a specific
         * program components. 
         */
        GolfView view = golfTool.getView();        
    }
    
}
