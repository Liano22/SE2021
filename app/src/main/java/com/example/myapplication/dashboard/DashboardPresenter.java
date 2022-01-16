package com.example.myapplication.dashboard;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class DashboardPresenter extends AppCompatActivity {

    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        welcome = findViewById(R.id.welcome);
        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("currentUser");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("currentUser");
        }
        welcome.setText("Welcome " + newString);
    }
}
