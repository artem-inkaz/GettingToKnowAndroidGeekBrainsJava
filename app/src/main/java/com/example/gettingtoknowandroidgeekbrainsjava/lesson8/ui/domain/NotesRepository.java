package com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain;

import java.util.List;

public interface NotesRepository {

    void getNotesCity(Callback<List<NotesCity>> callback);

    void clearNotes(Callback<Object> voidCallback);

    void addNewNote(Callback<NotesCity> noteCallback);

    void addNewNote2(NotesCity notesCity,Callback<NotesCity> noteCallback);

    void updateNote(NotesCity notesCity,Callback<Object> objectCallback);

    void deleteNote(NotesCity notesCity, Callback<Object> objectCallback);
}
