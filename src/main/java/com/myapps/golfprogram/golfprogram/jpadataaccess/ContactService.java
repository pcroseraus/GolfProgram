/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram.jpadataaccess;

import com.myapps.golfprogram.golfprogram.dataaccess.Contact;
import java.util.List;

/**
 *
 * @author roserp
 */
public interface ContactService {
    
    //Find all contacts
    public List<Contact> findAll();
    
    //Find all contacts
    public List<Contact> findAllWithDetail();
    
    //Find a contact with details by Id
    public Contact findById(Long id);
    
    //Insert or update a contact
    public Contact save(Contact contact);
    
    //Delete a contact
    public void delete(Contact contact);
    
}
