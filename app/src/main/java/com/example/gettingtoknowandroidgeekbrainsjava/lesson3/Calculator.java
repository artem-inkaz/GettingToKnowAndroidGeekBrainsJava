package com.example.gettingtoknowandroidgeekbrainsjava.lesson3;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    Double operand = 0.0;  // Результат число
    String subStr = "";
    String lastOperation = "="; // последняя операция "=" знак равно
    private List<String> al = new ArrayList<>();
    final String[] temp = new String[1];
    final Double[] temp2 = {0.0};


    public Double getOperand() {
        return operand;
    }

    public void incrementSubStr(String btn1) {
        subStr += btn1;
    }

    public String incrementEmptySubStr() {
        return subStr = "";
    }

    public String getSubStr() {
        return subStr.toString();
    }

    public String setLastOperationAdd() {
        return lastOperation = "+";
    }

    public String setLastOperationSub() {
        return lastOperation = "-";
    }

    public String setLastOperationDiv() {
        return lastOperation = "/";
    }

    public String setLastOperationMul() {
        return lastOperation = "*";
    }

    public void operationAdd(String subStrAdd) {
        lastOperation = "+";
        al.add(subStrAdd);
        al.add(lastOperation);
        subStr = "";
    }

    public void operationSub(String subStrSub) {
        lastOperation = "-";
        al.add(subStrSub);
        al.add(lastOperation);
        subStr = "";
    }

    public void operationDiv(String subStrDiv) {
        lastOperation = "/";
        al.add(subStrDiv.toString());
        al.add(lastOperation);
        subStr = "";
    }

    public void operationMul(String subStrMul) {
        lastOperation = "*";
        al.add(subStrMul.toString());
        al.add(lastOperation);
        subStr = "";
    }

    public Double magicCalc() {
        al.add(subStr.toString());
        for (int i = 0; i < al.size(); i++) {
            temp[0] = al.get(i);
            if (al.get(i).equals("+") && temp2[0] == 0.0) {
                temp2[0] = Double.parseDouble(al.get(i - 1)) + Double.parseDouble(al.get(i + 1));
            } else if (al.get(i).equals("+") && temp2[0] != 0.0) {
                if (al.get(i + 2).equals("+") || al.get(i + 2).equals("-")) {
                    temp2[0] = temp2[0] + Double.parseDouble(al.get(i + 1));
                }
            }
            if (al.get(i).equals("-") && temp2[0] == 0.0) {
                temp2[0] = Double.parseDouble(al.get(i - 1)) - Double.parseDouble(al.get(i + 1));
            } else if (al.get(i).equals("-") && temp2[0] != 0.0) {
                if (al.get(i + 2).equals("+") || al.get(i + 2).equals("-")) {
                    temp2[0] = temp2[0] - Double.parseDouble(al.get(i + 1));
                }
            }
            if (al.get(i).equals("*") && temp2[0] == 0.0) {
                temp2[0] = Double.parseDouble(al.get(i - 1)) * Double.parseDouble(al.get(i + 1));
            } else if (al.get(i).equals("*") && temp2[0] != 0.0 && al.get(i - 2).equals("+")) {
                temp2[0] = temp2[0] + (Double.parseDouble(al.get(i - 1)) * Double.parseDouble(al.get(i + 1)));
            } else if (al.get(i).equals("*") && temp2[0] != 0.0 && al.get(i - 2).equals("-")) {
                temp2[0] = temp2[0] - (Double.parseDouble(al.get(i - 1)) * Double.parseDouble(al.get(i + 1)));
            }
            if (al.get(i).equals("/") && temp2[0] == 0.0) {
                temp2[0] = Double.parseDouble(al.get(i - 1)) / Double.parseDouble(al.get(i + 1));
            } else if (al.get(i).equals("/") && temp2[0] != 0.0 && al.get(i - 2).equals("+")) {
                temp2[0] = temp2[0] + (Double.parseDouble(al.get(i - 1)) * Double.parseDouble(al.get(i + 1)));
            } else if (al.get(i).equals("/") && temp2[0] != 0.0 && al.get(i - 2).equals("-")) {
                temp2[0] = temp2[0] - (Double.parseDouble(al.get(i - 1)) * Double.parseDouble(al.get(i + 1)));
            }
        }
        return temp2[0];
    }
}
