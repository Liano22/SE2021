package com.example.myapplication.signUp;

import com.example.myapplication.User;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.Query;

public interface ISignUpContract {

    interface IView {
        void setErrorMessage(String field, String msg);
    }

    interface IPresenter{
        Boolean validateUsername(TextInputLayout username);
        void userExists(String username, String firstName, String name, String email, String postalCode, String phoneNumber, String bio, String  password);
        void writeUser(String username, String firstName, String name, String email, String postalCode, String phoneNumber, String bio, String password);
    }

    interface IModel{
        Query readUserFromDatabase(String username, String childName);
        void writeUserToDatabase(User newUser, String usernameInput);
    }
}
