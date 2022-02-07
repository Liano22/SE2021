package com.example.myapplication.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DatabaseConnector;
import com.example.myapplication.R;
import com.example.myapplication.dogCreation.Dog;
import com.example.myapplication.dogCreation.DogCreationView;
import com.example.myapplication.logIn.LogInView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashboardView extends AppCompatActivity implements IDashboardContract.DashboardView{

    Button addDogBtn;
    RecyclerView recyclerViewDashboard;
    private DashboardPresenter dashboardPresenter;
    private DashboardAdapter adapter;

    private DatabaseReference database;

    ArrayList<Dashboard> dogItems = new ArrayList<>();

    String key;
    String bio;
    String name;

    String data;
    Intent intent = getIntent();

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = intent.getExtras();
        if(extras != null)
            data = extras.getString("keyName");

        setContentView(R.layout.dashboard_activity);

        addDogBtn = findViewById(R.id.addDogBtn);
        recyclerViewDashboard =  findViewById(R.id.recyclerViewDashboard);
        dashboardPresenter = new DashboardPresenter(this);

        Intent creation = new Intent(this, DogCreationView.class);


        database = FirebaseDatabase.getInstance().getReference().child("dogs");
        ValueEventListener dogListener = new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {

                dogItems.clear();

                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        key = ds.getKey();
                        bio = ds.child("bio").getValue(String.class);
                        name = ds.child("name").getValue(String.class);
                        //Log.d("test", key + bio + name);
                        dogItems.add(new Dashboard(key, name));
                    }

                adapter.notifyDataSetChanged();
            }


            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("loadPost:onCancelled", databaseError.toException());
            }
        };
        database.addValueEventListener(dogListener);



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
