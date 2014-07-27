/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram.dataaccess;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author roserp
 */
public class ContactData {

    String lastName;
    String firstName;
    String birthdate;
    String homePhone;
    String workPhone;
    String mobilePhone;

      

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Date convertStringDateToDate(String date){
        String[] items = date.split("/");
        int month = Integer.parseInt(items[0]);
        // Java is still starting at 0 .. 11.  Must subtract 1 so month 10 = 10
        month = month - 1;
        int day = Integer.parseInt(items[1]);
        int year = Integer.parseInt(items[2]);
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        
        return calendar.getTime();
        
    }
    @Override
    public String toString() {
        return "ContactData{" + "lastName=" + lastName + 
                ", firstName=" + firstName + ", birthdate=" + birthdate +
                ", homePhone=" + homePhone + ", workPhone=" + workPhone + 
                ", mobilePhone=" + mobilePhone + '}';
    }
    
    
}
