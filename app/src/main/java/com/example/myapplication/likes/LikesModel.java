package com.example.myapplication.likes;

import com.example.myapplication.dogCreation.DogCreationPresenter;

//Nils Behrens

public class LikesModel implements ILikesContract.IModel{

    LikesPresenter likesPresenter;

    public LikesModel(LikesPresenter presenter) {
        this.likesPresenter = presenter;
    }

}

//Nils Behrens
