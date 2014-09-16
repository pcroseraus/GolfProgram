package com.myapps.golfprogram.golfprogram;

import com.myapps.golfprogram.golfprogram.dataaccess.Contact;
import com.myapps.golfprogram.golfprogram.dataaccess.ContactDao;
import com.myapps.golfprogram.golfprogram.dataaccess.ContactTelDetail;
import com.myapps.golfprogram.golfprogram.scores.ScoresTool;
import com.myapps.golfprogram.golfprogram.ui.AddContactButtonPanel;
import com.myapps.golfprogram.golfprogram.ui.ContactPanel;
import java.awt.BorderLayout;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;

/**
 * The Tool is the controller in the Model view Controller pattern usually 
 * abbreviated MVC. This class should implement the Tool interface. TODO: 
 * implement the Tool interface. 
 * TODO: The Golf Model and Golf View are immutable and should be finals
 * What does it mean to be final.  final indicates that a class member is 
 * cannot be changed once set. So creating a final String and setting it to
 * "Foo" Means you cannot set it to "Bar"  TODO:  Later we will expand our use
 * of the contactDao and inject it here instead of passing it in. 
 */
public class GolfTool {
    
    private GolfModel golfModel;
    private GolfView golfView;
    private ContactDao contactDao;
    private JFrame contactFrame = new JFrame("Add Contact Panel");
    private ContactPanel panel = new ContactPanel(this);
    
    
    /**
     * The Constructor for the Tool.  A constructor of any  class is designed to
     * accept arguments and to create and populate class members. This tool 
     * should not be creating a view component.  But it does.  JFrame is
     * being constructed here. This should be done in the View. TODO; Move the
     * creation of the JFrame to the GolfView. Never create Strings that are 
     * immutable in classes.  TODO:  Create a public class ViewConstants.java
     * that contains all public Strings for all UI classes. TODO: Make the "Add
     * Contact Panel" String passed in to the UI a public static String in the 
     * View Constants file. Use this string in the construction of the JFrame in 
     * Golf View.  Why is this important.  static Strings are not 
     * constructed during at run time. The more Strings you create at run time,
     * the slower your program will run.  Doesn't sound like much of a slow 
     * down, but if your product has 1000 classes, enhancements like this make 
     * a big difference.  TODO Why are calling the contactDao to get all 
     * contacts, passing in the contacts, then passing in the contactDao. Why 
     * not use the contactDao in here to get the Contact List and reduce the 
     * arguments passed in. 
     * @param contacts
     * @param contactDao 
     */
    public GolfTool(List<Contact> contacts, ContactDao contactDao) {
        //Models typically hold the data. This model will hold the contacts. 
        golfModel = new GolfModel(contacts);
        golfView = new GolfView(this,golfModel);
        this.contactDao = contactDao;
        String[] names = golfModel.getNames();
        golfView.setJComboBox(names);
    }

    /**
     * Return the model
     * @return 
     */
    public GolfModel getGolfModel() {
        return golfModel;
    }

    /**
     * Return the view
     * @return 
     */
    public GolfView getView() {
        return golfView;
    }
    
    /**
     * Responds to the add Button when a user wants to add a contact.  Where 
     * does this get called from.  The View.  Right click on this method, then
     * select findUsages.  A pane will appear that shows the class that calls it
     * and the line number and content. You can then double click the line and
     * it will take you there. 
     * 
     * Why not just handle the button press in the View. Views should not handle 
     * business logic. 
     */
    public void notifyAddSelected(){
        if(contactFrame == null){
            contactFrame = new JFrame("Add Contact Panel");
        }
        //panel = new ContactPanel(this);
        AddContactButtonPanel buttonPanel = new AddContactButtonPanel(this);
        contactFrame.setLayout(new BorderLayout());
        contactFrame.add(panel, BorderLayout.CENTER);
        contactFrame.add(buttonPanel, BorderLayout.SOUTH);
        contactFrame.add(panel);
        contactFrame.setSize(400,250);
        contactFrame.setVisible(true);
    }

    public void notifySaveContact() {
        
        Contact data = panel.getContactData();
        Long id = golfModel.getIdForName(data.getLastName() + "," + data.getFirstName());
        if( id == 0 ){
            //this is a save because the id could not be found
            contactDao.save(data);
            golfView.getComboModel().addElement(data.getLastName() + ", " + data.getFirstName());
            golfModel.addContact(data);
            
        }else{
            //this is an edit because the id was found and must be > 0
            Contact contact = contactDao.findById(id);
            contact.setFirstName(data.getFirstName());
            contact.setLastName(data.getLastName());
            contact.setBirthDate(data.getBirthDate());
            
            
            Set<ContactTelDetail> details = contact.getContactTelDetails();
            ContactTelDetail homeDeleteDetail = null;
            ContactTelDetail workDeleteDetail = null;
            ContactTelDetail mobileDeleteDetail = null;
            
            // Remove Home, Work, and Mobile Phone Records.
            for(ContactTelDetail contactTel: details){
                if(contactTel.getTelType().equals("Home")){
                    homeDeleteDetail = contactTel;
                }
            }
            if(homeDeleteDetail != null){
                System.out.println("Setting Home phone to " + panel.getHomePhone());
                homeDeleteDetail.setTelNumber(panel.getHomePhone());
            }
            
            for(ContactTelDetail contactTel: details){
                if(contactTel.getTelType().equals("Mobile")){
                    mobileDeleteDetail = contactTel;
                }
            }
            
            if(mobileDeleteDetail != null){
                System.out.println("Setting Mobile phone to " + panel.getMobilePhone());
                mobileDeleteDetail.setTelNumber(panel.getMobilePhone());
            }
            for(ContactTelDetail contactTel: details){
                if(contactTel.getTelType().equals("Work")){
                    workDeleteDetail = contactTel;
                }
            }
            
            if(workDeleteDetail != null){
                System.out.println("Setting Mobile phone to " + panel.getWorkPhone());
                workDeleteDetail.setTelNumber(panel.getWorkPhone());
            }
            
            
            
            contactDao.save(contact);
            golfView.getComboModel().addElement(contact.getLastName() + "' " + contact.getFirstName());
            
            //this is an edit
            
        }
        
        //
        contactFrame.setVisible(false);
        contactFrame = null;
    }

    /**
     * When the user does not want to add the New Contact , This method closes
     * the frame. 
     */
    public void notifyAddContactCanceled() {
        contactFrame.setVisible(false);
        contactFrame = null;
    }

    public void notifyDeleteContact(String item) {
        long id = golfModel.getIdForName(item);
        Contact contact = contactDao.findById(id);
        contactDao.delete(contact);
        golfView.getComboModel().removeElement(contact.getLastName()+ ", " + contact.getFirstName());
        golfModel.removeContact(contact);
    }
    
    public void notifyEditContact(String item) {
        
        // Need to open a contact panel set the data for it. 
        Contact data = panel.getContactData();
        contactDao.save(data);
        contactFrame.setVisible(false);
        contactFrame = null;
        
    }
    
    public ContactPanel getContactPanel(){
        return this.panel;
    }
    
    public JFrame getFrame(){
        return contactFrame;
    }

    void addScore() {
        ScoresTool scoreTool = new ScoresTool(contactDao);
        
    }
    
}
