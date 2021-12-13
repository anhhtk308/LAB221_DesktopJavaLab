/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.BrowseByLineInJTableFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class BrowseByLineInJTableControl {

    BrowseByLineInJTableFrame frame;

    public BrowseByLineInJTableControl(BrowseByLineInJTableFrame frame) {
        this.frame = frame;
        int size = frame.getTblDisplay().getRowCount();//get size
        if (checkTableRow()) {
            frame.getTblDisplay().setRowSelectionInterval(0, 0);
        } else {
            showMessage();
        }
        //btnFirst
        frame.getBtnMoveFirst().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkTableRow()) {
                    frame.getTblDisplay().setRowSelectionInterval(0, 0);
                } else {
                    showMessage();
                }
            }
        });
        //btnLast
        frame.getBtnMoveLast().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkTableRow()) {
                    frame.getTblDisplay().setRowSelectionInterval(size - 1, size - 1);
                } else {
                    showMessage();
                }
            }
        });
        //btnNext
        frame.getBtnMoveNext().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = frame.getTblDisplay().getSelectedRow();
                selected++;
                if (checkTableRow()) {
                    if (selected >= size) {
                        frame.getTblDisplay().setRowSelectionInterval(0, 0);
                    } else {
                        frame.getTblDisplay().setRowSelectionInterval(selected, selected);
                    }
                } else {
                    showMessage();
                }
            }
        });
        //btnPrevious
        frame.getBtnMovePrevious().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = frame.getTblDisplay().getSelectedRow();
                selected--;
                if (checkTableRow()) {
                    if (selected < 0) {
                        frame.getTblDisplay().setRowSelectionInterval(size - 1, size - 1);
                    } else {
                        frame.getTblDisplay().setRowSelectionInterval(selected, selected);
                    }
                } else {
                    showMessage();
                }
            }
        });
    }

    public void showMessage() {
        JOptionPane.showMessageDialog(null, "No data!", "Notice!", JOptionPane.WARNING_MESSAGE);
    }

    public boolean checkTableRow() {
        int size = frame.getTblDisplay().getRowCount();
        if (size > 0) {
            return true;
        }
        return false;
    }

}
