package com.example.gettingtoknowandroidgeekbrainsjava.lesson7;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;
import com.example.gettingtoknowandroidgeekbrainsjava.Constants;
import com.example.gettingtoknowandroidgeekbrainsjava.R;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson6.NotesFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson7.ui.NoteFavouriteFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson7.ui.SettingFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson7.ui.SignInFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class NavigationNoteBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_note_book);
        readSettings();
        setTitle("Записная книжка");
        if (savedInstanceState == null) {
            addFragmentBottom(new NotesFragment(), "NotesFragment");
        }

        initView();
    }

    private void initView() {
        Toolbar toolbar = initToolbar();
        initDrawer(toolbar);
        intitBottom();
        toolbarPress(toolbar);

    }

    private Toolbar initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    private void toolbarPress(Toolbar toolbar) {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.action_share) {
                    Toast.makeText(NavigationNoteBookActivity.this, R.string.action_main, Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    // регистрация drawer
    private void initDrawer(Toolbar toolbar) {
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        // Обработка навигационного меню
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (navigateFragment(id)) {
//                Toast.makeText(NavigationNoteBookActivity.this, "navigateFragment", Toast.LENGTH_SHORT).show();
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
            return false;
        });
    }

    private void SnackbarShow(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @SuppressLint("ResourceAsColor")
    private void intitBottom() {
        final BottomNavigationView bottomNavView = findViewById(R.id.bottom_nav);
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.action_home:
//                        addFragmentBottom(new HomeNoteFragment(), "HomeNoteFragment");
                        addFragmentBottom(new NotesFragment(), "HomeNoteFragment");
                        setTitle("Главная.Список записей");
                        break;
                    case R.id.action_favorite:
                        addFragmentBottom(new NoteFavouriteFragment(), "NoteFavouriteFragment");
                        setTitle("Избранные записи");
                        break;
                    case R.id.action_add:
//                        addFragmentBottom(new MainFragment(), "MainFragment");
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_toolbar, menu);
        MenuItem search = menu.findItem(R.id.action_search); // поиск пункта меню поиска
        SearchView searchText = (SearchView) search.getActionView(); // строка поиска
        searchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // реагирует на конец ввода поиска
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(NavigationNoteBookActivity.this, query, Toast.LENGTH_SHORT).show();
                return true;
            }

            // реагирует на нажатие каждой клавиши
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Обработка выбора пункта меню приложения (активити)
        int id = item.getItemId();
        if (id == R.id.action_share) {
//            Toast.makeText(NavigationNoteBookActivity.this, R.string.action_main, Toast.LENGTH_SHORT).show();
        } else if (navigateFragment(id)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    private boolean navigateFragment(int id) {
        switch (id) {
            case R.id.action_sign_in:
                addFragmentDrawer(new SignInFragment());
                setTitle("Войти");
                return true;
            case R.id.action_home_note:
//                addFragmentDrawer(new HomeNoteFragment());
                addFragmentDrawer(new NotesFragment());
                setTitle("Главная.Список записей");
                return true;
            case R.id.action_note_favourite:
                addFragmentDrawer(new NoteFavouriteFragment());
                setTitle("Избранные записи");
                return true;
            case R.id.action_note_setting:
                addFragmentDrawer(new SettingFragment());
                setTitle("Настройки приложения");
                return true;
            case R.id.action_sign_out:
//                addFragmentDrawer(new FavoriteFragment());
                return true;
        }
        return false;
    }

    private void addFragmentDrawer(Fragment fragment) {

        //Получить менеджер фрагментов
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Открыть транзакцию
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Удалить видимый фрагмент
        if (Constants.IsDeleteBeforeAdd) {
            Fragment fragmentToRemove = getVisibleFragment(fragmentManager);
            if (fragmentToRemove != null) {
                fragmentTransaction.remove(fragmentToRemove);
            }
        }

        // Добавить фрагмент
        if (Constants.IsAddFragment) {
            fragmentTransaction.add(R.id.fragment_container, fragment, "ADD_TAG");
        } else {
            fragmentTransaction.replace(R.id.fragment_container, fragment, "TAG");
        }

//        fragmentTransaction.show(fragment);
//        fragmentTransaction.hide(fragment);

        // Добавить транзакцию в бакстек
        if (Constants.IsBackStack) {
            fragmentTransaction.addToBackStack("Label");
        }

        // Закрыть транзакцию
        fragmentTransaction.commit();

        fragmentManager.popBackStack("Label", 0);

//        Fragment tagFragment = fragmentManager.findFragmentByTag("TAG");
//        Fragment tagFragment2 = fragmentManager.findFragmentById(R.id.action_add);
//
//        if (tagFragment instanceof FavoriteFragment) {
//
        //}
    }

    private Fragment getVisibleFragment(FragmentManager fragmentManager) {
        List<Fragment> fragments = fragmentManager.getFragments();
        int countFragments = fragments.size();
        for (int i = countFragments - 1; i >= 0; i--) {
            Fragment fragment = fragments.get(i);
            if (fragment.isVisible())
                return fragment;
        }
        return null;
    }

    private void addFragmentBottom(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, tag)
                .commit();
    }

    // Чтение настроек
    private void readSettings() {
        // Специальный класс для хранения настроек
        SharedPreferences sharedPref = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
        // Считываем значения настроек
        Constants.IsBackStack = sharedPref.getBoolean(Constants.IS_BACK_STACK_USED, false);
        Constants.IsAddFragment = sharedPref.getBoolean(Constants.IS_ADD_FRAGMENT_USED, true);
        Constants.IsBackAsRemove = sharedPref.getBoolean(Constants.IS_BACK_AS_REMOVE_FRAGMENT, true);
        Constants.IsDeleteBeforeAdd = sharedPref.getBoolean(Constants.IS_DELETE_FRAGMENT_BEFORE_ADD, false);
    }
}