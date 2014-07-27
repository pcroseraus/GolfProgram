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
    private JComboBox memberCombo = new JComboBox(new String[0]);
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

    public void notifyCloseSelected() {
        System.exit(0);
    }
    
    public void notifyAddSelected() {
        tool.notifyAddSelected();
    }
    
    private void initButtonPanel(){
        buttonPanel = new MainButtonPanel(this);
    }
    
    private void initContactDetailsPanel(){
        detailsPanel = new ContactTablePanel();
    }
    
       
    private void addComponents(){
        
        this.getContentPane().add(listPanel, BorderLayout.NORTH);
        this.getContentPane().add(detailsPanel, BorderLayout.CENTER);
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void initJListPanel() {
        memberCombo.addActionListener(this);
        JScrollPane scroller = new JScrollPane(memberCombo);  
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);  
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);  
        listPanel.add(memberCombo);
    }
    
    public void setJComboBox(String[] names){
        for(int i = 0; i < names.length; i++)
        memberCombo.addItem(names[i]);
    }

//    public void saveContactInfo(){
//        
//         GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//         ctx.load("classpath:app-context.xml");
//         ctx.refresh();
//        
//         ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);
//         //ContactData data = contactPanel.getContactData();
//         System.out.println(data.toString());
//         Date convertedDate= data.convertStringDateToDate(data.getBirthdate());
//         System.out.println("Date is now " + convertedDate.toString());
//        
//         Contact contact = new Contact();
//         contact.setFirstName(data.getFirstName());
//         contact.setLastName(data.getLastName());
//         contact.setBirthDate(convertedDate);
//         ContactTelDetail contactTelDetail = new ContactTelDetail("Home", data.getHomePhone());
//         contact.addContactTelDetail(contactTelDetail);
//         contactTelDetail = new ContactTelDetail("Mobile", data.getMobilePhone());
//         contact.addContactTelDetail(contactTelDetail);
//         contactTelDetail = new ContactTelDetail("Work", data.getWorkPhone());
//         contact.addContactTelDetail(contactTelDetail);
//         contactDao.save(contact);
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == memberCombo){   
            
            String item = (String) memberCombo.getSelectedItem();
            System.out.println("Item selected is " + item );
            Contact theContact = model.getContact(item);
            detailsPanel.setContact(theContact);
            
            
        }
    }

    public void notifyDeleteSelected() {
        String item = (String)memberCombo.getSelectedItem();
        System.out.println("Delete Button Selected " + item);
        tool.notifyDeleteContact(item);
    }

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
}
