package com.example.myapplication;

import com.example.myapplication.dogCreation.Dog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

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
        reference = rootNode.getReference("dogs");
        Query getDogId = reference.orderByChild("nextId");
        return getDogId;
    }

    public void writeNextDogID(int newDogId){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("dogs");
        reference.child("nextId").setValue(newDogId);
    }

    public void changeUserAttribute(String child, String value){
    }
}
