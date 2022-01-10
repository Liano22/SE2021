package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LogIn extends AppCompatActivity {

    //Variablen:
    TextInputLayout benutzername, passwort;

    Button goToSignUp, letTheUserLogIn;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        //Zuweisungen:
        letTheUserLogIn = findViewById(R.id.letTheUserLogIn);
        goToSignUp = findViewById(R.id.regBtnLogIn);
        benutzername = findViewById(R.id.benutzernameLogIn);
        passwort = findViewById(R.id.passwortLogIn);

        //Intent: Wechsel zur Registrierung
        Intent intentSignUp = new Intent(this, SignUp.class);

        letTheUserLogIn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                LogIn(view); //Überprüfen der Log-In-Daten
            }
        });

        goToSignUp.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(intentSignUp); //Wechsel zur Registrierung
            }
        });
    }

    /**
     * Validierung des Benutzernamens.
     * Benutzername darf nicht leer sein.
     * TextInputLayout ermöglicht Error-Ausgaben, wird der Name akzeptiert,
     * werden eventuelle vorherige Fehler und Ausgaben gelöscht.
     * @return Boolean. True, wenn akzeptiert. False, wenn nicht.
     */
    private Boolean validateUsername(){
        String val = benutzername.getEditText().getText().toString();

        if(val.isEmpty()){
            benutzername.setError("Benutzername darf nicht leer sein");
            return false;
        } else {
            benutzername.setError(null);
            benutzername.setErrorEnabled(false);
            return true;
        }
    }

    /**
     * Validierung des Passworts.
     * Passwort darf nicht leer sein.
     * TextInputLayout ermöglicht Error-Ausgaben, wird das Passwort akzeptiert,
     * werden eventuelle vorherige Fehler und Ausgaben gelöscht.
     * @return Boolean. True, wenn akzeptiert. False, wenn nicht.
     */
    private Boolean validatePassword() {

        String val = passwort.getEditText().getText().toString();

        if(val.isEmpty()){
            passwort.setError("Passwort darf nicht leer sein");
            return false;
        } else {
            passwort.setError(null);
            passwort.setErrorEnabled(false);
            return true;
        }

    }

    /**
     * Aufruf zur Überprüfung des Benutzernamens und des Passworts.
     * Abbruch, falls invalide.
     * Andernfalls überprüfung der Existenz des Nutzers in der Datenbank.
     * @param view
     */
    public void LogIn(View view){
        if(!validateUsername() | !validatePassword()){
            return;
        }
        else{
            userExists();
        }
    }

    /**
     * Über eine Referenz zu den gespeicherten Nutzern der Datenbank werden die Eingaben abgeglichen.
     * Es wird versucht, einen der Eingabe entsprechenden Nutzer in der Datenbank zu finden.
     * Dieser wird in checkUser zwischengespeichert.
     * Existiert ein Nutzer mit entsprechendem Namen, wird das Passwort aus der DB mit der Eingabe abgeglichen,
     * falls korrekt, erfolgt eine Weiterleitung auf das Dashboard. Ansonsten Ausgabe einer Fehlermeldung.
     * Existiert kein entsprechender Nutzer in der DB, erfolgt ebenfalls eine Fehlermeldung.
     */
    private void userExists(){

        //Texteingaben:
        String benutzernameInput = benutzername.getEditText().getText().toString();
        String passwortInput = passwort.getEditText().getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("NutzerTest");

        Query checkUser = reference.orderByChild("benutzername").equalTo(benutzernameInput); //Zwischenspeichern des Nutzers aus DB

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){ //Konnte ein Nutzer gefunden und gespeichert werden?

                    benutzername.setError(null); //Falls es mehrere Versuche/Fehler gab, muss die Ausgabe entfernt werden
                    benutzername.setErrorEnabled(false);

                    String passwortAusDB = snapshot.child(benutzernameInput).child("passwort").getValue(String.class); //Passwort aus DB

                    if(passwortAusDB.equals(passwortInput)){ //Gleichen die Passwörter einander?
                        passwort.setError(null);
                        passwort.setErrorEnabled(false);
                        Intent intentDashboard = new Intent(getApplicationContext(),Dashboard.class);
                        startActivity(intentDashboard);
                    }
                    else{
                        passwort.setError("Falsches Passwort");
                    }
                }
                else {
                    benutzername.setError("Dieser Nutzer existiert nicht");
                    benutzername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
