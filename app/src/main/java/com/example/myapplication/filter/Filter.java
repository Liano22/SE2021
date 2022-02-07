package com.example.myapplication.filter;

public class Filter {
    private String race, age, minPrice, maxPrice;
    private boolean papersAvailable;

    public Filter(String race, String age, String minPrice, String maxPrice, boolean papersAvailable) {
        this.race = race;
        this.age = age;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.papersAvailable = papersAvailable;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public boolean isPapersAvailable() {
        return papersAvailable;
    }

    public void setPapersAvailable(boolean papersAvailable) {
        this.papersAvailable = papersAvailable;
    }
}
