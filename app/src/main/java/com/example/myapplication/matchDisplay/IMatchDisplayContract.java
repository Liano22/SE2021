//-- Bennedict --
package com.example.myapplication.matchDisplay;

public interface IMatchDisplayContract {

    interface IModel{
        public void getValues(String interestDog);
    }

    interface IPresenter{
        public void getValues(String interestDog);
        public void setValues(String phoneNumber, String email);
    }

    interface IView{
        public void setValues(String phoneNumber, String email);
    }

}
//-- Bennedict --