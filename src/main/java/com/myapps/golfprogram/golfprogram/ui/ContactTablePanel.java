/*
 * The purpose for this class is the show for each contact selected, to show the
 * home, mobile and work phones 
 */

package com.myapps.golfprogram.golfprogram.ui;

import com.myapps.golfprogram.golfprogram.dataaccess.Contact;
import com.myapps.golfprogram.golfprogram.dataaccess.ContactTelDetail;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.CONTACT_PANEL_DETAIL_HOME;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.CONTACT_PANEL_DETAIL_MOBILE;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.CONTACT_PANEL_DETAIL_WORK;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.CONTACT_PANEL_HOME_PHONE;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.CONTACT_PANEL_MOBILE_PHONE;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.CONTACT_PANEL_WORK_PHONE;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * Will Represent a table of contact information.  Contact information contains
 * only a Home, Work, and Mobile Phone. 
 */
public class ContactTablePanel extends JPanel {
    
    String[] columnNames = {CONTACT_PANEL_HOME_PHONE,
                            CONTACT_PANEL_WORK_PHONE,
                            CONTACT_PANEL_MOBILE_PHONE};
    
    Object[][] data = new Object[1][3];
    JTable table = new JTable(data, columnNames);
    
    public ContactTablePanel() {
        super();
        setLayoutManager();
        JScrollPane scrollPane = new JScrollPane(table);
        
        table.setFillsViewportHeight(true);
        add(table.getTableHeader());
        add(scrollPane); 
    }
    
    private void setLayoutManager(){
        // does nothing 
    }

    /**
     * This method populates the table of contact information for a contact. 
     * That includes the home, mobile, and work phone. If no data is included in
     * the Contact object and empty cell is created. 
     * @param theContact 
     */
    public void setContact(Contact theContact) {
        int numberDetails = theContact.getContactTelDetails().size();
        Object[][] newData = new Object[1][3];
        newData[0][0] = "";
        newData[0][1] = "";
        newData[0][2] = "";
        Set<ContactTelDetail>details = theContact.getContactTelDetails();
        Iterator <ContactTelDetail>detailIter = details.iterator();
        while( detailIter.hasNext()){
            ContactTelDetail detail = detailIter.next();
            if( detail.getTelType().equals(CONTACT_PANEL_DETAIL_HOME)){
                newData[0][0] = detail.getTelNumber();                
            }
            if( detail.getTelType().equals(CONTACT_PANEL_DETAIL_WORK)){
                newData[0][1] = detail.getTelNumber();                
            }
            if( detail.getTelType().equals(CONTACT_PANEL_DETAIL_MOBILE)){
                newData[0][2] = detail.getTelNumber();                
            }
        }
          
         for(int i = 0; i < 3; i++){
                table.getModel().setValueAt(newData[0][i], 0, i); 
                table.updateUI();    
        }   
    }  
}
