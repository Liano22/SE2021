package com.example.myapplication.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.dogCreation.DogCreationView;

public class DashboardPresenter extends AppCompatActivity {

    TextView welcome;

    Button newDogBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        newDogBtn = findViewById(R.id.newDogBtn);
        Intent intentDogCreation = new Intent(this, DogCreationView.class);

        welcome = findViewById(R.id.welcome);
        String currentUser;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                currentUser= null;
            } else {
                currentUser= extras.getString("currentUser");
            }
        } else {
            currentUser= (String) savedInstanceState.getSerializable("currentUser");
        }
        welcome.setText("Willkommen " + currentUser);

        newDogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intentDogCreation.putExtra("currentUser", currentUser);
                startActivity(intentDogCreation);
            }
        });
    }
}
