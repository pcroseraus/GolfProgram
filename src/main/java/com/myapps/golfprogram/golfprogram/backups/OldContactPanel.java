/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram.backups;


import com.myapps.golfprogram.golfprogram.GolfTool;
import com.myapps.golfprogram.golfprogram.dataaccess.Contact;
import com.myapps.golfprogram.golfprogram.dataaccess.ContactTelDetail;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author roserp
 */
public class OldContactPanel extends JPanel{

    GolfTool tool;
    
    JLabel firstNameLabel = new JLabel("First Name");
    JTextField firstNameField = new JTextField(20);
    
    JLabel lastNameLabel = new JLabel("Last Name");
    JTextField lastNameField = new JTextField(20);
    
    JLabel birthDateLabel = new JLabel("Birth Date MM/DD/YYYY");
    JTextField birthDateField = new JTextField(20);
    
    JLabel homePhoneLabel = new JLabel("Home Phone");
    JTextField homePhoneField = new JTextField(20);
    
    JLabel workPhoneLabel = new JLabel("Work Phone");
    JTextField workPhoneField = new JTextField(20);
    
    JLabel mobilePhoneLabel = new JLabel("Mobile Phone");
    JTextField mobilePhoneField = new JTextField(20);
    
    
    
    
    public OldContactPanel(GolfTool tool) {
        this.tool = tool;
        setLayoutManager();
        initComponents();
        
    }
    
    private void setLayoutManager(){
        GridLayout layout = new GridLayout(6, 2);
        this.setLayout(layout);
    }
    
    private void initComponents(){
        
    add(firstNameLabel);
    add(firstNameField);
    
    add(lastNameLabel);
    add(lastNameField);
    
    add(birthDateLabel);
    add(birthDateField);
    
    add(homePhoneLabel);
    add(homePhoneField);
    
    add(workPhoneLabel);
    add(workPhoneField);
    
    add(mobilePhoneLabel);
    add(mobilePhoneField);
    }
    
    public Contact getContactData(){
        Contact data = new Contact();
        String[] dateMembers = (birthDateField.getText()).split("/");
        int month = Integer.parseInt(dateMembers[0]);
        int day = Integer.parseInt(dateMembers[1]);
        int year = Integer.parseInt(dateMembers[2]);
        
        Calendar calender = Calendar.getInstance();
        calender.set(year, month, day - 1);
        calender.getTime();
        
        data.setBirthDate(calender.getTime());
        data.setFirstName(firstNameField.getText());
        data.setLastName(lastNameField.getText());
        ContactTelDetail homeDetail = new ContactTelDetail("Home", homePhoneField.getText());
        ContactTelDetail workDetail = new ContactTelDetail("Work", workPhoneField.getText());
        ContactTelDetail mobileDetail = new ContactTelDetail("Mobile", mobilePhoneField.getText());
        data.addContactTelDetail(homeDetail);
        data.addContactTelDetail(workDetail);
        data.addContactTelDetail(mobileDetail);
        return data;   
    }
    
    public void setContactData(Contact contact, String homePhone, String workPhone, String mobilePhone){
         
     firstNameField.setText(contact.getFirstName());
     lastNameField.setText(contact.getLastName());
     String birthDate = contact.getBirthDate().toString();
     String[] items = birthDate.split("-");
     String formattedDate = items[1] + "/" + items[2] + "/" + items[0];
     birthDateField.setText(formattedDate);
     homePhoneField.setText(homePhone);
     workPhoneField.setText(workPhone);
     mobilePhoneField.setText(mobilePhone);
    }
    
    public String getHomePhone(){
        String homePhone = homePhoneField.getText();
        if( homePhone == null ){
            return "";
        }
        else{
            return homePhone;
        }
            
    }
    public String getWorkPhone(){
        String workPhone = workPhoneField.getText();
        if( workPhone == null ){
            return "";
        }
        else{
            return workPhone;
        }
            
    }
    
    public String getMobilePhone(){
        String mobilePhone = mobilePhoneField.getText();
        if( mobilePhone == null ){
            return "";
        }
        else{
            return mobilePhone;
        }
            
    }
}
