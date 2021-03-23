package com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain;

import com.example.gettingtoknowandroidgeekbrainsjava.R;
import java.util.ArrayList;
import java.util.List;

public class ListNotesRepository implements NotesRepositoryInterface {

    public static final NotesRepositoryInterface INSTANCE = new ListNotesRepository();

    @Override
    public List<NotesCity> getNotesCity() {
        ArrayList data = new ArrayList();

        data.add( new NotesCity(1,"Москва",
                "г. Москва. Cтолица России. " +
                "Историческая столица Великого\n" +
                "        княжества Московского, Русского царства, Российской империи\n" +
                "        (в 1728—1730 годах), Советской России и СССР. Город-герой",
                "01.03.2021 г.",
                "https://kudamoscow.ru/uploads/d530151cfc8d48ac1f12c85ed4a0aacf.jpg",
                R.drawable.msc));
        data.add( new NotesCity(2,
                "Санкт-Петербург",
                "г. Санкт-Петербург. Второй по численности населения город России.\n" +
                        "        Город федерального значения.Назван в честь Святого Петра.\n" +
                        "        Был центром трёх революций: 1905—1907 годов,\n" +
                        "        Февральской и Октябрьской революций 1917 года",
                "03.03.2021 г.",
                "https://wallbox.ru/wallpapers/main/201249/70ef643ada8197f.jpg",
                R.drawable.spb));
        data.add( new NotesCity(3,
                "Екатеринбург",
                "г. Екатеринбург. Административный центр Уральского федерального округа\n" +
                        "        и Свердловской области. Основан 7 ноября (18 ноября) 1723 года\n" +
                        "        как железоделательный завод.Имя городу было дано в честь\n" +
                        "        императрицы Екатерины Первой.\n",
                "04.03.2021 г.",
                "https://ekb.stanok-kpo.ru/netcat_files/userfiles/Ekaterinburg-2.jpg",
                R.drawable.ebrg));
        data.add( new NotesCity(4,
                "Новосибирск",
                "г. Новосибирск. Третий по численности населения город России." +
                        "Крупнейший торговый,\n" +
                        "        деловой, культурный, транспортный, образовательный и " +
                        "научный центр Сибири.\n\n",
                "05.03.2021 г.",
                "http://fresher.ru/images7/novosibirsk-s-nevozmozhnyx-rakursov/big/6.jpg",
                R.drawable.nsk));
        data.add( new NotesCity(5,
                "Самара",
                "г. Самара. Город трудовой и боевой славы. " +
                        "Восьмой по численности населения город России." +
                        "Крупный экономический, транспортный, научно-образовательный и культурный центр\n\n",
                "05.03.2021 г.",
                "http://fresher.ru/images7/novosibirsk-s-nevozmozhnyx-rakursov/big/6.jpg",
                R.drawable.sam));
        return data;
    }
}
