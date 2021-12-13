/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.CalculateFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class CalculateControl {

    CalculateFrame cf;
    private final JTextField text;
    private BigDecimal firstNumber, secondNumber;
    private boolean calculating1Opd = false; //check process is calculating % sqrt 1/x +/-
    private boolean calculating2Opd = false; //check process is calculating + - * /
    private boolean isError = false;
    private boolean useMemory = false;
    private String errorMessage = "Error";
    private int operator = -1;

    private BigDecimal memory = new BigDecimal("0");//set memory

    public CalculateControl(CalculateFrame cf) {
        this.cf = cf;
        this.text = cf.getTxtDisplay();
        addActionButton();
        operator = -1;
    }

    //function handle button number
    public void pressNumber(JButton btn) {
        //case press new number
        if (calculating1Opd || calculating2Opd || isError) {
            text.setText("0");
            calculating1Opd = false;
            calculating2Opd = false;
            isError = false;
        }
        useMemory = false;
        String value = btn.getText();
        text.setText(new BigDecimal(text.getText() + value) + "");
    }

    //function handle lbl clear
    public void pressClear() {
        firstNumber = new BigDecimal("0");
        secondNumber = new BigDecimal("0");
        operator = -1;
    }

    //function handle button dot
    public void pressDot() {
        //case press new number, 0 -> 0.
        if (calculating1Opd || calculating2Opd || isError) {
            text.setText("0");
            calculating1Opd = false;
            calculating2Opd = false;
            isError = false;
        }
        //check number have no dot
        if (!text.getText().contains(".")) {
            text.setText(text.getText() + ".");
        }
    }

    public BigDecimal getValue() {
        //case MR have value
        if (useMemory) {
            return memory;
        }
        return new BigDecimal(text.getText());
    }

    //function calculate
    public void calculate() {
        boolean flag = false;
        // case computation between 2 operands
        if (!calculating2Opd) {
            //add first number
            if (operator == -1) {//default opt
                firstNumber = getValue();
            } else {
                secondNumber = getValue();
                switch (operator) {
                    case 1:
                        firstNumber = firstNumber.add(secondNumber).stripTrailingZeros();
                        break;
                    case 2:
                        firstNumber = firstNumber.subtract(secondNumber).stripTrailingZeros();
                        break;
                    case 3:
                        firstNumber = firstNumber.multiply(secondNumber).stripTrailingZeros();
                        break;
                    case 4:
                        //case divide can excute
                        if (secondNumber.doubleValue() != 0) {
                            firstNumber = firstNumber.divide(secondNumber, 16, RoundingMode.CEILING).stripTrailingZeros();
                        } else {
                            flag = true;
                        }
                }
            }
            text.setText(firstNumber + "");
            if (flag) {
                isError = true;
                text.setText(errorMessage);
            }
            calculating1Opd = false;
            calculating2Opd = true;
        }
    }

    //function handle button result
    public void pressResult() {
        //case not error calculating
        if (!isError) {
            calculate();
            operator = -1;
        } else {
            text.setText(firstNumber + "");
        }
    }

    //function handle button negate
    public void pressNegate() {
        pressResult();
        text.setText(getValue().negate() + "");
        calculating2Opd = false;
        isError = false;
        calculating1Opd = true;
    }

    //function handle button sqrt
    public void pressSqrt() {
        pressResult();
        //case calculating can excute
        if (getValue().doubleValue() >= 0) {
            double temp = Math.sqrt(getValue().doubleValue());
            int intTemp = (int) temp;
            text.setText((temp == intTemp) ? (intTemp + "") : (temp + ""));
            calculating2Opd = false;
            isError = false;
        } else {
            isError = true;
            text.setText(errorMessage);
        }
        calculating1Opd = true;
    }

    //function handle button percent
    public void pressPercent() {
        pressResult();
        text.setText(getValue().doubleValue() / 100 + "");
        calculating2Opd = false;
        isError = false;
        calculating1Opd = true;
    }

    //function handle button invert
    public void pressInvert() {
        pressResult();
        //case calculating can excute
        if (getValue().doubleValue() != 0) {
            double temp = 1.0 / getValue().doubleValue();
            int intTemp = (int) temp;
            text.setText((temp == intTemp) ? (intTemp + "") : (temp + ""));
            calculating2Opd = false;
            isError = false;
        } else {
            isError = true;
            text.setText(errorMessage);
        }
        calculating1Opd = true;
    }

    //function handle button MR
    public void pressMR() {
        text.setText(memory + "");
        useMemory = true;
    }

    //function handle button MC
    public void pressMC() {
        memory = new BigDecimal("0");
    }

    //function handle button Madd
    public void pressMAdd() {
        memory = memory.add(getValue()).stripTrailingZeros();
        calculating2Opd = false;
        calculating1Opd = true;
    }

    //function handle button Msub
    public void pressMSub() {
        memory = memory.add(getValue().negate()).stripTrailingZeros();
        calculating2Opd = false;
        calculating1Opd = true;
    }

    //function add action for button
    public void addActionButton() {
        cf.getBtn0().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressNumber(cf.getBtn0());
            }
        });
        cf.getBtn1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressNumber(cf.getBtn1());
            }
        });
        cf.getBtn2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressNumber(cf.getBtn2());
            }
        });
        cf.getBtn3().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressNumber(cf.getBtn3());
            }
        });
        cf.getBtn4().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressNumber(cf.getBtn4());
            }
        });
        cf.getBtn5().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressNumber(cf.getBtn5());
            }
        });
        cf.getBtn6().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressNumber(cf.getBtn6());
            }
        });
        cf.getBtn7().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressNumber(cf.getBtn7());
            }
        });
        cf.getBtn8().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressNumber(cf.getBtn8());
            }
        });
        cf.getBtn9().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressNumber(cf.getBtn9());
            }
        });
        cf.getLblClear().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cf.getTxtDisplay().setText("0");
                pressClear();
            }
        });
        cf.getBtnDot().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressDot();
            }
        });
        cf.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
                operator = 1;
            }
        });
        cf.getBtnSub().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
                operator = 2;
            }
        });
        cf.getBtnMul().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
                operator = 3;
            }
        });
        cf.getBtnDiv().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
                operator = 4;
            }
        });
        cf.getBtnResult().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressResult();
            }
        });
        cf.getBtnNegate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressNegate();
            }
        });
        cf.getBtnSqrt().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressSqrt();
            }
        });
        cf.getBtnPercent().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressPercent();
            }
        });
        cf.getBtnInvert().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressInvert();
            }
        });
        cf.getBtnMR().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressMR();
            }
        });
        cf.getBtnMC().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressMC();
                cf.getBtnMR().setBackground(Color.orange);
            }
        });
        cf.getBtnMAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressMAdd();
                cf.getBtnMR().setBackground(new Color(255, 102, 102));
            }
        });
        cf.getBtnMSub().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressMSub();
                cf.getBtnMR().setBackground(new Color(255, 102, 102));
            }
        });
    }

}
