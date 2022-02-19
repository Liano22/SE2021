package com.example.myapplication.logIn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.dashboard.DashboardPresenter;
import com.example.myapplication.dashboard.DashboardView;
import com.example.myapplication.filter.FilterView;
import com.example.myapplication.signUp.SignUpView;
import com.google.android.material.textfield.TextInputLayout;

public class LogInView extends AppCompatActivity implements ILogInContract.IView {

    //Deklaration von Variablen
    private TextInputLayout username, password;
    private Button logInBtn, logInToSignUpBtn;
    private LogInPresenter logInPresenter = new LogInPresenter(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        //Zuweisungen:
        logInBtn = findViewById(R.id.logInBtn);
        logInToSignUpBtn = findViewById(R.id.logInToSignUpBtn);
        username = findViewById(R.id.usernameLogIn);
        password = findViewById(R.id.passwordLogIn);


        //Intent: Wechsel zur Registrierung
        Intent intentSignUp = new Intent(this, SignUpView.class);


        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logInPresenter.logIn(view, username.getEditText().getText().toString(), password.getEditText().getText().toString()); //Überprüfen der Log-In-Daten
            }
        });

        logInToSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentSignUp); //Wechsel zur Registrierung
            }
        });
        
    }


    /**
     * Ausgabe von Fehlermeldungen, die vom Presenter übergeben werden.
     * @param field Eingabefeld - String
     * @param ErrorMessage Fehlermeldung - String
     */
    @Override
    public void setErrorMessage(String field, String ErrorMessage) {
        if(field.equals("password")) {
            password.setError(ErrorMessage);
        }
        if(field.equals("username")) {
            username.setError(ErrorMessage);
        }
    }

    /**
     * Wechsel zum Dashboard.
     * Aufgerufen nach erfolgreicher Validierung des Benutzers.
     * @param username Aktueller Benutzer (wird unter den Activities weitergereicht) - String
     */
    @Override
    public void changeToDashboard(String username) {
        Intent intentDashboard = new Intent(this, DashboardView.class);
        intentDashboard.putExtra("currentUser", username);
        startActivity(intentDashboard);
    }
}
