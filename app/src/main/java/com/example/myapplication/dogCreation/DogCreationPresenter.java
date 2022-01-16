package com.example.myapplication.dogCreation;

import com.example.myapplication.DatabaseConnector;

public class DogCreationPresenter implements  IDogCreationContract.IPresenter{

    Dog newDog;
    DatabaseConnector dbConnector = new DatabaseConnector();
    IDogCreationContract.IView dogCreationView;

    public DogCreationPresenter(IDogCreationContract.IView dogCreationView) {
        this.dogCreationView = dogCreationView;
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
