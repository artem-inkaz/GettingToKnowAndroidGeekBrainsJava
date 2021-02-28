package com.example.gettingtoknowandroidgeekbrainsjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button buttonActivity = findViewById(R.id.button_activity2);


       buttonActivity.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               try {
                   MainActivity2();
               }catch (Exception e) {
                   Log.d("TAG","Error Activity");

               }
           }
       });
    }

    private void MainActivity2(){
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }
}