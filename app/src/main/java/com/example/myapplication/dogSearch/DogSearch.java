package com.example.myapplication.dogSearch;

import android.widget.Button;
import android.widget.ImageView;

public class DogSearch {

    private String searchDogName;
    private String rasseTextView;
    private String geschlechtTextView;
    private String alterTextView;
    private String papiereTextView;

    private Button likeButton;
    private Button dislikeButton;

    private ImageView searchDogImage;

    public DogSearch(String searchDogName, String rasseTextView, String alterTextView, String papiereTextView) {
        this.searchDogName = searchDogName;
        this.rasseTextView = rasseTextView;
        //this.geschlechtTextView = geschlechtTextView;
        this.alterTextView = alterTextView;
        this.papiereTextView = papiereTextView;
        //this.likeButton = likeButton;
        //this.dislikeButton = dislikeButton;
        //this.searchDogImage = searchDogImage;
    }

    public void dislike() {
        // mach etwas, wenn der dislike button gedrückt wurde
    }

    public void like() {
        // mach etwas, wenn der like Button gedrückt wurde
    }

    public String getSearchDogName() {
        return searchDogName;
    }

    public void setSearchDogName(String searchDogName) {
        this.searchDogName = searchDogName;
    }

    public String getRasseTextView() {
        return rasseTextView;
    }

    public void setRasseTextView(String rasseTextView) {
        this.rasseTextView = rasseTextView;
    }

    public String getGeschlechtTextView() {
        return geschlechtTextView;
    }

    public void setGeschlechtTextView(String geschlechtTextView) {
        this.geschlechtTextView = geschlechtTextView;
    }

    public String getAlterTextView() {
        return alterTextView;
    }

    public void setAlterTextView(String alterTextView) {
        this.alterTextView = alterTextView;
    }

    public String getPapiereTextView() {
        return papiereTextView;
    }

    public void setPapiereTextView(String papiereTextView) {
        this.papiereTextView = papiereTextView;
    }

    public Button getLikeButton() {
        return likeButton;
    }

    public void setLikeButton(Button likeButton) {
        this.likeButton = likeButton;
    }

    public Button getDislikeButton() {
        return dislikeButton;
    }

    public void setDislikeButton(Button dislikeButton) {
        this.dislikeButton = dislikeButton;
    }

    public ImageView getSearchDogImage() {
        return searchDogImage;
    }

    public void setSearchDogImage(ImageView searchDogImage) {
        this.searchDogImage = searchDogImage;
    }
}
