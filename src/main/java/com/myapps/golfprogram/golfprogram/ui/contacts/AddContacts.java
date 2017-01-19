/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram.ui.contacts;

import com.myapps.golfprogram.golfprogram.GolfTool;
import com.myapps.golfprogram.golfprogram.dataaccess.Contact;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.CONTACT_ADD_PANEL_NAME;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author Brett
 */
public class AddContacts extends JFrame {
   
    private GolfTool tool;
    ContactPanel contactPanel;
    AddContactButtonPanel buttonPanel;
    
    public AddContacts(GolfTool tool) {        
        super(CONTACT_ADD_PANEL_NAME);        
        this.tool = tool;       
        initComponents();
        setLayout();
        addComponents();
    }
    
    private void initComponents() {
        contactPanel = new ContactPanel();
        buttonPanel = new AddContactButtonPanel(tool);        
    }
    
    private void setLayout() {
        setLayout(new BorderLayout());
    }
    
    private void addComponents() {
        add(contactPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void showFrame() {
        setSize(300, 300);
        setVisible(true);
    }
    
    public void closeFrame() {
        setVisible(false);
    }
    
    public Contact getContactData() {        
        return contactPanel.getContactData();
    }
    
}
      