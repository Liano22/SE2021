package com.example.myapplication;

public class Nutzer {

    private String passwort;
    public String benutzername, email, telefonnummer, ort, postleitzahl, bio;

    public Nutzer(String benutzername, String passwort, String email, String telefonnummer, String ort, String postleitzahl, String bio){
        benutzername = this.benutzername;
        passwort = this.passwort;
        email = this.email;
        telefonnummer = this.telefonnummer;
        ort = this.ort;
        postleitzahl = this.postleitzahl;
        bio = this.bio;
    }

    public void schreibeDB(){
        //todo
    }

    public void ladeDB(){
        //todo
    }

    public void bearbeiten(String cmd, String data){
        switch(cmd){
            case "benutzername":
                this.benutzername = data;
                //todo schreibeDB()
                break;
            case "passwort":
                this.passwort = data;
                //todo schreibeDB()
                break;
            case "email":
                this.email = data;
                //todo schreibeDB()
                break;
            case "telefonnummer":
                this.telefonnummer = data;
                //todo schreibeDB()
                break;
            case "ort":
                this.ort = data;
                //todo schreibeDB()
                break;
            case "postleitzahl":
                this.postleitzahl = data;
                //todo schreibeDB()
                break;
            case "bio":
                this.bio = data;
                //todo schreibeDB()
                break;
        }

    }
}
