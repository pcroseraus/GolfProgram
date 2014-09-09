/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram.scores;

import com.myapps.golfprogram.golfprogram.dataaccess.Contact;
import com.myapps.golfprogram.golfprogram.dataaccess.ContactDao;
import com.myapps.golfprogram.golfprogram.dataaccess.Score;
import com.myapps.golfprogram.golfprogram.framework.Tool;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * 
 */
public class ScoresTool implements Tool{
    private ScoresModel scoresModel;
    private ScoresView scoresView;
    private ContactDao contactDao;

    public ScoresTool(ContactDao contactDao) {
        this.contactDao = contactDao;
        List<Contact> contacts = contactDao.findAll();
        scoresModel = new ScoresModel(contacts);
        scoresView = new ScoresView( this, scoresModel);
        scoresView.showScoreView();
    }

    /**
     * Handles the save of a Score for a member. Note we first get the contact
     * from the model.  For hibernate to work we must get the same object as 
     * is currenlty stored in the DB. Then we create a Score Object which is 
     * associated with a contact because it is a Set<Scores>.  So a contact can
     * have from 1 to N socees.  finally we call the contactDao to save the 
     * contact which saves the score. 
     */
    public void notifySaveScore() {
        System.out.println("Save Pressed");
        String contact = scoresView.getContact();
        String score = scoresView.getScore();
        String date = scoresView.getDate();
        String golfCourse = scoresView.getCourseName();
        
        Date scoreDate = convertStringDateToDate(date);
        Contact theContact = scoresModel.getContact(contact);
        Score scoreToSave = new Score();
        scoreToSave.setContact(theContact);
        scoreToSave.setCourseName(golfCourse);
        scoreToSave.setScore(Integer.parseInt(score));
        scoreToSave.setScoreDate(scoreDate);
        
        theContact.addScore(scoreToSave);
        contactDao.save(theContact);
        
        
        
        
    }

    /**
     * Handles canceling the scoresView
     */
    public void notifyAddScoreCanceled() {
        System.out.println("Cancel Pressed");
        scoresView.setVisible(false);

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
    
    
}
