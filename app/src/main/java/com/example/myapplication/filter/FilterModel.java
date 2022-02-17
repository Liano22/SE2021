package com.example.myapplication.filter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FilterModel implements IFilterContract.IModel {
    //Ralf & Karl

    //Deklaration von Variablen
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    //Herstellen der Verbindung zur Datenbank bzw. das Auslesen aller Hunde aus der Datenbank.
    public Query getAllDogs() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("dogs/");
        Query dogs = reference;
        return dogs;
    }
}
