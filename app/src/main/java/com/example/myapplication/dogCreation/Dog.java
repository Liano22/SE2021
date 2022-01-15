package com.example.myapplication.dogCreation;

public class Dog {
    private String name, alter, rasse, bild, bio, preis;
    private boolean mischling, papiere;
    // TODO Instanzen der Klassen Suche, Likes, Historie hinzufügen


    public Dog(String name, String alter, String rasse, String bild, String bio, String preis, boolean mischling, boolean papiere) {
        this.name = name;
        this.alter = alter;
        this.rasse = rasse;
        this.bild = bild;
        this.bio = bio;
        this.preis = preis;
        this.mischling = mischling;
        this.papiere = papiere;
    }

    public void schreibeHund(){
        //todo
    }

    public void ladeHund(){
        //todo
    }

    public void bearbeiten(String cmd, String data){
        switch(cmd){
            case "name":
                this.name = data;
                //todo schreibeDB()
                break;
            case "alter":
                this.alter = data;
                //todo schreibeDB()
                break;
            case "rasse":
                this.rasse = data;
                //todo schreibeDB()
                break;
            case "bild":
                this.bild = data;
                //todo schreibeDB()
                break;
            case "bio":
                this.bio = data;
                //todo schreibeDB()
                break;
            case "preis":
                this.preis = data;
                //todo schreibeDB()
                break;
        }

    }
    public void bearbeiten(String cmd, boolean data){
        switch(cmd){
            case "mischling":
                this.mischling = data;
                //todo schreibeDB()
                break;
            case "papiere":
                this.papiere = data;
                //todo schreibeDB()
                break;
        }

    }
}
