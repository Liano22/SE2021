package com.example.myapplication.logIn;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.DatabaseConnector;
import com.example.myapplication.dashboard.DashboardPresenter;
import com.example.myapplication.dogCreation.DogCreationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LogInPresenter implements ILogInContract.IPresenter{

    //Deklaration von Variablen
    private LogInView logInView;
    private LogInModel logInModel;

    //Konstruktor der Klasse LogInPresenter
    public LogInPresenter(LogInView view){
        this.logInView = view;
        logInModel = new LogInModel();
    }

    public LogInPresenter(LogInView logInView, LogInModel logInModel) {
        this.logInView = logInView;
        this.logInModel = logInModel;
    }

    /**
     * Überprüft, ob eine Eingabe des Benutzernamens stattgefunden hat.
     * @param username Eingabe des Benutzers - String
     * @return Boolean: true, falls String nicht leer; false, falls String leer.
     */
    @Override
    public Boolean validateUsername(String username) {

        if(username.isEmpty()){
            logInView.setErrorMessage("username","Benutzername darf nicht leer sein");
            return false;
        } else {
            logInView.setErrorMessage("username",null);
            return true;
        }
    }

    /**
     * Überprüft, ob eine Eingabe des Passworts stattgefunden hat
     * @param password Eingabe des Benutzers - String
     * @return Boolean: true, falls String nicht leer; false, falls String leer.
     */
    @Override
    public Boolean validatePassword(String password) {
        if(password.isEmpty()){
            logInView.setErrorMessage("password","Passwort darf nicht leer sein");
            return false;
        } else {
            logInView.setErrorMessage("password",null);
            return true;
        }
    }

    /**
     * Verwaltet den Vorgang des Einloggens.
     * Verbindet die Funktionene validateUsername(), validatePasswort() und userExists().
     * @param view aktuelle View - View
     * @param username Benutzername - String
     * @param password Passwort - String
     */
    @Override
    public void logIn(View view, String username, String password) {
        if(!validateUsername(username) | !validatePassword(password)){
            return;
        }
        else{
            userExists(username, password);
        }
    }

    /**
     * Überprüft, ob ein passender Benutzer existiert.
     * Zuerst wird über das Model versucht, den gesuchten Benutzer zu laden. Existiert dieser,
     * wird das Passwort aus der Datenbank mit dem eingegebenen Passwort abgeglichen.
     * Stimmt beides überein, erfolgt eine Weiterleitung auf das Dashboard, andernfalls wird eine
     * Fehlermeldung ausgegeben. Existiert ein solcher User nicht, wird ebenfalls eine Fehlermeldung
     * ausgegeben.
     * @param username Benutzername - String
     * @param password Passwort - String
     */
    @Override
    public void userExists(String username, String password) {
        Query checkUser = logInModel.readUserFromDatabase(username,"username");

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){ //Konnte ein Nutzer gefunden und gespeichert werden?

                    logInView.setErrorMessage("username",null);
                    logInView.setErrorMessage("password",null);

                    String passwordFromDB = snapshot.child(username).child("password").getValue(String.class); //Passwort aus DB

                    if(passwordFromDB.equals(password)){ //Gleichen die Passwörter einander?
                        logInView.changeToDashboard(username);
                    }
                    else{
                        logInView.setErrorMessage("username","Benutzername oder Passwort falsch");
                        logInView.setErrorMessage("password","Benutzername oder Passwort falsch");
                    }
                }
                else {
                    logInView.setErrorMessage("username","Benutzername oder Passwort falsch");
                    logInView.setErrorMessage("password","Benutzername oder Passwort falsch");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(logInView.getApplicationContext(), "Es ist ein Fehler aufgetreten.", Toast.LENGTH_LONG);
            }
        });
    }
}

