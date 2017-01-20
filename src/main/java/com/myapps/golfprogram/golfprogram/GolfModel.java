/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram;

import com.myapps.golfprogram.golfprogram.dataaccess.Contact;
import com.myapps.golfprogram.golfprogram.dataaccess.ContactTelDetail;
import java.util.List;
import java.util.Set;

/**
 * Remove Author. 
 * @author roserp
 */
class GolfModel {
    private List<Contact> contacts;
    

    public GolfModel(List<Contact> contacts) {
        this.contacts = contacts;
    }
    
    public String[] getNames(){
        final String[] names = new String[contacts.size()];
        int i = 0;
        for( Contact contact : contacts ){
            names[i] = contact.getLastName() + ", "  + contact.getFirstName();
            i++;
        }
        
        return names;
    }
    
    public Set<ContactTelDetail> getHomePhone(String name){
        Set<ContactTelDetail> details = null;
        for( Contact contact : contacts ){
            String contactName = contact.getLastName() + ", " + contact.getFirstName();
            if( name.equals(contactName)){
                details = contact.getContactTelDetails();
                break;
            }  
        }
        return details;
    }
        
    
    public void getMobilePhone(String name){
        
    }
    
    public void getWorkPhone(String name){
        
    }
    
    public Contact getContact(String name){
        Contact theContact = null;
//        String[] names = name.split(",");
//        String lastName = names[0];
//        String firstName = names[1];
        for( Contact contact : contacts ){
            String contactName = contact.getLastName() + ", " + contact.getFirstName();
            if( name.equals(contactName)){
                theContact = contact;
                break;
            }  
        }
        return theContact;
    }

    public long getIdForName(String item) {
        String [] names = item.split(",");
        String lastName = names[0];
        String firstName = names[1];
        long id = 0;
        int i = 0;
        for( Contact contact : contacts ){
            if( contact.getLastName().equals(lastName.trim()) &&
                contact.getFirstName().equals(firstName.trim()) ){
                
                id = contact.getId();
            }
        
        } 
        System.out.println("ID found is " + id);
        return id;
    }
    
    public void addContact(Contact contact){
        contacts.add(contact);
    }

    void removeContact(Contact contact) {
        contacts.remove(contact);
    }
}
