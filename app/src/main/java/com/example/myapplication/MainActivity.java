package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.dashboard.DashboardView;
import com.example.myapplication.logIn.LogInView;
import com.example.myapplication.signUp.User;

public class MainActivity extends AppCompatActivity {




    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.meet2BreedLogo);
        Drawable res = getResources().getDrawable(R.drawable.meet2breed_logo);
        imageView.setImageDrawable(res);

        Intent intent = new Intent(this, LogInView.class);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                startActivity(intent);
            }
        }, 3000);




    }





}
