package com.example.myapplication.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.dashboard.DashboardView;
import com.google.android.material.textfield.TextInputLayout;
//Hier haben alle dran gearbeitet
public class SignUpView extends AppCompatActivity implements ISignUpContract.IView {

    SignUpPresenter presenter = new SignUpPresenter(this);

    //Deklaration von Variablen
    TextInputLayout username, firstName, name, email, postalCode, phoneNumber, bio, password;
    Button regBtn;

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
            public void onClick(View view) {


                String usernameInput = username.getEditText().getText().toString();
                String firstNameInput = firstName.getEditText().getText().toString();
                String nameInput = name.getEditText().getText().toString();
                String emailInput = email.getEditText().getText().toString();
                String postalCodeInput = postalCode.getEditText().getText().toString();
                String phoneNumberInput = phoneNumber.getEditText().getText().toString();
                String bioInput = bio.getEditText().getText().toString();
                String passwordInput = password.getEditText().getText().toString();

                if (presenter.validateUsername(usernameInput) && presenter.validatePassword(passwordInput) && presenter.validatePostalCode(postalCodeInput)) {
                    presenter.userExists(usernameInput, firstNameInput, nameInput, emailInput, postalCodeInput, phoneNumberInput, bioInput, passwordInput);
                }
                
            }
        });

    }

    /**
     * Error Handling
     * @param field wird bestimmt durch Methode in Presenter
     * @param msg Error Message, die angezeigt wird. (Kommt auch von Presenter)
     */
    public void setErrorMessage(String field, String msg) {
        switch (field) {
            case "username":
                username.setError(msg);
                break;
            case "firstName":
                firstName.setError(msg);
                break;
            case "name":
                name.setError(msg);
                break;
            case "email":
                email.setError(msg);
                break;
            case "postalCode":
                postalCode.setError(msg);
                break;
            case "phoneNumber":
                phoneNumber.setError(msg);
                break;
            case "bio":
                bio.setError(msg);
                break;
            case "password":
                password.setError(msg);
                break;
        }
    }

    public void goToDashboard(String username) {
        Intent signUpToDashboard = new Intent(this, DashboardView.class);
        signUpToDashboard.putExtra("currentUser", username);
        startActivity(signUpToDashboard);
    }

}
//Hier haben alle dran gearbeitet