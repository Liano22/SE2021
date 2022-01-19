package com.example.myapplication.dashboard;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class DashboardView extends AppCompatActivity implements DashboardContract.View{

    Button addDogBtn;

    RecyclerView recyclerViewDashboard;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        

    }
}
