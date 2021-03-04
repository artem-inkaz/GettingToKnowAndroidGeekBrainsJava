package com.example.gettingtoknowandroidgeekbrainsjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.gettingtoknowandroidgeekbrainsjava.lesson1.MainActivity1;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson2.CalculatorActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonLesson1 = findViewById(R.id.button_lesson_1);
        Button buttonLesson2 = findViewById(R.id.button_lesson_2);
//        Button buttonLesson3 = findViewById(R.id.button_lesson_3);
//        Button buttonLesson4 = findViewById(R.id.button_lesson_4);
//        Button buttonLesson5 = findViewById(R.id.button_lesson_5);
//        Button buttonLesson6 = findViewById(R.id.button_lesson_6);
//        Button buttonLesson7 = findViewById(R.id.button_lesson_7);
//        Button buttonLesson8 = findViewById(R.id.button_lesson_8);
//        Button buttonLesson9 = findViewById(R.id.button_lesson_9);
//        Button buttonLesson10 = findViewById(R.id.button_lesson_10);
//        Button buttonLesson11 = findViewById(R.id.button_lesson_11);

        buttonLesson1.setOnClickListener(v -> {
//            try {
                launchMainActivity1();
//            } catch (Exception e) {
//                Log.d("TAG", "Error Activity");
//            }
        });
        buttonLesson2.setOnClickListener(v -> {
//            try {
                launchCalculatorActivity();
//            } catch (Exception e) {
//                Log.d("TAG", "Error Activity");
//            }
        });
    }

    private void launchMainActivity1() {
        Intent intent = new Intent(MainActivity.this, MainActivity1.class);
        startActivity(intent);
    }

    private void launchCalculatorActivity(){
        Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
        startActivity(intent);
    }
}