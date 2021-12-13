/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import GUI.MainFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author chuy-Duonng
 */
public class PaintController {

    Graphics g;
    Point a, b;
    Color c;
    MainFrame main;
    public ArrayList<JPanel> panels = new ArrayList<>();
    public static final Color DEFAULT_COLOR = new Color(153, 153, 153);

    public PaintController(MainFrame aThis) {
        this.main = aThis;
        main.getContentPane().setBackground(Color.BLUE);
        System.out.println("jpanel 2 background color" + this.main.getjPanel2().getBackground());
        System.out.println("jpanel 2 background color" + this.main.getjPanel2().getBackground());
        System.out.println("jpanel 2 background color" + this.main.getjPanel2().getBackground());
        this.panels.add(this.main.getjPanel2());
        this.panels.add(this.main.getjPanel3());
        this.panels.add(this.main.getjPanel4());

    }

    public void draw() {
        g = main.panel.getGraphics();

        g.setColor(c);
        g.drawLine(a.x, a.y, b.x, b.y);
        a = b;
    }

    public void panelMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        a = evt.getPoint();
    }

    public void panelMouseDragged(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        b = evt.getPoint();
        draw();
    }

    public void panelMouseReleased(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        a = null;
        b = null;
    }

    public void newMouseClick(JPanel jPanel) {
        System.out.println("this is background color : " + jPanel.getBackground());
        mouseClicked(jPanel.getBackground(), jPanel);
    }

    public void mouseClicked(Color c, JPanel panel) {
        this.c = c;
        resetBorder();
        panel.setBorder(BorderFactory.createLineBorder(Color.yellow));
    }

    public void resetBorder() {
        for (JPanel panel : panels) {
            panel.setBorder(BorderFactory.createLineBorder(DEFAULT_COLOR));
        }
    }
}
