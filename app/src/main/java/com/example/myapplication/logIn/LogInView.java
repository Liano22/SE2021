package com.example.myapplication.logIn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.SignUp;
import com.example.myapplication.dashboard.DashboardPresenter;
import com.google.android.material.textfield.TextInputLayout;

public class LogInView extends AppCompatActivity implements ILogInContract.IView{

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
        Intent intentSignUp = new Intent(this, SignUp.class);

        logInBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                logInPresenter.logIn(view,username.getEditText().getText().toString(), password.getEditText().getText().toString()); //Überprüfen der Log-In-Daten
            }
        });

        logInToSignUpBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(intentSignUp); //Wechsel zur Registrierung
            }
        });
    }

    @Override
    public void setErrorMessage(String ErrorMessage) {
        password.setError(ErrorMessage);
        username.setError(ErrorMessage);
    }

    @Override
    public void changeToDashboard(String username) {
        Intent intentDashboard = new Intent(this, DashboardPresenter.class);
        intentDashboard.putExtra("currentUser", username);
        startActivity(intentDashboard);
    }
}
