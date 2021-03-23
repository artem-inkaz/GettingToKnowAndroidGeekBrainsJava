package com.example.gettingtoknowandroidgeekbrainsjava.lesson6;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import com.example.gettingtoknowandroidgeekbrainsjava.R;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotesDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotesDetailsFragment extends Fragment {

    //    public static final String ARG_INDEX = "ARG_INDEX";
    public static final String ARG_NOTES = "ARG_NOTES";
    //    public static final String ARG_TAG = "ARG_TAG";
    private int index;
    private Notes currentNotes;

    private DatePicker mDatePicker;

    // Фабричный метод создания фрагмента
    // Фрагменты рекомендуется создавать через фабричные методы.
    // Этот метод получает на входе параметр — индекс элемента массива с записями,
    // который затем передаётся дальше. По этому индексу приложение вытаскивает
    // изображение записи из массива  <integer-array name="notes_imgs">.

    //    public static NotesDetailsFragment newInstance(int index) {
    public static NotesDetailsFragment newInstance(Notes currentNotes) {
        NotesDetailsFragment fragment = new NotesDetailsFragment();
        // передача параметров
        Bundle args = new Bundle();
//        args.putInt(ARG_INDEX, index);
        args.putParcelable(ARG_NOTES, currentNotes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
        if (getArguments() != null) {
//            index = getArguments().getInt(ARG_INDEX);
            currentNotes = getArguments().getParcelable(ARG_NOTES);
        }
    }

    // Метод onCreateView должен
    // вернуть наследника от класса View. Это всегда должен быть макет экрана,
    // который вы хотите отобразить. Метод onCreateView во фрагменте — аналог метода
    // setContentView в активити.
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Таким способом можно получить головной элемент из макета
        View view = inflater.inflate(R.layout.fragment_notes_details, container, false);

        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // найти в контейнере элемент-изображение
        AppCompatImageView imageNotes = view.findViewById(R.id.notes_image);
        // Получить из ресурсов массив указателей на изображения гербов
        @SuppressLint("Recycle") TypedArray images = getResources().obtainTypedArray(R.array.notes_imgs);
        // Выбрать по индексу подходящий
//        imageNotes.setImageResource(images.getResourceId(index, -1));
        imageNotes.setImageResource(images.getResourceId(currentNotes.getAvatar(), -1));
        // устанавливаем поля для вывода детальной информации
        TextView notesIdView = view.findViewById(R.id.textView_id_note);
        notesIdView.setText(Integer.toString(currentNotes.getId() + 1));

        TextView notesNameView = view.findViewById(R.id.textView_name_note);
        notesNameView.setText(currentNotes.getName());

        TextView notesDescriptionView = view.findViewById(R.id.textView_description_note);
        notesDescriptionView.setText(currentNotes.getDescription());

        TextView notesDateView = view.findViewById(R.id.textView_date_note);
        notesDateView.setText(currentNotes.getDataCreate());

        DatePicker mDatePicker = view.findViewById(R.id.datePicker);

        Calendar today = Calendar.getInstance();

        mDatePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                        Toast.makeText(requireContext(),
                                "onDateChanged", Toast.LENGTH_SHORT).show();

                        notesDateView.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
                        mDatePicker.setVisibility(View.INVISIBLE);
                    }
                });

        TextView textViewDateTitle = view.findViewById(R.id.textView_date_title);
        textViewDateTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatePicker.setVisibility(View.VISIBLE);
                notesDateView.setText(new StringBuilder()
                        // Месяц отсчитывается с 0, поэтому добавляем 1
                        .append(mDatePicker.getDayOfMonth()).append(".")
                        .append(mDatePicker.getMonth() + 1).append(".")
                        .append(mDatePicker.getYear()));
            }
        });
    }
}