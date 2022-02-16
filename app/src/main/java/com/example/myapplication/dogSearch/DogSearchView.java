package com.example.myapplication.dogSearch;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.filter.Filter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class DogSearchView extends AppCompatActivity {

    RecyclerView recyclerViewDogSearch;
    private DogSearchAdapter dogSearchAdapter;
    private DatabaseReference databaseDogs;

    String currentDog;

    ArrayList<DogSearch> dogList = new ArrayList<>();
    private Object ValueEventListener;
    String race;
    String age;
    String minPrice;
    String maxPrice;
    Boolean papersAvailable;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dog_search);
        Filter filterSettings = getIntent().getParcelableExtra("filterSettings");
        //Variablen von den Filter Einstellungen bekommen

        if (getIntent().hasExtra("dogID")) {
            Bundle extra = getIntent().getExtras();
            currentDog = extra.getString("dogID");
        }

        race = filterSettings.getRace();
        age = filterSettings.getAge();
        minPrice = filterSettings.getMinPrice();
        maxPrice = filterSettings.getMaxPrice();
        papersAvailable = filterSettings.isPapersAvailable();
        Log.d("Rasse: ", race);
        Log.d("Alter: ", age);
        Log.d("Min Preis: ", minPrice);
        Log.d("Max Preis: ", maxPrice);
        Log.d("Papiere verfügbar: ", String.valueOf(papersAvailable));
        Log.d("DogID: ", currentDog);

        recyclerViewDogSearch = findViewById(R.id.searchView);

        databaseDogs = FirebaseDatabase.getInstance().getReference().child("dogs");

    /*    ValueEventListener dogListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dogList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("username").getValue(String.class).equals(currentDog)) {
                        dogList.add(ds.child("myDogs").getValue(String.class));
                    }
                }
                userDogs = Arrays.asList(userDogsList.get(0).split(","));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("loadPost:onCancelled", error.toException());
            }
        }*/

    }

}
