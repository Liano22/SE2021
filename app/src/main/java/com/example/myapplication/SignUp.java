package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    //Variablen:
    TextInputLayout username, firstName, name, email, postalCode, phoneNumber, bio, password;
    Button regBtn;

    //FirebaseDatabase rootNode;
    //DatabaseReference reference;

    DatabaseConnector dbConnector = new DatabaseConnector();

    protected void onCreate(Bundle savedinstanceState) {
        super.onCreate(savedinstanceState);
        setContentView(R.layout.sign_up);

        //Zuweisungen:
        regBtn = findViewById(R.id.regBtn);
        username = findViewById(R.id.usernameReg);
        firstName = findViewById(R.id.firstNameReg);
        name = findViewById(R.id.nameReg);
        email = findViewById(R.id.emailReg);
        postalCode = findViewById(R.id.postalCodeReg);
        phoneNumber = findViewById(R.id.phoneNumberReg);
        bio = findViewById(R.id.bioReg);
        password = findViewById(R.id.passwordReg);

        //Senden des neuen Nutzers an die Datenbank:
        regBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                //rootNode = FirebaseDatabase.getInstance();
                //reference = rootNode.getReference("users/");


                validateUsername(username);

                String usernameInput = username.getEditText().getText().toString();
                String firstNameInput = firstName.getEditText().getText().toString();
                String nameInput = name.getEditText().getText().toString();
                String emailInput = email.getEditText().getText().toString();
                String postalCodeInput = postalCode.getEditText().getText().toString();
                String phoneNumberInput = phoneNumber.getEditText().getText().toString();
                String bioInput = bio.getEditText().getText().toString();
                String passwordInput = password.getEditText().getText().toString();

                User newUser = new User(usernameInput,firstNameInput,nameInput,emailInput,postalCodeInput,phoneNumberInput,bioInput, passwordInput);

                //reference.child(usernameInput).setValue(newUser);

                dbConnector.writeUserToDatabase("users/",newUser,usernameInput);

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
     * @param username Eingabe aus Textfeld Benutzername
     * @return Boolean-Wert. True, wenn Name akzeptiert. False, wenn nicht.
     */
    private Boolean validateUsername(TextInputLayout username){

        String val = username.getEditText().getText().toString();
        String noSpace = "\\A\\w{4,20}\\z";

        //todo muss noch Anforderungen als Verzeichnis für Firebase erfüllen
        //todo muss einzigartig sein

        if(val.isEmpty()){
            username.setError("Benutzername darf nicht leer sein");
            return false;
        } else if (val.length() >= 20){
            username.setError("Benutzername zu lang");
            return false;
        } else if(!val.matches(noSpace)) {
            username.setError("Es sind keine Leerzeichen erlaubt");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    /**
     * Validierung des Passworts.
     * Passwort darf nicht leer sein.
     * TextInputLayout ermöglicht Error-Ausgaben, wird das Passwort akzeptiert,
     * werden eventuelle vorherige Fehler und Ausgaben gelöscht.
     * @param password Eingabe aus Textfeld passwort
     * @return Boolean-Wert. True, wenn Passwort akzeptiert. False, wenn nicht.
     */
    private Boolean validatePassword(TextInputLayout password) {

        String val = password.getEditText().getText().toString();

        if(val.isEmpty()){
            password.setError("Passwort darf nicht leer sein");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }

    }

}
