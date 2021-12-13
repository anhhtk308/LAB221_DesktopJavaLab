/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.CalculateControl;
import gui.CalculateFrame;

/**
 *
 * @author Admin
 */
public class Main {
    public static void main(String[] args) {
        CalculateFrame frame = new CalculateFrame();
        CalculateControl control = new CalculateControl(frame);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
