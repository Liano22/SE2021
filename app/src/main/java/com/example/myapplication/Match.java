package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Match extends AppCompatActivity {

    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    String interestDog;
    TextView phoneView;
    TextView emailView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match);

        phoneView = findViewById(R.id.phoneMatch);
        emailView = findViewById(R.id.emailMatch);

        if (getIntent().hasExtra("interestDog")) {
            Bundle extra = getIntent().getExtras();
            interestDog = extra.getString("interestDog");
        } else {
            Log.d("Kein Extra!", "Intent überreicht keinen Dog");
        }
        getValues(interestDog);
    }

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
                            phoneView.setText(phoneNumber);
                            emailView.setText(email);
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
