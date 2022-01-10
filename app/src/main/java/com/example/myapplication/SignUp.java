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

    //Variablen:
    TextInputLayout benutzername, vorname, nachname, email, postleitzahl, telefonnummer, bio, passwort;
    Button regBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    protected void onCreate(Bundle savedinstanceState) {
        super.onCreate(savedinstanceState);
        setContentView(R.layout.sign_up);

        //Zuweisungen:
        regBtn = findViewById(R.id.regBtn);
        benutzername = findViewById(R.id.BenutzernameTxt);
        vorname = findViewById(R.id.VornameTxt);
        nachname = findViewById(R.id.NachnameTxt);
        email = findViewById(R.id.EmailTxt);
        postleitzahl = findViewById(R.id.PostleitzahlTxt);
        telefonnummer = findViewById(R.id.TelefonnummerTxt);
        bio = findViewById(R.id.BiografieTxt);
        passwort = findViewById(R.id.PasswortTxt);

        //Senden des neuen Nutzers an die Datenbank:
        regBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("NutzerTest/");

                validateUsername(benutzername);

                String benutzernameUser = benutzername.getEditText().getText().toString();
                String vornameUser = vorname.getEditText().getText().toString();
                String nachnameUser = nachname.getEditText().getText().toString();
                String emailUser = email.getEditText().getText().toString();
                String postleitzahlUser = postleitzahl.getEditText().getText().toString();
                String telefonnummerUser = telefonnummer.getEditText().getText().toString();
                String bioUser = bio.getEditText().getText().toString();
                String passwortUser = passwort.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(benutzernameUser,vornameUser,nachnameUser,emailUser,postleitzahlUser,telefonnummerUser,bioUser, passwortUser);

                reference.child(benutzernameUser).setValue(helperClass);
                regBtn.setText("Geklickt");
            }
        });

    }

    /**
     * Validierung des Benutzernamens.
     * Benutzername darf nicht leer oder zu lang sein
     * und darf keine Leerzeichen enthalten.
     * TextInputLayout ermöglicht Error-Ausgaben, wird der Name akzeptiert,
     * werden eventuelle vorherige Fehler und Ausgaben gelöscht.
     * @param benutzername Eingabe aus Textfeld Benutzername
     * @return Boolean-Wert. True, wenn Name akzeptiert. False, wenn nicht.
     */
    private Boolean validateUsername(TextInputLayout benutzername){

        String val = benutzername.getEditText().getText().toString();
        String noSpace = "\\A\\w{4,20}\\z";

        //todo Muss noch Anforderungen als Verzeichnis für Firebase erfüllen

        if(val.isEmpty()){
            benutzername.setError("Benutzername darf nicht leer sein");
            return false;
        } else if (val.length() >= 20){
            benutzername.setError("Benutzername zu lang");
            return false;
        } else if(!val.matches(noSpace)) {
            benutzername.setError("Es sind keine Leerzeichen erlaubt");
            return false;
        } else {
            benutzername.setError(null);
            benutzername.setErrorEnabled(false);
            return true;
        }
    }

    /**
     * Validierung des Passworts.
     * Passwort darf nicht leer sein.
     * TextInputLayout ermöglicht Error-Ausgaben, wird das Passwort akzeptiert,
     * werden eventuelle vorherige Fehler und Ausgaben gelöscht.
     * @param passwort Eingabe aus Textfeld passwort
     * @return Boolean-Wert. True, wenn Passwort akzeptiert. False, wenn nicht.
     */
    private Boolean validatePassword(TextInputLayout passwort) {

        String val = passwort.getEditText().getText().toString();

        if(val.isEmpty()){
            passwort.setError("Passwort darf nicht leer sein");
            return false;
        } else {
            passwort.setError(null);
            passwort.setErrorEnabled(false);
            return true;
        }

    }

}
