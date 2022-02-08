package com.example.myapplication.matches;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MatchModel {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    public Query readMatchesFromDatabase() {
        reference = rootNode.getReference("matches");
        Query matches = reference;
        return matches;
    }

}
