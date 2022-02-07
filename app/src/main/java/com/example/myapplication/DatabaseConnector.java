package com.example.myapplication;

import com.example.myapplication.signUp.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

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
    //already copied to LogInModel, but might be useful in other packages
    //is used in Karl's Kram
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
