package com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.ListNotesRepository;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesCity;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesRepository;

import java.util.List;

public class NotesViewModel extends ViewModel {

    private final NotesRepository notesRepository = ListNotesRepository.INSTANCE;

    private final MutableLiveData<List<NotesCity>> notesCityLiveData = new MutableLiveData<>();

    public void fetchNotes() {
        notesCityLiveData.setValue(notesRepository.getNotesCity());

    }

    public LiveData<List<NotesCity>> getNotesCityLiveData() {
        return notesCityLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    //--------------------------------------------------------------------------------------
    private NotesCity notesCity;

    public void setNotesCity(NotesCity notesCity) {
        this.notesCity = notesCity;
    }

    public NotesCity getNotesCity() {
        return notesCity;
    }
}
