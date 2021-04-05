package com.example.gettingtoknowandroidgeekbrainsjava.lesson11;

import android.text.Editable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.Callback;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesCity;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddBottomSheetViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private final NotesRepository notesRepository;

    public AddBottomSheetViewModel(NotesRepository repository) {
        this.notesRepository = repository;
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

//    public void addNewNote() {
//        progressLiveData.setValue(true);
//        notesRepository.addNewNote(new Callback<NotesCity>() {
//            @Override
//            public void onResult(NotesCity value) {
//                newNoteAddedLiveData.postValue(value);
//                progressLiveData.setValue(false);
//            }
//        });
//    }

    public void addNewNote2(Editable id, Editable name, Editable description, Editable dataCreate,String imageUrl,String avatar, NotesCity notesCity) {
        notesCity.setId(id.toString());
        notesCity.setName(name.toString());
        notesCity.setDescription(description.toString());
        notesCity.setDataCreate(dataCreate.toString());
        notesCity.setImageUrl(imageUrl.toString());
        notesCity.setAvatar(avatar.toString());

        progress.setValue(true);

        notesRepository.addNewNote2(notesCity, new Callback<NotesCity>() {
            @Override
            public void onResult(NotesCity value) {
                progress.setValue(false);
                saveSucceed.setValue(new NotesCity());
            }
        });

    }

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
    private final MutableLiveData<String> selectedDateLiveData = new MutableLiveData<>();
    public LiveData<String> getSelectedDateLiveData() {
        return selectedDateLiveData;
    }
    public void dateSelected(Long selection) {
        selectedDateLiveData.setValue(simpleDateFormat.format(new Date(selection)));
    }
}