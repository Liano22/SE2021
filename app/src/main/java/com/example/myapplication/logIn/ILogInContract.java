package com.example.myapplication.logIn;

import android.view.View;

import com.google.firebase.database.Query;

public interface ILogInContract {
    interface IView{
        void setErrorMessage(String field,String ErrorMessage);
        void changeToDashboard(String username);
    }

    interface IPresenter{
        Boolean validateUsername(String username);
        Boolean validatePassword(String password);
        void logIn(View view, String username, String password);
        void userExists(String username, String password);
    }

    interface IModel{
        Query readUserFromDatabase(String username, String childName);
    }
}
