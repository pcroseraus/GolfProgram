/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram.ui;

import com.myapps.golfprogram.golfprogram.backups.OldContactPanel;
import com.myapps.golfprogram.golfprogram.GolfTool;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author roserp
 */
public class ModifyContactPanel extends JFrame{
    
    private ContactButtonPanel buttonPanel = null;
    private OldContactPanel contactPanel = null;
    private GolfTool tool = null;
    
    public ModifyContactPanel(GolfTool tool, 
                              ContactButtonPanel contactButtonPanel){
        super();
        this.tool = tool;
        setLayoutManager();
        contactPanel = new OldContactPanel(tool);
        this.buttonPanel = contactButtonPanel;
        addComponents();
    }
    
    private void setLayoutManager(){
        this.getContentPane().setLayout(new BorderLayout());
    }
    
    private void addComponents(){
        this.getContentPane().add(contactPanel, BorderLayout.CENTER);
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
    
}
