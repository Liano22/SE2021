package com.example.myapplication.filter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class FilterView extends AppCompatActivity implements IFilterContract.IView {

    FilterPresenter filterPresenter = new FilterPresenter(this);
    Button updateFilterPreferences;
    private Spinner raceSpinner, ageSpinner;
    private RadioButton available, optional;
    private EditText priceFrom, priceTo;

    boolean papersAvailable;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_preferences);

        //Zuweisungen
        updateFilterPreferences = findViewById(R.id.preferences_button);
        raceSpinner = findViewById(R.id.rasse_spinner);
        ageSpinner = findViewById(R.id.alter_spinner);
        priceFrom = findViewById(R.id.text_min_preis);
        priceTo = findViewById(R.id.text_max_preis);

        updateFilterPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String race = raceSpinner.getSelectedItem().toString();
                String age = ageSpinner.getSelectedItem().toString();
                String minPrice = priceFrom.getText().toString();
                String maxPrice = priceTo.getText().toString();

                // die Einträge des Nutzers werden als Strings (boolean) an den Presenter weitergereicht
                if (!race.isEmpty() && !age.isEmpty() && !minPrice.isEmpty() && !maxPrice.isEmpty()) {
                    filterPresenter.filter(race, age, minPrice, maxPrice, papersAvailable);
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







