package com.example.gettingtoknowandroidgeekbrainsjava.lesson11;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson10.FirestoreNotesRepository;

public class AddBottomSheetViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddBottomSheetViewModel(FirestoreNotesRepository.INSTANCE);
    }
}
