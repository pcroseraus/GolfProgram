/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author roserp
 */
public class TestJListUtility extends JFrame implements ActionListener //, ItemListener
{
    
    private JButton closeButton = new JButton("Close");
    String [] listEntries = {"alpha", "beta", "gamma", "delta", "epsilon", "zeta", "eta", "theta",}; 
    private JPanel buttonPanel = new JPanel();
    
    private JPanel listPanel = new JPanel();
    
    private JComboBox memberCombo = new JComboBox(listEntries);

    public TestJListUtility() {
        super("TestJList Program");
    }
    
    
    public static void main(String[] args){
        TestJListUtility utility = new TestJListUtility();
        utility.setLayout();
        utility.initButtonPanel();
        utility.initJListPanel();
        utility.addComponents();
        utility.showPanel();
    }
    
    public void setLayout(){
        this.setLayout(new BorderLayout());
    }

    private void initButtonPanel() {
        closeButton.addActionListener(this);
        buttonPanel.add(closeButton);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == closeButton){
            System.exit(0);
        } else if(e.getSource() == memberCombo){   
            
            String item = (String) memberCombo.getSelectedItem();
            System.out.println("Item selected is " + item );
            
            
        }
    }
        
    private void addComponents() {
        this.getContentPane().add(listPanel, BorderLayout.CENTER);
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    
    
}

    private void showPanel() {
        this.setSize(400,400);
        this.setVisible(true);
    }

    private void initJListPanel() {
        memberCombo.addActionListener(this);
        JScrollPane scroller = new JScrollPane(memberCombo);  
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);  
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);  
        listPanel.add(memberCombo);
        
    }

    
//    @Override
//    public void itemStateChanged(ItemEvent e) {
//        if( e.get)
//        if (e.getStateChange() == ItemEvent.SELECTED)
//        {
//            Object selectedItem = e.getItem();
//            String itemSelected = (String)selectedItem;
//            System.out.println("Value is " + itemSelected);
//            // Do something with the selected item...
//        }
//    }
}
