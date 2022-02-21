//-- Bennedict --
package com.example.myapplication.matchDisplay;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class MatchDisplayView extends AppCompatActivity implements IMatchDisplayContract.IView{

    String interestDog;
    TextView phoneView;
    TextView emailView;
    ImageView logo;
    MatchDisplayPresenter matchDisplayPresenter = new MatchDisplayPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match);

        phoneView = findViewById(R.id.phoneMatch);
        emailView = findViewById(R.id.emailMatch);
        logo = findViewById(R.id.logoMatch);

        Drawable res = getResources().getDrawable(R.drawable.meet2breed_logo);
        logo.setImageDrawable(res);

        if (getIntent().hasExtra("interestDog")) {
            Bundle extra = getIntent().getExtras();
            interestDog = extra.getString("interestDog");
        } else {
            Log.d("Kein Extra!", "Intent überreicht keinen Dog");
        }
        matchDisplayPresenter.getValues(interestDog);
    }

    /**
     * Lässt die TextView-Felder folgendes anzeigen:
     * @param phoneNumber Die Telefonnumer des Hundebesitzers
     * @param email Die Email-Adresse des Hundebesitzers
     */
    public void setValues(String phoneNumber, String email){
        phoneView.setText("Telefonnummer: " + phoneNumber);
        emailView.setText("E-Mail Adresse: " + email);
    }

}
//-- Bennedict --