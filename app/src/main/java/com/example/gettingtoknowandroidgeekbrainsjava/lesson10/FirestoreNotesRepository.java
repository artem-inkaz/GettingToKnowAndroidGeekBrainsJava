package com.example.gettingtoknowandroidgeekbrainsjava.lesson10;

import androidx.annotation.NonNull;

import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.Callback;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesCity;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FirestoreNotesRepository implements NotesRepository {

    public static final NotesRepository INSTANCE = new FirestoreNotesRepository();

    private static final String NOTES_COLLECTION = "notes";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_IMAGE_URL = "imageUrl";
    public static final String FIELD_DATE_CREATE = "dataCreate";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_AVATAR = "avatar";

    private final FirebaseFirestore fireStore = FirebaseFirestore.getInstance();

    @Override
    public void getNotesCity(Callback<List<NotesCity>> callback) {

        fireStore.collection(NOTES_COLLECTION).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<DocumentSnapshot> documents = task.getResult().getDocuments();

                        ArrayList<NotesCity> result = new ArrayList<>();

                        for (DocumentSnapshot doc : documents) {

                            String name = doc.getString(FIELD_NAME);
                            String imageUrl = doc.getString(FIELD_IMAGE_URL);
//                            Date date = doc.getDate(FIELD_DATE_CREATE);
                            String dateCreate = doc.getString(FIELD_DATE_CREATE);
                            String description = doc.getString(FIELD_DESCRIPTION);
                            String avatar = doc.getString(FIELD_AVATAR);
                            // int id, String name, String description, String dataCreate, String imageUrl, String avatar
                            NotesCity notesCity = new NotesCity(doc.getId(), name, description, dateCreate, imageUrl, avatar);

                            result.add(notesCity);
                        }

                        callback.onResult(result);
                    }
                });
    }

    @Override
    public void clearNotes(Callback<Object> voidCallback) {

    }

    @Override
    public void addNewNote(Callback<NotesCity> noteCallback) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        // int id, String name, String description, String dataCreate, String imageUrl, String avatar  calendar.getTime());
        NotesCity notesCity = new NotesCity("1", "Крым","Вошел в состав Российской Федерации в 2018 году","30.03.2021 г." ,"https://images.unsplash.com/photo-1584714268709-c3dd9c92b378", "https://images.unsplash.com/photo-1584714268709-c3dd9c92b378");

        HashMap<String, Object> data = new HashMap<>();
//        data.put(FIELD_NAME, notesCity.getName());
        data.put(FIELD_NAME, notesCity.getName());
        data.put(FIELD_DESCRIPTION, notesCity.getDescription());
        data.put(FIELD_DATE_CREATE, notesCity.getDataCreate());
        data.put(FIELD_IMAGE_URL, notesCity.getImageUrl());
        data.put(FIELD_AVATAR, notesCity.getAvatar());

        fireStore.collection(NOTES_COLLECTION)
                .add(data).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {

                String id = task.getResult().getId();

//                int id = Integer.parseInt(task.getResult().getId());
                notesCity.setId(id);

                noteCallback.onResult(notesCity);

            }
        });
    }

    @Override
    public void updateNote(Callback<NotesCity> noteCallback) {

//        fireStore.collection(NOTES_COLLECTION).document("id").update()
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        List<DocumentSnapshot> documents = task.getResult().getDocuments();

//                        for (DocumentSnapshot doc : documents) {

//                            String name = doc.getString(FIELD_NAME);
//                            String imageUrl = doc.getString(FIELD_IMAGE_URL);
////                            Date date = doc.getDate(FIELD_DATE_CREATE);
//                            String dateCreate = doc.getString(FIELD_DATE_CREATE);
//                            String description = doc.getString(FIELD_DESCRIPTION);
//                            String avatar = doc.getString(FIELD_AVATAR);
//                            // int id, String name, String description, String dataCreate, String imageUrl, String avatar
//                            NotesCity notesCity = new NotesCity(doc.getId(), name, description, dateCreate, imageUrl, avatar);

//                            result.add(notesCity);
//                        }

//                        callback.onResult(result);

//                    }
//                });
    }

    @Override
    public void deleteNote(NotesCity notesCity, Callback<Object> objectCallback) {

        fireStore.collection(NOTES_COLLECTION)
                .document(String.valueOf(notesCity.getId()))
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        objectCallback.onResult(new Object());
                    }
                });
    }
}
