package com.example.gettingtoknowandroidgeekbrainsjava.lesson6;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gettingtoknowandroidgeekbrainsjava.R;

public class NotesFragment extends Fragment {
    // для сохранения состояния при повороте
    public static final String CURRENT_NOTES = "CURRENT_NOTES";
    // закомментировали т.к. будем работать через класс Notes
//    private int currentPosition = 0;    // Текущая позиция (выбранный город)
    private Notes currentNotes;
    private boolean isLandscape;
    // объявляем интерфейс
    private OnNotesSelected listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnNotesSelected) {
            listener = (OnNotesSelected) context;
        }
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    // При создании фрагмента укажем его макет
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    // вызывается после создания макета фрагмента, здесь мы проинициализируем список
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
        // здесь описываем
//        getChildFragmentManager()
    }

    // создаём список записей на экране из массива в ресурсах
    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] notes = getResources().getStringArray(R.array.notes_id);

        // В этом цикле создаём элемент TextView,
        // заполняем его значениями,
        // и добавляем на экран.
        // Кроме того, создаём обработку касания на элемент
        for (int i = 0; i < notes.length; i++) {
            String note = notes[i];
            TextView tv = new TextView(getContext());
            tv.setText(note);
            tv.setTextSize(30);
            layoutView.addView(tv);
            // При нажатии на запись открываем новую активити с изображением записи и передаём туда
            // параметр, в новой активити сразу создаём фрагмент и перенаправляем туда переданный параметр.
            final int notesId = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //---------------
                    currentNotes = new Notes(notesId,
                            getResources().getStringArray(R.array.notes_name)[notesId],
                            getResources().getStringArray(R.array.notes_description)[notesId],
                            getResources().getStringArray(R.array.notes_date_create)[notesId],
                            notesId);
                    // для сохранения состояния запоминания текущей позиции
                    showNotesDetails(currentNotes);

                }
            });
        }
    }

    private void showPortNotesDetails(Notes currentNotes) {

        // открываем вторую активити
        Intent intent = new Intent();
        intent.setClass(getActivity(), NotesLandScapeActivity.class);
        // и передаем туда параметры
        intent.putExtra(NotesDetailsFragment.ARG_NOTES, currentNotes);
        startActivity(intent);
    }

    // отображение портрет или landScape через data class
    private void showNotesDetails(Notes currentNotes) {
        if (isLandscape) {
            showLandNotesDetails(currentNotes);
        } else {
            showPortNotesDetails(currentNotes);
        }
    }

    private void showLandNotesDetails(Notes currentNotes) {

        if (listener != null) {
            listener.onNotesSelected(currentNotes);
        }
    }

    // activity создана, можно к ней обращаться. Выполним начальные действия
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Определение, можно ли будет расположить рядом герб в другом фрагменте
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        // Если это не первое создание, то восстановим текущую позицию
        if (savedInstanceState != null) {
            // Восстановление текущей позиции. при поворотах
//            currentPosition = savedInstanceState.getInt(CURRENT_NOTES, 0);
            currentNotes = savedInstanceState.getParcelable(CURRENT_NOTES);
        } else {
            // Если восстановить не удалось, то сделаем объект с первым индексом
            currentNotes = new Notes(0,
                    getResources().getStringArray(R.array.notes_name)[0],
                    getResources().getStringArray(R.array.notes_description)[0],
                    getResources().getStringArray(R.array.notes_date_create)[0],
                    0);
        }

        // Если можно нарисовать рядом герб, то сделаем это
        if (isLandscape) {
            showLandNotesDetails(currentNotes);
        }
    }

    // Сохраним текущую позицию (вызывается перед выходом из фрагмента)
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
//        outState.putInt(CURRENT_NOTES,currentPosition);
        outState.putParcelable(CURRENT_NOTES, currentNotes);
        super.onSaveInstanceState(outState);
    }

    interface OnNotesSelected {
        void onNotesSelected(Notes notes);
    }
}