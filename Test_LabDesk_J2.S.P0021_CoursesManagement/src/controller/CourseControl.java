/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.AddCourse;
import gui.CourseManagement;
import gui.ListCourse;
import gui.SearchCourse;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class CourseControl {
    CourseManagement cm;

    public CourseControl(CourseManagement cm) {
        this.cm = cm;
        add();
        search();
        list();
        exit();
    }
    
    //function of button "AddCourse"
    public void add(){
        cm.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCourse add = new AddCourse(cm, true);
                AddControl addControl = new AddControl(add);
                add.setLocationRelativeTo(null);
                add.setVisible(true);
            }
        });
    }
    
    //function of button display
    public void list(){
        cm.getBtnDisplay().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListCourse list = new ListCourse(cm, true);
                ListControl listControl = new ListControl(list);
                list.setLocationRelativeTo(null);
                list.setVisible(true);
            }
        });
    }
    
    //function of button search
    public void search(){
        cm.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchCourse search = new SearchCourse(cm, true);
                SearchControl searchControl = new SearchControl(search);
                search.setLocationRelativeTo(null);
                search.setVisible(true);
            }
        });
    }
    
    //function of button Exit
    public void exit(){
        cm.getBtnExit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(cm, "Exit Application", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
    }
}
