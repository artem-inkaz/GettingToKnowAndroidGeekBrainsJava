package com.example.gettingtoknowandroidgeekbrainsjava.lesson9.ui.update;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson10.FirestoreNotesRepository;

public class UpdateNoteViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UpdateNoteViewModel(FirestoreNotesRepository.INSTANCE);
    }
}
