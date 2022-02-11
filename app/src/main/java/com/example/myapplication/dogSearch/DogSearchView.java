package com.example.myapplication.dogSearch;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.filter.Filter;

import java.util.ArrayList;

public class DogSearchView extends AppCompatActivity {

    RecyclerView recyclerViewDogSearch;
    private DogSearchAdapter dogSearchAdapter;

    ArrayList<DogSearch> dogs = new ArrayList<>();

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        //Variablen von den Filter Einstellungen bekommen
        Filter filterSettings = getIntent().getParcelableExtra("filterSettings");

    }

}
