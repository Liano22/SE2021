package com.example.myapplication.dogSearch;

import android.widget.Button;
import android.widget.ImageView;

/**
 * Klasse für die Einträge der Recyclerview in Search
 * enthält alle wichtigen Information zum Anzeigen eines Hundes, bzw. zum Weitergeben an andere Activities
 *
 * @author Kilian Mauson
 */


public class DogSearchModel {

    //Deklaration von Variablen
    private String searchDogName;
    private String rasseTextView;
    private String geschlechtTextView;
    private String alterTextView;
    private String papiereTextView;
    private String image;
    private String dogId;
    private String searchDogId;
    private String dogUser;
    private String searchUser;

    private String priceTextView;
    private Button likeButton;
    private Button dislikeButton;
    private ImageView searchDogImage;

    public DogSearchModel(String searchDogName, String rasseTextView, String alterTextView, String papiereTextView, String geschlechtTextView, String image, String priceTextView, String dogId, String searchDogId, String dogUser, String searchUser) {
        this.searchDogName = searchDogName;
        this.rasseTextView = rasseTextView;
        this.geschlechtTextView = geschlechtTextView;
        this.alterTextView = alterTextView;
        this.papiereTextView = papiereTextView;
        this.image = image;
        this.priceTextView = priceTextView;
        this.dogId = dogId;
        this.searchDogId = searchDogId;
        this.dogUser = dogUser;
        this.searchUser = searchUser;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setSearchDogImage(ImageView searchDogImage) {
        this.searchDogImage = searchDogImage;
    }

    public String getPriceTextView() {
        return priceTextView;
    }

    public void setPriceTextView(String priceTextView) {
        this.priceTextView = priceTextView;
    }

    public String getDogId() {
        return dogId;
    }

    public void setDogId(String dogId) {
        this.dogId = dogId;
    }

    public String getSearchDogId() {
        return searchDogId;
    }

    public void setSearchDogId(String searchDogId) {
        this.searchDogId = searchDogId;
    }

    public String getDogUser() {
        return dogUser;
    }

    public void setDogUser(String dogUser) {
        this.dogUser = dogUser;
    }

    public String getSearchUser() {
        return searchUser;
    }

    public void setSearchUser(String searchUser) {
        this.searchUser = searchUser;
    }
}
