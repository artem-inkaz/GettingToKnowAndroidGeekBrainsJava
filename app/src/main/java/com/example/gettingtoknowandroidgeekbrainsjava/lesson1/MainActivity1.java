package com.example.gettingtoknowandroidgeekbrainsjava.lesson1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gettingtoknowandroidgeekbrainsjava.R;

public class MainActivity1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lesson_1);
        Button buttonActivity = findViewById(R.id.button_activity2);
        buttonActivity.setOnClickListener(v -> {
            try {
                MainActivity2();
            } catch (Exception e) {
                Log.d("TAG", "Error Activity");
            }
        });
    }

    private void MainActivity2() {
        Intent intent = new Intent(MainActivity1.this, MainActivity2.class);
        startActivity(intent);
    }
}