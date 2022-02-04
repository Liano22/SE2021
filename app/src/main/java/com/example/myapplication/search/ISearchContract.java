package com.example.myapplication.search;

public interface ISearchContract {
    interface IView{

    }

    interface IPresenter{
        void filter(String race, String age, String minPrice, String maxPrice, boolean papersAvailable);

    }
}
