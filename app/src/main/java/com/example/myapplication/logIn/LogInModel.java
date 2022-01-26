package com.example.myapplication.logIn;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class LogInModel implements ILogInContract.IModel{

    FirebaseDatabase rootNode= FirebaseDatabase.getInstance();
    DatabaseReference reference;

    public Query readUserFromDatabase(String username, String childName) {
        reference = rootNode.getReference("users");
        Query checkUser = reference.orderByChild(childName).equalTo(username);
        return checkUser;
    }
}
