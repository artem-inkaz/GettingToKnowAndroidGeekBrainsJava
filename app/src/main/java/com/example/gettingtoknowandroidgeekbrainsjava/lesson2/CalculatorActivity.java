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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class CalculatorActivity extends AppCompatActivity {

  private   TextView resultField; // текстовое поле для вывода результата
  private TextInputLayout mTextInputLayout;
  private TextInputEditText numberField;   // поле для ввода числа
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

        initButtonClickListener(R.id.button_0);
        initButtonClickListener(R.id.button_1);
        initButtonClickListener(R.id.button_2);
        initButtonClickListener(R.id.button_3);
        initButtonClickListener(R.id.button_4);
        initButtonClickListener(R.id.button_5);
        initButtonClickListener(R.id.button_6);
        initButtonClickListener(R.id.button_7);
        initButtonClickListener(R.id.button_8);
        initButtonClickListener(R.id.button_9);
        initButtonClickListener(R.id.button_dot);

        initButtonAddClickListener();
        initButtonEqualClickListener();
        initButtonSubClickListener();
        initButtonDivClickListener();
        initButtonMultClickListener();
    }

    public void initButtonClickListener(int id) {
        Button btn = findViewById(id);
        btn.setOnClickListener(v -> {
            numberField.append(btn.getText());
            subStr += btn.getText();
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