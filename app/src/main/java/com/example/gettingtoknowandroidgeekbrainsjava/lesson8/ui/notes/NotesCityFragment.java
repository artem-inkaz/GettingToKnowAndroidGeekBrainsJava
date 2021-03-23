package com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gettingtoknowandroidgeekbrainsjava.R;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson6.Notes;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson6.NotesDetailsFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson6.NotesLandScapeActivity;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson6.OnNotesSelected;

public class NotesCityFragment extends Fragment  {

    private NotesViewModel notesViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notesViewModel =
                new ViewModelProvider(this).get(NotesViewModel.class);

        notesViewModel.fetchNotes();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_city,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_container);
        recyclerView.setLayoutManager( new GridLayoutManager(requireContext(),2));
    }
}