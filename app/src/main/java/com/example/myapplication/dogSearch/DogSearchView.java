package com.example.myapplication.dogSearch;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DogSearchView extends AppCompatActivity {

    RecyclerView recyclerViewDogSearch;
    private DogSearchPresenter dogSearchPresenter;
    private DogSearchAdapter dogSearchAdapter;

    ArrayList<DogSearch> dogs = new ArrayList<>();

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

}
