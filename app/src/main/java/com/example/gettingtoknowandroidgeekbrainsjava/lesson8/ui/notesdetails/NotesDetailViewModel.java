package com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notesdetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesCity;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesDetailRepository;

import java.util.List;

public class NotesDetailViewModel extends ViewModel  {

    private final NotesDetailRepository notesDetailRepository;

    private final MutableLiveData<List<NotesCity>> notesCityDetailLiveData = new MutableLiveData<>();

    public NotesDetailViewModel(NotesDetailRepository notesDetailRepository, NotesCity notesCity) {
        this.notesDetailRepository = notesDetailRepository;
        this.notesCity = notesCity;
    }

    public LiveData<List<NotesCity>> getNotesCityDetailLiveData() {
        return notesCityDetailLiveData;
    }
    private NotesCity notesCity;

    public void setNotesCity(NotesCity notesCity) {
        this.notesCity = notesCity;
    }

    public NotesCity getNotesCity() {
        return notesCity;
    }

    public void fetchValue(){
//        notesCityDetailLiveData.setValue(notesDetailRepository.getNotesDetailCity());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
