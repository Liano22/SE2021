package com.example.myapplication.dogCreation;

public class  Dog {
    private String name, age, gender, race, pic, bio, price;
    private boolean hybrid, papers;



    public Dog() {
    }

    // wird bei der Erstellung eines neuen Hundes verwendet:
    public Dog(String name, String age, String gender, String race, String pic, String bio, String price, boolean hybrid, boolean papers) {
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

    /*
    public void schreibeHund(){

    }

    public void ladeHund(){

    }

    public void bearbeiten(String cmd, String data){
        switch(cmd){
            case "name":
                this.name = data;
                break;
            case "alter":
                this.alter = data;
                break;
            case "rasse":
                this.rasse = data;
                break;
            case "bild":
                this.bild = data;
                break;
            case "bio":
                this.bio = data;
                break;
            case "preis":
                this.preis = data;
                break;
        }

    }
    public void bearbeiten(String cmd, boolean data){
        switch(cmd){
            case "mischling":
                this.mischling = data;
                break;
            case "papiere":
                this.papiere = data;
                break;
        }

    } */
}
