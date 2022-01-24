package com.example.myapplication.dashboard;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class DashboardView extends AppCompatActivity implements IDashboardContract.DashboardView{

    Button addDogBtn;
    RecyclerView recyclerViewDashboard;
    private DashboardPresenter dashboardPresenter;
    ArrayList<Dashboard> items;


    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.dashboard_activity);

        addDogBtn = findViewById(R.id.addDogBtn);
        recyclerViewDashboard =  findViewById(R.id.recyclerViewDashboard);
        dashboardPresenter = new DashboardPresenter(this);

        // Initialize contacts
        items = dashboardPresenter.getAllItems();
        // Create adapter passing in the sample user data
        DashboardAdapter adapter = new DashboardAdapter(items);
        // Attach the adapter to the recyclerview to populate items
        recyclerViewDashboard.setAdapter(adapter);
        // Set layout manager to position the items
        recyclerViewDashboard.setLayoutManager(new LinearLayoutManager(this));
        // That's all!

        updateRecyclcerView();

        addDogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardPresenter.addNewRecyclerViewItem();
            }
        });

    }

    private ArrayList<Dashboard> initItems() {
        ArrayList<Dashboard> list = new ArrayList<>();

        list.add(new Dashboard("Manfred", "holger"));
        list.add(new Dashboard("Ulrich", "stefan"));

        return list;
    }

    @Override
    public void updateRecyclcerView() {
        //lade alle Hunde in die Liste
    }
}
