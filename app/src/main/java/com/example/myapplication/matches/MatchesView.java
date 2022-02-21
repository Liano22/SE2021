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

//Nils Behrens

public class MatchesView extends AppCompatActivity implements IMatchesContract.MatchesView{

    //Deklaration von Variablen
    private RecyclerView recyclerView;
    private MatchAdapter adapter;
    private DatabaseReference database_match_check;
    private DatabaseReference database_matched_dogs_data;
    ArrayList<Match> matchesList = new ArrayList<>();
    ArrayList<String> matched_dogs_ids = new ArrayList<>();
    String dog_id, name, race, age, price, user_name, user_email, selectedDog;

    //Verbindung zum Presenter
    MatchesPresenter matchesPresenter = new MatchesPresenter(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hier wird geprüft, ob der Intent, der diese Activity aufgerufen hat, eine dogID als extra enthält.
        //Die dogID wird benötigt, um die Matches in der Datenbank zu durchsuchen.
        if (getIntent().hasExtra("dogID")) {
            Bundle extra = getIntent().getExtras();
            selectedDog = extra.getString("dogID");
        }

        setContentView(R.layout.dog_matches);

        //Zuweisungen
        recyclerView = findViewById(R.id.dog_matcheslist);
        matched_dogs_ids = new ArrayList<>();
        matchesList = new ArrayList<>();

        findMatches();
    }

    public void findMatches() {
        //Erstellung einer Instanz des Matches-Datensatz aus der Datenbank
        database_match_check = FirebaseDatabase.getInstance().getReference().child("matches");

        ValueEventListener matchListener = new ValueEventListener() {

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                matched_dogs_ids.clear();

                //Matches-Datensatz wird nach der ID des gewünschten Hundes durchsucht.
                //Bei allen Matches (match = true), die den gewünschten Hund enthalten, wird die ID des jeweils anderen Hundes zu matched_dog_ids hinzugefügt.
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.child("dog_id_1").getValue(String.class).equals(selectedDog)) {
                        if(ds.child("match").getValue(String.class).equals("true")) {
                            dog_id = ds.child("dog_id_2").getValue(String.class);
                            matched_dogs_ids.add(String.valueOf(dog_id));
                        }
                    }

                    if(ds.child("dog_id_2").getValue(String.class).equals(selectedDog)) {
                        if(ds.child("match").getValue(String.class).equals("true")) {
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

    public void setMatchInfo() {

        //Erstellung einer Instanz der Datenbank
        //Man benötigt hierbei die komplette Datenbank, da man bei der Erstellung eines Matches sowohl auf die Hunde als auch auf die Nutzer zugreifen muss.
        database_matched_dogs_data = FirebaseDatabase.getInstance().getReference();
        ValueEventListener matchedDogsListener = new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {

                matchesList.clear();

                    //Die gematchten Hunde werden anhand Ihrer IDs (matched_dog_ids) aus der Datenbank ausgelesen und Ihren Informationen werden zur matchesList hinzugefügt.
                    for(String id : matched_dogs_ids) {
                        name = dataSnapshot.child("dogs").child(id).child("name").getValue(String.class);
                        race = dataSnapshot.child("dogs").child(id).child("race").getValue(String.class);
                        age = dataSnapshot.child("dogs").child(id).child("age").getValue(String.class);
                        price = dataSnapshot.child("dogs").child(id).child("price").getValue(String.class);
                        user_name = dataSnapshot.child("dogs").child(id).child("username").getValue(String.class);

                        user_email = dataSnapshot.child("users").child(String.valueOf(user_name)).child("email").getValue(String.class);

                        matchesList.add(new Match(String.valueOf(name), String.valueOf(race), String.valueOf(age), String.valueOf(price), String.valueOf(user_name), String.valueOf(user_email)));
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

    //Zuweisung des LayoutManagers und Adapters zum RecyclerView
    //Außerdem werden hier die Informationen der gematchten Hunde an den Adapter übergeben
    public void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MatchAdapter(this, matchesList);
        recyclerView.setAdapter(adapter);
    }

}

//Nils Behrens
