package com.example.myapplication.dashboard;

import java.util.ArrayList;

public interface IDashboardContract {
    interface DashboardView {

        void updateRecyclcerView();

    }

    interface DashboardPresenter {

        void onDestroy();
        void addNewDog();
        ArrayList getAllItems();

    }

    interface DashboardModel {

        void getListData();

    }
}
