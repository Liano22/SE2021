package com.example.myapplication.matches;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;

public class MatchesView extends AppCompatActivity implements IMatchesContract.MatchesView{

    //Deklaration von Variablen
    private RecyclerView recyclerView;
    private MatchesPresenter matchesPresenter;
    private MatchAdapter adapter;
    private DatabaseReference database_match_check;
    private DatabaseReference database_matched_dogs_data;
    ArrayList<Match> matchesList = new ArrayList<>();
    ArrayList<String> matched_dogs_ids = new ArrayList<>();
    String dog_id, name, race, age, price, selectedDog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().hasExtra("dogID")) {
            Bundle extra = getIntent().getExtras();
            selectedDog = extra.getString("dogID");
        }

        setContentView(R.layout.dog_matches);

        recyclerView = findViewById(R.id.dog_matcheslist);
        matched_dogs_ids = new ArrayList<>();
        matchesList = new ArrayList<>();

        database_match_check = FirebaseDatabase.getInstance().getReference().child("matches");
        database_matched_dogs_data = FirebaseDatabase.getInstance().getReference().child("dogs");

        ValueEventListener matchListener = new ValueEventListener() {

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                matched_dogs_ids.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.child("dog_id_1").getValue(String.class).equals(selectedDog)) {
                        if(ds.child("match").getValue(Boolean.class).equals(true)) {
                            dog_id = ds.child("dog_id_2").getValue(String.class);
                            matched_dogs_ids.add(String.valueOf(dog_id));
                        }
                    }

                    if(ds.child("dog_id_2").getValue(String.class).equals(selectedDog)) {
                        if(ds.child("match").getValue(Boolean.class).equals(true)) {
                            dog_id = ds.child("dog_id_1").getValue(String.class);
                            matched_dogs_ids.add(String.valueOf(dog_id));
                        }
                    }
                }
                setMatchInfo();
                setAdapter();
                adapter.notifyDataSetChanged();
            }


            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("loadPost:onCancelled", databaseError.toException());
            }
        };
        database_match_check.addValueEventListener(matchListener);
    }

    //Daten der gematchten Hunde anhand von ID auslesen und zur matchesList hinzufügen
    private void setMatchInfo() {
        database_matched_dogs_data = FirebaseDatabase.getInstance().getReference().child("dogs");
        ValueEventListener matchedDogsListener = new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {

                matchesList.clear();

                Log.d("start", String.valueOf(matched_dogs_ids) + "value of matched dog ids");
                Log.d("start", String.valueOf(matched_dogs_ids.size()) + "size of matched dog ids");

                    for(String id : matched_dogs_ids) {
                        name = dataSnapshot.child(id).child("name").getValue(String.class);
                        race = dataSnapshot.child(id).child("race").getValue(String.class);
                        age = dataSnapshot.child(id).child("age").getValue(String.class);
                        price = dataSnapshot.child(id).child("price").getValue(String.class);

                        //Log.d("start", name);

                        matchesList.add(new Match(String.valueOf(name), String.valueOf(race), String.valueOf(age), String.valueOf(price)));;
                    }
                adapter.notifyDataSetChanged();
            }

            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("loadPost:onCancelled", databaseError.toException());
            }
        };
        database_matched_dogs_data.addValueEventListener(matchedDogsListener);
    }

    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MatchAdapter(this, matchesList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateRecyclcerView() {
        //lade alle Hunde in die Liste
    }

}
