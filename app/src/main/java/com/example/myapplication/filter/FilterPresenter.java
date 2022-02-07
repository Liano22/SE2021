package com.example.myapplication.filter;

import com.example.myapplication.DatabaseConnector;

public class FilterPresenter implements IFilterContract.IPresenter {

    Filter filter;
    IFilterContract.IView filterView;

    DatabaseConnector dbConnector = new DatabaseConnector();
    public FilterPresenter(IFilterContract.IView filterView) {
        this.filterView = filterView;
    }
    @Override
    public void filter(String race, String age, String minPrice, String maxPrice, boolean papersAvailable) {
        filter = new Filter(race, age, minPrice, maxPrice, papersAvailable);
    }
}
