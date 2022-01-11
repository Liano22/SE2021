package com.example.myapplication;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DatabaseConnector {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    //write user to Database
    public void writeUserToDatabase(String path, User newUser, String usernameInput){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference(path);
        reference.child(usernameInput).setValue(newUser);
    }

    //write dog to Database
    public void writeDogToDatabase(String path, Dog newDog, String dogId){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference(path);
        reference.child(dogId).setValue(newDog);
    }

    //read user from Database
    public Query readUserFromDatabase(String path, String username, String childName){
        Query checkUser = reference.orderByChild(childName).equalTo(username);
        return checkUser;
    }

    //read dog from Database
    public Query readDogFromDatabase(String path, String dogId, String childName){
        Query checkDog = reference.orderByChild(childName).equalTo(dogId);
        return checkDog;
    }
}
