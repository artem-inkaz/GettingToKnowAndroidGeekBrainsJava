package com.example.gettingtoknowandroidgeekbrainsjava.lesson6;

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
    private int currentPosition = 0;    // Текущая позиция (выбранный город)

    private boolean isLandscape;

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
    }

    // создаём список записей на экране из массива в ресурсах
    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] notes = getResources().getStringArray(R.array.notes);

        // В этом цикле создаём элемент TextView,
        // заполняем его значениями,
        // и добавляем на экран.
        // Кроме того, создаём обработку касания на элемент
        for(int i=0; i < notes.length; i++){
            String note = notes[i];
            TextView tv = new TextView(getContext());
            tv.setText(note);
            tv.setTextSize(30);
            layoutView.addView(tv);
            // При нажатии на запись открываем новую активити с изображением записи и передаём туда
            // параметр, в новой активити сразу создаём фрагмент и перенаправляем туда переданный параметр.
            final int fi = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    showPortNotesDetails(fi);
                    currentPosition = fi;
//                    showNotesDetails(fi);
                    // для сохранения состояния запоминания текущей позиции
                    showNotesDetails(currentPosition);
                }
            });
        }
    }
    // отображение портрет или landScape
    private void showNotesDetails(int fi) {
        if (isLandscape){
            showLandNotesDetails(fi);
        } else {
            showPortNotesDetails(fi);
        }
    }

    // показываем запись в потретной ориентации
    private void showPortNotesDetails(int index) {
        // открываем вторую активити
        Intent intent = new Intent();
        intent.setClass(getActivity(), NotesLandScapeActivity.class);
        // и передаем туда параметры
        intent.putExtra(NotesDetailsFragment.ARG_INDEX, index);
        startActivity(intent);
    }

    // activity создана, можно к ней обращаться. Выполним начальные действия
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Определение, можно ли будет расположить рядом герб в другом фрагменте
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        // Если это не первое создание, то восстановим текущую позицию
        if (savedInstanceState != null){
        // Восстановление текущей позиции. при поворотах
            currentPosition = savedInstanceState.getInt(CURRENT_NOTES, 0);
        }

        // Если можно нарисовать рядом герб, то сделаем это
        if (isLandscape) {
            showLandNotesDetails(0);
        }
    }

    // Показать герб в ландшафтной ориентации
    private void showLandNotesDetails(int index){
        // Создаём новый фрагмент с текущей позицией для вывода записи
        NotesDetailsFragment notesDetailsFragment = NotesDetailsFragment.newInstance(index);

        // Выполняем транзакцию по замене фрагмента
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.notes_details, notesDetailsFragment); // замена фрагмента
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    // Сохраним текущую позицию (вызывается перед выходом из фрагмента)
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CURRENT_NOTES,currentPosition);
        super.onSaveInstanceState(outState);
    }
}