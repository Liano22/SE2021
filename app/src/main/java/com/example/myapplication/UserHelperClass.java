package com.example.myapplication;

public class UserHelperClass {

    String vorname, nachname, email, postleitzahl, telefonnummer, bio;

    public UserHelperClass() {
    }

    public UserHelperClass(String vorname, String nachname, String email, String postleitzahl, String telefonnummer, String bio) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.postleitzahl = postleitzahl;
        this.telefonnummer = telefonnummer;
        this.bio = bio;
    }

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
}
