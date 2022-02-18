package com.example.myapplication.dogCreation;

import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.DatabaseConnector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DogCreationPresenter implements  IDogCreationContract.IPresenter{

    // --- Bennedict und Johanna ---

    Dog newDog; // der neu erstellte Hund, der später in die Datenbank geladen werden soll
    DogCreationModel dogCreationModel; // um Verbindung zur Datenbank herstellen zu können
    DogCreationView dogCreationView;

    public DogCreationPresenter(DogCreationView dogCreationView) {
        this.dogCreationView = dogCreationView; // Verbindung zur View
        dogCreationModel = new DogCreationModel(this); // Instanziierung des Models
    }

    // Konstruktor zum unabhängigen Testen des Presenters
    public DogCreationPresenter(DogCreationView dogCreationView, DogCreationModel dogCreationModel) {
        this.dogCreationView = dogCreationView; // Verbindung zur View
        this.dogCreationModel = dogCreationModel;
    }

    @Override
    public void saveDog(String username, String name, String age, String gender, String race, String pic, String bio, String price, boolean hybrid, boolean papers) {

        // wenn alle Felder ausgefüllt wurden:
        if(validateFields(name,age,race,bio,price)) {

            // Konstruktor von Dog aufrufen
            newDog = new Dog(name, age, gender, race, pic, bio, price, hybrid, papers);

            // Query erstellen und ListenerForSingleValueEvent hinzufügen
            Query dogId = dogCreationModel.getNextDogID();

            dogId.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    // nextId auslesen, dogCreationModel Hund abspeichern lassen
                    int idFromDB = snapshot.getValue(Integer.class);
                    dogCreationModel.writeDogToDatabase(newDog, String.valueOf(idFromDB));

                    // DogId bei Hund abspeichern
                    linkDogToUser(username, idFromDB);

                    // DogId erhöhen und die neue nächste Id abspeichern
                    idFromDB++;
                    dogCreationModel.writeNextDogID(idFromDB);

                    // mit Intent zurück zu Dashboard
                    dogCreationView.changeToDashboard();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(dogCreationView.getApplicationContext(), "Es ist ein Fehler aufgetreten.", Toast.LENGTH_LONG);
                }
            });

        }

    }

    // Das Model die DogId beim User abspeichern lassen
    public void linkDogToUser(String username, int dogId){
        String dogIdString = String.valueOf(dogId);
        dogCreationModel.changeUserDogList(username,dogIdString);
    }

    // Prüfen, ob alle Felder ausgefüllt wurden
    // für leere Felder eine Fehlermeldung in der View setzen lassen
    public Boolean validateFields(String name, String age, String race, String bio, String price){

        // wird nur auf false gesetzt falls eines der Felder leer ist
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

        // true: alle Felder wurden ausgefüllt
        // false: es wurden nicht alle Felder ausgefüllt
        return validation;

    }

    // Snackbar in View anzeigen lassen
    public void activateSnackbar(String msg){
        dogCreationView.setSnackbar(msg);
    }

    public void savePicture(Uri imageUri, String key){
        dogCreationModel.uploadPicture(imageUri, key);
    }


// --- Bennedict und Johanna ---

}
