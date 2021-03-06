package com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gettingtoknowandroidgeekbrainsjava.ChangeFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.Constants;
import com.example.gettingtoknowandroidgeekbrainsjava.R;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson10.AuthFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson10.SignInOut;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson6.NotesDetailsFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson7.ui.HomeNoteFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson7.ui.NoteFavouriteFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson7.ui.SettingFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson7.ui.SignInFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesCity;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes.NotesCityFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes.NotesViewModel;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes.NotesViewModelFactory;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes.adapters.NotesCityAdapter;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notesdetails.NoteDetailFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson9.ui.ActionInterface;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson9.ui.AddNewFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson9.ui.update.UpdateNoteFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class NavigationNoteBookActivity2 extends AppCompatActivity implements ChangeFragment, ActionInterface, SignInOut {

    private NotesViewModel notesViewModel;
    private NotesCityAdapter notesCityAdapter;
    private ActionInterface mListener;
    private BottomNavigationView bottomNavView;
    private  Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_note_book);
        readSettings();
        setTitle("Записная книжка");
        if (savedInstanceState == null) {
            addFragmentBottom(new AuthFragment(), "AuthFragment");
        } else {
//            addFragmentBottom(new NotesCityFragment(), "NotesFragment");
        }

        initView();

        notesViewModel =
                new ViewModelProvider(this, new NotesViewModelFactory()).get(NotesViewModel.class);

        notesCityAdapter = new NotesCityAdapter(new NotesCityFragment());

    }

    private void activityAction() {
//        mListener.addNoteTo();
    }

    private void initView() {
        Toolbar toolbar = initToolbar();
        initDrawer(toolbar);
        intitBottom();
        toolbarPress(toolbar);
    }

    private Toolbar initToolbar() {
//        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    private void toolbarPress(Toolbar toolbar) {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.action_share) {
                    Toast.makeText(NavigationNoteBookActivity2.this, R.string.action_main, Toast.LENGTH_SHORT).show();
                }
                if (menuItem.getItemId() == R.id.action_delete_all) {
                    notesViewModel.clearNotes();
//                    mListener.addNoteTo();
//                    activityAction();
                }
                return false;
            }
        });
    }

    // регистрация drawer
    private void initDrawer(Toolbar toolbar) {
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView = findViewById(R.id.nav_view);
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
//        final BottomNavigationView bottomNavView = findViewById(R.id.bottom_nav);
        bottomNavView = findViewById(R.id.bottom_nav);
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.action_home:
//                        addFragmentBottom(new HomeNoteFragment(), "HomeNoteFragment");
                        addFragmentBottom(new NotesCityFragment(), "HomeNoteFragment");
                        setTitle("Главная.Список записей");
                        break;
                    case R.id.action_favorite:
                        addFragmentBottom(new NoteFavouriteFragment(), "NoteFavouriteFragment");
                        setTitle("Избранные записи");
                        break;
                    case R.id.action_add:
                        addFragmentBottom(new AddNewFragment(), "AddNewFragment");
                        setTitle("Добавить новую запись");
//                        notesViewModel.addNewNote();
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
                Toast.makeText(NavigationNoteBookActivity2.this, query, Toast.LENGTH_SHORT).show();
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
//                addFragmentDrawer(new AuthFragment());
                addFragmentBottom(new AuthFragment(), "AuthFragment");
                setTitle("Войти");
                return true;
            case R.id.action_home_note:
//                addFragmentDrawer(new HomeNoteFragment());
                addFragmentBottom(new NotesCityFragment(), "NotesCityFragment");
//                addFragmentDrawer(new NotesCityFragment());
                setTitle("Главная.Список записей");
                return true;
            case R.id.action_note_favourite:
//                addFragmentDrawer(new NoteFavouriteFragment());
                addFragmentBottom(new NoteFavouriteFragment(), "NoteFavouriteFragment");
                setTitle("Избранные записи");
                return true;
            case R.id.action_note_setting:
//                addFragmentDrawer(new SettingFragment());
                addFragmentBottom(new SettingFragment(), "SettingFragment");
                setTitle("Настройки приложения");
                return true;
            case R.id.action_sign_out:
                addFragmentBottom(new AuthFragment(), "AuthFragment");
//                addFragmentDrawer(new AuthFragment());
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

    private void addFragmentPort(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, tag)
                .commit();
    }

    private void addFragmentLandscape(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.notes_details, fragment, tag)
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

    @Override
    public void gotoFragmentNotesCityDetails(NotesCity notesCity) {
        if (Constants.isLandscapeCity) {
            addFragmentLandscape(new NoteDetailFragment(notesCity), "NoteDetailFragment");
        } else {
            addFragmentPort(new NoteDetailFragment(notesCity), "NoteDetailFragment");
        }
    }

    @Override
    public void gotoFragmentNotesCityUpdate(NotesCity notesCity) {
//        addFragmentPort(new NoteDetailFragment(), "NoteDetailFragment");
        if (Constants.isLandscapeCity) {
            addFragmentLandscape(new UpdateNoteFragment(notesCity), "NoteUpdateFragment");
        } else {
            addFragmentPort(new UpdateNoteFragment(notesCity), "NoteUpdateFragment");
        }
    }

    @Override
    public void addNoteTo() {
//        notesViewModel.clearNotes();
//        Toast.makeText(this, "R", Toast.LENGTH_SHORT).show();
        // Создаём новый фрагмент с текущей позицией для вывода записи
        NotesCityFragment notesCityFragment = NotesCityFragment.newInstance();
        // Выполняем транзакцию по замене фрагмента
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(String.valueOf(new NotesCityFragment()));
//        notesCityFragment.getView().findViewById()
        if (fragment instanceof NotesCityFragment) {

            Toast.makeText(this, "R.string.action_main", Toast.LENGTH_SHORT).show();
//            fragment.getView().findViewById(R.id.recyclerView_container);
//            RecyclerView recyclerView = fragment.getView().findViewById(R.id.recyclerView_container);
//            RecyclerView recyclerView1 = notesCityFragment.getView().findViewById(R.id.recyclerView_container);
//            recyclerView1.setAdapter(notesCityAdapter);
//            notesCityAdapter.clear();
//                        notesCityAdapter.clear();
//                        notesCityAdapter.addItems(notesCities);
//            notesCityAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void interfaceSignIn() {
        bottomNavView.setVisibility(View.VISIBLE);
        toolbar.setVisibility(View.VISIBLE);
        navigationView.setVisibility(View.VISIBLE);
//        addFragmentBottom(new NotesCityFragment(), "NotesCityFragment");
    }

    @Override
    public void interfaceSignOut() {
        bottomNavView.setVisibility(View.INVISIBLE);
        toolbar.setVisibility(View.INVISIBLE);
        navigationView.setVisibility(View.INVISIBLE);
    }
}