/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.Course;
import gui.AddCourse;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class AddControl {

    AddCourse ac;
    public static HashMap<String, Course> course = new HashMap<>();

    //contructor
    public AddControl(AddCourse ac) {
        this.ac = ac;
        add();
        clear();
    }

    //check and add new Course
    public void add() {
        ac.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //check code empty
                    if (ac.getTxtCode().getText().trim().isEmpty()) {
                        showMessage("Code not be empty");
                    }//check course name empty
                    else if (ac.getTxtName().getText().trim().isEmpty()) {
                        showMessage("Name course not be empty");
                    }//check credit empty
                    else if (ac.getTxtCredit().getText().trim().isEmpty()) {
                        showMessage("Credit not be empty");
                    }//check duplicate code
                    else if (course.containsKey(ac.getTxtCode().getText().trim())) {
                        showMessage("Your code already exists");
                        return;
                    }

                    //valid course name
                    String name = normalFormCourseName(ac.getTxtName().getText().trim().toLowerCase().replaceAll("\\s+", " "));
                    //valid credit
                    String credit = validateCredit(ac.getTxtCredit().getText());

                    if (credit != null) {
                        if (JOptionPane.showConfirmDialog(ac, "Do you want to add?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                            //close this frame
                            ac.dispose();
                        } else {
                            String code = ac.getTxtCode().getText().trim();
                            course.put(code, new Course(code, name, credit));
                            showMessage("Add new course successfully");
                            clearAdd();
                        }
                    }
                } catch (Exception ex) {
                }
            }
        });
    }

    //clear textfield
    public void clear() {
        ac.getBtnClear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAdd();
            }
        });
    }

    //clear textfield after add success
    public void clearAdd() {
        ac.getTxtCode().setText("");
        ac.getTxtName().setText("");
        ac.getTxtCredit().setText("");
    }

    //show error of user
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(ac, message);
    }

    //Normal form the course name
    public String normalFormCourseName(String name) {
        //split name by space
        String nameArr[] = name.split(" ");
        name = "";
        //point to each items in array
        for (String string : nameArr) {
            //if items equalsIgnoreCase with 'and' then keep it is 'and'
            if (string.equalsIgnoreCase("and")) {
                name += "and ";
            } else {
                //point to each character in string
                for (int i = 0; i < string.length(); i++) {
                    //find the first character is alphabetic in string and uppercase
                    if (Character.isAlphabetic(string.charAt(i))) {
                        name += string.substring(0, i + 1).toUpperCase() + string.substring(i + 1) + " ";
                        break;
                    }
                }
            }
        }
        //substring to romove 1 space
        name = name.substring(0, name.length() - 1);
        return name;
    }

    //validate credit
    public String validateCredit(String credit) {
        String validateCredit = null;
        try {
            int validCredit = Integer.parseInt(credit);
            //if credit is numberic but <= 0 || >33 them show message
            if (validCredit <= 0 || validCredit > 33) {
                showMessage("Credit must be POSITIVE number and less or equal than 33");
            } else {
                validateCredit = credit;
            }
        } catch (Exception e) {
            //show message if credit is'nt numberic
            showMessage("Credit must be NUMBERIC");
        }
        return validateCredit;
    }
}
