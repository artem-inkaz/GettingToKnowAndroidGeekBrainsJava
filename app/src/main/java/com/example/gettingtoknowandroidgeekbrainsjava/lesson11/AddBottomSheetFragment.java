package com.example.gettingtoknowandroidgeekbrainsjava.lesson11;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gettingtoknowandroidgeekbrainsjava.ChangeFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.R;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesCity;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes.NotesViewModel;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes.NotesViewModelFactory;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson9.ui.update.OnNoteSaved;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson9.ui.update.UpdateNoteFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class AddBottomSheetFragment extends BottomSheetDialogFragment {

    public static final String TAG = "AddBottomSheetFragment";
    private static final String ARG_NOTES_CITY = "Param_NotesCity";
    private AddBottomSheetViewModel mViewModel;

    private DatePicker mDatePicker;
    private MaterialDatePicker picker;
    private ChangeFragment changeFragment;

    private NotesViewModel notesViewModel;
    private AddBottomSheetViewModel addBottomSheetViewModel;
    private NotesCity notesCityAdd;
    private OnNoteAdded onNoteAdded;
    EditText dateNoteAdd;

    public static AddBottomSheetFragment newInstance() {
        return new AddBottomSheetFragment();
    }

    public static AddBottomSheetFragment newInstance(NotesCity notesCityAdd) {
        AddBottomSheetFragment fragment = new AddBottomSheetFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTES_CITY, (Parcelable) notesCityAdd);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            notesCityAdd = getArguments().getParcelable(ARG_NOTES_CITY);
        }

        notesViewModel =
                new ViewModelProvider(this, new NotesViewModelFactory()).get(NotesViewModel.class);

        addBottomSheetViewModel = new ViewModelProvider(this, new AddBottomSheetViewModelFactory()).get(AddBottomSheetViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_new, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonAdd = view.findViewById(R.id.button_add);
        dateNoteAdd = view.findViewById(R.id.edit_text_date_note_city_add);
        EditText idNoteAdd = view.findViewById(R.id.edit_text_id_note_city_add);
        EditText nameNoteAdd = view.findViewById(R.id.textView_name_note_city_add);
        ImageView avatarNoteAdd = view.findViewById(R.id.notes_image_city_add);
        EditText descriptionNoteAdd = view.findViewById(R.id.edit_text_description_note_city_add);
        ImageView imageNoteAdd = view.findViewById(R.id.imageView_image_note_city_add);

        addBottomSheetViewModel.getSelectedDateLiveData()
                .observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String message) {
//                        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
                        dateNoteAdd.setText(message);
                    }
                });

        ImageView imageViewCalendar = view.findViewById(R.id.imageView_calendar_add);
        imageViewCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker();
            }
        });

        idNoteAdd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addBottomSheetViewModel.validateInput(s.toString());
            }
        });
        nameNoteAdd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addBottomSheetViewModel.validateInput(s.toString());
            }
        });
        descriptionNoteAdd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addBottomSheetViewModel.validateInput(s.toString());
            }
        });
        dateNoteAdd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addBottomSheetViewModel.validateInput(s.toString());
            }
        });

        // int id, String name, String description, String dataCreate, String imageUrl, String avatar
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notesCityAdd = new NotesCity();
                addBottomSheetViewModel.addNewNote2(
                        idNoteAdd.getText(),
                        nameNoteAdd.getText(),
                        descriptionNoteAdd.getText(),
                        dateNoteAdd.getText(),
                        "imageNoteAdd.",
                        "avatar",
                        notesCityAdd);
            }
        });

        addBottomSheetViewModel.saveEnabled().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
//                buttonUpdate.setEnabled(aBoolean);
                buttonAdd.setEnabled(true);
            }
        });

        addBottomSheetViewModel.saveSucceed().observe(getViewLifecycleOwner(), new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                if (onNoteAdded != null) {
                    onNoteAdded.onNoteAdded();
                }
            }
        });

        ((BottomSheetDialog) getDialog()).getBehavior().addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddBottomSheetViewModel.class);
        // TODO: Use the ViewModel
    }

    private void datePicker() {
        picker = MaterialDatePicker.Builder.datePicker().build();
        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onPositiveButtonClick(Long selection) {
                addBottomSheetViewModel.dateSelected(selection);
//                dateNoteAdd.setText(selection.toString());
            }
        });
        picker.show(getChildFragmentManager(), "MaterialDatePicker");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
//        changeFragment = (ChangeFragment) getContext();
        if (context instanceof OnNoteAdded) {
            onNoteAdded = (OnNoteAdded) context;
        }
    }

    @Override
    public void onDetach() {
//        changeFragment = null;
        onNoteAdded = null;
        super.onDetach();
    }

}