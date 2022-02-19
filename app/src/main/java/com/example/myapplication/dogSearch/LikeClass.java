package com.example.myapplication.dogSearch;

public class LikeClass {

    private String dog_id_1, dog_id_2, like, match, user_id_1, user_id_2;

    public LikeClass(String dog_id_1, String dog_id_2, String like, String match, String user_id_1, String user_id_2) {
        this.dog_id_1 = dog_id_1;
        this.dog_id_2 = dog_id_2;
        this.like = like;
        this.match = match;
        this.user_id_1 = user_id_1;
        this.user_id_2 = user_id_2;
    }

    public String getDog_id_1() {
        return dog_id_1;
    }

    public void setDog_id_1(String dog_id_1) {
        this.dog_id_1 = dog_id_1;
    }

    public String getDog_id_2() {
        return dog_id_2;
    }

    public void setDog_id_2(String dog_id_2) {
        this.dog_id_2 = dog_id_2;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getUser_id_1() {
        return user_id_1;
    }

    public void setUser_id_1(String user_id_1) {
        this.user_id_1 = user_id_1;
    }

    public String getUser_id_2() {
        return user_id_2;
    }

    public void setUser_id_2(String user_id_2) {
        this.user_id_2 = user_id_2;
    }
}
