package com.example.myapplication;

public class UserHelperClass {

    private String benutzername, vorname, nachname, email, postleitzahl, telefonnummer, bio, passwort;

    public UserHelperClass() {
    }

    public UserHelperClass(String benutzername, String vorname, String nachname, String email, String postleitzahl, String telefonnummer, String bio, String passwort) {
        this.benutzername = benutzername;
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.postleitzahl = postleitzahl;
        this.telefonnummer = telefonnummer;
        this.bio = bio;
        this.passwort = passwort;
    }

    public String getBenutzername(){return benutzername;}

    public void setBenutzername(String benutzername){this.benutzername = benutzername;}

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
