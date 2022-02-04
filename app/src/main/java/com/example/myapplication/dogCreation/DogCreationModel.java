package com.example.myapplication.dogCreation;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DogCreationModel implements IDogCreationContract.IModel{

    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    DatabaseReference reference;

    public void writeDogToDatabase(Dog newDog, String dogId) {
        reference = rootNode.getReference("dogs");
        reference.child(dogId).setValue(newDog);
    }

    public Query getNextDogID() {
        reference = rootNode.getReference("nextDogId");
        Query getDogId = reference;
        return getDogId;
    }

    public void writeNextDogID(int newDogId) {
        reference = rootNode.getReference("nextDogId");
        reference.setValue(newDogId);
    }

    public void changeUserDogList(String username, String dogID) {
        reference = rootNode.getReference("users/" + username);
        Query user = reference;
        user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dogsFromDB = snapshot.child("myDogs").getValue(String.class);
                String appendDog = dogsFromDB + dogID + ", ";
                reference = rootNode.getReference("users/" + username + "/myDogs");
                reference.setValue(appendDog);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
