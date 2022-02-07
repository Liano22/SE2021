package com.example.myapplication.dogCreation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.dashboard.DashboardPresenter;
import com.google.android.material.textfield.TextInputLayout;

public class DogCreationView extends AppCompatActivity implements IDogCreationContract.IView {

    // Elemente aus der xml
    Button dogSaveBtn;
    TextInputLayout name, age, race, pic, bio, price;
    CheckBox hybrid, papers;

    RadioGroup genderCheckGroup;
    RadioButton genderCheckBtn;

    String gender;

    // Verbindung zum Presenter
    DogCreationPresenter dogCreationPresenter = new DogCreationPresenter(this);

    Intent intentDashboardFromDogCreation;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dog_creation);

        // Zuweisungen:
        dogSaveBtn = findViewById(R.id.dogSaveBtn);

        genderCheckGroup = findViewById(R.id.dogGenderCheck);

        name = findViewById(R.id.dogName);
        age = findViewById(R.id.dogAge);
        race = findViewById(R.id.dogRace);
        pic = findViewById(R.id.dogPic);
        bio = findViewById(R.id.dogBio);
        price = findViewById(R.id.dogPrice);

        hybrid = findViewById(R.id.checkDogHybrid);
        papers = findViewById(R.id.checkDogPapers);

        //Aktueller User wird aus Intent ausgelesen
        String currentUser;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                currentUser= null;
            } else {
                currentUser= extras.getString("currentUser");
            }
        } else {
            currentUser= (String) savedInstanceState.getSerializable("currentUser");
        }

        //Neuer Intent nach Dashboard wir gepackt, User wird beigelegt
        intentDashboardFromDogCreation = new Intent(this, DashboardPresenter.class);
        intentDashboardFromDogCreation.putExtra("currentUser", currentUser);

        dogSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // die Einträge des Nutzers werden als Strings an den Presenter weitergereicht
                dogCreationPresenter.saveDog(currentUser,name.getEditText().getText().toString(), age.getEditText().getText().toString(), gender,
                        race.getEditText().getText().toString(),pic.getEditText().getText().toString(), bio.getEditText().getText().toString(),
                        price.getEditText().getText().toString(), hybrid.isChecked(), papers.isChecked());
            }
        });
    }
    public void checkRadioButton(View view) {
        int radioId = genderCheckGroup.getCheckedRadioButtonId();
        genderCheckBtn = findViewById(radioId);
        gender = genderCheckBtn.getText().toString();
    }

    @Override
    public void setErrorMessage(String field, String errorMessage) {
        switch (field){
            case "name" :
                name.setError(errorMessage);
            case "age" :
                age.setError(errorMessage);
            case "race" :
                race.setError(errorMessage);
            case "bio" :
                bio.setError(errorMessage);
            case "price" :
                price.setError(errorMessage);
        }
    }

    @Override
    public void changeToDashboard() {
        startActivity(intentDashboardFromDogCreation);
    }
}
