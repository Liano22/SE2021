package com.example.myapplication.signUp;

import com.example.myapplication.logIn.ILogInContract;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

//Karl
public class SignUpModel {

    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    DatabaseReference reference;

    public Query readUserFromDatabase(String username, String childName) {
        reference = rootNode.getReference("users");
        Query checkUser = reference.orderByChild(childName).equalTo(username);
        return checkUser;
    }
}
