/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram.ui;

import com.myapps.golfprogram.golfprogram.GolfTool;
import com.myapps.golfprogram.golfprogram.GolfView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.MAIN_BUTTONPANEL_CLOSE_BUTTON;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.MAIN_BUTTONPANEL_ADD_BUTTON;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.MAIN_BUTTONPANEL_DELETE_BUTTON;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.MAIN_BUTTONPANEL_EDIT_BUTTON;
import static com.myapps.golfprogram.golfprogram.ui.StringConstants.MAIN_BUTTONPANEL_ADD_SCORE_BUTTON;

/**
 *
 * @author roserp
 */
public class MainButtonPanel extends JPanel implements ActionListener{
    
    private JButton closeButton = new JButton(MAIN_BUTTONPANEL_CLOSE_BUTTON);
    private JButton addButton = new JButton(MAIN_BUTTONPANEL_ADD_BUTTON);
    private JButton deleteButton = new JButton(MAIN_BUTTONPANEL_DELETE_BUTTON);
    private JButton editButton = new JButton(MAIN_BUTTONPANEL_EDIT_BUTTON);
    private JButton addScoreButton  = new JButton(MAIN_BUTTONPANEL_ADD_SCORE_BUTTON);
    private GolfView frame;
    private GolfTool tool;

    public MainButtonPanel(GolfView frame, GolfTool tool) {
        this.frame = frame;
        this.tool = tool;
        addListeners();
        initComponents();
    }
    
    private void addListeners(){
        addButton.addActionListener(this);
        closeButton.addActionListener(this);
        deleteButton.addActionListener(this);
        editButton.addActionListener(this);
        addScoreButton.addActionListener(this);
    }
    
    private void initComponents(){
        add(editButton);
        add(deleteButton);
        add(addButton);
        add(addScoreButton);
        add(closeButton);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == closeButton)
            frame.notifyCloseSelected();
        else if (e.getSource() == addButton)
            tool.createAddContactPanel();
        else if(e.getSource() == deleteButton){
            frame.notifyDeleteSelected();
        }else if(e.getSource() == editButton){
            frame.notifyEditSelected();
        }else if(e.getSource() == addScoreButton){
            frame.notifyAddScoreSelected();
        }        
    }    
    
}
