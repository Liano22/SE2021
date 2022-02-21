package com.example.myapplication.matchDisplay;

public class MatchPresenter {

    MatchView matchView;
    MatchModel matchModel = new MatchModel(this);

    public MatchPresenter(MatchView matchView){
        this.matchView = matchView;
    }

    /**
     * Fordert das Model auf, die Daten des Hundebesitzers auszulesen.
     * @param interestDog ID des Hundes, mit dem gematched wurde
     */
    public void getValues(String interestDog){
        matchModel.getValues(interestDog);
    }

    /**
     * Fordert die View auf, die Daten des Hundebesitzers anzuzeigen.
     * @param phoneNumber Die Telefonnumer des Hundebesitzers
     * @param email Die Email-Adresse des Hundebesitzers
     */
    public void setValues(String phoneNumber, String email){
        matchView.setValues(phoneNumber,email);
    }

}
