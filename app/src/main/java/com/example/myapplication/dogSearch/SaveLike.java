package com.example.myapplication.dogSearch;

import com.example.myapplication.signUp.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SaveLike {

    //Deklaration & Initialisierung von Variablen
    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    DatabaseReference reference;


    //Schreibe Benutzer in die Datenbank
    public void writeLikeToDatabase(LikeClass newLike, String usernameInput) {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");
        reference.child(usernameInput).setValue(newLike);
    }

}
