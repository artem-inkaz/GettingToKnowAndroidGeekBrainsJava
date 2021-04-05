package com.example.gettingtoknowandroidgeekbrainsjava.lesson10;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gettingtoknowandroidgeekbrainsjava.ChangeFragment;
import com.example.gettingtoknowandroidgeekbrainsjava.R;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes.NotesCityFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

public class AuthFragment extends Fragment {

    public static final String TAG = "HomeFragment";

    private AuthViewModel mViewModel;

    private GoogleSignInClient googleSignInClient;
    private static final int RC_SIGN_IN = 1;
    // Кнопка регистрации через Google
    private com.google.android.gms.common.SignInButton buttonSignIn;
    private Button buttonSignOut;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private String  email;
    private String  password;
    private SignInOut signInOut;

    public static AuthFragment newInstance() {
        return new AuthFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Проверим, входил ли пользователь в это приложение через Google
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
        if (account != null) {
            // Пользователь уже входил, сделаем кнопку недоступной
            disableSign();
            // Обновим почтовый адрес этого пользователя и выведем его на экран
//            updateUI(account.getEmail());
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        signInOut = (SignInOut) getContext();
    }

    @Override
    public void onDetach() {
        signInOut = null;
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        // Получаем клиента для регистрации и данные по клиенту
        googleSignInClient = GoogleSignIn.getClient(getContext(), gso);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.auth_fragment, container, false);
        initGoogleSign();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextEmail = view.findViewById(R.id.edit__text_email_address);
        editTextPassword = view.findViewById(R.id.edit_text_password);
        buttonSignOut = view.findViewById(R.id.button_sign_out);
        buttonSignIn  = view.findViewById(R.id.sign_in_button);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
//                signInOut.interfaceSignIn();
//                buttonSignIn.setEnabled(true);
//                buttonSignOut.setEnabled(false);
            }
        });
        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                signInOut.interfaceSignOut();
                buttonSignIn.setEnabled(false);
                buttonSignOut.setEnabled(true);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        if (requestCode == RC_SIGN_IN) {
            // Когда сюда возвращается Task, результаты аутентификации уже
            // готовы
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    // Инициализация запроса на аутентификацию
    private void initGoogleSign() {
        // Конфигурация запроса на регистрацию пользователя, чтобы получить
        // идентификатор пользователя, его почту и основной профайл
        // (регулируется параметром)
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Получаем клиента для регистрации и данные по клиенту
        googleSignInClient = GoogleSignIn.getClient(getContext(), gso);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//            Toast.makeText(requireContext(),"Авторизация прошла успешно", Toast.LENGTH_LONG).show();
            // Регистрация прошла успешно
            signInOut.interfaceSignIn();
            buttonSignIn.setEnabled(false);
            buttonSignOut.setEnabled(true);
            addFragmentBottom(new NotesCityFragment(),"NotesCityFragment");
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure
            // reason. Please refer to the GoogleSignInStatusCodes class
            // reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }
    // Инициируем регистрацию пользователя
    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    // Выход из учётной записи в приложении
    private void signOut() {
        googleSignInClient.signOut()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
//                        updateUI("");
                        enableSign();
                    }
                });
    }

    private void signInEmailPassword(){
      email =  editTextEmail.toString();
      password =  editTextPassword.toString();
    }


    // Разрешить аутентификацию и запретить остальные действия
    private void enableSign(){
        buttonSignIn.setEnabled(true);
        buttonSignOut.setEnabled(false);
        signInOut.interfaceSignOut();
//        continue_.setEnabled(false);
//        buttonSingOut.setEnabled(false);
    }

    // Запретить аутентификацию (уже прошла) и разрешить остальные действия
    private void disableSign(){
        buttonSignIn.setEnabled(false);
        buttonSignOut.setEnabled(true);
        signInOut.interfaceSignIn();
//        continue_.setEnabled(true);
//        buttonSingOut.setEnabled(true);
    }

    private void addFragmentBottom(Fragment fragment, String tag) {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, tag)
                .commit();
    }

}