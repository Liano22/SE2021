package com.example.myapplication.signUp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

//Karl
public class SignUpModel implements ISignUpContract.IModel {

    //Deklaration & Initialisierung von Variablen
    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    DatabaseReference reference;

    //Lese Benutzer aus der Datenbank aus
    public Query readUserFromDatabase(String username, String childName) {
        reference = rootNode.getReference("users");
        Query checkUser = reference.orderByChild(childName).equalTo(username);
        return checkUser;
    }

    //Schreibe Benutzer in die Datenbank
    public void writeUserToDatabase(User newUser, String usernameInput) {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");
        reference.child(usernameInput).setValue(newUser);
    }
}
