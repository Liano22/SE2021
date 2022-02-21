//-- Bennedict --
package com.example.myapplication.matchDisplay;

public class MatchDisplayPresenter implements IMatchDisplayContract.IPresenter{

    MatchDisplayView matchDisplayView;
    MatchDisplayModel matchDisplayModel = new MatchDisplayModel(this);

    public MatchDisplayPresenter(MatchDisplayView matchDisplayView){
        this.matchDisplayView = matchDisplayView;
    }

    /**
     * Fordert das Model auf, die Daten des Hundebesitzers auszulesen.
     * @param interestDog ID des Hundes, mit dem gematched wurde
     */
    public void getValues(String interestDog){
        matchDisplayModel.getValues(interestDog);
    }

    /**
     * Fordert die View auf, die Daten des Hundebesitzers anzuzeigen.
     * @param phoneNumber Die Telefonnumer des Hundebesitzers
     * @param email Die Email-Adresse des Hundebesitzers
     */
    public void setValues(String phoneNumber, String email){
        matchDisplayView.setValues(phoneNumber,email);
    }

}
//-- Bennedict --