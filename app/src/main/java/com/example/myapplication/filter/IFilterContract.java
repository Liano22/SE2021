package com.example.myapplication.filter;

public interface IFilterContract {
    //Karl & Ralf

    interface IView{

    }

    interface IPresenter{
        void filter(String race, String age, String minPrice, String maxPrice, boolean papersAvailable);

    }

    interface IModel{

    }
}
