package com.example.myapplication.search;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SearchModel {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    public Query getAllDogs() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("dogs/");
        Query dogs = reference;
        return dogs;
    }
}
