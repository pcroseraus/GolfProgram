/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram;

import com.myapps.golfprogram.golfprogram.dataaccess.Contact;
import com.myapps.golfprogram.golfprogram.dataaccess.ContactDao;
import com.myapps.golfprogram.golfprogram.dataaccess.ContactTelDetail;
import com.myapps.golfprogram.golfprogram.ui.AddContactButtonPanel;
import com.myapps.golfprogram.golfprogram.ui.ContactPanel;
import java.awt.BorderLayout;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;

/**
 * The Tool is the controller of the Model view Controller pattern. 
 */
public class GolfTool {
    
    private GolfModel golfModel;
    private GolfView golfView;
    private ContactDao contactDao;
    private JFrame contactFrame = new JFrame("Add Contact Panel");
    private ContactPanel panel = new ContactPanel(this);
    
    
    public GolfTool(List<Contact> contacts, ContactDao contactDao) {
        golfModel = new GolfModel(contacts);
        golfView = new GolfView(this,golfModel);
        this.contactDao = contactDao;
        String[] names = golfModel.getNames();
        golfView.setJComboBox(names);
    }

    
    public GolfModel getGolfModel() {
        return golfModel;
    }

    public GolfView getView() {
        return golfView;
    }
    
    public void notifyAddSelected(){
        if(contactFrame == null){
            contactFrame = new JFrame("Add Contact Panel");
        }
        //panel = new ContactPanel(this);
        AddContactButtonPanel buttonPanel = new AddContactButtonPanel(this);
        contactFrame.setLayout(new BorderLayout());
        contactFrame.add(panel, BorderLayout.CENTER);
        contactFrame.add(buttonPanel, BorderLayout.SOUTH);
        contactFrame.add(panel);
        contactFrame.setSize(400,250);
        contactFrame.setVisible(true);
        
    }

    public void notifySaveContact() {
        System.out.println("WTF");
        Contact data = panel.getContactData();
        Long id = golfModel.getIdForName(data.getLastName() + "," + data.getFirstName());
        if( id == 0 ){
            //this is a save because the id could not be found
            contactDao.save(data);
            
        }else{
            //this is an edit because the id was found nad must be > 0
            Contact contact = contactDao.findById(id);
            contact.setFirstName(data.getFirstName());
            contact.setLastName(data.getLastName());
            contact.setBirthDate(data.getBirthDate());
            
            
            Set<ContactTelDetail> details = contact.getContactTelDetails();
            ContactTelDetail homeDeleteDetail = null;
            ContactTelDetail workDeleteDetail = null;
            ContactTelDetail mobileDeleteDetail = null;
            
            // Remove Home, Work, and Mobile Phone Records.
            for(ContactTelDetail contactTel: details){
                if(contactTel.getTelType().equals("Home")){
                    homeDeleteDetail = contactTel;
                }
            }
            if(homeDeleteDetail != null){
                System.out.println("Setting Home phone to " + panel.getHomePhone());
                homeDeleteDetail.setTelNumber(panel.getHomePhone());
            }
            
            for(ContactTelDetail contactTel: details){
                if(contactTel.getTelType().equals("Mobile")){
                    mobileDeleteDetail = contactTel;
                }
            }
            
            if(mobileDeleteDetail != null){
                System.out.println("Setting Mobile phone to " + panel.getMobilePhone());
                mobileDeleteDetail.setTelNumber(panel.getMobilePhone());
            }
            for(ContactTelDetail contactTel: details){
                if(contactTel.getTelType().equals("Work")){
                    workDeleteDetail = contactTel;
                }
            }
            
            if(workDeleteDetail != null){
                System.out.println("Setting Mobile phone to " + panel.getWorkPhone());
                workDeleteDetail.setTelNumber(panel.getWorkPhone());
            }
            
            
            
            contactDao.save(contact);
            
            //this is an edit
            
        }
        
        //
        contactFrame.setVisible(false);
        contactFrame = null;
    }

    public void notifyAddContactCanceled() {
        contactFrame.setVisible(false);
        contactFrame = null;
    }

    public void notifyDeleteContact(String item) {
        long id = golfModel.getIdForName(item);
        Contact contact = contactDao.findById(id);
        contactDao.delete(contact);
    }
    
    public void notifyEditContact(String item) {
        
        // Need to open a contact panel set the data for it. 
        Contact data = panel.getContactData();
        contactDao.save(data);
        contactFrame.setVisible(false);
        contactFrame = null;
        
    }
    
    public ContactPanel getContactPanel(){
        return this.panel;
    }
    
    public JFrame getFrame(){
        return contactFrame;
    }
    
}
