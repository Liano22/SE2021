package com.example.myapplication.signUp;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
//Hier haben alle dran gearbeitet
public class SignUpPresenter implements ISignUpContract.IPresenter{

    //Deklaration von Variablen
    SignUpView view;
    SignUpModel model;

    //Konstruktor der Klasse SignUpPresenter
    public SignUpPresenter(SignUpView view){
        this.view = view;
        this.model = new SignUpModel();
    }

    public SignUpPresenter(SignUpView signUpView, SignUpModel signUpModel){
        this.view = signUpView;
        this.model = signUpModel;
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
    public Boolean validateUsername(String username) {

        String noSpace = "\\A\\w{4,20}\\z";

        if(username.isEmpty()) {
            view.setErrorMessage("username", "Benutzername darf nicht leer sein");
            return false;
        } else if (username.length() >= 20) {
            view.setErrorMessage("username", "Benutzername zu lang");
            return false;
        } else if(!username.matches(noSpace)) {
            view.setErrorMessage("username", "Es sind keine Leerzeichen erlaubt");
            return false;
        } else {
            return true;
        }
    }

    //----------Karls kram-----------------------------------
    //Prüfen ob der Nutzer in der Datenbank bereits existiert.
    public void userExists(String username, String firstName, String name, String email, String postalCode, String phoneNumber, String bio, String  password) {

        Query checkUser = model.readUserFromDatabase(username, "username");
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists() && snapshot.child(username).child("username").getValue(String.class).equals(username)) {
                    view.setErrorMessage("username", "Nutzername bereits vergeben");
                    return;
                } else {
                    view.setErrorMessage("username", null);
                    writeUser(username, firstName, name, email, postalCode, phoneNumber, bio, password);
                    view.goToDashboard(username);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    //----------Karls kram------------------------------------

    /**
     * Validierung des Passworts.
     * Passwort darf nicht leer sein.
     * TextInputLayout ermöglicht Error-Ausgaben, wird das Passwort akzeptiert,
     * werden eventuelle vorherige Fehler und Ausgaben gelöscht.
     * @param password Eingabe aus Textfeld passwort
     * @return Boolean-Wert. True, wenn Passwort akzeptiert. False, wenn nicht.
     */
    public boolean validatePassword(String password) {

        if(password.isEmpty()) {
            view.setErrorMessage("password", "Passwort darf nicht leer sein");
            return false;
        } else if (password.length() < 6) {
            view.setErrorMessage("password", "Passwort zu kurz");
            return false;
        } else if (password.length() > 40) {
            view.setErrorMessage("password", "Passwort zu lang");
            return false;
        } else {
            return true;
        }

    }
    //In Deutschland sind alle Plz 5-stellig, also ist alles andere ungültig.
    public boolean validatePostalCode(String postalCode) {
        if (postalCode.length() == 5) {
            return true;
        } else {
            view.setErrorMessage("postalCode", "Ungültige Postleitzahl");
            return false;
        }
    }

    public void writeUser(String username, String firstName, String name, String email, String postalCode, String phoneNumber, String bio, String password) {
        User newUser = new User(username,firstName,name,email,postalCode,phoneNumber,bio, password);
        model.writeUserToDatabase(newUser,username);
    }

}
//Hier haben alle dran gearbeitet
