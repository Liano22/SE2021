package com.example.myapplication.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dogCreation.DogCreationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashboardView extends AppCompatActivity implements IDashboardContract.DashboardView{

    //Deklaration & teilweise Initialisierung von Variablen
    private DashboardPresenter dashboardPresenter;
    private DashboardAdapter adapter;
    private DatabaseReference databaseDogs;
    private DatabaseReference databaseUser;
    Button addDogBtn;
    RecyclerView recyclerViewDashboard;
    ArrayList<Dashboard> dogItems = new ArrayList<>();
    ArrayList<String> userDogsList = new ArrayList<>();
    List<String> userDogs;
    String key, name, image, gender, currentUser;



    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().hasExtra("currentUser")) {
            Bundle extra = getIntent().getExtras();
            currentUser = extra.getString("currentUser");
        } else {
            Log.d("Kein Extra!", "Intent überreicht keinen user");
        }

        setContentView(R.layout.dashboard_activity);

        addDogBtn = findViewById(R.id.addDogBtn);
        recyclerViewDashboard =  findViewById(R.id.recyclerViewDashboard);
        dashboardPresenter = new DashboardPresenter(this);

        Intent creation = new Intent(this, DogCreationView.class);
        creation.putExtra("currentUser", currentUser);

        databaseDogs = FirebaseDatabase.getInstance().getReference().child("dogs");
        databaseUser = FirebaseDatabase.getInstance().getReference().child("users");

        ValueEventListener userListerner = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userDogsList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {

                    if (ds.child("username").getValue(String.class).equals(currentUser)) {
                        userDogsList.add(ds.child("myDogs").getValue(String.class));
                    }
                }
                userDogs = Arrays.asList(userDogsList.get(0).split(","));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("loadPost:onCancelled", error.toException());
            }
        };

        ValueEventListener dogListener = new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                dogItems.clear();
                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        key = ds.getKey();
                        name = ds.child("name").getValue(String.class);
                        image = ds.child("pic").getValue(String.class);
                        gender = ds.child("gender").getValue(String.class);

                        if (userDogs.contains(key)) {
                            dogItems.add(new Dashboard(key, name, image, gender, currentUser));
                        }
                    }
                adapter.notifyDataSetChanged();
            }

            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("loadPost:onCancelled", databaseError.toException());
            }
        };

        databaseUser.addValueEventListener(userListerner);
        databaseDogs.addValueEventListener(dogListener);


        addDogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AddDog Button", "add new Dog");
                startActivity(creation);
            }
        });

        recyclerViewDashboard.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DashboardAdapter(this, dogItems);
        recyclerViewDashboard.setAdapter(adapter);
    }

    @Override
    public void updateRecyclcerView() {
        //lade alle Hunde in die Liste
    }
}
