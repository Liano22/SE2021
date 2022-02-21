package com.example.myapplication.filter;

import android.os.Parcel;
import android.os.Parcelable;

//Karl

//Weil der Filter als Objekt übergeben wird, muss Parcelable implementiert sein.
public class Filter implements Parcelable{
    private String race, age, minPrice, maxPrice;
    String papersAvailable;

    //Konstruktor
    public Filter(String race, String age, String minPrice, String maxPrice, String papersAvailable) {
        this.race = race;
        this.age = age;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.papersAvailable = papersAvailable;
    }

    //Konstruktor für Parcel
    protected Filter(Parcel in) {
        race = in.readString();
        age = in.readString();
        minPrice = in.readString();
        maxPrice = in.readString();
        papersAvailable = in.readString();
    }

    public static final Creator<Filter> CREATOR = new Creator<Filter>() {
        @Override
        public Filter createFromParcel(Parcel in) {
            return new Filter(in);
        }

        @Override
        public Filter[] newArray(int size) {
            return new Filter[size];
        }
    };

    //Getter und Setter
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

    public String isPapersAvailable() {
        return papersAvailable;
    }

    public void setPapersAvailable(String papersAvailable) {
        this.papersAvailable = papersAvailable;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    //Parcel Objekt wird befüllt
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(race);
        parcel.writeString(age);
        parcel.writeString(minPrice);
        parcel.writeString(maxPrice);
        parcel.writeString(papersAvailable);
    }
}
