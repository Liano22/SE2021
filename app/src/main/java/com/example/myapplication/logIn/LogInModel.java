package com.example.myapplication.logIn;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class LogInModel implements ILogInContract.IModel {

    //Deklaration von Variablen
    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    DatabaseReference reference;

    /**
     * Liest einen gewüsnchten User aus.
     * @param username Gesuchter Benutzername - String
     * @param childName Name im Pfad in der Datenbank - String
     * @return Query mit dem gewünschten User (leer falls User nicht existent)
     */
    public Query readUserFromDatabase(String username, String childName) {
        reference = rootNode.getReference("users");
        Query checkUser = reference.orderByChild(childName).equalTo(username);
        return checkUser;
    }
}
