/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram.ui;

import com.myapps.golfprogram.golfprogram.GolfTool;
import com.myapps.golfprogram.golfprogram.dataaccess.Contact;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author roserp
 */
public class AddContactButtonPanel extends JPanel implements ActionListener{
    GolfTool tool;
    private JButton saveButton = new JButton("Save");
    private JButton cancelButton = new JButton("Cancel");

    public AddContactButtonPanel(GolfTool tool) {
        super();
        this.tool = tool;
        addListeners();
        addComponents();
    }
    
    private void addListeners(){
        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }
    
    private void addComponents(){
        add(saveButton);
        add(cancelButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveButton){
           tool.notifySaveContact();
            
        }else if( e.getSource() == cancelButton){
            tool.notifyAddContactCanceled();
        }
        
    }
    
} 
