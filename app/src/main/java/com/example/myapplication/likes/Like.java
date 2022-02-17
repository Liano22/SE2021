package com.example.myapplication.likes;

public class Like {

    //Deklaration von Variablen
    private String dog_name;
    private String dog_race;
    private String dog_age;
    private String dog_price;

    //Konstruktor der Klasse Like
    public Like(String dog_name, String dog_race, String dog_age, String dog_price) {
        this.dog_name = dog_name;
        this.dog_race = dog_race;
        this.dog_age = dog_age;
        this.dog_price = dog_price;
    }

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
}
