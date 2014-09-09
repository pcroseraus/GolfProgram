/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram;

import com.myapps.golfprogram.golfprogram.dataaccess.Contact;
import com.myapps.golfprogram.golfprogram.dataaccess.ContactTelDetail;
import com.myapps.golfprogram.golfprogram.ui.AddContactButtonPanel;
import com.myapps.golfprogram.golfprogram.ui.ContactPanel;
import com.myapps.golfprogram.golfprogram.ui.ContactTablePanel;
import com.myapps.golfprogram.golfprogram.ui.MainButtonPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author roserp
 */
public class GolfView extends JFrame implements ActionListener{
    
    public static String TITLE = "Golf Contacts";
    
    private GolfModel model;
    private GolfTool tool;
    
    private MainButtonPanel buttonPanel;
    private ContactPanel contactPanel;
    private JPanel listPanel = new JPanel();
    
    private DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
    
    private JComboBox memberCombo = new JComboBox(comboModel);
    
    private ContactTablePanel detailsPanel = null;

    public GolfView(GolfTool tool, GolfModel golfModel) {
       
        super(TITLE);
        setLayoutManager();
        initJListPanel();
        initButtonPanel();
        initContactDetailsPanel();
        addComponents();
        this.model = golfModel;
        this.tool = tool;
        
    }
    
    private void setLayoutManager(){
        BorderLayout layout = new BorderLayout();
        getContentPane().setLayout(layout);
    }

    /**
     * When the user selects close from the MainButtonPanel  on the GolfView, 
     * this method will handle closure of the Program. 
     */
    public void notifyCloseSelected() {
        System.exit(0);
    }
    
    /**
     * Responds to the add Button on the Golf View, when a user wants to add a 
     * contact. The tool initiates and add Panel and its button panel.  The user
     * can add and save a new contact. 
     */
    public void notifyAddSelected() {
        tool.notifyAddSelected();
    }
    
    /**
     * This method creates the ButtonPanel with buttons for closeButton, 
     * addButton , deleteButton  associated with Contact management. 
     */
    private void initButtonPanel(){
        buttonPanel = new MainButtonPanel(this);
    }
    
    /**
     * This method instantiates the contact JTable that will display the phone
     * information for a contact. 
     */
    private void initContactDetailsPanel(){
        detailsPanel = new ContactTablePanel();
    }
    
    /**
     * This method adds to the Golf Frame,the following, a list panel which is a
     * JPanel with a Combo Box of Members, A details panel which is designed to
     * 
     */   
    private void addComponents(){
        
        this.getContentPane().add(listPanel, BorderLayout.NORTH);
        this.getContentPane().add(detailsPanel, BorderLayout.CENTER);
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /**
     * This is a panel with the Member Combo that will allow a user to select a
     * member. 
     */
    private void initJListPanel() {
        memberCombo.addActionListener(this);
        JScrollPane scroller = new JScrollPane(memberCombo);  
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);  
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);  
        listPanel.add(memberCombo);
    }
    
    /**
     * This panel sets the data on the Member Combo Box. 
     * @param names 
     */
    public void setJComboBox(String[] names){
        for(int i = 0; i < names.length; i++)
        memberCombo.addItem(names[i]);
    }

    /**
     * In order to use the ComboBox Model, this method provides access to it. 
     * @return 
     */
    public DefaultComboBoxModel getComboModel() {
        return comboModel;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == memberCombo){   
            
            String item = (String) memberCombo.getSelectedItem();
            System.out.println("Item selected is " + item );
            Contact theContact = model.getContact(item);
            detailsPanel.setContact(theContact);
            
            
        }
    }

    /**
     * Handles the delete of a member. 
     */
    public void notifyDeleteSelected() {
        String item = (String)memberCombo.getSelectedItem();
        System.out.println("Delete Button Selected " + item);
        tool.notifyDeleteContact(item);
    }

    /**
     * Handles the edit of a Contact. 
     */
    public void notifyEditSelected() {
        
        String homePhone = null;
        String workPhone = null;
        String mobilePhone = null;
        
        String item = (String)memberCombo.getSelectedItem();
        Contact theContact = model.getContact(item);
        // The Contact gives us all the information required to represet the
        // member.  Now bring up a dialog with the information in it for editing. 
        Set<ContactTelDetail> details = theContact.getContactTelDetails();
        Iterator <ContactTelDetail>detailIter = details.iterator();
        while(detailIter.hasNext()){
            ContactTelDetail detail = detailIter.next();
            if( detail.getTelType().equals("Home")){
               homePhone = detail.getTelNumber();
            }
            if( detail.getTelType().equals("Work")){
                workPhone = detail.getTelNumber();
            }
            if( detail.getTelType().equals("Mobile")){
                mobilePhone = detail.getTelNumber();
            }
        }
        this.contactPanel = tool.getContactPanel();
        contactPanel.setContactData(theContact, homePhone, workPhone, mobilePhone);
        JFrame frame = tool.getFrame();
        if(frame == null){
            frame = new JFrame("Edit Contact Panel");
        }
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add( new AddContactButtonPanel(tool), BorderLayout.SOUTH);
        frame.getContentPane().add(contactPanel, BorderLayout.CENTER);
        frame.setSize(400, 300);
        frame.setVisible(true);
          
        System.out.println("need to bring up an editing session for member " + item + 
            theContact.toString());
    }

    public void notifyAddScoreSelected() {
        System.out.println("Add Score Button Selected");
        tool.addScore();
    }
}
