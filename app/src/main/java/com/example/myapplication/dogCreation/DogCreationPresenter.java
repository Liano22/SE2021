package com.example.myapplication.dogCreation;

import androidx.annotation.NonNull;

import com.example.myapplication.DatabaseConnector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DogCreationPresenter implements  IDogCreationContract.IPresenter{

    Dog newDog; // der neu erstellte Hund, der später in die Datenbank geladen werden soll
    DatabaseConnector dbConnector = new DatabaseConnector(); // um Verbindung zur Datenbank herstellen zu können
    IDogCreationContract.IView dogCreationView;

    public DogCreationPresenter(IDogCreationContract.IView dogCreationView) {
        this.dogCreationView = dogCreationView; // Verbindung zur View
    }

    @Override
    public void saveDog(String name, String age, String gender, String race, String pic, String bio, String price, boolean hybrid, boolean papers) {
        newDog = new Dog(name, age, gender, race, pic, bio, price, hybrid, papers);
        Query dogId = dbConnector.getNextDogID();

        dogId.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int idFromDB = snapshot.child("nextId").getValue(Integer.class); //Passwort aus DB
                dbConnector.writeDogToDatabase(newDog, String.valueOf(idFromDB));
                idFromDB++;
                dbConnector.writeNextDogID(idFromDB);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // TODO dogId muss bei Nutzer unter Hunde eigetragen werden
    }
}
