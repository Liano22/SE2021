package com.example.myapplication.dogCreation;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference;

    public DogCreationModel(DogCreationPresenter presenter) {
        this.dogCreationPresenter = presenter;
    }

    public void writeDogToDatabase(Dog newDog, String dogId) {
        reference = rootNode.getReference("dogs");
        reference.child(dogId).setValue(newDog);
    }

    public Query getNextDogID() {
        reference = rootNode.getReference("nextDogId");
        Query getDogId = reference;
        return getDogId;
    }

    public void writeNextDogID(int newDogId) {
        reference = rootNode.getReference("nextDogId");
        reference.setValue(newDogId);
    }

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

            }
        });

    }

    public void uploadPicture(Uri imageUri, String key){
        storageReference = storage.getReference();

        StorageReference riversRef = storageReference.child("images/" + key);

        riversRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                dogCreationPresenter.activateSnackbar("Bild erfolgreich hochgeladen.");
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
