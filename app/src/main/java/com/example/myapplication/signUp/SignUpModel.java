package com.example.myapplication.signUp;

import com.example.myapplication.User;
import com.example.myapplication.logIn.ILogInContract;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

//Karl
public class SignUpModel implements ISignUpContract.IModel {

    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    DatabaseReference reference;

    public Query readUserFromDatabase(String username, String childName) {
        reference = rootNode.getReference("users");
        Query checkUser = reference.orderByChild(childName).equalTo(username);
        return checkUser;
    }

    //write user to Database
    public void writeUserToDatabase(User newUser, String usernameInput) {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");
        reference.child(usernameInput).setValue(newUser);
    }
}
