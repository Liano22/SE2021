package com.example.myapplication.matches;

//Nils Behrens

public class Match {

    //Deklaration von Variablen
    private String dog_name;
    private String dog_race;
    private String dog_age;
    private String dog_price;
    private String user_name;
    private String user_email;

    //Konstruktor der Klasse Match
    public Match(String dog_name, String dog_race, String dog_age, String dog_price, String user_name, String user_email) {
        this.dog_name = dog_name;
        this.dog_race = dog_race;
        this.dog_age = dog_age;
        this.dog_price = dog_price;
        this.user_name = user_name;
        this.user_email = user_email;
    }

    //Getter und Setter für die Klassenvariablen
    public String getDog_name() {
        return dog_name;
    }

    public void setDog_name(String dog_name) {
        this.dog_name = dog_name;
    }

    public String getDog_race() {
        return dog_race;
    }

    public void setDog_race(String dog_race) {
        this.dog_race = dog_race;
    }

    public String getDog_age() {
        return dog_age;
    }

    public void setDog_age(String dog_age) {
        this.dog_age = dog_age;
    }

    public String getDog_price() {
        return dog_price;
    }

    public void setDog_price(String dog_price) {
        this.dog_price = dog_price;
    }

    public String getUser_name() { return user_name; }

    public void setUser_name(String user_name) { this.user_name = user_name; }

    public String getUser_email() { return user_email; }

    public void setUser_email(String user_email) { this.user_email = user_email; }
}

//Nils Behrens
