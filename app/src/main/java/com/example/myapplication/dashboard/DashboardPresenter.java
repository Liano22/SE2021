package com.example.myapplication.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DatabaseConnector;
import com.example.myapplication.R;
import com.example.myapplication.dogCreation.DogCreationView;

import java.util.ArrayList;

public class DashboardPresenter extends AppCompatActivity implements IDashboardContract.DashboardPresenter {

    private IDashboardContract.DashboardView dashboardView;
    private Dashboard dashboard;
    private DatabaseConnector dbConnector = new DatabaseConnector();

    // Button um auf die Seite zum Erstellen neuer Hunde weiterzuleiten
    //private Button newDogBtn;


    public DashboardPresenter() {
    }

    DashboardPresenter(IDashboardContract.DashboardView view) {
        //dashboard = new Dashboard();
        dashboardView = view;
    }
    @Override
    public ArrayList getAllItems() {

        ArrayList list = new ArrayList<>();

        //get all Data from Database (through Model) and put into list

        return list;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void addNewDog() {

        //got to DogCreation

    }
}

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        // Zuweisung zur Weiterleitung auf dogCreation
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
                // Weiterleitung zur Seite zum Erstellen eines neuen Hundes zu Testzwecken
                intentDogCreation.putExtra("currentUser", currentUser);
                startActivity(intentDogCreation);
            }
        });
    }
}
*/

