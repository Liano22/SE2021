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

    /**
     * Es wird ein neuer Hund in die Datenbank gespeichert.
     * Zuerst werden die Eingaben des Nutzers überprüft, dann eine neue Instanz von Dog erstellt.
     * Nach dem Auslesen der aktuellen DogID wird der Hund in die Datenbank gespeichert und die DogID beim
     * entsprechenden User unter myDogs hinterlegt. Die DogID wird anschließend erhöht und als nextDogID
     * in der Datenbank hinterlegt. Zu guter Letzt wird ein Intent zum Dasjboard ausgeführt.
     * @param username Benutzername - String
     * @param name Name des Hundes - String
     * @param age Alter des Hundes - String
     * @param gender Geschlecht des Hundes - String
     * @param race Rasse des Hundes - String
     * @param pic Key für das Bild des Hundes - String
     * @param bio Bio des Hundes - String
     * @param price Preis für Paarung - String
     * @param hybrid Mischling? - Boolean
     * @param papers Papiere? - Boolean
     */
    @Override
    public void saveDog(String username, String name, String age, String gender, String race, String pic, String bio, String price, boolean hybrid, boolean papers) {

        // wenn alle Felder ausgefüllt wurden:
        if(validateFields(name,age,race,bio,price)) {

            // Konstruktor von Dog aufrufen
            newDog = new Dog(username, name, age, gender, race, pic, bio, price, hybrid, papers);

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


    /**
     * Änderung der myDogs des Users durch das Model veranlassen.
     * @param username Benutzername - String
     * @param dogId DogID - Integer
     */
    public void linkDogToUser(String username, int dogId){
        String dogIdString = String.valueOf(dogId);
        dogCreationModel.changeUserDogList(username,dogIdString);
    }

    /**
     * Prüfen, ob alle Felder ausgefüllt wurden.
     * Für leere Felder eine Fehlermeldung in der View setzen lassen.
     * @param name Name des Hundes - String
     * @param age Alter - String
     * @param race Rasse - String
     * @param bio Bio - String
     * @param price Preis - String
     * @return Boolean: true, wenn alle Felder befüllt; false, wenn mind. ein Feld leer.
     */
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

    /**
     * Snackbar in View anzeigen lassen.
     * @param msg Fehlermeldung - String
     */
    public void activateSnackbar(String msg){
        dogCreationView.setSnackbar(msg);
    }

    /**
     * ImageUri für ausgewähltes Bild an das Model weiterreichen.
     * @param imageUri Uri des gewünschten Bildes - Uri
     * @param key Key zum Abrufen des Bildes - String
     */
    public void savePicture(Uri imageUri, String key){
        dogCreationModel.uploadPicture(imageUri, key);
    }

    public void setPictureToken(String token){
        dogCreationView.pictureToken = token;
    }


// --- Bennedict und Johanna ---

}
