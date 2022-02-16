package com.example.myapplication.filter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.dogSearch.DogSearchView;

public class FilterView extends AppCompatActivity implements IFilterContract.IView {
//Ralf & Karl
    FilterPresenter filterPresenter = new FilterPresenter(this);
    Button updateFilterPreferences;
    private Spinner raceSpinner, ageSpinner;
    private RadioButton available, optional;
    private EditText priceFrom, priceTo;
    String dogID;


    boolean papersAvailable;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_preferences);

        //Zuweisungen
        if (getIntent().hasExtra("dogID")) {
            Bundle extra = getIntent().getExtras();
            dogID = extra.getString("dogID");
        }
        updateFilterPreferences = findViewById(R.id.preferences_button);
        raceSpinner = findViewById(R.id.rasse_spinner);
        ageSpinner = findViewById(R.id.alter_spinner);
        priceFrom = findViewById(R.id.text_min_preis);
        priceTo = findViewById(R.id.text_max_preis);


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

                // die Einträge des Nutzers werden als Objekt an die DogSearchView weitergereicht.
                if (!race.isEmpty() && !age.isEmpty() && !minPrice.isEmpty() && !maxPrice.isEmpty()) {
                    Filter filter = new Filter(race, age, minPrice, maxPrice, papersAvailable);
                    filterPresenter.filter(race, age, minPrice, maxPrice, papersAvailable);
                    Intent searchIntent = new Intent(FilterView.this, DogSearchView.class);
                    searchIntent.putExtra("filterSettings", filter);
                    searchIntent.putExtra("dogID", dogID);
                    startActivity(searchIntent);
                } else {
                    priceFrom.setError("Bitte alle Felder ausfüllen");
                    priceTo.setError("Bitte alle Felder ausfüllen");
                }
            }

            //Methode um den Boolean Wert bei den Papieren zu setzen.
            public void onRadioButtonClicked(View view) {
                boolean checked = ((RadioButton) view).isChecked();

                switch (view.getId()) {
                    case R.id.available:
                        if (checked) {
                            papersAvailable = true;
                        }
                    case R.id.optional:
                        if (checked) {
                            papersAvailable = false;
                        }
                }
            }
        });


    }


}







