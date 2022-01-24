package com.example.myapplication.search;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class SearchView extends AppCompatActivity implements ISearchContract.IView {

    SearchPresenter searchPresenter = new SearchPresenter(this);
    //String race, age, priceFrom, priceUpTo;
    private boolean papersAvailable, papersOptional;
    Button updateSearchPreferences;

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

                // die Einträge des Nutzers werden als Strings an den Presenter weitergereicht
                searchPresenter.filter(race, age, minPrice, maxPrice, papersAvailable);
            }

        });
        super.onCreate(savedInstanceState);
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.checkbox:
                if (checked) {
                    papersAvailable = true;
                    papersOptional = false;
                } else {
                    break;
                }

            case R.id.checkbox_papers_optinal:
                if (checked) {
                    papersAvailable = false;
                    papersOptional = true;
                } else {
                    break;
                }
        }
    }


}







