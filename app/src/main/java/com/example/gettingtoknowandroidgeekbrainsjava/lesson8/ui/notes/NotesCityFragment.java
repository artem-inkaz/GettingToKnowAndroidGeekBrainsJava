package com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gettingtoknowandroidgeekbrainsjava.ChangeFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.Constants;
import com.example.gettingtoknowandroidgeekbrainsjava.MainActivity;
import com.example.gettingtoknowandroidgeekbrainsjava.R;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson6.Notes;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson6.NotesDetailsFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson6.OnNotesSelected;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesCity;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes.adapters.NotesCityAdapter;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson9.ui.ActionInterface;

import java.util.List;

public class NotesCityFragment extends Fragment implements ActionInterface {

    public static final String ARG_NOTES = "ARG_NOTES";

    private NotesViewModel notesViewModel;
    private NotesCity notesCityContext;
    private NotesCityAdapter notesCityAdapter;
    private ChangeFragment changeFragment;
    private int contextMenuItemPosition;
    private ActionInterface mListener;

    //    public static NotesDetailsFragment newInstance(int index) {
    public static NotesCityFragment newInstance() {
        NotesCityFragment fragment = new NotesCityFragment();
        // передача параметров
        Bundle args = new Bundle();
//        args.putInt(ARG_INDEX, index);
//        args.putParcelable(ARG_NOTES, currentNotes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notesViewModel =
                new ViewModelProvider(this, new NotesViewModelFactory()).get(NotesViewModel.class);

        notesViewModel.fetchNotes();

        notesCityAdapter = new NotesCityAdapter(this);
        notesCityAdapter.setNoteClicked(new NotesCityAdapter.OnNoteClicked() {
            @Override
            public void onNoteClicked(NotesCity notesCity) {
                //               Toast.makeText(requireContext(), notesCity.getName(), Toast.LENGTH_SHORT).show();
                changeFragment.gotoFragmentNotesCityDetails(notesCity);
                notesViewModel.setNotesCity(notesCity);
            }
        });

        notesCityAdapter.setNoteLongClicked(new NotesCityAdapter.OnNoteLongClicked() {
            @Override
            public void onNoteLongClicked(View itemView, int position, NotesCity notesCity) {
                contextMenuItemPosition = position;
                notesCityContext = notesCity;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    itemView.showContextMenu(10, 10);
                } else {
                    itemView.showContextMenu();
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_city, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProgressBar progressBar = view.findViewById(R.id.progress);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_container);
        recyclerView.setAdapter(notesCityAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        notesViewModel.getNotesCityLiveData()
                .observe(getViewLifecycleOwner(), new Observer<List<NotesCity>>() {
                    @Override
                    public void onChanged(List<NotesCity> notesCities) {
                        notesCityAdapter.setItems(notesCities);
//                        notesCityAdapter.clear();
//                        notesCityAdapter.addItems(notesCities);
                        notesCityAdapter.notifyDataSetChanged();
                    }
                });

        notesViewModel.getProgressLiveData()
                .observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean isVisible) {
                        if (isVisible) {
                            progressBar.setVisibility(View.VISIBLE);
                        } else {
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

        notesViewModel.getNewNoteAddedLiveData()
                .observe(getViewLifecycleOwner(), new Observer<NotesCity>() {
                    @Override
                    public void onChanged(NotesCity note) {
                        notesCityAdapter.addItem(note);
                        notesCityAdapter.notifyItemInserted(notesCityAdapter.getItemCount() - 1);
                        recyclerView.smoothScrollToPosition(notesCityAdapter.getItemCount() - 1);
                    }
                });

        notesViewModel.getRemovedItemPositionLiveData()
                .observe(getViewLifecycleOwner(), new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer position) {
                        notesCityAdapter.removeAtPosition(position);
                        notesCityAdapter.notifyItemRemoved(position);
//                        if (mListener != null) {
//                            notesViewModel.clearNotes();
//                            //                           notesCityAdapter.clear();
//                        mListener.addNoteTo();
//                        }
                    }
                });

        notesViewModel.getUpdateItemPositionLiveData()
                .observe(getViewLifecycleOwner(), new Observer<NotesCity>() {
                    @Override
                    public void onChanged(NotesCity notesCity) {
//                        notesCityAdapter.setItems(notesCity);
//                        notesCityAdapter.notifyItemChanged(notesCity);
                    }
                });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        changeFragment = (ChangeFragment) getContext();
        if (context instanceof ActionInterface) {
            mListener = (ActionInterface) context;
        }
    }

    @Override
    public void onDetach() {
        changeFragment = null;
         mListener = null;
        super.onDetach();
    }

    // activity создана, можно к ней обращаться. Выполним начальные действия
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Определение, можно ли будет расположить рядом герб в другом фрагменте
        Constants.isLandscapeCity = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = requireActivity().getMenuInflater();
        menuInflater.inflate(R.menu.popup, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        int position = notesCityAdapter.getMenuPosition();
        switch (item.getItemId()) {
            case R.id.item1_popup_open_detail:
                changeFragment.gotoFragmentNotesCityDetails(notesCityContext);
                notesViewModel.setNotesCity(notesCityContext);
//                Toast.makeText(getContext(), "Chosen popup open detail", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item1_popup_update:
                notesViewModel.updatePosition(notesCityContext);
                changeFragment.gotoFragmentNotesCityUpdate(notesCityContext);
                Toast.makeText(getContext(), "Chosen popup update", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2_popup_delete:
//                mListener.addNoteTo();
//                contextMenuItemPosition
//                notesViewModel.clearNotes();
//                notesViewModel.addNewNote(notesCityContext);
                showAlert();
//                notesViewModel.deleteAtPosition(contextMenuItemPosition, notesCityAdapter.getItemAtIndex(contextMenuItemPosition));

//                Toast.makeText(getContext(), "Chosen popup deleted "+contextMenuItemPosition, Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void addNoteTo() {
        notesViewModel.clearNotes();
//        notesCityAdapter.setItems(notesCities);
//                       notesCityAdapter.clear();
////                        notesCityAdapter.addItems(notesCities);
//        notesCityAdapter.notifyDataSetChanged();
    }

    private void showAlert() {
        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setTitle(R.string.attention)
                .setMessage(R.string.alert_title_delete)
                .setIcon(R.drawable.ic_alert)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(requireContext(), R.string.ok, Toast.LENGTH_SHORT).show();
                        notesViewModel.deleteAtPosition(contextMenuItemPosition, notesCityAdapter.getItemAtIndex(contextMenuItemPosition));
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(requireContext(), R.string.cancel, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton(R.string.neutral, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(requireContext(), R.string.neutral, Toast.LENGTH_SHORT).show();
                    }
                })
                .setCancelable(true)
                .create();

        dialog.show();
    }
}