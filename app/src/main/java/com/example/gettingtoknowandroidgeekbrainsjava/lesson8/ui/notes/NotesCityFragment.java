package com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gettingtoknowandroidgeekbrainsjava.ChangeFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.Constants;
import com.example.gettingtoknowandroidgeekbrainsjava.R;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesCity;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes.adapters.NotesCityAdapter;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notesdetails.NoteDetailFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notesdetails.NotesDetailViewModel;

import java.util.List;

public class NotesCityFragment extends Fragment {

    private NotesViewModel notesViewModel;
//    private NotesDetailViewModel notesDetailViewModel;

    private NotesCityAdapter notesCityAdapter;

    private ChangeFragment changeFragment;
//    private boolean isLandscape;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notesViewModel =
                new ViewModelProvider(this).get(NotesViewModel.class);

//        notesDetailViewModel =
//                new ViewModelProvider(this).get(NotesDetailViewModel.class);

        notesViewModel.fetchNotes();

        notesCityAdapter = new NotesCityAdapter();
        notesCityAdapter.setNoteClicked(new NotesCityAdapter.OnNoteClicked() {
            @Override
            public void onNoteClicked(NotesCity notesCity) {
                //               Toast.makeText(requireContext(), notesCity.getName(), Toast.LENGTH_SHORT).show();
                changeFragment.gotoFragmentNotesCityDetails(notesCity);
                notesViewModel.setNotesCity(notesCity);
//               notesDetailViewModel.setNotesCity(notesCity);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_city, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_container);
        recyclerView.setAdapter(notesCityAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        notesViewModel.getNotesCityLiveData()
                .observe(getViewLifecycleOwner(), new Observer<List<NotesCity>>() {
                    @Override
                    public void onChanged(List<NotesCity> notesCities) {
                        notesCityAdapter.clear();
                        notesCityAdapter.addItems(notesCities);
                        notesCityAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        changeFragment = (ChangeFragment) getContext();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        changeFragment = null;
    }

    // activity создана, можно к ней обращаться. Выполним начальные действия
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Определение, можно ли будет расположить рядом герб в другом фрагменте
        Constants.isLandscapeCity = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
}