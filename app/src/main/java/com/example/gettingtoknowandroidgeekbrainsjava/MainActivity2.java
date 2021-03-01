package com.example.gettingtoknowandroidgeekbrainsjava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_digits);
        EditText numberOneEditText = findViewById(R.id.number_one_edit_text);
        EditText numberTwoEditText = findViewById(R.id.number_two_edit_text);
        Button buttonCompare = findViewById(R.id.button_compare);
        Button buttonActivity = findViewById(R.id.button_activity3);
        TextView resultText = findViewById(R.id.result_text);
        buttonCompare.setOnClickListener(v -> {
            try {
                Integer valueOne = Integer.valueOf(numberOneEditText.getText().toString());
                Integer valueTwo = Integer.valueOf(numberTwoEditText.getText().toString());
                if (valueOne.equals(valueTwo)) {
                    resultText.setText("Equals");
                } else {
                    resultText.setText(" Not Equals!");
                }
            } catch (NumberFormatException e) {
                Log.d("TAG", "Incorrect enter");
                resultText.setText(" Please enter right number!");
            }
        });
        buttonActivity.setOnClickListener(v -> {
            try {
                MainActivity3();
            } catch (Exception e) {
                Log.d("TAG", "error Activity");
            }
        });
    }

    private void MainActivity3() {
        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        startActivity(intent);
    }
}