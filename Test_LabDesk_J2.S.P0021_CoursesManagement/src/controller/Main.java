/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.CourseManagement;

/**
 *
 * @author Admin
 */
public class Main {
    public static void main(String[] args) {
        CourseManagement cm = new CourseManagement();
        CourseControl cc = new CourseControl(cm);
        cm.setLocationRelativeTo(null);
        cm.setVisible(true);
    }
}
