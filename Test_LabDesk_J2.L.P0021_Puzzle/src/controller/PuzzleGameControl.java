/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.PuzzleGameFrame;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Admin
 */
public class PuzzleGameControl {
    PuzzleGameFrame pgf;
    private JPanel panel;
    private JButton[] buttons;
    private int size;
    private int moveCount;
    private boolean isPlay;
    private Timer thread;
    //private TimeControl thread;
    private boolean isReady;
    private int widthBtn = 50, heightBtn = 50;
    private int hgap = 10, vgap = 10;

    public PuzzleGameControl() {
        pgf = new PuzzleGameFrame();
        this.panel = pgf.getPnDisplay();
        isPlay = false;
        moveCount = 0;
        //thread = new TimeControl(pgf);
        countTime();
        pgf.getLblMove().setText("Move Count: " + moveCount);
        isReady = true;
        //thread.start();
        viewFirst();
        addEventforButtonNewGame();
        pgf.setVisible(true);
        pgf.setLocationRelativeTo(null);
    }
    
    public void countTime(){
        pgf.getLblElapsed().setText("Elapsed: 0 sec");
        thread = new Timer(1000, new ActionListener() {
            int second = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                second++;
                pgf.getLblElapsed().setText("Elapsed: " + String.valueOf(second) + " sec");
            }
        });
        //thread.start();
    }
    
    public int getIndexOfEmptyButton(){
        //point to each button in puzzle
        for (int i = 0; i < buttons.length; i++) {
            //return index when "text" of buttion not number
            if(buttons[i].getText().equals("")){
                return i;
            }
        }
        return -1;
    }
    
    public void countUpdate(){
        moveCount++;
        pgf.getLblMove().setText("Move Count: " + moveCount);
    }
    
    public boolean isWin(){
        int temp = 1;
        //point to each button in puzzle
        for (int i = 0; i < buttons.length - 1; i++) {
            //from 1st buttion to size*size (th) button, it has value != temp => not win
            if(buttons[i].getText().equals("") || Integer.parseInt(buttons[i].getText()) != temp){
                return false;
            }
            temp++;
        }
        return true;
    }
    
    public void addValueForButton(){
        ArrayList<Integer> arr = new ArrayList<>();
        Random rd = new Random();
        
        //create a list Integer with list.size < (size * size)
        while (arr.size() < size * size) {            
            int i = rd.nextInt(size * size) + 1;
            //help us to create a list has only different number
            if(!arr.contains(i)){
                arr.add(i);
            }
        }
        
        //point to each button in puzzle
        for (int i = 0; i < buttons.length; i++) {
            //only add value for button if number in list < (size * size)
            if(arr.get(i) != size * size){
                buttons[i].setText(arr.get(i) + "");
            }else{
                buttons[i].setText("");
            }
        }
    }
    
    public JButton getEmptyButton(){
        //point to each button in puzzle
        for (int i = 0; i < buttons.length; i++) {
            //return button when value of buttion is not number
            if(buttons[i].getText().equals("")){
                return buttons[i];
            }
        }
        return null;
    }
    
    public int getRowEmptyButton(String td){
        String[] arr = td.split(",");
        return Integer.parseInt(arr[0].trim());
    }
    
    public int countNumGreater(){
        int count = 0;
        //point from ith button to last button
        for (int i = 0; i < buttons.length; i++) {
            //point from (i+1)th button to last button
            for (int j = i+1; j < buttons.length; j++) {
                if(!buttons[i].getText().equals("")//ith button is a numerical value
                        && !buttons[j].getText().equals("")//(i+1)th button to last button is is a numerical value
                        && Integer.valueOf(buttons[i].getText().trim()) < Integer.valueOf(buttons[j].getText().trim())){/*Value of ith button < value of (i+1)th button to last button*/
                    count++;
                }
            }
        }
        return count;
    }
    
    public void showMessage(String message){
        JOptionPane.showMessageDialog(null, message);
    }
    
    public boolean checkSolvable(){
        //case size is odd and count number greater is even
        if (size % 2 != 0 && countNumGreater() % 2 == 0) {
            return true;
        }
        //case size is even and count number greater is even and row(has empty button) is even
        if (size % 2 == 0 && countNumGreater() % 2 == 0
                && getRowEmptyButton(getEmptyButton().getName()) % 2 == 0) {
            return true;
        }
        //case size is even and count number greater is odd and row(has empty button) is odd
        if (size % 2 == 0 && countNumGreater() % 2 != 0
                && getRowEmptyButton(getEmptyButton().getName()) % 2 != 0) {
            return true;
        }
        return false;
    }
    
    public void swap(int btn){
        int empty = getIndexOfEmptyButton();
        if((btn - 1 == empty && btn % size != 0)//only move right
                || (btn + 1 == empty && btn % size != size - 1)//only move left
                || btn - size == empty//only move up
                || btn + size == empty){//only move down
            buttons[empty].setText(buttons[btn].getText());
            buttons[btn].setText("");
            countUpdate();
        }
    }
    
    public void addEventForButton(JButton btn){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //game is starting when player click new game
                if(isPlay == true){
                    //help us to find which button is clicked
                    JButton b = (JButton) e.getSource();
                    int index = 0;
                    //point to each btn in puzzle
                    for (int i = 0; i < buttons.length; i++) {
                        //find index of clicked button
                        if(buttons[i].equals(b)){
                            index = i;
                            break;
                        }
                    }
                    //swap
                    swap(index);
                    //after swap, check win
                    if(isWin() == true){
                        isPlay = false;
                        //thread.stopped();
                        thread.stop();
                        showMessage("You Won!");
                    }
                }else{
                    showMessage("I know you love this game. Please click new game and enjoying!");
                }
            }
        });
    }
    
    public void showButton(){
        //clear all button of panel
        panel.removeAll();
        //create layout for panel(GridLayout: rows = columns = size and hgap = vgap = 10)
        panel.setLayout(new GridLayout(size, size, hgap, vgap));
        //create array of button with length = size * size
        buttons = new JButton[size * size];
        
        int numOfBut = 0;
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                buttons[numOfBut] = new JButton();
                //set size for button
                buttons[numOfBut].setPreferredSize(new Dimension(widthBtn, heightBtn));
                //help us to get row
                buttons[numOfBut].setName(i + "," + j);
                addEventForButton(buttons[numOfBut]);
                panel.add(buttons[numOfBut]);
                buttons[numOfBut].setVisible(true);
                numOfBut++;
            }
        }
        //set width an height for puzzle panel and main-frame
        panel.setSize(new Dimension(size * widthBtn + hgap * (size - 1), size * heightBtn + vgap * (size - 1)));
        pgf.setSize(new Dimension(size * widthBtn + hgap * (size - 1) + 60, size * heightBtn + vgap * (size - 1) + 250));
    }
    
    public void addEventforButtonNewGame(){
        pgf.getBtnNewGame().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //thread.stopped();
                thread.stop();
                int click = JOptionPane.showConfirmDialog(panel, "Do you want to play new game?", "New Game", JOptionPane.YES_NO_OPTION);
                //when user started playing game
                if(click == JOptionPane.YES_OPTION){
                    countTime();
                    thread.start();
                    //thread.resetTime();
                    moveCount = 0;
                    isPlay = true;
                    //thread.excecuted();
                    pgf.getLblMove().setText("Move Count: " + moveCount);
                    size = pgf.getCbxSize().getSelectedIndex() + 3;
                    if(!isReady){
                        showButton();
                        addValueForButton();
                        while (!checkSolvable()) {                            
                            addValueForButton();
                        }
                    }else{
                        if(pgf.getCbxSize().getSelectedIndex() != 0){
                            showButton();
                            addValueForButton();
                            while (!checkSolvable()) {
                                addValueForButton();
                            }
                        }
                        isReady = false;
                    }
                }
                //when user click new game and press no
                else if((click == JOptionPane.NO_OPTION || click == -1) && !isReady){
                    pgf.getCbxSize().setSelectedIndex(size - 3);
                    if(isWin() == true){
                        //thread.stopped();
                        thread.stop();
                    }else{
                        //thread.excecuted();
                        thread.start();
                    }
                }
            }
        });
    }
    
    public void viewFirst(){
        size = 3;
        showButton();
        addValueForButton();
        while (!checkSolvable()) {            
            addValueForButton();
        }
    }
    
    
}
