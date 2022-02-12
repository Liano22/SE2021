package com.example.myapplication.dogCreation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class DogCreationModelTest {
    Dog testDog;
    DogCreationModel dogCreationmodelTest;
    @Mock
    DogCreationPresenter dogCreationPresenterTest;
    @Mock
    DogCreationView dogCreationViewTest;

    @BeforeAll
    void initiateValues() {
        testDog = new Dog("Testi","3", "weiblich", "Dackel", "123242342","Hier sollte eine Biografie stehen", "42", true, false);
        dogCreationmodelTest = new DogCreationModel(dogCreationPresenterTest);
    }

    @Test
    void writeDogToDatabase() {
        dogCreationmodelTest.writeDogToDatabase(testDog,"test1");
    }

    @org.junit.jupiter.api.Test
    void getNextDogID() {
    }

    @org.junit.jupiter.api.Test
    void writeNextDogID() {
    }

    @org.junit.jupiter.api.Test
    void changeUserDogList() {
    }

    @org.junit.jupiter.api.Test
    void uploadPicture() {
    }
}