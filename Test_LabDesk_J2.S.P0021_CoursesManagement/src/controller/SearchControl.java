/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.AddControl.course;
import data.Course;
import gui.SearchCourse;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class SearchControl {
    SearchCourse sc;
    
    //contructor
    public SearchControl(SearchCourse sc) {
        this.sc = sc;
        search();
    }
    
    //check and return course with inputted code
    public void search(){
        sc.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int check = 0;
                sc.getTxtCourseName().setText("");
                sc.getTxtCredit().setText("");
                //check inpputted is empty or not
                if(sc.getTxtCode().getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(sc, "Code not be empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else //input exists
                {
                    if(course.containsKey(sc.getTxtCode().getText().trim())){
                        check = 1;
                        Course temp = course.get(sc.getTxtCode().getText().trim());
                        sc.getTxtCourseName().setText(temp.getName());
                        sc.getTxtCredit().setText(temp.getCredit());
                    }else if(check == 0){//cannot find data
                        JOptionPane.showMessageDialog(sc, "Can not find any data", "Not found", JOptionPane.OK_OPTION);
                    }
                }
            }
        });
    }

}
