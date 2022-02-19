package com.example.myapplication.dogCreation;

import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class DogCreationModel implements IDogCreationContract.IModel{

    // --- Bennedict und Johanna ---

    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    DatabaseReference reference;

    DogCreationPresenter dogCreationPresenter;

    // Storage-Instanz
    // In Storage werden die DogPics hochgeladen
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference;

    // Konstruktor: wird im Presenter aufgerufen und derselbige wird übergeben
    public DogCreationModel(DogCreationPresenter presenter) {
        this.dogCreationPresenter = presenter;
    }

    /**
     * Ein Objekt der Klasse Hund mit ID unter dogs in der Datenbank speichern.
     * @param newDog Neuer Hund - Dog
     * @param dogId ID des neuen Hundes - String
     */
    public void writeDogToDatabase(Dog newDog, String dogId) {
        reference = rootNode.getReference("dogs");
        reference.child(dogId).setValue(newDog);
    }

    /**
     * Die DogId für den neu zu speichernden Hund aus der Datenbank auslesen und als Query zurückgeben.
     * @return Gewünschte Daten - Query
     */
    public Query getNextDogID() {
        reference = rootNode.getReference("nextDogId");
        Query getDogId = reference;
        return getDogId;
    }

    /**
     * Die nextDogId in der Datenbank für den nächsten zu speichernden Hund abspeichern.
     * @param newDogId Neue DogID - String
     */
    public void writeNextDogID(int newDogId) {
        reference = rootNode.getReference("nextDogId");
        reference.setValue(newDogId);
    }

    /**
     * Die Id des neu hinzugefügten Hundes unter users -> username -> myDogs dem myDogs-String des aktuellen Users hinzufügen.
     * @param username Benutzername - String
     * @param dogID Id des eben hinzugefügten Hundes - String
     */
    public void changeUserDogList(String username, String dogID) {
        reference = rootNode.getReference("users/" + username);
        Query user = reference;
        user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dogsFromDB = snapshot.child("myDogs").getValue(String.class);
                String appendDog = dogsFromDB + dogID + ",";
                reference = rootNode.getReference("users/" + username + "/myDogs");
                reference.setValue(appendDog);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(dogCreationPresenter.dogCreationView.getApplicationContext(), "Es ist ein Fehler aufgetreten.", Toast.LENGTH_LONG);
            }
        });

    }

    /**
     * Das vom User ausgewählte DogPic in Storage unter images/ abspeichern.
     * Fehlermeldung als Snackbar in View anzeigen lassen, wenn das Hochladen nicht klappt.
     * @param imageUri Uri des gewünschten Bildes - Uri
     * @param key Schlüssel für Verbindung zwischen Storage und Realtime Database - String
     */
    public void uploadPicture(Uri imageUri, String key){


        storageReference = storage.getReference();

        StorageReference riversRef = storageReference.child("images/" + key);

        riversRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                dogCreationPresenter.activateSnackbar("Bild erfolgreich hochgeladen.");
                Task<Uri> url = taskSnapshot.getStorage().getDownloadUrl();
                url.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String imageUrl = uri.toString();
                        dogCreationPresenter.setPictureToken(imageUrl);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dogCreationPresenter.activateSnackbar("Bild konnte nicht hochgeladen werden.");
            }
        });
    }


    // --- Bennedict und Johanna ---
    }


