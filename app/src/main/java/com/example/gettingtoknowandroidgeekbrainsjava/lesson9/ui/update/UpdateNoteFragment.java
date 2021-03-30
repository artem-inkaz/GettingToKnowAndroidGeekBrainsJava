package com.example.gettingtoknowandroidgeekbrainsjava.lesson9.ui.update;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gettingtoknowandroidgeekbrainsjava.ChangeFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.R;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesCity;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes.NotesViewModel;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes.NotesViewModelFactory;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateNoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateNoteFragment extends Fragment {

    private static final String ARG_NOTES_CITY = "Param_NotesCity";

    private NotesCity notesCity;
    private DatePicker mDatePicker;
    private ChangeFragment changeFragment;

    private NotesViewModel notesViewModel;

    public UpdateNoteFragment() {
        // Required empty public constructor
    }

    public UpdateNoteFragment(NotesCity notesCity) {
        this.notesCity = notesCity;
    }

    // TODO: Rename and change types and number of parameters
    public static UpdateNoteFragment newInstance(NotesCity notesCity) {
        UpdateNoteFragment fragment = new UpdateNoteFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTES_CITY, (Parcelable) notesCity);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            notesCity = getArguments().getParcelable(ARG_NOTES_CITY);
        }

//        notesViewModel =
//                new ViewModelProvider(this, new NotesViewModelFactory()).get(NotesViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_note, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        notesViewModel =
                new ViewModelProvider(this, new NotesViewModelFactory()).get(NotesViewModel.class);

        Button buttonUpdate = view.findViewById(R.id.button_update);
        EditText idNote = view.findViewById(R.id.edit_text_id_note_city_update);
        EditText nameNote = view.findViewById(R.id.edit_text_name_note_city_update);
        EditText dateNote = view.findViewById(R.id.edit_text_date_note_city_update);
        ImageView avatarNote = view.findViewById(R.id.notes_image_city_update);
        EditText descriptionNote = view.findViewById(R.id.edit_text_description_title_city_update);
        ImageView imageNote = view.findViewById(R.id.imageView_image_note_city_update);

        Glide.with(Objects.requireNonNull(getContext()))
                .load(notesCity.getImageUrl())
                .into(imageNote);

        notesViewModel.getUpdateItemPositionLiveData().observe(getViewLifecycleOwner(), new Observer<NotesCity>() {
            @Override
            public void onChanged(NotesCity notesCity2) {
                idNote.setText(Integer.toString(notesCity.getId()));
            }
        });
//        idNote.setText(Integer.toString(notesCity.getId()));

        nameNote.setText(notesCity.getName());
        dateNote.setText(notesCity.getDataCreate());
        avatarNote.setImageResource(notesCity.getAvatar());
        descriptionNote.setText(notesCity.getDescription());

        DatePicker mDatePicker = view.findViewById(R.id.datePicker_note_city_update);
        Calendar today = Calendar.getInstance();
        mDatePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                        Toast.makeText(requireContext(),
                                "onDateChanged", Toast.LENGTH_SHORT).show();
                        dateNote.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
                        mDatePicker.setVisibility(View.GONE);
                    }
                });

        ImageView imageViewCalendar = view.findViewById(R.id.imageView_calendar_update);
        imageViewCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatePicker.setVisibility(View.VISIBLE);
                dateNote.setText(new StringBuilder()
                        // Месяц отсчитывается с 0, поэтому добавляем 1
                        .append(mDatePicker.getDayOfMonth()).append(".")
                        .append(mDatePicker.getMonth() + 1).append(".")
                        .append(mDatePicker.getYear()));

                datePicker();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"SetTextI18n", "ResourceType"})
            @Override
            public void onClick(View v) {
                idNote.setText("20");
            }
        });

        notesViewModel.getSelectedDateLiveData()
                .observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String message) {
                        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
                        dateNote.setText(message);
                    }
                });
    }

    private void datePicker() {
        MaterialDatePicker picker = MaterialDatePicker.Builder.datePicker().build();
        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                notesViewModel.dateSelected(selection);
            }
        });
        picker.show(getChildFragmentManager(), "MaterialDatePicker");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        changeFragment = (ChangeFragment) getContext();
    }

    @Override
    public void onDetach() {
        changeFragment = null;
        super.onDetach();
    }
}