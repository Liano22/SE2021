package com.example.myapplication.filter;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.DatabaseConnector;
import com.example.myapplication.dogSearch.DogSearchView;
import com.example.myapplication.logIn.ILogInContract;

public class FilterPresenter implements IFilterContract.IPresenter {
    //Ralf & Karl

    //Deklaration von Variablen
    Filter filter;
    FilterView filterView;
    FilterModel filterModel;
    DatabaseConnector dbConnector = new DatabaseConnector();


    //Konstruktor der Klasse mit dessen Hilfe wir die Verbindung zwischen Presenter und View herstellen.
    public FilterPresenter(FilterView filterView) {
        this.filterView = filterView;
    }

    public FilterPresenter(FilterView filterView, FilterModel filterModel) {
        this.filterView = filterView;
        this.filterModel = filterModel;
    }

    //Eine Methode zum erstellen eines neuen Objektes vom Typ Filter.
    /*@Override
    public Filter filter(String race, String age, String minPrice, String maxPrice, String papersAvailable) {
        filter = new Filter(race, age, minPrice, maxPrice, papersAvailable);
        return filter;
    }*/

    public boolean validateFields(String race, String age, String minPrice, String maxPrice, String papersAvailable) {
        if (minPrice.isEmpty()) {
            filterView.setErrorMessage("minPrice", "Darf nicht leer sein.");
            return false;
        }
        if (maxPrice.isEmpty()) {
            filterView.setErrorMessage("maxPrice", "Darf nicht leer sein.");
            return false;
        }
        if (Integer.valueOf(minPrice) > Integer.valueOf(maxPrice)) {
            filterView.setErrorMessage("minPrice", "Mindestpreis muss kleiner als Höchstpreis sein.");
            return false;
        }
        return true;
    }

}

