package com.example.gettingtoknowandroidgeekbrainsjava.lesson9.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.gettingtoknowandroidgeekbrainsjava.ChangeFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.R;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesCity;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes.NotesViewModel;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes.NotesViewModelFactory;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddNewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private DatePicker mDatePicker;
    private ChangeFragment changeFragment;

    private NotesViewModel notesViewModel;

    private NotesCity notesCityAdd;

    public AddNewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddNewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddNewFragment newInstance(String param1, String param2) {
        AddNewFragment fragment = new AddNewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        notesViewModel =
                new ViewModelProvider(this, new NotesViewModelFactory()).get(NotesViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonAdd = view.findViewById(R.id.button_add);

        EditText dateNote = view.findViewById(R.id.edit_text_date_note_city_add);

        @SuppressLint("CutPasteId") DatePicker mDatePicker = view.findViewById(R.id.datePicker_note_city_add);

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

        ImageView imageViewCalendar = view.findViewById(R.id.imageView_calendar_add);
        imageViewCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatePicker.setVisibility(View.VISIBLE);
                dateNote.setText(new StringBuilder()
                        // Месяц отсчитывается с 0, поэтому добавляем 1
                        .append(mDatePicker.getDayOfMonth()).append(".")
                        .append(mDatePicker.getMonth() + 1).append(".")
                        .append(mDatePicker.getYear()));
            }
        });
        // int id, String name, String description, String dataCreate, String imageUrl, String avatar
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                notesViewModel.addNewNote();
            }
        });
    }
}