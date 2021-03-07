package com.example.gettingtoknowandroidgeekbrainsjava.lesson3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gettingtoknowandroidgeekbrainsjava.R;

import java.util.ArrayList;
import java.util.List;

public class CalculatorActivity2 extends AppCompatActivity {

    TextView resultField; // текстовое поле для вывода результата
    EditText numberField;   // поле для ввода числа
    List<String> al = new ArrayList<>();
    private final static String KEY_CALCULATOR = "CALCULATOR";
    private final static String KEY_RESULT_FIELD = "RESULT_FIELD";
    private final static String KEY_NUMBER_FIELD = "NUMBER_FIELD";

    Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        calculator = new Calculator();
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(KEY_CALCULATOR, calculator);
        outState.putString(KEY_RESULT_FIELD, String.valueOf(resultField.getText().toString()));
        outState.putString(KEY_NUMBER_FIELD,String.valueOf(numberField.getText().toString()) );
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculator = (Calculator) savedInstanceState.getSerializable(KEY_CALCULATOR);
        numberField.setText(savedInstanceState.getString(KEY_NUMBER_FIELD));
        resultField.setText(savedInstanceState.getString(KEY_RESULT_FIELD));

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
            if (calculator.getOperand() != null) {
                calculator.incrementSubStr((String) btn1.getText());
                resultField.setText(calculator.getSubStr());
            }
        });
    }

    public void initButton2ClickListener() {
        Button btn2 = findViewById(R.id.button_2);
        btn2.setOnClickListener(v -> {
                    numberField.append(btn2.getText());
                    calculator.incrementSubStr((String) btn2.getText());
                    if (calculator.getOperand() != null) {
                        resultField.setText(calculator.getSubStr());
                    }
                }
        );
    }

    public void initButton3ClickListener() {
        Button btn3 = findViewById(R.id.button_3);
        btn3.setOnClickListener(v -> {
                    numberField.append(btn3.getText());
                    calculator.incrementSubStr((String) btn3.getText());
                    if (calculator.getOperand() != null) {
                        resultField.setText(calculator.getSubStr());
                    }
                }
        );
    }

    public void initButton4ClickListener() {
        Button btn4 = findViewById(R.id.button_4);
        btn4.setOnClickListener(v -> {
            numberField.append(btn4.getText());
            calculator.incrementSubStr((String) btn4.getText());
            if (calculator.getOperand() != null) {
                resultField.setText(calculator.getSubStr());
            }
        });
    }

    public void initButton5ClickListener() {
        Button btn5 = findViewById(R.id.button_5);
        btn5.setOnClickListener(v -> {
            numberField.append(btn5.getText());
            calculator.incrementSubStr((String) btn5.getText());
            if (calculator.getOperand() != null) {
                resultField.setText(calculator.getSubStr());
            }
        });
    }

    public void initButton6ClickListener() {
        Button btn6 = findViewById(R.id.button_6);
        btn6.setOnClickListener(v -> {
            numberField.append(btn6.getText());
            calculator.incrementSubStr((String) btn6.getText());
            if (calculator.getOperand() != null) {
                resultField.setText(calculator.getSubStr());
            }
        });
    }

    public void initButton7ClickListener() {
        Button btn7 = findViewById(R.id.button_7);
        btn7.setOnClickListener(v -> {
            numberField.append(btn7.getText());
            calculator.incrementSubStr((String) btn7.getText());
            if (calculator.getOperand() != null) {
                resultField.setText(calculator.getSubStr());
            }
        });
    }

    public void initButton8ClickListener() {
        Button btn8 = findViewById(R.id.button_8);
        btn8.setOnClickListener(v -> {
            numberField.append(btn8.getText());
            calculator.incrementSubStr((String) btn8.getText());
            if (calculator.getOperand() != null) {
                resultField.setText(calculator.getSubStr());
            }
        });
    }

    public void initButton9ClickListener() {
        Button btn9 = findViewById(R.id.button_9);
        btn9.setOnClickListener(v -> {
            numberField.append(btn9.getText());
            calculator.incrementSubStr((String) btn9.getText());
            if (calculator.getOperand() != null) {
                resultField.setText(calculator.getSubStr());
            }
        });
    }

    public void initButton0ClickListener() {
        Button btn0 = findViewById(R.id.button_0);
        btn0.setOnClickListener(v -> {
            numberField.append(btn0.getText());
            calculator.incrementSubStr((String) btn0.getText());
            if (calculator.getOperand() != null) {
                resultField.setText(calculator.getSubStr());
            }
        });
    }

    public void initButtonDotClickListener() {
        Button btnDot = findViewById(R.id.button_dot);
        btnDot.setOnClickListener(v -> {
                    numberField.append(btnDot.getText());
                    calculator.incrementSubStr((String) btnDot.getText());
                    if (calculator.getOperand() != null) {
                        resultField.setText(calculator.getSubStr());
                    }
                }
        );
    }

    public void initButtonAddClickListener() {
        Button btnAdd = findViewById(R.id.button_add);
        btnAdd.setOnClickListener(v -> {
            numberField.append(btnAdd.getText());
//                lastOperation = "+";
//                calculator.setLastOperationAdd();
//                al.add(calculator.getSubStr());
//                al.add(calculator.setLastOperationAdd());
//                calculator.incrementEmptySubStr();
//            if (calculator.getOperand() != null) {
               calculator.operationAdd(calculator.getSubStr());
//            }
//            resultField.setText(calculator.getSubStr());

        });
    }

    @SuppressLint("SetTextI18n")
    public void initButtonSubClickListener() {
        Button btnSub = findViewById(R.id.button_sub);
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberField.append(btnSub.getText());
                calculator.operationSub(calculator.getSubStr());
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void initButtonDivClickListener() {
        Button btnDiv = findViewById(R.id.button_div);
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberField.append(btnDiv.getText());
                calculator.operationDiv(calculator.getSubStr());
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void initButtonMultClickListener() {
        Button btnMult = findViewById(R.id.button_multi);
        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberField.append(btnMult.getText());
                calculator.operationMul(calculator.getSubStr());
            }
        });
    }

    private void initButtonEqualClickListener() {
        Button btnEqual = findViewById(R.id.button_equal);
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultField.setText(calculator.magicCalc().toString());
             }
        });
    }
}