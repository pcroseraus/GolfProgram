/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram.ui.contacts;

import com.myapps.golfprogram.golfprogram.GolfTool;
import com.myapps.golfprogram.golfprogram.dataaccess.Contact;
import com.myapps.golfprogram.golfprogram.dataaccess.ContactTelDetail;
import com.myapps.golfprogram.golfprogram.exceptions.DateFormatException;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.CONTACT_PANEL_BIRTH_DATE;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.CONTACT_PANEL_DETAIL_HOME;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.CONTACT_PANEL_DETAIL_MOBILE;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.CONTACT_PANEL_DETAIL_WORK;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.CONTACT_PANEL_FIRST_NAME;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.CONTACT_PANEL_HOME_PHONE;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.CONTACT_PANEL_LAST_NAME;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.CONTACT_PANEL_MOBILE_PHONE;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.CONTACT_PANEL_WORK_PHONE;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.DATE_FORMAT_EXCEPTION_MESSAGE;
import java.awt.GridLayout;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Brett
 */
public class ContactPanel extends JPanel {

    GolfTool tool;
    
    JLabel firstNameLabel = new JLabel(CONTACT_PANEL_FIRST_NAME);
    JTextField firstNameField = new JTextField(20);
    
    JLabel lastNameLabel = new JLabel(CONTACT_PANEL_LAST_NAME);
    JTextField lastNameField = new JTextField(20);
    
    JLabel birthDateLabel = new JLabel(CONTACT_PANEL_BIRTH_DATE);
    JTextField birthDateField = new JTextField(20);
    
    JLabel homePhoneLabel = new JLabel(CONTACT_PANEL_HOME_PHONE);
    JTextField homePhoneField = new JTextField(20);
    
    JLabel workPhoneLabel = new JLabel(CONTACT_PANEL_WORK_PHONE);
    JTextField workPhoneField = new JTextField(20);
    
    JLabel mobilePhoneLabel = new JLabel(CONTACT_PANEL_MOBILE_PHONE);
    JTextField mobilePhoneField = new JTextField(20);
    
    public ContactPanel(GolfTool tool) {
        this.tool = tool;
        setLayoutManager();
        initComponents();    
    }
    
    private void setLayoutManager() {
        GridLayout layout = new GridLayout(6, 2);
        this.setLayout(layout);
    }
    
    private void initComponents() {
        
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
    
    public Contact getContactData() {
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
        ContactTelDetail homeDetail = 
            new ContactTelDetail(CONTACT_PANEL_DETAIL_HOME, homePhoneField.getText());
        ContactTelDetail workDetail = 
            new ContactTelDetail(CONTACT_PANEL_DETAIL_WORK, workPhoneField.getText());
        ContactTelDetail mobileDetail = 
            new ContactTelDetail(CONTACT_PANEL_DETAIL_MOBILE, mobilePhoneField.getText());
        data.addContactTelDetail(homeDetail);
        data.addContactTelDetail(workDetail);
        data.addContactTelDetail(mobileDetail);
        return data;   
    }
    
    public void setContactData(Contact contact, String homePhone, 
                               String workPhone, String mobilePhone) throws DateFormatException {         
        firstNameField.setText(contact.getFirstName());
        lastNameField.setText(contact.getLastName());
        String birthDate = contact.getBirthDate().toString();
        String[] items = birthDate.split("-");        
        String formattedDate = verifyDate(items);
        birthDateField.setText(formattedDate);
        homePhoneField.setText(homePhone);
        workPhoneField.setText(workPhone);
        mobilePhoneField.setText(mobilePhone);
    }
    
    private String verifyDate(String[] items) throws DateFormatException {       
        String month = items[1];
        String day = items[2];
        String year = items[3];
        String formattedDate;
        
        /* This checks that the values for month, day and year were entered and
           provides some basic date verification checking. */
        if((month == null || (Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12)) && 
            (day == null || (Integer.parseInt(day) < 1) || Integer.parseInt(day) > 31) && 
            (year == null)) {
            throw new DateFormatException(DATE_FORMAT_EXCEPTION_MESSAGE);
        } else {
            formattedDate = month + "/" + day + "/" + year;
            System.out.println("The entered date is: \"" + formattedDate + "\"");
        }
                
        return formattedDate;
    }
    
    public String getHomePhone() {
        String homePhone = homePhoneField.getText();
        if( homePhone == null ) {
            return "";
        } else {
            return homePhone;
        }            
    }
    
    public String getWorkPhone() {
        String workPhone = workPhoneField.getText();
        if( workPhone == null ) {
            return "";
        } else {
            return workPhone;
        }            
    }
    
    public String getMobilePhone() {
        String mobilePhone = mobilePhoneField.getText();
        if( mobilePhone == null ) {
            return "";
        } else {
            return mobilePhone;
        }
    }
            
}
