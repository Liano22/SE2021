package com.example.myapplication.dogCreation;

// --- Johanna ---

public class  Dog {


    private String username, name, age, gender, race, pic, bio, price;
    private boolean hybrid, papers;



    public Dog() {
    }

    // wird bei der Erstellung eines neuen Hundes verwendet:
    public Dog(String username,String name, String age, String gender, String race, String pic, String bio, String price, boolean hybrid, boolean papers) {
        this.username = username;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.race = race;
        this.pic = pic;
        this.bio = bio;
        this.price = price;
        this.hybrid = hybrid;
        this.papers = papers;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isHybrid() {
        return hybrid;
    }

    public void setHybrid(boolean hybrid) {
        this.hybrid = hybrid;
    }

    public boolean isPapers() {
        return papers;
    }

    public void setPapers(boolean papers) {
        this.papers = papers;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    // --- Johanna ---
}
