package com.example.myapplication.search;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import com.example.myapplication.R;

public class SearchPresenter extends ComponentActivity implements SearchContract.Presenter {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_preferences);
    }
}
