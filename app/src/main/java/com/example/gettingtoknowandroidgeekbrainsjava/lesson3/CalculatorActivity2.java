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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class CalculatorActivity2 extends AppCompatActivity {

    private TextView resultField; // текстовое поле для вывода результата
    private TextInputLayout mTextInputLayout;
    private EditText numberField;   // поле для ввода числа
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
        initButtonOnClickListener();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        outState.putSerializable(KEY_CALCULATOR, calculator);
        outState.putParcelable(KEY_CALCULATOR, calculator);
        outState.putString(KEY_RESULT_FIELD, String.valueOf(resultField.getText().toString()));
        outState.putString(KEY_NUMBER_FIELD, String.valueOf(numberField.getText().toString()));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        calculator = (Calculator) savedInstanceState.getSerializable(KEY_CALCULATOR);
        calculator = (Calculator) savedInstanceState.getParcelable(KEY_CALCULATOR);
        numberField.setText(savedInstanceState.getString(KEY_NUMBER_FIELD));
        resultField.setText(savedInstanceState.getString(KEY_RESULT_FIELD));

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
        initButtonOnClickListener();

    }

    public void initButtonClickListener(int id) {
        Button btn = findViewById(id);
        btn.setOnClickListener(v -> {
            numberField.append(btn.getText());
            calculator.incrementSubStr((String) btn.getText());
            if (calculator.getOperand() != null) {
                resultField.setText(calculator.getSubStr());
            }
        });
    }

    public void initButtonAddClickListener() {
        Button btnAdd = findViewById(R.id.button_add);
        btnAdd.setOnClickListener(v -> {
            numberField.append(btnAdd.getText());
            calculator.operationAdd(calculator.getSubStr());
        });
    }

    public void initButtonOnClickListener() {
        Button btnClear = findViewById(R.id.button_clear);
        btnClear.setOnClickListener(v -> {
            numberField.setText("");
            calculator.operationOn();
            resultField.setText("");
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