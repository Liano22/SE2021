package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.dashboard.DashboardView;
import com.example.myapplication.logIn.LogInView;
import com.example.myapplication.signUp.User;

public class MainActivity extends AppCompatActivity {

    User currentUser;


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button startBtn = findViewById(R.id.startBtn);

        Intent intent = new Intent(this, LogInView.class);
        Intent dash = new Intent(this, DashboardView.class);

        startBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startBtn.setText("Gestartet");
            }
        });


        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Log.d("start", "button wurde geklickt");

                try {
                    startActivity(intent);
                    //startActivity(dash);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                //startBtn.setText("Gestartet");
            }
        });

    }

    public void setCurrentUser(User user){
        this.currentUser = user;
    }



}
