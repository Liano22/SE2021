package com.example.myapplication.dogSearch;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.signUp.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SaveLike {

    //Deklaration & Initialisierung von Variablen
    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    DatabaseReference reference;


    public void writeNextMatchID(int newMatchId) {
        reference = rootNode.getReference("nextMatchId");
        reference.setValue(newMatchId);
    }

    //Schreibe Like in die Datenbank
    public void writeLikeToDatabase(LikeClass newLike, String id) {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("matches");
        reference.child(id).setValue(newLike);
    }

    public void changeUserDogList(String goToDog, String IdToWrite) {
        reference = rootNode.getReference("dogs/" + goToDog);
        Query dogs = reference;
        dogs.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dogsFromDB = snapshot.child("likes").getValue(String.class);
                String appendDog;
                if (dogsFromDB == null) {
                    appendDog = IdToWrite + ",";
                } else {
                    appendDog = dogsFromDB + IdToWrite + ",";
                }

                reference = rootNode.getReference("dogs/" + goToDog + "/likes");
                reference.setValue(appendDog);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Error", error.toException().toString());
            }
        });
    }
}
