package com.example.gettingtoknowandroidgeekbrainsjava.lesson9.ui.update;

import android.text.Editable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.Callback;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesCity;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesRepository;

public class UpdateNoteViewModel extends ViewModel {

    private final NotesRepository repository;

    public UpdateNoteViewModel(NotesRepository repository) {
        this.repository = repository;
    }

    private MutableLiveData<Boolean> progress = new MutableLiveData<>(false);
    public LiveData<Boolean> getProgress() {
        return progress;
    }

    private MutableLiveData<Boolean> saveEnabled = new MutableLiveData<>(false);
    public LiveData<Boolean> saveEnabled() {
        return saveEnabled;
    }

    private MutableLiveData<Object> saveSucceed = new MutableLiveData<>();
    public LiveData<Object> saveSucceed() {
        return saveSucceed;
    }

    public void validateInput(String newName) {
        saveEnabled.setValue(!newName.isEmpty());
    }
    // String id, String name, String description, String dataCreate, String imageUrl, String avatar
    public void saveNote(Editable id,Editable name,Editable description,Editable dataCreate, NotesCity notesCity) {
        notesCity.setId(id.toString());
        notesCity.setName(name.toString());
        notesCity.setDescription(description.toString());
        notesCity.setDataCreate(dataCreate.toString());

        progress.setValue(true);

        repository.updateNote(notesCity, new Callback<Object>() {
            @Override
            public void onResult(Object value) {
                progress.setValue(false);
                saveSucceed.setValue(new Object());
            }
        });

    }
}
