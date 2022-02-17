package com.example.myapplication.likes;

import com.example.myapplication.matches.IMatchesContract;
import com.example.myapplication.matches.MatchesModel;

//Nils Behrens

public class LikesPresenter implements ILikesContract.IPresenter{

    LikesModel likesModel = new LikesModel(this);
    ILikesContract.LikesView LikesView;

    public LikesPresenter(ILikesContract.LikesView likesView) {
        this.LikesView = likesView; // Verbindung zur View
    }

}

//Nils Behrens
