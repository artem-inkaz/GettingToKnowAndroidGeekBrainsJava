package com.example.gettingtoknowandroidgeekbrainsjava.lesson6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.gettingtoknowandroidgeekbrainsjava.R;

public class NoteBookActivity extends AppCompatActivity implements NotesFragment.OnNotesSelected {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_book);
    }

    @Override
    public void onNotesSelected(Notes notes) {
        // Создаём новый фрагмент с текущей позицией для вывода записи
        NotesDetailsFragment notesDetailsFragment = NotesDetailsFragment.newInstance(notes);
        // Выполняем транзакцию по замене фрагмента
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.notes_details, notesDetailsFragment); // замена фрагмента
//        fragmentTransaction.replace(R.id.notes_details, notesDetailsFragment, NotesDetailsFragment.ARG_TAG); // замена фрагмента
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
//        Fragment fragment =fragmentManager.findFragmentByTag(NotesDetailsFragment.ARG_TAG);
//        if (fragment instanceof NotesDetailsFragment){}
    }
}