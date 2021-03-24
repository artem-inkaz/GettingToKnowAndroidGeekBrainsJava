package com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notesdetails;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.gettingtoknowandroidgeekbrainsjava.ChangeFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.Constants;
import com.example.gettingtoknowandroidgeekbrainsjava.R;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesCity;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes.NotesViewModel;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class NoteDetailFragment extends Fragment {

    private NotesCity notesCity;
    private DatePicker mDatePicker;
    private ChangeFragment changeFragment;

    private NotesDetailViewModel notesDetailViewModel;
    private NotesViewModel notesViewModel;

    public NoteDetailFragment(NotesCity notesCity) {
        this.notesCity = notesCity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        notesDetailViewModel =
//                new ViewModelProvider(this).get(NotesDetailViewModel.class);

        notesViewModel =
                new ViewModelProvider(this).get(NotesViewModel.class);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notes_city_details, container, false);
        return root;
//        return inflater.inflate(R.layout.fragment_notes_city_details, container, false);

    }

    // activity создана, можно к ней обращаться. Выполним начальные действия
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Определение, можно ли будет расположить рядом герб в другом фрагменте
        Constants.isLandscapeCity = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView idNote = view.findViewById(R.id.textView_id_note_city);
        TextView nameNote = view.findViewById(R.id.textView_name_note_city);
        TextView dateNote = view.findViewById(R.id.textView_date_note_city);
        ImageView avatarNote = view.findViewById(R.id.notes_image_city);
        TextView descriptionNote = view.findViewById(R.id.textView_description_note_city);
        ImageView imageNote = view.findViewById(R.id.imageView_image_note_city);

//        notesDetailViewModel.getNotesCityDetailLiveData()
//                .observe(getViewLifecycleOwner(), (List<NotesCity> notesCities) -> {
//                    idNote.setText(Integer.toString(notesDetailViewModel.getNotesCity().getId()));
//                });
        notesViewModel.getNotesCityLiveData()
                    .observe(getViewLifecycleOwner(), new Observer<List<NotesCity>>() {
                        @Override
                        public void onChanged(List<NotesCity> notesCityList) {
                            idNote.setText(Integer.toString(notesCityList.hashCode()));
                        }
                    });
//        idNote.setText(Integer.toString(notesViewModel.getNotesCity().getId()));

        Glide.with(Objects.requireNonNull(getContext()))
                .load(notesCity.getImageUrl())
                .into(imageNote);
//       idNote.setText(Integer.toString(notesDetailViewModel.));
        idNote.setText(Integer.toString(notesCity.getId()));
        nameNote.setText(notesCity.getName());
        dateNote.setText(notesCity.getDataCreate());
        avatarNote.setImageResource(notesCity.getAvatar());
        descriptionNote.setText(notesCity.getDescription());

        DatePicker mDatePicker = view.findViewById(R.id.datePicker_note_city_detail);

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

        ImageView imageViewCalendar = view.findViewById(R.id.imageView_calendar);
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