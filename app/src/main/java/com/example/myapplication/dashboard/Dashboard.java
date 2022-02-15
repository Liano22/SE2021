package com.example.myapplication.dashboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.dogSearch.DogSearchView;
import com.example.myapplication.likes.LikesView;
import com.example.myapplication.matches.MatchesView;

public class Dashboard extends AppCompatActivity {

    private String dogID;
    private String dogName;
    private Button matchesButton;
    private Button likesButton;
    private Button searchButton;
    private ImageView dogPicture;

    public Dashboard(String dogID, String hundeName) {

        this.dogName = hundeName;
        this.dogID = dogID;
        //this.dogPicture.setImageResource(profilePicture);
    }

    public void goToMatches(Context context) {
        Log.d("Matches Button", "Go to Matches of " + this.dogName + " with the ID " + this.dogID);

        Intent matches = new Intent(context, MatchesView.class);
        matches.putExtra("dogID", this.dogID);
        context.startActivity(matches);

    }

    public void goToLikes(Context context) {
        Log.d("Likes Button", "Go to Likes of " + this.dogName + " with the ID " + this.dogID);

        Intent likes = new Intent(context, LikesView.class);
        likes.putExtra("dogID", this.dogID);
        context.startActivity(likes);
    }


    public void goToSearch(Context context) {
        Log.d("Search Button", "Go to Search of " + this.dogName + " with the ID " + this.dogID);

        Intent search = new Intent(context, DogSearchView.class);
        search.putExtra("dogID", this.dogID);
        context.startActivity(search);
    }

    /*
    public void goToDogProfile(Context context) {
        Log.d("Hundebild", "Go to DogProfile of " + this.dogName + " with the ID " + this.dogID);

        Intent profile = new Intent(this, DogProfile.class);
        profile.putExtra("dogID", this.dogID);
        startActivity(profile);
    } */


    public String getDogName() {
        return dogName;
    }

    public Button getMatchesButton() {
        return matchesButton;
    }

    public Button getLikesButton() {
        return likesButton;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public ImageView getDogPicture() {
        return dogPicture;
    }

}
