package com.example.myapplication.dogSearch;

import com.example.myapplication.signUp.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

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

}
