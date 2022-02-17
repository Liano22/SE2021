package com.example.myapplication.matches;

//Nils Behrens

import com.example.myapplication.dogCreation.IDogCreationContract;

public class MatchesPresenter implements IMatchesContract.IPresenter{

    MatchesModel matchesModel = new MatchesModel(this);
    IMatchesContract.MatchesView MatchesView;

    public MatchesPresenter(IMatchesContract.MatchesView matchesView) {
        this.MatchesView = matchesView; // Verbindung zur View
    }

}

//Nils Behrens
