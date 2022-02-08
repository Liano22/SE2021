package com.example.myapplication.dogCreation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.dashboard.DashboardPresenter;
import com.example.myapplication.dashboard.DashboardView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.UUID;

public class DogCreationView extends AppCompatActivity implements IDogCreationContract.IView {

    // Elemente aus der xml
    Button dogSaveBtn;
    TextInputLayout name, age, race, bio, price;
    CheckBox hybrid, papers;
    ImageView dogPic;

    RadioGroup genderCheckGroup;
    RadioButton genderCheckBtn;

    String gender, pictureKey;

    public Uri imageUri;

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
        dogPic = findViewById(R.id.dogPicCreation);
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
        intentDashboardFromDogCreation = new Intent(this, DashboardView.class);
        intentDashboardFromDogCreation.putExtra("currentUser", currentUser);

        dogSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // die Einträge des Nutzers werden als Strings an den Presenter weitergereicht
                dogCreationPresenter.saveDog(currentUser,name.getEditText().getText().toString(), age.getEditText().getText().toString(), gender,
                        race.getEditText().getText().toString(),pictureKey, bio.getEditText().getText().toString(),
                        price.getEditText().getText().toString(), hybrid.isChecked(), papers.isChecked());
            }
        });

        dogPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPicture();
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

    public void selectPicture(){
        Intent getPicture = new Intent();
        getPicture.setType("image/*");
        getPicture.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(getPicture, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();
            dogPic.setImageURI(imageUri);
            final String randomKey = UUID.randomUUID().toString();
            pictureKey = randomKey;
            dogCreationPresenter.savePicture(imageUri, randomKey);
        }
    }

    public void setSnackbar(String msg){
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG).show();
    }
}
