package com.example.myapplication.matches;

import com.example.myapplication.dogCreation.DogCreationPresenter;

//Nils Behrens

public class MatchesModel implements IMatchesContract.IModel{

    MatchesPresenter matchesPresenter;

    public MatchesModel(MatchesPresenter presenter) {
        this.matchesPresenter = presenter;
    }

}

//Nils Behrens
