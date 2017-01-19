/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram.ui.contacts;

import com.myapps.golfprogram.golfprogram.dataaccess.Contact;
import com.myapps.golfprogram.golfprogram.dataaccess.ContactTelDetail;
import com.myapps.golfprogram.golfprogram.exceptions.DateFormatException;
import com.myapps.golfprogram.golfprogram.exceptions.DayOutOfRangeException;
import com.myapps.golfprogram.golfprogram.exceptions.MonthOutOfRangeException;
import com.myapps.golfprogram.golfprogram.exceptions.YearOutOfRangeException;
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
import com.myapps.golfprogram.golfprogram.utils.DateUtils;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Brett
 */
public class ContactPanel extends JPanel {
    
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
    
    public ContactPanel() {
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
        Contact data = null;
        
        try {
            DateUtils utils = new DateUtils();
            data = new Contact();
            
            String dateString = birthDateField.getText().trim();
            String[] dateMembers = dateString.split("/");
            
            int year = utils.validateYear(dateMembers[2]);
            int month = utils.validateMonth(dateMembers[0]);
            int day = utils.validateDay(month, year, dateMembers[1]);
            
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
        } catch (YearOutOfRangeException ex) {
            Logger.getLogger(ContactPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MonthOutOfRangeException ex) {
            Logger.getLogger(ContactPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DayOutOfRangeException ex) {
            Logger.getLogger(ContactPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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

    public String getFirstNameField() {
        return firstNameField.getText();
    }

    public void setFirstName(String firstName) {
        firstNameField.setText(firstName);
    }

    public String getLastName() {
        return lastNameField.getText();
    }

    public void setLastName(String lastName) {
        lastNameField.setText(lastName);
    }

    public String getBirthDate() {
        return birthDateField.getText();
    }

    public void setBirthDate(String birthDate) {
        birthDateField.setText(birthDate);
    }

//    public String getHomePhone() {
//        return homePhoneField.getText();
//    }

    public void setHomePhone(String homePhone) {
        homePhoneField.setText(homePhone);
    }

//    public String getWorkPhone() {
//        return workPhoneField.getText();
//    }

    public void setWorkPhone(String workPhone) {
        workPhoneField.setText(workPhone);
    }

//    public String getMobilePhone() {
//        return mobilePhoneField.getText();
//    }

    public void setMobilePhone(String mobilePhone) {
        mobilePhoneField.setText(mobilePhone);
    }
            
}
