/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram.ui;

import com.myapps.golfprogram.golfprogram.dataaccess.Contact;
import com.myapps.golfprogram.golfprogram.dataaccess.ContactDao;
import com.myapps.golfprogram.golfprogram.dataaccess.ContactData;
import com.myapps.golfprogram.golfprogram.dataaccess.ContactTelDetail;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.util.Date;
import javax.swing.JFrame;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 *
 * @author roserp
 */
public class GolfFrame extends JFrame{
    
    public static String TITLE = "Golf Contacts";
    
    private MainButtonPanel buttonPanel;
    //private ContactPanel contactPanel;

    public GolfFrame() throws HeadlessException {
        super(TITLE);
        setLayoutManager();
        initButtonPanel();
        //initContactPanel();
        addComponents();
    }
    
    private void setLayoutManager(){
        BorderLayout layout = new BorderLayout();
        getContentPane().setLayout(layout);
    }

    public void notifyCloseSelected() {
        System.exit(0);
    }
    
    public void notifySaveSelected() {
        System.out.println("Save Button Pressed");
        //saveContactInfo();
    }
    private void initButtonPanel(){
        //buttonPanel = new MainButtonPanel(this);
    }
    
//    private void initContactPanel(){
//        contactPanel = new ContactPanel();
//    }
    
    private void addComponents(){
        //this.getContentPane().add(contactPanel, BorderLayout.NORTH);
        this.getContentPane().add(new ContactTablePanel(), BorderLayout.CENTER);
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

//    public void saveContactInfo(){
//        
//         GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//         ctx.load("classpath:app-context.xml");
//         ctx.refresh();
//        
//         ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);
//         ContactData data = contactPanel.getContactData();
//         System.out.println(data.toString());
//         Date convertedDate= data.convertStringDateToDate(data.getBirthdate());
//         System.out.println("Date is now " + convertedDate.toString());
//        
//         Contact contact = new Contact();
//         contact.setFirstName(data.getFirstName());
//         contact.setLastName(data.getLastName());
//         contact.setBirthDate(convertedDate);
//         ContactTelDetail contactTelDetail = new ContactTelDetail("Home", data.getHomePhone());
//         contact.addContactTelDetail(contactTelDetail);
//         contactTelDetail = new ContactTelDetail("Mobile", data.getMobilePhone());
//         contact.addContactTelDetail(contactTelDetail);
//         contactTelDetail = new ContactTelDetail("Work", data.getWorkPhone());
//         contact.addContactTelDetail(contactTelDetail);
//         contactDao.save(contact);
//    }
    
    
    
    
    
    
}
