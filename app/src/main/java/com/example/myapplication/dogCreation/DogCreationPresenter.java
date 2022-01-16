package com.example.myapplication.dogCreation;

import com.example.myapplication.DatabaseConnector;

public class DogCreationPresenter implements  IDogCreationContract.IPresenter{

    Dog newDog; // der neu erstellte Hund, der später in die Datenbank geladen werden soll
    DatabaseConnector dbConnector = new DatabaseConnector(); // um Verbindung zur Datenbank herstellen zu können
    IDogCreationContract.IView dogCreationView;

    public DogCreationPresenter(IDogCreationContract.IView dogCreationView) {
        this.dogCreationView = dogCreationView; // Verbindung zur View
    }

    @Override
    public void saveDog(String name, String age, String gender, String race, String pic, String bio, String price, boolean hybrid, boolean papers) {
        newDog = new Dog(name, age, gender, race, pic, bio, price, hybrid, papers);
        // TODO dogId muss hochgezählt werden, damit die Hunde nicht überschrieben werden
        int dogId = 42;
        dbConnector.writeDogToDatabase(newDog, String.valueOf(dogId));

        // TODO dogId muss bei Nutzer unter Hunde eigetragen werden
    }
}
