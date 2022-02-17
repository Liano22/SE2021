package com.example.myapplication.logIn;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.myapplication.DatabaseConnector;
import com.example.myapplication.dashboard.DashboardPresenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LogInPresenter implements ILogInContract.IPresenter{

    //Deklaration von Variablen
    private ILogInContract.IView logInView;
    private LogInModel logInModel = new LogInModel();

    //Konstruktor der Klasse LogInPresenter
    public LogInPresenter(ILogInContract.IView view){
        this.logInView = view;
    }

    @Override
    public Boolean validateUsername(String username) {

        if(username.isEmpty()){
            logInView.setErrorMessage("Benutzername oder Passwort darf nicht leer sein");
            return false;
        } else {
            logInView.setErrorMessage(null);
            return true;
        }
    }

    @Override
    public Boolean validatePassword(String password) {
        if(password.isEmpty()){
            logInView.setErrorMessage("Benutzername oder Passwort darf nicht leer sein");
            return false;
        } else {
            logInView.setErrorMessage(null);
            return true;
        }
    }

    @Override
    public void logIn(View view, String username, String password) {
        if(!validateUsername(username) | !validatePassword(password)){
            return;
        }
        else{
            userExists(username, password);
        }
    }

    @Override
    public void userExists(String username, String password) {
        Query checkUser = logInModel.readUserFromDatabase(username,"username");

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){ //Konnte ein Nutzer gefunden und gespeichert werden?

                    logInView.setErrorMessage(null);

                    String passwordFromDB = snapshot.child(username).child("password").getValue(String.class); //Passwort aus DB

                    if(passwordFromDB.equals(password)){ //Gleichen die Passwörter einander?
                        logInView.setErrorMessage(null);
                        logInView.changeToDashboard(username);
                    }
                    else{
                        logInView.setErrorMessage("Benutzername oder Passwort falsch");
                    }
                }
                else {
                    logInView.setErrorMessage("Benutzername oder Passwort falsch");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

