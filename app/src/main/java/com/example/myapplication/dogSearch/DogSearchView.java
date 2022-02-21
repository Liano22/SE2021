package com.example.myapplication.dogSearch;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.filter.Filter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * initialisiert die RecyclerView für die Search Funktion der App
 * es werden alle Hunde aus der Datenbank geladen, die den Filtereinstellungen von filterSettings entsprechen
 * diese werden in die ArrayList dogList gespeichert und an den DogSearchAdapter weitergereicht
 *
 * @author Kilian Mauson
 */


public class DogSearchView extends AppCompatActivity {

    //Deklaration von Variablen
    private DogSearchAdapter dogSearchAdapter;
    private DatabaseReference databaseDogs;
    private String searchDogName, rasseTextView, geschlechtTextView, alterTextView, papiereTextView, image, priceTextView, thisDogId, thisDogUser;
    RecyclerView recyclerViewDogSearch;
    ArrayList<DogSearchModel> dogList = new ArrayList<>();
    String currentDog, currentDogUser;
    String currentDogGender;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dog_search);

        //Variablen von den Filter Einstellungen bekommen
        Filter filterSettings = getIntent().getParcelableExtra("filterSettings");

        if (getIntent().hasExtra("dogID")) {
            Bundle extra = getIntent().getExtras();
            currentDog = extra.getString("dogID");
            currentDogGender = extra.getString("dogGender");
            currentDogUser = extra.getString("dogUser");
        }

        recyclerViewDogSearch = findViewById(R.id.searchView);

        databaseDogs = FirebaseDatabase.getInstance().getReference().child("dogs");

        ValueEventListener dogListener = new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                dogList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    thisDogId = ds.getKey();
                    thisDogUser = ds.child("username").getValue(String.class);
                    searchDogName = ds.child("name").getValue(String.class);
                    rasseTextView = ds.child("race").getValue(String.class);
                    geschlechtTextView = ds.child("gender").getValue(String.class);
                    alterTextView = ds.child("age").getValue(String.class);
                    image = ds.child("pic").getValue(String.class);
                    priceTextView = ds.child("price").getValue(String.class);
                    
                    Boolean papers = ds.child("papers").getValue(Boolean.class);


                    if (papers.equals(true)) {
                        papiereTextView = "ja";
                    } else {
                        papiereTextView = "nein";
                    }

                    if (!currentDog.equals(ds.getKey())) {
                        if (geschlechtTextView.equals(currentDogGender) != true) {
                            if (rasseTextView.equals(filterSettings.getRace())) {
                                if (Integer.parseInt(filterSettings.getAge()) >= Integer.parseInt(alterTextView)) {
                                    if (Integer.parseInt(priceTextView) >= Integer.parseInt(filterSettings.getMinPrice()) && Integer.parseInt(priceTextView) <= Integer.parseInt(filterSettings.getMaxPrice())) {
                                        dogList.add(new DogSearchModel(searchDogName, rasseTextView, alterTextView, papiereTextView, geschlechtTextView, image, priceTextView, thisDogId, currentDog, thisDogUser, currentDogUser));
                                    }
                                }
                            }
                        } else {
                            Log.d("test", "nicht hinzugefügt");
                        }
                    }

                }
                dogSearchAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("loadPost:onCancelled", error.toException());
            }
        };
        databaseDogs.addValueEventListener(dogListener);

        recyclerViewDogSearch.setLayoutManager(new LinearLayoutManager(this));
        dogSearchAdapter = new DogSearchAdapter(this, dogList);
        recyclerViewDogSearch.setAdapter(dogSearchAdapter);

    }


}
