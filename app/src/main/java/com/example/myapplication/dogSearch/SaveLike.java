package com.example.myapplication.dogSearch;



import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.Match;
import com.example.myapplication.signUp.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * ist zuständig für das Speichern eines Likes in der Datenbank, sobald auf den Wau-Button in der Suche geklickt wurde
 *
 * @author Kilian Mauson
 */

public class SaveLike {

    //Deklaration & Initialisierung von Variablen
    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    Context context;

    public SaveLike(Context context){
        this.context = context;
    }

    /**
     * setzt nextMatchId der Datenbank auf newMatchId
     *
     * @param newMatchId
     */
    public void writeNextMatchID(int newMatchId) {
        reference = rootNode.getReference("nextMatchId");
        reference.setValue(newMatchId);
    }

    /**
     * schreibt eine Instanz der Like-KLasse als neuen Wert in die Datenbank an die Stelle id
     *
     * @param newLike
     * @param id
     */
    public void writeLikeToDatabase(LikeClass newLike, String id) {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("matches");
        reference.child(id).setValue(newLike);
    }

    /**
     * erweitert den "likes" Eintrag bei den gespeicherten Hunden um die jeweilige ID des Hundes der ein Like ausgesprochen hat
     *
     * @param goToDog
     * @param IdToWrite
     */
    public void changeUserDogList(String goToDog, String IdToWrite) {
        reference = rootNode.getReference("dogs/" + goToDog);
        Query dogs = reference;
        dogs.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dogsFromDB = snapshot.child("likes").getValue(String.class);
                String appendDog;
                if (dogsFromDB == null) {
                    appendDog = IdToWrite + ",";
                } else {
                    appendDog = dogsFromDB + IdToWrite + ",";
                }

                reference = rootNode.getReference("dogs/" + goToDog + "/likes");
                reference.setValue(appendDog);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Error", error.toException().toString());
            }
        });
    }

    /**
     * schreibe die Match id in den entsprechenden Hundeeintrag der Datenbank
     *
     * @param goToDog
     * @param IdToWrite
     */
    public void changeMatchesInDogs(String goToDog, String IdToWrite) {
        reference = rootNode.getReference("dogs/" + goToDog);
        Query dogs = reference;
        dogs.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dogsFromDB = snapshot.child("matches").getValue(String.class);
                String appendDog;
                if (dogsFromDB == null) {
                    appendDog = IdToWrite + ",";
                } else {
                    appendDog = dogsFromDB + IdToWrite + ",";
                }

                reference = rootNode.getReference("dogs/" + goToDog + "/matches");
                reference.setValue(appendDog);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Error", error.toException().toString());
            }
        });
    }


    /**
     * @author Bennedict Schweimer
     */

    public void checkForLike(String meDog, String interestDog){
        reference = rootNode.getReference("dogs/" + meDog);
        Query dog = reference;
        dog.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String likesFromDB = snapshot.child("likes").getValue(String.class);
                Log.i("Likes",likesFromDB);
                if(likesFromDB == null){
                    return;
                }
                else {
                    String[] likes = likesFromDB.split(",");
                    for (String like : likes) {
                        Log.i("Match",like);
                        Log.i("Match", interestDog);
                        if (like.equals(interestDog)) {
                            //gehe zu matches in dog
                            //gehe für jeden Einrag in den jeweiligen match mit der gleichen id
                            //vergleiche ob dog_id_2 == interestDog ist, falls ja, dann setze in dem Eintrag matches auf true

                            String matchesFromDB = snapshot.child("matches").getValue(String.class);
                            if (matchesFromDB == null) {
                                return;
                            } else {
                                String[] matches = matchesFromDB.split(",");
                                for (String match : matches) {

                                }
                            }

                            //Starte die Matches Activity
                            Intent match = new Intent(context, Match.class);
                            match.putExtra("interestDog",interestDog);
                            context.startActivity(match);
                            return;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Error", error.toException().toString());
            }
        });
    }


}
