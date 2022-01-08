package com.example.myapplication;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class LogIn extends AppCompatActivity {

    TextInputLayout email, passwort;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        email = findViewById(R.id.EmailLogIn);
        passwort = findViewById(R.id.passwortLogIn);
    }

    public void logIn(View view){
        if(!validateFields()){
            return;
        }

        String emailTxt = email.getEditText().getText().toString().trim();
        String passwortTxt = passwort.getEditText().getText().toString().trim();

        Query checkUser = FirebaseDatabase.getInstance().getReference("nutzer/nutzer_id").orderByChild("email").equalTo(emailTxt);
    }

    private boolean validateFields(){
        return true;
    }
}
