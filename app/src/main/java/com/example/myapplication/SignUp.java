package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextInputLayout vorname, nachname, email, postleitzahl, telefonnummer, bio;
    Button regBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    protected void onCreate(Bundle savedinstanceState) {
        super.onCreate(savedinstanceState);
        setContentView(R.layout.sign_up);

        regBtn = findViewById(R.id.regBtn);
        vorname = findViewById(R.id.VornameTxt);
        nachname = findViewById(R.id.NachnameTxt);
        email = findViewById(R.id.EmailTxt);
        postleitzahl = findViewById(R.id.PostleitzahlTxt);
        telefonnummer = findViewById(R.id.TelefonnummerTxt);
        bio = findViewById(R.id.BiografieTxt);

        regBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Test");

                String vornameUser = vorname.getEditText().toString();
                String nachnameUser = nachname.getEditText().toString();
                String emailUser = email.getEditText().toString();
                String postleitzahlUser = postleitzahl.getEditText().toString();
                String telefonnummerUser = telefonnummer.getEditText().toString();
                String bioUser = bio.getEditText().toString();

                UserHelperClass helperClass = new UserHelperClass(vornameUser,nachnameUser,emailUser,postleitzahlUser,telefonnummerUser,bioUser);

                reference.setValue(helperClass);
                regBtn.setText("Geklickt");
            }
        });

    }

}
