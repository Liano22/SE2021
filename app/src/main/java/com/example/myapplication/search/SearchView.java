package com.example.myapplication.search;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class SearchView extends AppCompatActivity implements ISearchContract.IView {

    SearchPresenter searchPresenter = new SearchPresenter(this);
    //String race, age, priceFrom, priceUpTo;
    RadioButton radioButton;
    RadioGroup radioGroup;
    Button updateSearchPreferences;
    boolean papersAvailable;

    protected void onCreate(Bundle savedInstanceState) {
        //Zuweisungen
        updateSearchPreferences = findViewById(R.id.preferences_button);
        Spinner raceSpinner = (Spinner) findViewById(R.id.rasse_spinner);
        String race = raceSpinner.getSelectedItem().toString();

        Spinner ageSpinner = (Spinner) findViewById(R.id.alter_spinner);
        String age = ageSpinner.getSelectedItem().toString();

        EditText priceFrom = (EditText) findViewById(R.id.text_min_preis);
        String minPrice = priceFrom.getText().toString();

        EditText priceTo = (EditText) findViewById(R.id.text_max_preis);
        String maxPrice = priceTo.getText().toString();

        updateSearchPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // die Einträge des Nutzers werden als Strings (boolean) an den Presenter weitergereicht
                if (!race.isEmpty() && !age.isEmpty() && !minPrice.isEmpty() && !maxPrice.isEmpty()){
                    searchPresenter.filter(race, age, minPrice, maxPrice, papersAvailable);
                } else {
                    priceFrom.setError("Bitte alle Felder ausfüllen");
                    priceTo.setError("Bitte alle Felder ausfüllen");
                }

            }

        });
        super.onCreate(savedInstanceState);
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


}







