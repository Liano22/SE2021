package com.example.myapplication.dogSearch;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dashboard.Dashboard;
import com.example.myapplication.filter.Filter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class DogSearchView extends AppCompatActivity {

    //Deklaration von Variablen
    private DogSearchAdapter dogSearchAdapter;
    private DatabaseReference databaseDogs;
    private String searchDogName,rasseTextView, geschlechtTextView, alterTextView, papiereTextView, image;
    RecyclerView recyclerViewDogSearch;
    ArrayList<DogSearch> dogList = new ArrayList<>();
    String currentDog;
    String currentDogGender;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dog_search);

        //Variablen von den Filter Einstellungen bekommen
        Filter filterSettings = getIntent().getParcelableExtra("filterSettings");

        Log.d("filter Rasse", filterSettings.getRace());

        if (getIntent().hasExtra("dogID")) {
            Bundle extra = getIntent().getExtras();
            currentDog = extra.getString("dogID");
        }

        recyclerViewDogSearch = findViewById(R.id.searchView);

        databaseDogs = FirebaseDatabase.getInstance().getReference().child("dogs");

        ValueEventListener dogListener = new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                dogList.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    searchDogName = ds.child("name").getValue(String.class);
                    rasseTextView = ds.child("race").getValue(String.class);
                    geschlechtTextView = ds.child("gender").getValue(String.class);
                    alterTextView = ds.child("age").getValue(String.class);
                    image = ds.child("pic").getValue(String.class);
                    Boolean papers = ds.child("papers").getValue(Boolean.class);
                    if (papers.equals(true)) {
                        papiereTextView = "ja";
                    } else {
                        papiereTextView = "nein";
                    }

                    if (currentDog.equals(ds.getKey())) {
                        currentDogGender = ds.child("gender").getValue(String.class);
                    }

                    /*
                    if (rasseTextView.equals(filterSettings.getRace())) {
                        if (Integer.parseInt(alterTextView) >= Integer.parseInt(filterSettings.getMaxPrice())) {
                            if (papers.equals(filterSettings.isPapersAvailable())) {
                                if (!geschlechtTextView.equals(currentDogGender)) {
                                    dogList.add(new DogSearch(searchDogName, rasseTextView, alterTextView, papiereTextView, geschlechtTextView));
                                }

                            }
                        }
                    }
                    
                     */



                    dogList.add(new DogSearch(searchDogName, rasseTextView, alterTextView, papiereTextView, geschlechtTextView, image));
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
