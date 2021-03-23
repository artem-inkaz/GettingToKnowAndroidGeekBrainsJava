package com.example.gettingtoknowandroidgeekbrainsjava.lesson6;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import com.example.gettingtoknowandroidgeekbrainsjava.R;

// Эта activity для показа записи в портретной ориентации
public class NotesLandScapeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_land_scape);
//        setContentView(R.layout.activity_navigation_note_book);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Если устройство перевернули в альбомную ориентацию, то надо эту activity закрыть
            finish();
            return;
        }
        if (savedInstanceState == null) {
            // Если эта activity запускается первый раз (с каждым новой записью первый раз),
            // то перенаправим параметр фрагменту
            NotesDetailsFragment detailsFragment = new NotesDetailsFragment();
            detailsFragment.setArguments(getIntent().getExtras());
            // Добавим фрагмент на activity
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container2, detailsFragment)
//                    .replace(R.id.fragment_container, detailsFragment)
                    .commit();
        }
    }
}