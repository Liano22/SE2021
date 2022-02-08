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

public class LikesView extends AppCompatActivity implements ILikesContract.IView {

    ArrayList<Like> likesList = new ArrayList<>();
    ArrayList<String> liked_dogs_ids;
    private RecyclerView recyclerView;
    private LikesPresenter likesPresenter;
    private LikesAdapter adapter;

    private DatabaseReference database_like_check;
    private DatabaseReference database_liked_dogs_data;

    String dog_id;

    String name;
    String race;
    String age;
    String price;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dog_likes);

        recyclerView = findViewById(R.id.dog_likeslist);
        likesList = new ArrayList<>();
        liked_dogs_ids = new ArrayList<>();

        database_like_check = FirebaseDatabase.getInstance().getReference().child("matches");
        ValueEventListener likeListener = new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {

                liked_dogs_ids.clear();

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.child("like").getValue(Boolean.class).equals(true) && ds.child("match").getValue(Boolean.class).equals(false)) {
                        dog_id = ds.child("dog_id_2").getValue(String.class);
                        liked_dogs_ids.add(String.valueOf(dog_id));
                        //Log.d("start", String.valueOf(matched_dogs_ids));
                    }
                }
            }


            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("loadPost:onCancelled", databaseError.toException());
            }
        };
        database_like_check.addValueEventListener(likeListener);

        setLikeInfo();
        setAdapter();

        //IDs von gematchten Hunden speichern

    }

    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LikesAdapter(this, likesList);
        recyclerView.setAdapter(adapter);
    }

    //Daten der gematchten Hunde anhand von ID auslesen und zur matchesList hinzufügen
    private void setLikeInfo() {
        database_liked_dogs_data = FirebaseDatabase.getInstance().getReference().child("dogs");
        ValueEventListener likedDogsListener = new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {

                likesList.clear();

                for(String id : liked_dogs_ids) {
                    name = dataSnapshot.child(id).child("name").getValue(String.class);
                    race = dataSnapshot.child(id).child("race").getValue(String.class);
                    age = dataSnapshot.child(id).child("age").getValue(String.class);
                    price = dataSnapshot.child(id).child("price").getValue(String.class);

                    likesList.add(new Like(String.valueOf(name), String.valueOf(race), String.valueOf(age), String.valueOf(price)));
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
}
