package com.example.myapplication.signUp;

public class User {

    private String username, firstName, name, email, postalCode, phoneNumber, bio, password, myDogs;

    public User() {
    }

    public User(String username, String firstName, String name, String email, String postalCode, String phoneNumber, String bio, String password) {
        this.username = username;
        this.firstName = firstName;
        this.name = name;
        this.email = email;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
        this.password = password;
        this.myDogs = "";
    }

    public String getMyDogs() {
        return myDogs;
    }

    public void setMyDogs(String myDogs) {
        this.myDogs = myDogs;
    }

    public String getUsername(){return username;}

    public void setUsername(String username){this.username = username;}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
