/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.ColorsSlider;
import java.awt.Color;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Admin
 */
public class ColorsSliderControl {
    ColorsSlider cs;
    int r = 0, g = 0, b = 0;

    public ColorsSliderControl(ColorsSlider cs) {
        this.cs = cs;
        //set background for panel is black
        cs.getPnDislay().setBackground(new Color(0, 0, 0));
        //add event for slider Red
        cs.getSldRed().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                r = cs.getSldRed().getValue();
                cs.getPnDislay().setBackground(new Color(r, g, b));
                cs.getLblRed().setText("R = " + r);
            }
        });
        //add event for slider Green
        cs.getSldGreen().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                g = cs.getSldGreen().getValue();
                cs.getPnDislay().setBackground(new Color(r, g, b));
                cs.getLblGreen().setText("G = " + g);
            }
        });
        //add event for slider Blue
        cs.getSldBlue().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                b = cs.getSldBlue().getValue();
                cs.getPnDislay().setBackground(new Color(r, g, b));
                cs.getLblBlue().setText("B = " + b);
            }
        });
    }
    
    
}
