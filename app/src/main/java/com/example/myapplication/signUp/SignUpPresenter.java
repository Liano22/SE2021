package com.example.myapplication.signUp;

import androidx.annotation.NonNull;

import com.example.myapplication.User;
import com.example.myapplication.logIn.ILogInContract;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignUpPresenter implements ISignUpContract.IPresenter{

    ISignUpContract.IView view;

    public SignUpPresenter(ISignUpContract.IView view){
        this.view = view;
    }
    ISignUpContract.IModel model = new SignUpModel();

    public SignUpPresenter(){

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
    public Boolean validateUsername(TextInputLayout username) {

        String val = username.getEditText().getText().toString();
        String noSpace = "\\A\\w{4,20}\\z";

        //todo muss noch Anforderungen als Verzeichnis für Firebase erfüllen
        //todo muss einzigartig sein

        if(val.isEmpty()) {
            username.setError("Benutzername darf nicht leer sein");
            return false;
        } else if (val.length() >= 20) {
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

    //----------Karls kram-----------------------------------


    public void userExists(String username, String firstName, String name, String email, String postalCode, String phoneNumber, String bio, String  password) {

        Query checkUser = model.readUserFromDatabase(username, "username");
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists() && snapshot.child(username).child("username").getValue(String.class).equals(username)) {
                    //username.setError("Nutzername bereits vergeben");
                    view.setErrorMessage("username", "Nutzername bereits vergeben");
                    return;
                } else {
                    view.setErrorMessage("username", null);
                    writeUser(username, firstName, name, email, postalCode, phoneNumber, bio, password);
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
    private Boolean validatePassword(TextInputLayout password) {

        String val = password.getEditText().getText().toString();

        if(val.isEmpty()) {
            password.setError("Passwort darf nicht leer sein");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }

    }

    public void writeUser(String username, String firstName, String name, String email, String postalCode, String phoneNumber, String bio, String password) {

        User newUser = new User(username,firstName,name,email,postalCode,phoneNumber,bio, password);
        model.writeUserToDatabase(newUser,username);
    }

}
