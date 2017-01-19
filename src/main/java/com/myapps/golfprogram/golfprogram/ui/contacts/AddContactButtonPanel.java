/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram.ui.contacts;

import com.myapps.golfprogram.golfprogram.GolfTool;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.ADD_CONTACT_BUTTONPANEL_CANCEL_BUTTON;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.ADD_CONTACT_BUTTONPANEL_SAVE_BUTTON;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Brett
 */
public class AddContactButtonPanel extends JPanel implements ActionListener {

    private GolfTool tool;
    // A user can save a new contact or cancel the operation. 
    private JButton saveButton = new JButton(ADD_CONTACT_BUTTONPANEL_SAVE_BUTTON);
    private JButton cancelButton = new JButton(ADD_CONTACT_BUTTONPANEL_CANCEL_BUTTON);

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

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveButton){
            tool.notifySaveContact();            
        }else if( e.getSource() == cancelButton){
            tool.notifyAddContactCanceled();
        }        
    }
               
}
