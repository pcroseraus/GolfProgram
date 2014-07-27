/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.myapps.golfprogram.golfprogram.GolfView;
/**
 *
 * @author roserp
 */
public class MainButtonPanel extends JPanel implements ActionListener{
    
    private JButton closeButton = new JButton("Close");
    private JButton addButton = new JButton("Add Contact");
    private JButton deleteButton = new JButton("Delete Contact");
    private JButton editButton = new JButton("Edit Contact");
    private GolfView frame;

    public MainButtonPanel(GolfView frame) {
        this.frame = frame;
        addListeners();
        initComponents();
        
    }
    
    private void addListeners(){
        addButton.addActionListener(this);
        closeButton.addActionListener(this);
        deleteButton.addActionListener(this);
        editButton.addActionListener(this);
    }
    
    private void initComponents(){
        add(editButton);
        add(deleteButton);
        add(addButton);
        add(closeButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == closeButton)
        frame.notifyCloseSelected();
        else if (e.getSource() == addButton)
        frame.notifyAddSelected();
        else if(e.getSource() == deleteButton){
        frame.notifyDeleteSelected();
        }else if(e.getSource() == editButton){
        frame.notifyEditSelected();
        }
        
    }
    
    
}
