package com.example.myapplication.filter;

import com.example.myapplication.DatabaseConnector;

public class FilterPresenter implements IFilterContract.IPresenter {
    //Ralf & Karl

    //Deklaration von Variablen
    Filter filter;
    IFilterContract.IView filterView;
    DatabaseConnector dbConnector = new DatabaseConnector();

    //Konstruktor der Klasse mit dessen Hilfe wir die Verbindung zwischen Presenter und View herstellen.
    public FilterPresenter(IFilterContract.IView filterView) {
        this.filterView = filterView;
    }

    //Eine Methode zum erstellen eines neuen Objektes vom Typ Filter.
    @Override
    public void filter(String race, String age, String minPrice, String maxPrice, boolean papersAvailable) {
        filter = new Filter(race, age, minPrice, maxPrice, papersAvailable);
    }
}
