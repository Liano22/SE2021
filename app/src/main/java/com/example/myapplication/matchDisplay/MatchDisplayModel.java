//-- Bennedict --
package com.example.myapplication.matchDisplay;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MatchDisplayModel implements IMatchDisplayContract.IModel{

    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    MatchDisplayPresenter matchDisplayPresenter;

    public MatchDisplayModel(MatchDisplayPresenter matchDisplayPresenter){
        this.matchDisplayPresenter = matchDisplayPresenter;
    }
    
    /**
     * Liest die Telefonnummer und die Emailadresse einens Hundebesitzers aus.
     * Zuerst wird der gesuchte Hund aus der Datenbank gefischt. Der Benutzername seines
     * Besitzers wird ausgelesen, woraufhin dessen Telefonnummer und Emailadresse erfragt
     * und als Strings zurückgegeben werden. Mit diesen Werten wird der Presenter verständigt.
     * @param interestDog Gesuchter Hund
     */
    public void getValues(String interestDog){

        if(interestDog != null) {
            reference = rootNode.getReference("dogs/" + interestDog);
            Query dog = reference;
            dog.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String user = snapshot.child("username").getValue(String.class);
                    Query query = rootNode.getReference("users/" + user);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String phoneNumber = snapshot.child("phoneNumber").getValue(String.class);
                            String email = snapshot.child("email").getValue(String.class);
                            matchDisplayPresenter.setValues(phoneNumber,email);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.d("Kein User", "Abbruch des Auslesens");
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("Kein Dog", "Abbruch des Auslesens");
                }
            });
        }
    }


}
//-- Bennedict --