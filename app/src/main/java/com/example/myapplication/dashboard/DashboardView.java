package com.example.myapplication.dashboard;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class DashboardView extends AppCompatActivity implements DashboardContract.View{

    Button addDogBtn;

    RecyclerView recyclerViewDashboard;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.dashboard_activity);

        addDogBtn = findViewById(R.id.addDogBtn);
        recyclerViewDashboard = findViewById(R.id.recyclerViewDashboard);

        addDogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add new Dog
            }
        });

    }
}
