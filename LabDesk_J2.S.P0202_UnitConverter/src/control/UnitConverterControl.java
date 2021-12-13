/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import gui.UnitConverterFrame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class UnitConverterControl {
    UnitConverterFrame uc;

    public UnitConverterControl() {
        uc = new UnitConverterFrame();
        uc.setLocationRelativeTo(null);
        uc.setVisible(true);
        addActionForBtnConvert();
    }
    
    public void addActionForBtnConvert(){
        uc.getBtnConvert().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double inputValue = 0;
                //user has not been entered value
                if(uc.getTxtFrom().getText().isEmpty()){
                    JOptionPane.showMessageDialog(uc, "Value is empty! Please input value");
                    return;
                }
                try {
                    inputValue = Double.parseDouble(uc.getTxtFrom().getText());
                    //value which user enter less than 0
                    if(inputValue < 0){
                        JOptionPane.showMessageDialog(uc, "Value must be greater than 0!");
                        return;
                    }
                } //case value which user enter is alphabetic
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(uc, "Please input a numberic!");
                    return;
                }
                int selectedIndexFrom = uc.getCbxFrom().getSelectedIndex();
                int selectedIndexTo = uc.getCbxTo().getSelectedIndex();
                double result = getResultAfterConvert(selectedIndexFrom, selectedIndexTo, inputValue);
                //result is equal to its rounding value
                if(Math.round(result) == result){
                    uc.getTxtTo().setText((int) result + "");
                    uc.getTxtTo().setCaretPosition(0);
                }else{
                    uc.getTxtTo().setText(result + "");
                    uc.getTxtTo().setCaretPosition(0);
                }
            }
        });
    }
    
    public double getResultAfterConvert(int selectedIndexFrom, int selectedIndexTo, double inputValue){
        double result = 0;
        switch(selectedIndexFrom){
            //user selected "Meter" to convert
            case 0:
                //user selected "Meter" in cbx To
                if(selectedIndexTo == 0){
                    result = inputValue;
                }//user selected "Mile" in cbx To
                else if(selectedIndexTo == 1){
                    result = inputValue / 1609.34;
                }//user selected "Inch" in cbx To
                else{
                    result = inputValue * 39.3701;
                }
                break;
            //user selected "Mile" to convert
            case 1:
                //user selected "Meter" in cbx To
                if(selectedIndexTo == 0){
                    result = inputValue * 1609.34;
                }//user selected "Mile" in cbx To
                else if(selectedIndexTo == 1){
                    result = inputValue;
                }//user selected "Inch" in cbx To
                else{
                    result = inputValue * 63360;
                }
                break;
            //user selected "Inch" to convert
            case 2:
                //user selected "Meter" in cbx To
                if(selectedIndexTo == 0){
                    result = inputValue / 39.3701;
                }//user selected "Mile" in cbx To
                else if(selectedIndexTo == 1){
                    result = inputValue / 63360;
                }//user selected "Inch" in cbx To
                else{
                    result = inputValue;
                }
                break;
            default:
                break;
        }
        return result;
    }
    
    
    
}
