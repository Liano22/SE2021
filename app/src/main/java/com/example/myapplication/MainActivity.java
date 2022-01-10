package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = findViewById(R.id.startBtn);

        Intent intent = new Intent(this, LogIn.class);

        /*startBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startBtn.setText("Gestartet");
            }
        });*/

        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                startActivity(intent);
                //startBtn.setText("Gestartet");
            }
        });
    }

}
