package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    TextInputLayout benutzername, passwort;

    Intent intent = new Intent(this, Dashboard.class);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        benutzername = findViewById(R.id.benutzernameLogIn);
        passwort = findViewById(R.id.passwortLogIn);
    }

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

    public void LogIn(View view){
        if(!validateUsername() | !validatePassword()){
            return;
        }
        else{
            userExists();
        }
    }

    private void userExists(){
        String benutzernameInput = benutzername.getEditText().getText().toString();
        String passwortInput = passwort.getEditText().getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("NutzerTest");

        Query checkUser = reference.orderByChild("username").equalTo(benutzernameInput);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    benutzername.setError(null);
                    benutzername.setErrorEnabled(false);

                    String passwortAusDB = snapshot.child(benutzernameInput).child("passwort").getValue(String.class);

                    if(passwortAusDB.equals(benutzernameInput)){
                        passwort.setError(null);
                        passwort.setErrorEnabled(false);
                        startActivity(intent);
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
