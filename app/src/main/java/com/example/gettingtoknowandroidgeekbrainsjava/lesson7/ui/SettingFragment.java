package com.example.gettingtoknowandroidgeekbrainsjava.lesson7.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gettingtoknowandroidgeekbrainsjava.Constants;
import com.example.gettingtoknowandroidgeekbrainsjava.R;

import static android.content.Context.MODE_PRIVATE;

public class SettingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    // Сохранение настроек приложения
    private void writeSettings() {
        // Специальный класс для хранения настроек
        SharedPreferences sharedPref = requireActivity().getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
        // Настройки сохраняются посредством специального класса editor.
        SharedPreferences.Editor editor = sharedPref.edit();
        // Задаем значения настроек
        editor.putBoolean(Constants.IS_BACK_STACK_USED, Constants.IsBackStack);
        editor.putBoolean(Constants.IS_ADD_FRAGMENT_USED, Constants.IsAddFragment);
        editor.putBoolean(Constants.IS_BACK_AS_REMOVE_FRAGMENT, Constants.IsBackAsRemove);
        editor.putBoolean(Constants.IS_DELETE_FRAGMENT_BEFORE_ADD, Constants.IsDeleteBeforeAdd);
        // Сохраняем значения настроек
        editor.apply();
    }
}