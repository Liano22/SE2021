package com.example.myapplication.dogSearch;

import android.os.Bundle;
import android.util.Log;

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

public class DogSearchView extends AppCompatActivity {

    //Deklaration von Variablen
    private DogSearchAdapter dogSearchAdapter;
    private DatabaseReference databaseDogs;
    private String searchDogName, rasseTextView, geschlechtTextView, alterTextView, papiereTextView, image, priceTextView, thisDogId, thisDogUser;
    RecyclerView recyclerViewDogSearch;
    ArrayList<DogSearch> dogList = new ArrayList<>();
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
                    Log.d("gender", geschlechtTextView);

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
                                        dogList.add(new DogSearch(searchDogName, rasseTextView, alterTextView, papiereTextView, geschlechtTextView, image, priceTextView, thisDogId, currentDog, thisDogUser, currentDogUser));
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
