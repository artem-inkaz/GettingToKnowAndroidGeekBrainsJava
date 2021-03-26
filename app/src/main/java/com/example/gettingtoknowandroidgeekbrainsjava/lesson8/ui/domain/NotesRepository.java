package com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain;

import java.util.List;

public interface NotesRepository {

    List<NotesCity> getNotesCity();

    void clearNotes();

    void addNewNote();
//    List<NotesCity> getNotesDetailCity();
}
