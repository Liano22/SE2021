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

public class LogInView extends AppCompatActivity implements ILogInContract.IView{

    EditText username, password;

    Button logInBtn, logInToSignUpBtn;
    LogInPresenter logInPresenter = new LogInPresenter(this);

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
                logInPresenter.logIn(view,username.getText().toString(), password.getText().toString()); //Überprüfen der Log-In-Daten
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
