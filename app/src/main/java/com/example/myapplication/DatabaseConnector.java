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
    public void writeUserToDatabase(User newUser, String usernameInput) {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");
        reference.child(usernameInput).setValue(newUser);
    }


    //read user from Database
    public Query readUserFromDatabase(String username, String childName) {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");
        Query checkUser = reference.orderByChild(childName).equalTo(username);
        return checkUser;
    }

    //read dog from Database
    public Query readDogFromDatabase(String dogId, String childName) {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("dogs");
        Query checkDog = reference.orderByChild(childName).equalTo(dogId);
        return checkDog;
    }

    public Query getAllDogs() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("dogs/");
        Query dogs = reference;
        return dogs;
    }
}
