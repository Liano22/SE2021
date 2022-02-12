package com.example.myapplication.dogCreation;

import android.net.Uri;

import com.google.firebase.database.Query;

public interface IDogCreationContract {
    // --- Bennedict und Johanna ---
    interface IView{
        void setErrorMessage(String field, String ErrorMessage);
        void changeToDashboard();
        void setSnackbar(String msg);
    }

    interface IPresenter{
        void saveDog(String username, String name, String age, String gender, String race, String pic, String bio, String price, boolean hybrid, boolean papers);
        void linkDogToUser(String username, int dogId);
        Boolean validateFields(String name, String age, String race, String bio, String price);
        void activateSnackbar(String msg);
        void savePicture(Uri imageUri, String key);
    }

    interface IModel{
        void writeDogToDatabase(Dog newDog, String dogId);
        Query getNextDogID();
        void writeNextDogID(int newDogId);
        void changeUserDogList(String username, String dogID);
        void uploadPicture(Uri imageUri, String key);
    }
    // --- Bennedict und Johanna ---
}
