package com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.gettingtoknowandroidgeekbrainsjava.lesson10.FirestoreNotesRepository;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.ListNotesRepository;

public class NotesViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NotesViewModel(FirestoreNotesRepository.INSTANCE);
    }
}
