package com.example.gettingtoknowandroidgeekbrainsjava.lesson2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gettingtoknowandroidgeekbrainsjava.R;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson3.Calculator;

import java.util.ArrayList;
import java.util.List;

public class CalculatorActivity extends AppCompatActivity {

    TextView resultField; // текстовое поле для вывода результата
    EditText numberField;   // поле для ввода числа
    String operationField;    // Переменная содержит знак операции
    Double operand = 0.0;  // Результат число
    String subStr = "";
    String lastOperation = "="; // последняя операция "=" знак равно
    private List<String> al = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        resultField = findViewById(R.id.textView_result);
        numberField = findViewById(R.id.edit_text_input);
        initButton1ClickListener();
        initButtonAddClickListener();
        initButton2ClickListener();
        initButtonEqualClickListener();
        initButtonSubClickListener();
        initButtonDivClickListener();
        initButtonMultClickListener();
        initButton3ClickListener();
        initButton4ClickListener();
        initButton5ClickListener();
        initButton6ClickListener();
        initButton7ClickListener();
        initButton8ClickListener();
        initButton9ClickListener();
        initButton0ClickListener();
        initButtonDotClickListener();

    }

    public void initButton1ClickListener() {
        Button btn1 = findViewById(R.id.button_1);
        btn1.setOnClickListener(v -> {
            numberField.append(btn1.getText());
            if (operand != null) {
                subStr += btn1.getText();
//                    operand += Double.parseDouble(subStr);
//                    resultField.setText(operand.toString());
                resultField.setText(subStr.toString());
            }
        });
    }

    public void initButton2ClickListener() {
        Button btn2 = findViewById(R.id.button_2);
        btn2.setOnClickListener(v -> {
            numberField.append(btn2.getText());
            subStr += btn2.getText();
            if (operand != null) {
                resultField.setText(subStr.toString());
            }
        });
    }

    public void initButton3ClickListener() {
        Button btn3 = findViewById(R.id.button_3);
        btn3.setOnClickListener(v -> {
            numberField.append(btn3.getText());
            subStr += btn3.getText();
            if (operand != null) {
                resultField.setText(subStr.toString());
            }
        });
    }

    public void initButton4ClickListener() {
        Button btn4 = findViewById(R.id.button_4);
        btn4.setOnClickListener(v -> {
            numberField.append(btn4.getText());
            subStr += btn4.getText();
            if (operand != null) {
                resultField.setText(subStr.toString());
            }
        });
    }

    public void initButton5ClickListener() {
        Button btn5 = findViewById(R.id.button_5);
        btn5.setOnClickListener(v -> {
            numberField.append(btn5.getText());
            subStr += btn5.getText();
            if (operand != null) {
                resultField.setText(subStr.toString());
            }
        });
    }

    public void initButton6ClickListener() {
        Button btn6 = findViewById(R.id.button_6);
        btn6.setOnClickListener(v -> {
            numberField.append(btn6.getText());
            subStr += btn6.getText();
            if (operand != null) {
                resultField.setText(subStr.toString());
            }
        });
    }

    public void initButton7ClickListener() {
        Button btn7 = findViewById(R.id.button_7);
        btn7.setOnClickListener(v -> {
            numberField.append(btn7.getText());
            subStr += btn7.getText();
            if (operand != null) {
                resultField.setText(subStr.toString());
            }
        });
    }

    public void initButton8ClickListener() {
        Button btn8 = findViewById(R.id.button_8);
        btn8.setOnClickListener(v -> {
            numberField.append(btn8.getText());
            subStr += btn8.getText();
            if (operand != null) {
                resultField.setText(subStr.toString());
            }
        });
    }

    public void initButton9ClickListener() {
        Button btn9 = findViewById(R.id.button_9);
        btn9.setOnClickListener(v -> {
            numberField.append(btn9.getText());
            subStr += btn9.getText();
            if (operand != null) {
                resultField.setText(subStr.toString());
            }
        });
    }

    public void initButton0ClickListener() {
        Button btn0 = findViewById(R.id.button_0);
        btn0.setOnClickListener(v -> {
            numberField.append(btn0.getText());
            subStr += btn0.getText();
            if (operand != null) {
                resultField.setText(subStr.toString());
            }
        });
    }

    public void initButtonDotClickListener() {
        Button btnDot = findViewById(R.id.button_dot);
        btnDot.setOnClickListener(v -> {
            numberField.append(btnDot.getText());
            subStr += btnDot.getText();
            if (operand != null) {
                resultField.setText(subStr.toString());
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void initButtonAddClickListener() {
        Button btnAdd = findViewById(R.id.button_add);
        btnAdd.setOnClickListener(v -> {
            lastOperation = "+";
            numberField.append(btnAdd.getText());
            al.add(subStr);
            al.add(lastOperation);
            subStr = "";
        });
    }

    @SuppressLint("SetTextI18n")
    public void initButtonSubClickListener() {
        Button btnSub = findViewById(R.id.button_sub);
        btnSub.setOnClickListener(v -> {
            lastOperation = "-";
            numberField.append(btnSub.getText());
            al.add(subStr);
            al.add(lastOperation);
            subStr = "";
        });
    }

    @SuppressLint("SetTextI18n")
    public void initButtonDivClickListener() {
        Button btnDiv = findViewById(R.id.button_div);
        btnDiv.setOnClickListener(v -> {
            lastOperation = "/";
            numberField.append(btnDiv.getText());
            al.add(subStr);
            al.add(lastOperation);
            subStr = "";
        });
    }

    @SuppressLint("SetTextI18n")
    public void initButtonMultClickListener() {
        Button btnMult = findViewById(R.id.button_multi);
        btnMult.setOnClickListener(v -> {
            lastOperation = "*";
            numberField.append(btnMult.getText());
            al.add(subStr);
            al.add(lastOperation);
            subStr = "";
        });
    }

    private void initButtonEqualClickListener() {
        Button btnEqual = findViewById(R.id.button_equal);
        final String[] temp = new String[1];
        final Double[] temp2 = {0.0};
        btnEqual.setOnClickListener(v -> {
                    al.add(subStr);
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
                    resultField.setText(temp2[0].toString());
                }
        );
    }
}