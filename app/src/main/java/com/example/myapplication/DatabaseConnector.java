package com.example.myapplication;

import androidx.annotation.NonNull;

import com.example.myapplication.dogCreation.Dog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DatabaseConnector {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    //write user to Database
    public void writeUserToDatabase(User newUser, String usernameInput){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");
        reference.child(usernameInput).setValue(newUser);
    }

    //write dog to Database
    public void writeDogToDatabase(Dog newDog, String dogId){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("dogs");
        reference.child(dogId).setValue(newDog);
    }

    //read user from Database
    public Query readUserFromDatabase(String username, String childName){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");
        Query checkUser = reference.orderByChild(childName).equalTo(username);
        return checkUser;
    }

    //read dog from Database
    public Query readDogFromDatabase(String dogId, String childName){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("dogs");
        Query checkDog = reference.orderByChild(childName).equalTo(dogId);
        return checkDog;
    }

    public Query getNextDogID(){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("nextDogId");
        Query getDogId = reference;
        return getDogId;
    }

    public void writeNextDogID(int newDogId){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("nextDogId");
        reference.setValue(newDogId);
    }

    public void changeUserDogList(String username, String dogID){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users/"+username);
        Query user = reference;
        user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dogsFromDB = snapshot.child("myDogs").getValue(String.class);
                String appendDog = dogsFromDB + dogID + ", ";
                reference = rootNode.getReference("users/"+username+"/myDogs");
                reference.setValue(appendDog);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
