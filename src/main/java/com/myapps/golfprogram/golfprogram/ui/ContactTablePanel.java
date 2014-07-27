/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram.ui;

import com.myapps.golfprogram.golfprogram.dataaccess.Contact;
import com.myapps.golfprogram.golfprogram.dataaccess.ContactTelDetail;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * Will Represent a table of contacts
 * 
 */
public class ContactTablePanel extends JPanel {
    
    String[] columnNames = {"Home Phone",
                            "Work Phone",
                            "Mobile Phone"};
    
    Object[][] data = new Object[1][3];
    //DefaultTableModel model = new DefaultTableModel(data, columnNames);
    JTable table = new JTable(data, columnNames);
    
    public ContactTablePanel() {
        super();
        setLayoutManager();
        
        JScrollPane scrollPane = new JScrollPane(table);
        
        //table.setFillsViewportHeight(true);
        //scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);  
        //scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);  
        //table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        add(table.getTableHeader());
        add(scrollPane);
        
    }
    
    private void setLayoutManager(){
        // does nothing 
    }

    public void setContact(Contact theContact) {
        int numberDetails = theContact.getContactTelDetails().size();
        Object[][] newData = new Object[1][3];
        newData[0][0] = "";
        newData[0][1] = "";
        newData[0][2] = "";
        Set<ContactTelDetail>details = theContact.getContactTelDetails();
        Iterator <ContactTelDetail>detailIter = details.iterator();
        while( detailIter.hasNext()){
            ContactTelDetail detail = detailIter.next();
            if( detail.getTelType().equals("Home")){
                newData[0][0] = detail.getTelNumber();
                
            }
            if( detail.getTelType().equals("Work")){
                newData[0][1] = detail.getTelNumber();
                
            }
            if( detail.getTelType().equals("Mobile")){
                newData[0][2] = detail.getTelNumber();
                
            }
        }
          //DefaultTableModel model = new DefaultTableModel(newData, columnNames);
          //model.addRow(newData);
          
//            model.addRow(data[0]);
//            table.setModel(model);
//            model.fireTableDataChanged();
            for(int i = 0; i < 3; i++){
                table.getModel().setValueAt(newData[0][i], 0, i); 
            table.updateUI();
            
        }
        
        
        
    }
   
    
    
    
}
