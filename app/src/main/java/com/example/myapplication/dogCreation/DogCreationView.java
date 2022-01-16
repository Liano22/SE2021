package com.example.myapplication.dogCreation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.dashboard.DashboardPresenter;

public class DogCreationView extends AppCompatActivity implements IDogCreationContract.IView {

    // Elemente aus der xml
    Button dogSaveBtn;
    EditText name, age, gender, race, pic, bio, price;
    CheckBox hybrid, papers;

    // Verbindung zum Presenter
    DogCreationPresenter dogCreationPresenter = new DogCreationPresenter(this);

    // TODO Intent weiterleiten zu Dashboard
    // Intent intentDashboardFromDogCreation = new Intent(this, DashboardPresenter.class);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dog_creation);

        // Zuweisungen:
        dogSaveBtn = findViewById(R.id.dogSaveBtn);

        name = findViewById(R.id.dogName);
        age = findViewById(R.id.dogAge);
        gender = findViewById(R.id.dogGender);
        race = findViewById(R.id.dogRace);
        pic = findViewById(R.id.dogPic);
        bio = findViewById(R.id.dogBio);
        price = findViewById(R.id.dogPrice);

        hybrid = findViewById(R.id.checkDogHybrid);
        papers = findViewById(R.id.checkDogPapers);

        dogSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // die Einträge des Nutzers werden als Strings an den Presenter weitergereicht
                dogCreationPresenter.saveDog(name.getText().toString(), age.getText().toString(), gender.getText().toString(), race.getText().toString(),
                        pic.getText().toString(), bio.getText().toString(), price.getText().toString(), hybrid.isChecked(), papers.isChecked());
            }
        });
    }

    @Override
    public void setErrorMessage(String ErrorMessage) {
        // TODO Fehlermeldung bei leeren Feldern hinzufügen
    }

    @Override
    public void changeToDashboard(String username) {
        // TODO weiterleiten zu Dashboard
        //startActivity(intentDashboardFromDogCreation);
    }
}
