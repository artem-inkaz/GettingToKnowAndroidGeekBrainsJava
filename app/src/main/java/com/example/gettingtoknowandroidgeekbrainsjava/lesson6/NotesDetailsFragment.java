package com.example.gettingtoknowandroidgeekbrainsjava.lesson6;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gettingtoknowandroidgeekbrainsjava.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotesDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotesDetailsFragment extends Fragment {

    private static final String ARG_INDEX = "ARG_INDEX";
    private int index;

    // Фабричный метод создания фрагмента
    // Фрагменты рекомендуется создавать через фабричные методы.
    // TODO: Rename and change types and number of parameters
    // Этот метод получает на входе параметр — индекс элемента массива с записями,
    // который затем передаётся дальше. По этому индексу приложение вытаскивает
    // изображение записи из массива  <integer-array name="notes_imgs">.

    public static NotesDetailsFragment newInstance(int index) {
        NotesDetailsFragment fragment = new NotesDetailsFragment();
        // передача параметров
        Bundle args = new Bundle();
        args.putString(ARG_INDEX, String.valueOf(index));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_INDEX);
        }
    }
    // Метод onCreateView должен
    // вернуть наследника от класса View. Это всегда должен быть макет экрана,
    // который вы хотите отобразить. Метод onCreateView во фрагменте — аналог метода
    // setContentView в активити.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Таким способом можно получить головной элемент из макета
       View view = inflater.inflate(R.layout.fragment_notes_details, container, false);

       // найти в контейнере элемент-изображение
        AppCompatImageView imageNotes = view.findViewById(R.id.notes_image);
        // Получить из ресурсов массив указателей на изображения гербов
        TypedArray images = getResources().obtainTypedArray(R.array.notes_imgs);
        // Выбрать по индексу подходящий
        imageNotes.setImageResource(images.getResourceId(index, -1));

        return view;
    }
}