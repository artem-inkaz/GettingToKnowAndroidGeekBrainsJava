package com.example.gettingtoknowandroidgeekbrainsjava.lesson2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gettingtoknowandroidgeekbrainsjava.R;
import java.util.ArrayList;
import java.util.List;

public class CalculatorActivity extends AppCompatActivity {

    TextView resultField; // текстовое поле для вывода результата
    EditText numberField;   // поле для ввода числа
    String operationField;    // Переменная содержит знак операции
    Double operand = 0.0;  // Результат число
    String number;
    String subStr= "";
    String lastOperation = "="; // последняя операция "=" знак равно
    Boolean add, sub, div, mul; // Операции: + - / *
    List<String> al = new ArrayList<>();
    List<Double> alresult = new ArrayList<>();
 //   private Object ArrayList;

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
        btn1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                numberField.append(btn1.getText());
//                al.add((String) btn1.getText());
                if ( operand != null) {
                      subStr += btn1.getText();
//                    operand += Double.parseDouble(subStr);
//                    resultField.setText(operand.toString());
                   resultField.setText(subStr.toString());
                }
            }
        }
        );
    }

    public void initButton2ClickListener() {
        Button btn2 = findViewById(R.id.button_2);
        btn2.setOnClickListener(v -> {
            numberField.append(btn2.getText());
            subStr += btn2.getText();
            if ( operand != null) {
//          al.add((String) btn2.getText());
//          resultField.setText(subStr);
                resultField.setText(subStr.toString());
            }
                }
        );
        number = numberField.getText().toString();
    }
    public void initButton3ClickListener() {
        Button btn3 = findViewById(R.id.button_3);
        btn3.setOnClickListener(v -> {
                    numberField.append(btn3.getText());
                    subStr += btn3.getText();
                    if ( operand != null) {
                        resultField.setText(subStr.toString());
                    }
                }
        );
        number = numberField.getText().toString();
    }
    public void initButton4ClickListener() {
        Button btn4 = findViewById(R.id.button_4);
        btn4.setOnClickListener(v -> {
                    numberField.append(btn4.getText());
                    subStr += btn4.getText();
                    if ( operand != null) {
                        resultField.setText(subStr.toString());
                    }
                }
        );
        number = numberField.getText().toString();
    }
    public void initButton5ClickListener() {
        Button btn5 = findViewById(R.id.button_5);
        btn5.setOnClickListener(v -> {
                    numberField.append(btn5.getText());
                    subStr += btn5.getText();
                    if ( operand != null) {
                        resultField.setText(subStr.toString());
                    }
                }
        );
        number = numberField.getText().toString();
    }
    public void initButton6ClickListener() {
        Button btn6 = findViewById(R.id.button_6);
        btn6.setOnClickListener(v -> {
                    numberField.append(btn6.getText());
                    subStr += btn6.getText();
                    if ( operand != null) {
                        resultField.setText(subStr.toString());
                    }
                }
        );
        number = numberField.getText().toString();
    }
    public void initButton7ClickListener() {
        Button btn7 = findViewById(R.id.button_7);
        btn7.setOnClickListener(v -> {
                    numberField.append(btn7.getText());
                    subStr += btn7.getText();
                    if ( operand != null) {
                        resultField.setText(subStr.toString());
                    }
                }
        );
        number = numberField.getText().toString();
    }
    public void initButton8ClickListener() {
        Button btn8 = findViewById(R.id.button_8);
        btn8.setOnClickListener(v -> {
                    numberField.append(btn8.getText());
                    subStr += btn8.getText();
                    if ( operand != null) {
                        resultField.setText(subStr.toString());
                    }
                }
        );
        number = numberField.getText().toString();
    }
    public void initButton9ClickListener() {
        Button btn9 = findViewById(R.id.button_9);
        btn9.setOnClickListener(v -> {
                    numberField.append(btn9.getText());
                    subStr += btn9.getText();
                    if ( operand != null) {
                        resultField.setText(subStr.toString());
                    }
                }
        );
        number = numberField.getText().toString();
    }
    public void initButton0ClickListener() {
        Button btn0 = findViewById(R.id.button_0);
        btn0.setOnClickListener(v -> {
                    numberField.append(btn0.getText());
                    subStr += btn0.getText();
                    if ( operand != null) {
                        resultField.setText(subStr.toString());
                    }
                }
        );
        number = numberField.getText().toString();
    }
    public void initButtonDotClickListener() {
        Button btnDot = findViewById(R.id.button_dot);
        btnDot.setOnClickListener(v -> {
                    numberField.append(btnDot.getText());
                    subStr += btnDot.getText();
                    if ( operand != null) {
                        resultField.setText(subStr.toString());
                    }
                }
        );
        number = numberField.getText().toString();
    }

    @SuppressLint("SetTextI18n")
    public void initButtonAddClickListener(){
        Button btnAdd = findViewById(R.id.button_add);
//        number = numberField.getText().toString();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastOperation = "+";
                numberField.append(btnAdd.getText());
                al.add(subStr);
                al.add(lastOperation);
                subStr = "";
            }
        });
    }
    @SuppressLint("SetTextI18n")
    public void initButtonSubClickListener(){
        Button btnSub = findViewById(R.id.button_sub);
//        number = numberField.getText().toString();
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastOperation = "-";
                numberField.append(btnSub.getText());
                al.add(subStr);
                al.add(lastOperation);
                subStr = "";
            }
        });
    }
    @SuppressLint("SetTextI18n")
    public void initButtonDivClickListener(){
        Button btnDiv = findViewById(R.id.button_div);
//        number = numberField.getText().toString();
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastOperation = "/";
                numberField.append(btnDiv.getText());
                al.add(subStr);
                al.add(lastOperation);
                subStr = "";
            }
        });
    }
    @SuppressLint("SetTextI18n")
    public void initButtonMultClickListener(){
        Button btnMult = findViewById(R.id.button_multi);
//        number = numberField.getText().toString();
        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastOperation = "*";
                numberField.append(btnMult.getText());
                al.add(subStr);
                al.add(lastOperation);
                subStr = "";
            }
        });
    }

    private void initButtonEqualClickListener() {
        Button btnEqual = findViewById(R.id.button_equal);
        final String[] temp = new String[1];
        final Double[] temp2 = {0.0};
//        al.add(subStr);
        btnEqual.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            al.add(subStr);
                                            for (int i = 0; i < al.size(); i++) {
                                                temp[0] = al.get(i);
                                                if (al.get(i).equals("+") && temp2[0] == 0.0) {
                                                   temp2[0] = Double.parseDouble(al.get(i-1)) + Double.parseDouble(al.get(i+1));
                                                } else if (al.get(i).equals("+") && temp2[0] != 0.0){
                                                    if (al.get(i+2).equals("+") || al.get(i+2).equals("-")) {
                                                        temp2[0] = temp2[0] + Double.parseDouble(al.get(i + 1));
                                                    }
                                                }
                                                if (al.get(i).equals("-") && temp2[0] == 0.0) {
                                                    temp2[0] = Double.parseDouble(al.get(i-1)) - Double.parseDouble(al.get(i+1));
                                                } else if (al.get(i).equals("-") && temp2[0] != 0.0){
                                                    if (al.get(i+2).equals("+") || al.get(i+2).equals("-")) {
                                                        temp2[0] = temp2[0] - Double.parseDouble(al.get(i + 1));
                                                    }
                                                }
                                                if (al.get(i).equals("*") && temp2[0] == 0.0) {
                                                    temp2[0] = Double.parseDouble(al.get(i-1)) * Double.parseDouble(al.get(i+1));
                                                } else if (al.get(i).equals("*") && temp2[0] != 0.0 && al.get(i-2).equals("+") ){
                                                    temp2[0] = temp2[0] + (Double.parseDouble(al.get(i-1)) * Double.parseDouble(al.get(i+1)));
                                                } else if (al.get(i).equals("*") && temp2[0] != 0.0 && al.get(i-2).equals("-") ){
                                                    temp2[0] = temp2[0] - (Double.parseDouble(al.get(i-1)) * Double.parseDouble(al.get(i+1)));
                                                }
                                                if (al.get(i).equals("/") && temp2[0] == 0.0) {
                                                    temp2[0] = Double.parseDouble(al.get(i-1)) / Double.parseDouble(al.get(i+1));
                                                } else if (al.get(i).equals("/") && temp2[0] != 0.0 && al.get(i-2).equals("+") ){
                                                    temp2[0] = temp2[0] + (Double.parseDouble(al.get(i-1)) * Double.parseDouble(al.get(i+1)));
                                                } else if (al.get(i).equals("/") && temp2[0] != 0.0 && al.get(i-2).equals("-") ){
                                                    temp2[0] = temp2[0] - (Double.parseDouble(al.get(i-1)) * Double.parseDouble(al.get(i+1)));
                                                }
                                            }
                                            resultField.setText(temp2[0].toString());
                                        }
                                    }
        );

//        resultField.setText(temp2[0].toString());
    }
    // обработка нажатия на числовую кнопку
    public void onNummerClick(View view) {
        // получение значение в поле android: text
//        Button button = (Button) view;
//        numberField.append(button.getText());
        // если последняя операция представляла собой получение результата (знак "равно"),
        // то мы сбрасываем переменную operand
        if (lastOperation.equals("=") && operand != null) {
            operand = null;
        }
    }
    // обработка нажатия на кнопку операции: + - / *
    public void onOperationClick(View view) {
        Button button = (Button) view;
        String op = button.getText().toString();
        String number = numberField.getText().toString();
        // если введенно что-нибудь
        if (number.length() > 0) {
            number = number.replace(',', '.');
            try {
                performOperation(Double.valueOf(number), op);
            } catch (NumberFormatException ex) {
                numberField.setText("");
            }
        }
        lastOperation = op;
        operationField = lastOperation;
    }

    private void performOperation(Double number, String operation) {

        // если операнд ранее не был установлен (при вводе самой первой операции)
        if (operand == null) {
            operand = number;
        } else {
            if (lastOperation.equals("=")) {
                lastOperation = operation;
            }
            switch (lastOperation) {
                case "=":
                    operand = number;
                    break;
                case "/":
                    if (number == 0) {
                        operand = 0.0;
                    } else {
                        operand /= number;
                    }
                    break;
                case "1/x":
                    if (number == 0) {
                        operand = 0.0;
                    } else {
                        operand = 1 / number;
                    }
                    break;
//                case "%":
//                    if (number == 0) {
//                        operand = 0.0;
//                    } else {
//                        operand =(number*100);
//                    }
//                    break;
                case "*":
                    operand *= number;
                    break;
                case "+":
                    operand += number;
                    break;
                case "-":
                    operand -= number;
                    break;
            }
        }
        resultField.setText(operand.toString().replace('.', ','));
        numberField.setText("");
    }

}