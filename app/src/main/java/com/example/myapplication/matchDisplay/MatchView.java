package com.example.myapplication.matchDisplay;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MatchView extends AppCompatActivity {

    String interestDog;
    TextView phoneView;
    TextView emailView;
    MatchPresenter matchPresenter = new MatchPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match);

        phoneView = findViewById(R.id.phoneMatch);
        emailView = findViewById(R.id.emailMatch);

        if (getIntent().hasExtra("interestDog")) {
            Bundle extra = getIntent().getExtras();
            interestDog = extra.getString("interestDog");
        } else {
            Log.d("Kein Extra!", "Intent überreicht keinen Dog");
        }
        matchPresenter.getValues(interestDog);
    }

    /**
     * Lässt die TextView-Felder folgendes anzeigen:
     * @param phoneNumber Die Telefonnumer des Hundebesitzers
     * @param email Die Email-Adresse des Hundebesitzers
     */
    public void setValues(String phoneNumber, String email){
        phoneView.setText("Telefonnumer: " + phoneNumber);
        emailView.setText("E-Mail Adresse: " + email);
    }

}
