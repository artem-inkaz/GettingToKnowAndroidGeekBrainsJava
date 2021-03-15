package com.example.gettingtoknowandroidgeekbrainsjava.lesson5;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.gettingtoknowandroidgeekbrainsjava.Constants;
import com.example.gettingtoknowandroidgeekbrainsjava.R;

public class SettingActivity extends ThemeActivity2 implements Constants {

    private int paramTheme; // параметр стиля темы
    private EditText numberField_settings;   // поле для ввода числа
    private TextView textViewResult;

    private final static String KEY_RESULT_FIELD = "RESULT_FIELD";
    private final static String KEY_NUMBER_FIELD = "NUMBER_FIELD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_setting);

        textViewResult = findViewById(R.id.textView_result_setting);
        // кнопка калькулятор
        Button buttonCalc = findViewById(R.id.button__calc_setting);
        buttonCalc.setOnClickListener(v -> launchMainActivity());
        // настраиваем тему приложения
        initButtonSetThemeClickListener(R.id.button_light_mode_setting, R.id.button_night_mode_setting, AppThemeLight);
        initButtonSetThemeClickListener(R.id.button_night_mode_setting, R.id.button_light_mode_setting, AppThemeDark);

        numberField_settings = findViewById(R.id.edit_text_input_setting);

        recieveDataFromOtherApp();

        Button buttonSendAndRecieve = findViewById(R.id.button_recieve_data);
        buttonSendAndRecieve.setOnClickListener(v -> recieveDataFromCalculator());

        setTheme();
        setData();
    }

    // сохранение при повороте
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(KEY_RESULT_FIELD, String.valueOf(textViewResult.getText().toString()));
        outState.putString(KEY_NUMBER_FIELD, String.valueOf(numberField_settings.getText().toString()));
        super.onSaveInstanceState(outState);
    }

    // сохранение при повороте
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        numberField_settings.setText(savedInstanceState.getString(KEY_NUMBER_FIELD));
        textViewResult.setText(savedInstanceState.getString(KEY_RESULT_FIELD));
    }

    // кнопка калькулятор
    private void launchMainActivity() {
        Intent intent = new Intent(SettingActivity.this, CalculatorActivity4.class);
        if (paramTheme == 1 || paramTheme == 0) {
            intent.putExtra(TYPE_THEME, paramTheme);
        }
        if (numberField_settings.length() != 0) {
            intent.putExtra(KEY_NUMBER_FIELD_INTENT, numberField_settings.getText().toString());
        }
        startActivity(intent);
    }

    // Кнопка получение и отправка
    private void recieveDataFromCalculator() {
        Intent intent = new Intent(SettingActivity.this, CalculatorActivity4.class);
        if (paramTheme == 1 || paramTheme == 0) {
            intent.putExtra(TYPE_THEME, paramTheme);
        }
        if (numberField_settings.length() != 0) {
            intent.putExtra(KEY_NUMBER_FIELD_INTENT, numberField_settings.getText().toString());
        }
//        startActivity(intent);
        startActivityForResult(intent, REQUEST_CODE_SETTING_ACTIVITY);
    }

    // кнопки темная и светлая темы
    @SuppressLint({"ResourceType", "NonConstantResourceId"})
    public void initButtonSetThemeClickListener(int id, int id2, final int codeStyle) {
        Button btn = findViewById(id);
        Button btn2 = findViewById(id2);
        btn.setOnClickListener(v -> {
            // пучаем номер темы настройки
            paramTheme = codeStyle;
            switch (id) {
                case R.id.button_light_mode_setting:
                    btn.setBackgroundColor(getResources().getColor(R.color.light_mode, null));
                    btn2.setBackgroundColor(getResources().getColor(R.color.purple_700, null));
//                    btn.setBackgroundTintMode();
                    break;
                case R.id.button_night_mode_setting:
                    btn.setBackgroundColor(getResources().getColor(R.color.black_mode, null));
                    btn2.setBackgroundColor(getResources().getColor(R.color.purple_700, null));
                    break;
            }
        });
    }

    // кнопка отправить настройки
    private void setTheme() {
        Button btn_theme = findViewById(R.id.button_send_settings);
        btn_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Чтобы стартовать активити, надо подготовить интент
                // В данном случае это будет явный интент, поскольку здесь передаётся класс активити
                Intent runSettings = new Intent(SettingActivity.this, CalculatorActivity4.class);
                // Передача данных через интент
                runSettings.putExtra(TYPE_THEME, paramTheme);
                // Метод стартует активити, указанную в интенте
//                startActivity(runSettings);
            }
        });

    }

    // кнопка отправить данные
    private void setData() {
        Button btn_data = findViewById(R.id.button__send_data);
        btn_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Чтобы стартовать активити, надо подготовить интент
                // В данном случае это будет явный интент, поскольку здесь передаётся класс активити
                Intent runSettings = new Intent(SettingActivity.this, CalculatorActivity4.class);
                // Передача данных через интент
                runSettings.putExtra(KEY_NUMBER_FIELD_INTENT, numberField_settings.getText().toString());
                // Метод стартует активити, указанную в интенте
//                startActivity(runSettings);
            }
        });

    }

    // метод для отправки и получения данных
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SETTING_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    textViewResult.setText(data.getStringExtra(KEY_NUMBER_FIELD_INTENT));
                }
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    private void recieveDataFromOtherApp() {
        String value = getIntent().getExtras().getString("SEND_DATA_TO_OTHER_APP");
        if (value != null) {
            numberField_settings.setText(value);
        }
    }
}