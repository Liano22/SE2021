package com.example.myapplication.filter;

import com.google.firebase.database.Query;

public interface IFilterContract {
    //Karl & Ralf

    interface IView{
        boolean checkRadioButton();

    }

    interface IPresenter{
        //void filter(String race, String age, String minPrice, String maxPrice, boolean papersAvailable);

        boolean validateFields(String minprice, String maxprice);
    }

    interface IModel{
        Query getAllDogs();
    }
}
