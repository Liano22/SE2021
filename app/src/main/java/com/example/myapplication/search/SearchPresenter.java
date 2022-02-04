package com.example.myapplication.search;

import com.example.myapplication.DatabaseConnector;

public class SearchPresenter implements ISearchContract.IPresenter {

    Search search;
    ISearchContract.IView searchView;

    DatabaseConnector dbConnector = new DatabaseConnector();
    public SearchPresenter (ISearchContract.IView searchView) {
        this.searchView = searchView;
    }
    @Override
    public void filter(String race, String age, String minPrice, String maxPrice, boolean papersAvailable) {
        search = new Search(race, age, minPrice, maxPrice, papersAvailable);
    }
}
