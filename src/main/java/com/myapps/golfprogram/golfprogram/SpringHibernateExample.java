/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram;

import com.myapps.golfprogram.golfprogram.dataaccess.Contact;
import com.myapps.golfprogram.golfprogram.dataaccess.ContactDao;
import com.myapps.golfprogram.golfprogram.dataaccess.ContactTelDetail;
import java.util.Date;
import java.util.List;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 *
 * @author roserp
 */
public class SpringHibernateExample {
    public static void main(String[] args ){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context.xml");
        ctx.refresh();
        
        ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);
        
        Contact contact = new Contact();
        contact.setFirstName("Michael");
        contact.setLastName("Jackson");
        contact.setBirthDate( new Date());
        ContactTelDetail contactTelDetail = new ContactTelDetail("Home", "111111111");
        contact.addContactTelDetail(contactTelDetail);
        contactTelDetail = new ContactTelDetail("Mobile", "222222222");
        contact.addContactTelDetail(contactTelDetail);
        contactDao.save(contact);
        
        
        
        List<Contact> contacts = contactDao.findAll();
        listContacts(contacts);
        listContactsWithDetail(contacts);
        
        contact = contactDao.findById(26l);
        contactDao.delete(contact);
        contacts = contactDao.findAllWithDetail();
        
        listContactsWithDetail(contacts);
    }
    
    private static void listContacts(List <Contact> contacts ){
        System.out.println("");
        System.out.println("Listing contacts without details");
        for(Contact contact: contacts ){
            System.out.println(contact);
            System.out.println();
        }
    }
    
    private static void listContactsWithDetail(List<Contact> contacts) {
        System.out.println("");
        System.out.println("Listing contacts with details:");
        for (Contact contact : contacts) {
            System.out.println(contact);
            if (contact.getContactTelDetails() != null) {
                for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
                    System.out.println(contactTelDetail);
                }
            }

        }
    }
        
    }
    