/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram.scores;


import com.myapps.golfprogram.golfprogram.scores.ScoresTool;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author roserp
 */
public class ScoresButtonPanel extends JPanel implements ActionListener{
    ScoresTool tool;
    // A user can save a new contact or cancel the operation. 
    private JButton saveButton = new JButton("Save");
    private JButton cancelButton = new JButton("Cancel");

    public ScoresButtonPanel(ScoresTool tool) {
        super();
        this.tool = tool;
        addListeners();
        addComponents();
    }
    
    private void addListeners(){
        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }
    
    private void addComponents(){
        add(saveButton);
        add(cancelButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveButton){
           tool.notifySaveScore();
            
        }else if( e.getSource() == cancelButton){
            tool.notifyAddScoreCanceled();
        }
        
    }
    
} 
