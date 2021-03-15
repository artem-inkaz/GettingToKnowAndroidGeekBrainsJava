package com.example.gettingtoknowandroidgeekbrainsjava.lesson3;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//public class Calculator implements Serializable {
public class Calculator implements Parcelable {
    Double operand = 0.0;  // Результат число
    String subStr = "";
    String lastOperation = "="; // последняя операция "=" знак равно
    private List<String> al = new ArrayList<>();
    String[] temp = new String[1];
    final Double[] temp2 = {0.0};
    private int flag = 0;

    public Calculator() {

    }

    public void setAl(List<String> al) {
        this.al = al;
    }

    protected Calculator(Parcel in) {
        if (in.readByte() == 0) {
            operand = null;
        } else {
            operand = in.readDouble();
        }
        subStr = in.readString();
        lastOperation = in.readString();
        al = in.createStringArrayList();
        temp = in.createStringArray();
    }

    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
        @Override
        public Calculator createFromParcel(Parcel in) {
            return new Calculator(in);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };

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

    public void operationOn(){
        al.clear();
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
    public void operationRecieveDadta(String formula) {
        flag = 1;
        String[] res = new String[formula.length()];
        for (int i = 0; i < formula.length(); i++) {
            res[i] = Character.toString(formula.charAt(i));
            al.add(res[i]);
        }

    }

    public Double magicCalc() {
        if (flag != 1) {
            al.add(subStr);
        }
        for (int i = 0; i < al.size(); i++) {
            temp[0] = al.get(i);
            if (al.get(i).equals("+") && temp2[0] == 0.0) {
                temp2[0] = Double.parseDouble(al.get(i - 1)) + Double.parseDouble(al.get(i + 1));
            } else if (al.get(i).equals("+") && temp2[0] != 0.0) {
                if (al.get(i + 1).equals("+") || al.get(i + 1).equals("-")) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (operand == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(operand);
        }
        dest.writeString(subStr);
        dest.writeString(lastOperation);
        dest.writeStringList(al);
        dest.writeStringArray(temp);
    }
}
