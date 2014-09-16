
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
 * This is the View of the Scores implementation. It extends JFrame to  provide
 * a container to add the UI components to.  JFrame constructor needs a String 
 * for its title. It h\olds a reference to the tool and model.  The model 
 * provides contacts for the JCombo Box, and the Tool will receive notifications
 * of user actions. Views should not have business logic which is why the View 
 * has a reference to the tool. 
 *
 */
public class ScoresView extends JFrame implements View, ActionListener{
    
    public static String TITLE = "Add a Score";
    
    private JComboBox contactListCombo;
    
    private ScoresTool scoreTool;
    
    private ScoresModel scoreModel;
    
    /**
     * The list panel will provide a container for the JComboBox.
     */
    private JPanel listPanel = new JPanel();
    
    /**
     * The labels that define the fields for adding a score. 
     */
    private JLabel contactsLabel = new JLabel("Contact");
    private JLabel scoreLabel = new JLabel("Score");
    private JLabel dateLabel = new JLabel("Date MM/DD/YYYY");
    private JLabel courseLabel = new JLabel("Course");
    
    /**
     * The fields that provide entry points for entering scores. 
     */
    private JTextField contactsField = new JTextField();
    private JTextField scoreField = new JTextField();
    private JTextField dateField = new JTextField();
    private JTextField courseField = new JTextField();
    
    
    private DefaultComboBoxModel contactModel = new DefaultComboBoxModel();
    private JComboBox memberCombo = new JComboBox(contactModel);
    
    private ScoresButtonPanel scoreButtonPanel;
    
    /*
    * Whenever a new contact is selected in the combo , item will be set to it
    */
    String item;

    public ScoresView(ScoresTool scoreTool, ScoresModel scoreModel) {
        super("Add Score For Contact");
        this.scoreTool = scoreTool;
        this.scoreModel = scoreModel;
        this.getContentPane().setLayout(new BorderLayout());
    }
    
    /**
     * Iterate over the combo list and define a contact with the format
     * lastname + ", " + firstname. This is what users will see in the combo.. 
     */
    public void fillComboBox(){
        for( Contact contact: scoreModel.getContacts() ){
            contactModel.addElement(contact.getLastName()  + ", " + contact.getFirstName());
        }
    }
    
    /**
     * Adds the combo box, The  Fields that make up a Score including contact,
     * golf course, score, and date, and finally a button panel where the user
     * can select to save the score, or cancel the operation. 
     */
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
    
    
    /**
     * Responds to a user action on the combo box.  Each time this is selected,
     * get the user name and set it into the contact field. 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == memberCombo){   
            
            item = (String) memberCombo.getSelectedItem();
            System.out.println("Item selected is " + item );
            this.contactsField.setText(item);
        }   
    }
    
    
    /**
     * The score panel holds four labels, and four fields. 
     * @return 
     */
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
    
    /**
     * When a user saves or cancels the  operation , this method will close 
     * the add score dialog. 
     */
    public void close(){
        this.setVisible(false);
    }
    
    //Getters for each field follow. 
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