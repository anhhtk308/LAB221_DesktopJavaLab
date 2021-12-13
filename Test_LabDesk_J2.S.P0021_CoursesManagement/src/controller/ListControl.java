/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.AddControl.course;
import data.Course;
import gui.ListCourse;
import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class ListControl{
    ListCourse lc;

    //contructor
    public ListControl(ListCourse lc) {
        this.lc = lc;
        display(sortByCredit());
    }
    
    //sort course by credit
    public ArrayList sortByCredit(){
        ArrayList<Course> listCourse = new ArrayList<>();
        //point to each key in map
        for (String key : course.keySet()) {
            Course temp = course.get(key);
            listCourse.add(temp);
        }
        Collections.sort(listCourse);
        return listCourse;
    }
    
    //display course to screen
    public void display(ArrayList<Course> listCourse){
        //has no any course
        if(listCourse.isEmpty()){
            JOptionPane.showMessageDialog(lc, "Has no any Course", "Notification", JOptionPane.OK_OPTION);
        }
        
        //point to each item in list
        for (Course course1 : listCourse) {
            lc.getTxtList().append(course1 + "\n");
        }
    }

}
