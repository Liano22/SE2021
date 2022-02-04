package com.example.myapplication.dogCreation;

import com.google.firebase.database.Query;

public interface IDogCreationContract {
    interface IView{
        void setErrorMessage(String ErrorMessage);
        void changeToDashboard();
    }

    interface IPresenter{
        void saveDog(String username, String name, String age, String gender, String race, String pic, String bio, String price, boolean hybrid, boolean papers);
        void linkDogToUser(String username, int dogId);
    }

    interface IModel{
        void writeDogToDatabase(Dog newDog, String dogId);
        Query getNextDogID();
        void writeNextDogID(int newDogId);
        void changeUserDogList(String username, String dogID);
    }
}
