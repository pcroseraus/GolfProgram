/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram.scores;

import com.myapps.golfprogram.golfprogram.dataaccess.Contact;
import com.myapps.golfprogram.golfprogram.framework.Model;
import java.util.List;

/**
 *
 * The model for the ScoreTool
 */
public class ScoresModel implements Model {
    private List<Contact> contacts;

    ScoresModel(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }
    
    public String[] getNames(){
        String[] names = new String[contacts.size()];
        int i = 0;
        for( Contact contact : contacts ){
            names[i] = contact.getLastName() + ", "  + contact.getFirstName();
            i++;
        }
        
        return names;
    }
    
    public Contact getContact(String fullName) {
        Contact theContact = null;
        for( Contact contact : contacts ){
              String contactName = contact.getLastName() + ", " + contact.getFirstName();
              if( fullName.equals(contactName)){
                theContact = contact;
                break;
            }  
        }
        return theContact;
    }
}
