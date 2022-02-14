package com.example.myapplication.dogCreation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;

public class DogCreationPresenterTest {

    Dog testDog;

    DogCreationPresenter dogCreationPresenter;

    @Mock
    DogCreationView dogCreationView;

    @Mock
    DogCreationModel dogCreationModel;

    @BeforeAll
    public void setup(){
        dogCreationPresenter =  new DogCreationPresenter(dogCreationView);
        testDog = new Dog("Testine", "12", "weiblich", "Chihuahua", "12323534634534214", "Weltumrunderin","VB", false,true);
    }

    @Test
    public void saveDog() {
        dogCreationPresenter.saveDog("BenniBanditos","Testine", "12", "weiblich", "Chihuahua", "12323534634534214", "Weltumrunderin","VB", false,true);
        verify(dogCreationModel).getNextDogID();
        verify(dogCreationModel).writeDogToDatabase(any(),anyString());
        verify(dogCreationModel).writeNextDogID(anyInt());
        verify(dogCreationView).changeToDashboard();
    }

    @Test
    public void linkDogToUser() {
    }

    @Test
    public void validateFields() {
    }

    @Test
    public void activateSnackbar() {
    }

    @Test
    public void savePicture() {
    }
}