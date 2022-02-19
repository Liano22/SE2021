package com.example.myapplication.filter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.dogSearch.DogSearchView;

public class FilterView extends AppCompatActivity implements IFilterContract.IView {
//Karl & Ralf

    //Initialisieren einer Variable
    FilterPresenter filterPresenter = new FilterPresenter(this);

    //Deklaration von Variablen
    private Spinner raceSpinner, ageSpinner;
    private EditText priceFrom, priceTo;
    Button updateFilterPreferences;
    String dogID, dogGender;
    String papersAvailable = "optional";
    RadioGroup papersCheckGroup;
    RadioButton papersCheckBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_preferences);

        //Zuweisungen
        if (getIntent().hasExtra("dogID")) {
            Bundle extra = getIntent().getExtras();
            dogID = extra.getString("dogID");
            dogGender = extra.getString("dogGender");
        }
        updateFilterPreferences = findViewById(R.id.preferences_button);
        raceSpinner = findViewById(R.id.rasse_spinner);
        ageSpinner = findViewById(R.id.alter_spinner);
        priceFrom = findViewById(R.id.text_min_preis);
        priceTo = findViewById(R.id.text_max_preis);
        papersCheckGroup = findViewById(R.id.searchPapersCheck);

        //Die beiden Spinner mittels Adapter zur strings.xml in res/values befüllen
        ArrayAdapter<CharSequence> raceAdapter = ArrayAdapter.createFromResource(this, R.array.races, android.R.layout.simple_spinner_item);
        raceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        raceSpinner.setAdapter(raceAdapter);

        ArrayAdapter<CharSequence> ageAdapter = ArrayAdapter.createFromResource(this, R.array.ages, android.R.layout.simple_spinner_item);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageSpinner.setAdapter(ageAdapter);

        //Filter Knopf wird gedrückt und Daten werden an die DogSearch übertragen
        updateFilterPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String race = raceSpinner.getSelectedItem().toString();
                String age = ageSpinner.getSelectedItem().toString();
                String minPrice = priceFrom.getText().toString();
                String maxPrice = priceTo.getText().toString();

                //Die Einträge des Nutzers werden als Objekt an die DogSearchView weitergereicht.
                //Hierbei müssen alle Felder ausgefüllt sein,
                //ansonsten wird eine Fehlermeldung mit der Aufforderung zum Ausfüllen aller Felder gesendet.

                if (filterPresenter.validateFields(race, age, minPrice, maxPrice, papersAvailable)) {
                    //filterPresenter.filter(race, age, minPrice, maxPrice, papersAvailable);
                    Intent searchIntent = new Intent(FilterView.this, DogSearchView.class);
                    Filter filter = new Filter(race, age, minPrice, maxPrice, papersAvailable);
                    searchIntent.putExtra("filterSettings", filter);
                    searchIntent.putExtra("dogID", dogID);
                    searchIntent.putExtra("dogGender", dogGender);
                    startActivity(searchIntent);
                }
            }
        });


    }

    public void setErrorMessage(String field, String errorMessage) {

        switch (field) {
            case "minPrice":
                priceFrom.setError(errorMessage);
            case "maxPrice":
                priceTo.setError(errorMessage);
        }
    }

    public void checkRadioButton(View view) {
        int radioId = papersCheckGroup.getCheckedRadioButtonId();
        papersCheckBtn = findViewById(radioId);
        papersAvailable = papersCheckBtn.getText().toString();
    }
}







