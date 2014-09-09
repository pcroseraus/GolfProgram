/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram.scores;

import com.myapps.golfprogram.golfprogram.dataaccess.Contact;
import com.myapps.golfprogram.golfprogram.framework.View;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author roserp
 */
public class ScoresView extends JFrame implements View, ActionListener{
    
    public static String TITLE = "Add a Score";
    
    private JComboBox contactListCombo;
    
    private ScoresTool scoreTool;
    
    private ScoresModel scoreModel;
    
    private JPanel listPanel = new JPanel();
    
    private JLabel contactsLabel = new JLabel("Contact");
    private JLabel scoreLabel = new JLabel("Score");
    private JLabel dateLabel = new JLabel("Date MM/DD/YYYY");
    private JLabel courseLabel = new JLabel("Course");
    
    private JTextField contactsField = new JTextField();
    private JTextField scoreField = new JTextField();
    private JTextField dateField = new JTextField();
    private JTextField courseField = new JTextField();
    
    
    private DefaultComboBoxModel contactModel = new DefaultComboBoxModel();
    private JComboBox memberCombo = new JComboBox(contactModel);
    
    private ScoresButtonPanel scoreButtonPanel;
    
    String item;

    public ScoresView(ScoresTool scoreTool, ScoresModel scoreModel) {
        super("Add Score For Contact");
        this.scoreTool = scoreTool;
        this.scoreModel = scoreModel;
        this.getContentPane().setLayout(new BorderLayout());
    }
    
    public void fillComboBox(){
        for( Contact contact: scoreModel.getContacts() ){
            contactModel.addElement(contact.getLastName()  + ", " + contact.getFirstName());
        }
    }
    
    public void showScoreView(){
        memberCombo.addActionListener(this);
        fillComboBox();
        ScoresButtonPanel buttonPanel = new ScoresButtonPanel(scoreTool);
        
        this.getContentPane().add(memberCombo, BorderLayout.NORTH);
        this.getContentPane().add(buildScorePanel(), BorderLayout.CENTER);
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        setSize(300, 200);
        this.setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == memberCombo){   
            
            item = (String) memberCombo.getSelectedItem();
            System.out.println("Item selected is " + item );
            this.contactsField.setText(item);
        }
        
        
    }
    
    
    
    private JPanel buildScorePanel(){
        JPanel scorePanel= new JPanel();
        scorePanel.setLayout(new GridLayout(4, 2));
        scorePanel.add(contactsLabel);
        scorePanel.add(contactsField);
        scorePanel.add(scoreLabel);
        scorePanel.add(scoreField);
        scorePanel.add(dateLabel);
        scorePanel.add(dateField);
        scorePanel.add(courseLabel);
        scorePanel.add(courseField);
        return scorePanel;
    }
    
    public void close(){
        this.setVisible(false);
    }
    
    public String getContact() {
        return contactsField.getText();
    }
     
    public String getScore(){
        return scoreField.getText();
    }
    
    public String getDate(){
        return dateField.getText();
    }
    
    public String getCourseName(){
        return courseField.getText();
    }
}