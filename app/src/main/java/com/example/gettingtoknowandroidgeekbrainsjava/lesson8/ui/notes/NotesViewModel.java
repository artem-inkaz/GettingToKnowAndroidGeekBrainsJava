package com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.ListNotesRepository;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesCity;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesRepository;

import java.util.ArrayList;
import java.util.List;

public class NotesViewModel extends ViewModel {

    private final NotesRepository notesRepository = ListNotesRepository.INSTANCE;

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

    private final MutableLiveData<Integer> removedItemPositionLiveData = new MutableLiveData<>();

    public LiveData<Integer> getRemovedItemPositionLiveData() {
        return removedItemPositionLiveData;
    }

    private final MutableLiveData<Integer> updateItemPositionLiveData = new MutableLiveData<>();

    public LiveData<Integer> getUpdateItemPositionLiveData() {
        return updateItemPositionLiveData;
    }

    private final MutableLiveData<String> selectedDateLiveData = new MutableLiveData<>();

    public void fetchNotes() {
//        progressLiveData.setValue(true);
        notesCityLiveData.setValue(notesRepository.getNotesCity());
//        progressLiveData.setValue(false);
//        notesRepository.getNotesCity();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void addNewNote(NotesCity notesCity){
//        progressLiveData.setValue(true);
        notesRepository.addNewNote();
        // передаем пустой список который перерисует весь список
        newNoteAddedLiveData.postValue(notesCity);
//        progressLiveData.setValue(false);
    }

    public void clearNotes(){
        progressLiveData.setValue(true);
        notesRepository.clearNotes();
        // передаем пустой список который перерисует весь список
        notesCityLiveData.postValue(new ArrayList<>());
        progressLiveData.setValue(false);
    }

    //--------------------------------------------------------------------------------------
    private NotesCity notesCity;

    public void setNotesCity(NotesCity notesCity) {
        this.notesCity = notesCity;
    }

    public NotesCity getNotesCity() {
        return notesCity;
    }

    public void deleteAtPosition(int contextMenuItemPosition) {
        removedItemPositionLiveData.setValue(contextMenuItemPosition);
    }

    public void updatePosition(int contextMenuItemPosition) {

    }


}
