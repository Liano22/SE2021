package com.example.myapplication.likes;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.matches.Match;
import com.example.myapplication.matches.MatchAdapter;
import com.example.myapplication.matches.MatchesPresenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//Nils Behrens

public class LikesView extends AppCompatActivity implements ILikesContract.LikesView {

    //Deklaration von Variablen
    private RecyclerView recyclerView;
    private LikesAdapter adapter;
    private DatabaseReference database_like_check;
    private DatabaseReference database_liked_dogs_data;
    ArrayList<Like> likesList = new ArrayList<>();
    ArrayList<String> liked_dogs_ids;
    String dog_id, name, race, age, price, selectedDog, image;

    //Verbindung zum Presenter
    LikesPresenter likesPresenter = new LikesPresenter(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hier wird geprüft, ob der Intent, der diese Activity aufgerufen hat, eine dogID als extra enthält.
        //Die dogID wird benötigt, um die Likes in der Datenbank zu durchsuchen.
        if (getIntent().hasExtra("dogID")) {
            Bundle extra = getIntent().getExtras();
            selectedDog = extra.getString("dogID");
        }

        setContentView(R.layout.dog_likes);

        //Zuweisungen
        recyclerView = findViewById(R.id.dog_likeslist);
        likesList = new ArrayList<>();
        liked_dogs_ids = new ArrayList<>();

        findLikes();

    }

    public void findLikes() {
        //Erstellung einer Instanz des Matches-Datensatz aus der Datenbank
        //Der Matches-Datensatz wird sowohl für Matches als auch für Likes verwendet
        database_like_check = FirebaseDatabase.getInstance().getReference().child("matches");
        ValueEventListener likeListener = new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {

                liked_dogs_ids.clear();

                //Matches-Datensatz wird nach der ID des gewünschten Hundes durchsucht.
                //Bei allen Likes (likes = true && match = false), die den gewünschten Hund enthalten, wird die ID des jeweils anderen Hundes zu liked_dog_ids hinzugefügt.
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.child("dog_id_1").getValue(String.class).equals(selectedDog)) {
                        if(ds.child("like").getValue(String.class).equals("true") && ds.child("match").getValue(String.class).equals("false")) {
                            dog_id = ds.child("dog_id_2").getValue(String.class);
                            liked_dogs_ids.add(String.valueOf(dog_id));
                        }
                    } if(ds.child("dog_id_2").getValue(String.class).equals(selectedDog)) {
                        if(ds.child("like").getValue(String.class).equals("true") && ds.child("match").getValue(String.class).equals("false")) {
                            dog_id = ds.child("dog_id_1").getValue(String.class);
                            liked_dogs_ids.add(String.valueOf(dog_id));
                        }
                    }
                }
                setLikeInfo();
                setAdapter();
                adapter.notifyDataSetChanged();
            }


            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("loadPost:onCancelled", databaseError.toException());
            }
        };
        database_like_check.addValueEventListener(likeListener);
    }

    public void setLikeInfo() {

        //Erstellung einer Instanz des Hunde-Datensatz aus der Datenbank
        database_liked_dogs_data = FirebaseDatabase.getInstance().getReference().child("dogs");
        ValueEventListener likedDogsListener = new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {

                likesList.clear();

                //Die gelikten Hunde werden anhand Ihrer IDs (liked_dog_ids) aus der Datenbank ausgelesen und Ihren Informationen werden zur likesList hinzugefügt.
                for(String id : liked_dogs_ids) {
                    name = dataSnapshot.child(id).child("name").getValue(String.class);
                    race = dataSnapshot.child(id).child("race").getValue(String.class);
                    age = dataSnapshot.child(id).child("age").getValue(String.class);
                    price = dataSnapshot.child(id).child("price").getValue(String.class);
                    image = dataSnapshot.child(id).child("pic").getValue(String.class);

                    Log.i("Bild", image);

                    likesList.add(new Like(String.valueOf(name), String.valueOf(race), String.valueOf(age), String.valueOf(price), String.valueOf(image)));
                }
                adapter.notifyDataSetChanged();
            }


            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("loadPost:onCancelled", databaseError.toException());
            }
        };
        database_liked_dogs_data.addValueEventListener(likedDogsListener);
    }

    //Zuweisung des LayoutManagers und Adapters zum RecyclerView
    //Außerdem werden hier die Informationen der gelikten Hunde an den Adapter übergeben
    public void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LikesAdapter(this, likesList);
        recyclerView.setAdapter(adapter);
    }

}

//Nils Behrens
