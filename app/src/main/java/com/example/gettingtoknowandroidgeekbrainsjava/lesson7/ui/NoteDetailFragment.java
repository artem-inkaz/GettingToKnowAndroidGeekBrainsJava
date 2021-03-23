package com.example.gettingtoknowandroidgeekbrainsjava.lesson7.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.gettingtoknowandroidgeekbrainsjava.R;

public class NoteDetailFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_detail, container, false);
        final TextView textView = view.findViewById(R.id.text_detail);
        return view;
    }
}