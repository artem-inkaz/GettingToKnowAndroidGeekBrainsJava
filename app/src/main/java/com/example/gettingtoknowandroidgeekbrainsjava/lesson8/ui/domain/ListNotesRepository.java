package com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.RequiresApi;

import com.example.gettingtoknowandroidgeekbrainsjava.R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListNotesRepository implements NotesRepository {

    public static final NotesRepository INSTANCE = new ListNotesRepository();

    private final Executor executor = Executors.newCachedThreadPool();

    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    public void getNotesCity(Callback<List<NotesCity>> callback) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        ArrayList<NotesCity> data = new ArrayList();

        data.add(new NotesCity("1", "Москва",
                "г. Москва. Cтолица России. " +
                        "Историческая столица Великого" +
                        "княжества Московского, Русского царства, Российской империи" +
                        "(в 1728—1730 годах), Советской России и СССР. Город-герой",
                "01.03.2021 г.",
                "https://kudamoscow.ru/uploads/d530151cfc8d48ac1f12c85ed4a0aacf.jpg",
                "https://kudamoscow.ru/uploads/d530151cfc8d48ac1f12c85ed4a0aacf.jpg"));
        data.add(new NotesCity("2",
                "Санкт-Петербург",
                "г. Санкт-Петербург. Второй по численности населения город России." +
                        "Город федерального значения.Назван в честь Святого Петра." +
                        "Был центром трёх революций: 1905—1907 годов," +
                        "Февральской и Октябрьской революций 1917 года",
                "03.03.2021 г.",
                "https://wallbox.ru/wallpapers/main/201249/70ef643ada8197f.jpg",
                "https://wallbox.ru/wallpapers/main/201249/70ef643ada8197f.jpg"));
        data.add(new NotesCity("3",
                "Екатеринбург",
                "г. Екатеринбург. Административный центр Уральского федерального округа" +
                        "и Свердловской области. Основан 7 ноября (18 ноября) 1723 года" +
                        "как железоделательный завод.Имя городу было дано в честь" +
                        "императрицы Екатерины Первой.",
                "04.03.2021 г.",
                "https://ekb.stanok-kpo.ru/netcat_files/userfiles/Ekaterinburg-2.jpg",
                "https://ekb.stanok-kpo.ru/netcat_files/userfiles/Ekaterinburg-2.jpg"));
        data.add(new NotesCity("4",
                "Новосибирск",
                "г. Новосибирск. Третий по численности населения город России." +
                        "Крупнейший торговый," +
                        "деловой, культурный, транспортный, образовательный и " +
                        "научный центр Сибири.",
                "05.03.2021 г.",
                "http://fresher.ru/images7/novosibirsk-s-nevozmozhnyx-rakursov/big/6.jpg",
                "http://fresher.ru/images7/novosibirsk-s-nevozmozhnyx-rakursov/big/6.jpg"));
        data.add(new NotesCity("5",
                "Самара",
                "г. Самара. Город трудовой и боевой славы. " +
                        "Восьмой по численности населения город России." +
                        "Крупный экономический, транспортный, научно-образовательный и культурный центр",
                "05.03.2021 г.",
                "http://fresher.ru/images7/novosibirsk-s-nevozmozhnyx-rakursov/big/6.jpg",
                "http://fresher.ru/images7/novosibirsk-s-nevozmozhnyx-rakursov/big/6.jpg"));

                mainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResult(data);
                    }
                });
            }
        });
    }

    @Override
    public void clearNotes(Callback<Object> voidCallback) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        voidCallback.onResult(new Object());
                    }
                });

            }
        });
    }

    @Override
    public void addNewNote(Callback<NotesCity> noteCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {

        NotesCity notesCity = new NotesCity("1", "Харьков",
                "г. Харьков . г. на Украине. ",
                "28.03.2021 г.",
                "https://kudamoscow.ru/uploads/d530151cfc8d48ac1f12c85ed4a0aacf.jpg",
                "https://kudamoscow.ru/uploads/d530151cfc8d48ac1f12c85ed4a0aacf.jpg");
                        noteCallback.onResult(notesCity);
                    }
                });
            }
        });
    }

    @Override
    public void addNewNote2(NotesCity notesCity, Callback<NotesCity> noteCallback) {

    }

    @Override
    public void updateNote(NotesCity notesCity,Callback<Object> objectCallback) {
        executor.execute(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                mainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        NotesCity notesCity = new NotesCity("1", "Харьков",
                                "г. Харьков . г. на Украине. ",
                                "28.03.2021 г.",
                                "https://kudamoscow.ru/uploads/d530151cfc8d48ac1f12c85ed4a0aacf.jpg",
                                "https://kudamoscow.ru/uploads/d530151cfc8d48ac1f12c85ed4a0aacf.jpg");
                        objectCallback.onResult(notesCity);
                    }
                });
            }
        });
    }

    @Override
    public void deleteNote(NotesCity notesCity, Callback<Object> objectCallback) {
        objectCallback.onResult(new Object());
    }
}
