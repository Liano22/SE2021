package com.example.myapplication.dashboard;

import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity implements IDashboardContract.DashboardModel {

    private String hundeName;
    private String matchesButtonName;

    public Dashboard(String hundeName, String matchesButtonName) {
        this.hundeName = hundeName;
        this.matchesButtonName = matchesButtonName;
    }

    @Override
    public void getListData() {

    }

}
