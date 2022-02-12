package com.example.myapplication.dogCreation;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.myapplication.DatabaseConnector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DogCreationPresenter implements  IDogCreationContract.IPresenter{
    // --- Bennedict und Johanna ---
    Dog newDog; // der neu erstellte Hund, der später in die Datenbank geladen werden soll
    DogCreationModel dogCreationModel = new DogCreationModel(this); // um Verbindung zur Datenbank herstellen zu können
    IDogCreationContract.IView dogCreationView;

    public DogCreationPresenter(IDogCreationContract.IView dogCreationView) {
        this.dogCreationView = dogCreationView; // Verbindung zur View
    }

    @Override
    public void saveDog(String username, String name, String age, String gender, String race, String pic, String bio, String price, boolean hybrid, boolean papers) {

        if(validateFields(name,age,race,bio,price)) {

            newDog = new Dog(name, age, gender, race, pic, bio, price, hybrid, papers);
            Query dogId = dogCreationModel.getNextDogID();

            dogId.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    int idFromDB = snapshot.getValue(Integer.class);
                    dogCreationModel.writeDogToDatabase(newDog, String.valueOf(idFromDB));

                    linkDogToUser(username, idFromDB);

                    idFromDB++;
                    dogCreationModel.writeNextDogID(idFromDB);

                    dogCreationView.changeToDashboard();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

    }


    public void linkDogToUser(String username, int dogId){
        String dogIdString = String.valueOf(dogId);
        dogCreationModel.changeUserDogList(username,dogIdString);
    }

    public Boolean validateFields(String name, String age, String race, String bio, String price){

        boolean validation = true;

        if(name.isEmpty()){
            dogCreationView.setErrorMessage("name","Name darf nicht leer sein");
            validation = false;
        } else {
            dogCreationView.setErrorMessage("name",null);
        }
        if(age.isEmpty()){
            dogCreationView.setErrorMessage("age","Alter darf nicht leer sein");
            validation = false;
        } else {
            dogCreationView.setErrorMessage("age",null);
        }
        if(race.isEmpty()){
            dogCreationView.setErrorMessage("race","Rasse darf nicht leer sein");
            validation = false;
        } else {
            dogCreationView.setErrorMessage("race",null);
        }
        if(bio.isEmpty()){
            dogCreationView.setErrorMessage("bio","Bio darf nicht leer sein");
            validation = false;
        } else {
            dogCreationView.setErrorMessage("bio",null);
        }
        if(price.isEmpty()){
            dogCreationView.setErrorMessage("price","Preis darf nicht leer sein");
            validation = false;
        } else {
            dogCreationView.setErrorMessage("price",null);
        }

        return validation;

    }

    public void activateSnackbar(String msg){
        dogCreationView.setSnackbar(msg);
    }

    public void savePicture(Uri imageUri, String key){
        dogCreationModel.uploadPicture(imageUri, key);
    }
// --- Bennedict und Johanna ---

}
