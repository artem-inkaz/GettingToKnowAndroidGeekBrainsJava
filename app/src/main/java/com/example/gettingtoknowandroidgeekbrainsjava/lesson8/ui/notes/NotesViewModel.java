package com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes;

import android.text.Editable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.Callback;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesCity;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NotesViewModel extends ViewModel {

    public NotesViewModel(NotesRepository repository) {
        this.notesRepository = repository;
    }

    private final NotesRepository notesRepository;

    private final MutableLiveData<List<NotesCity>> notesCityLiveData = new MutableLiveData<>();
    public LiveData<List<NotesCity>> getNotesCityLiveData() {
        return notesCityLiveData;
    }

    private final MutableLiveData<Boolean> progressLiveData = new MutableLiveData<>();
    public LiveData<Boolean> getProgressLiveData() {
        return progressLiveData;
    }

    private final MutableLiveData<NotesCity> newNoteAddedLiveData = new MutableLiveData<>();
    public LiveData<NotesCity> getNewNoteAddedLiveData() {
        return newNoteAddedLiveData;
    }
    public void addNewNote() {
        progressLiveData.setValue(true);
        notesRepository.addNewNote(new Callback<NotesCity>() {
            @Override
            public void onResult(NotesCity value) {
                newNoteAddedLiveData.postValue(value);
                progressLiveData.setValue(false);
            }
        });
    }

    private final MutableLiveData<Integer> removedItemPositionLiveData = new MutableLiveData<>();
    public LiveData<Integer> getRemovedItemPositionLiveData() {
        return removedItemPositionLiveData;
    }
//    public void deleteAtPosition(int contextMenuItemPosition, NotesCity notesCity) {
    public void deleteAtPosition(int contextMenuItemPosition, NotesCity notesCity) {
//       removedItemPositionLiveData.setValue(contextMenuItemPosition);

        progressLiveData.setValue(true);
        notesRepository.deleteNote(notesCity, new Callback<Object>() {
            @Override
            public void onResult(Object value) {
                progressLiveData.setValue(false);
                removedItemPositionLiveData.postValue(contextMenuItemPosition);

//                ArrayList<NotesCity> currentNotes = (ArrayList<NotesCity>) notesCityLiveData.getValue();
//                currentNotes.remove(notesCity);
//                notesCityLiveData.postValue(new ArrayList<>());
                progressLiveData.setValue(false);
            }
        });
    }

    private final MutableLiveData<NotesCity> updateItemPositionLiveData = new MutableLiveData<NotesCity>();
    public LiveData<NotesCity> getUpdateItemPositionLiveData() {
        return updateItemPositionLiveData;
    }
    public void updatePosition(NotesCity notesCity2) {
//        updateItemPositionLiveData.setValue(notesCity2);
        updateItemPositionLiveData.postValue(notesCity2);
    }

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
    private final MutableLiveData<String> selectedDateLiveData = new MutableLiveData<>();
    public LiveData<String> getSelectedDateLiveData() {
        return selectedDateLiveData;
    }
    public void dateSelected(Long selection) {
        selectedDateLiveData.setValue(simpleDateFormat.format(new Date(selection)));
    }

    public void fetchNotes() {
        progressLiveData.setValue(true);
        notesRepository.getNotesCity(new Callback<List<NotesCity>>() {
            @Override
            public void onResult(List<NotesCity> value) {
                notesCityLiveData.postValue(value);
                progressLiveData.setValue(false);
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void clearNotes() {
        progressLiveData.setValue(true);

        notesRepository.clearNotes(new Callback<Object>() {
            @Override
            public void onResult(Object value) {
                notesCityLiveData.postValue(new ArrayList<>());
                progressLiveData.setValue(false);
            }
        });
    }

    //--------------------------------------------------------------------------------------
    private NotesCity notesCity;

    public void setNotesCity(NotesCity notesCity) {
        this.notesCity = notesCity;
//        notesCityLiveData.setValue(notesCity);
//        notesCityLiveData.postValue((List<NotesCity>) notesCity);

    }

//    public NotesCity getNotesCity() {
//        return notesCity;
//    }
}
