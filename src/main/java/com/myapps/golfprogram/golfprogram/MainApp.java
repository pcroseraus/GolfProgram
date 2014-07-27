/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram;

import com.myapps.golfprogram.golfprogram.dataaccess.Contact;
import com.myapps.golfprogram.golfprogram.dataaccess.ContactDao;
import com.myapps.golfprogram.golfprogram.ui.GolfFrame;
import java.util.List;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 *
 * @author roserp
 */
public class MainApp {
    
    public static void main(String[] args){
        System.out.println("Main Program running");
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context.xml");
        ctx.refresh();
        
        ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);
        List<Contact> contacts = contactDao.findAll();
        GolfTool golfTool = new GolfTool(contacts, contactDao);
        
        GolfView view = golfTool.getView();
        view.setSize(600, 500);
        view.setVisible(true);
        
    }
    
}
