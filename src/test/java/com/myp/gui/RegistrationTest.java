package com.myp.gui;

import com.myp.POM.*;
import org.testng.annotations.Test;
import org.testng.Assert;
import utils.ContentGeneration;

import java.util.Random;

public class RegistrationTest extends TestObject{

    @Test
    public void verifyUserCanRegisterWithValidData() {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** Verify user can register in the system with valid data *** < ===");

        HomePage homePage = new HomePage(super.getWebDriver());

        final String USERNAME = ContentGeneration.createUser();
        final String EMAIL = ContentGeneration.createEmail();

        System.out.println("THE RANDOM GENERATED USERNAME IS: " + USERNAME);
        System.out.println("THE RANDOM GENERATED EMAIL IS: " + EMAIL);

        System.out.println("STEP 1: Not logged in user has opened the ISkilo HomePage.");
        homePage.openHomePage();

        System.out.println("STEP 2: The user has navigated to ISkilo LoginPage");
        homePage.clickOnNavigationLoginButton();

        System.out.println("STEP 3: The user has clicked Register");
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.clickOnRegistrationLink();

        System.out.println("STEP 4: The user provides registration data");
        RegistrationPage registrationPage = new RegistrationPage(super.getWebDriver());
        registrationPage.fullRegistrationInputsAndActions(USERNAME, EMAIL, "123456");

        System.out.println("STEP 5: Verify the user is successfully logged in after registration");
        HomePage loggedInHomePage = new HomePage(super.getWebDriver());
        String actualUsername = loggedInHomePage.getLoggedInUsername();

        Assert.assertEquals(actualUsername, USERNAME, "The logged in username should match the registered username");

        System.out.println("User registration and login successful with username: " + actualUsername);
    }

    @Test
    public void verifyUserCannotRegisterWithInvalidData() {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** Verify user cannot register in the system with invalid data *** < ===");

        HomePage homePage = new HomePage(super.getWebDriver());

        final String USERNAME = "InvalidUser";
        final String EMAIL = ContentGeneration.createInvalidEmail();

        System.out.println("Generated invalid USERNAME: " + USERNAME);
        System.out.println("Generated invalid EMAIL: " + EMAIL);

        System.out.println("STEP 1: Not logged in user opens the ISkilo HomePage.");
        homePage.openHomePage();

        System.out.println("STEP 2: The user navigates to the ISkilo LoginPage.");
        homePage.clickOnNavigationLoginButton();

        System.out.println("STEP 3: The user clicks the Register link.");
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.clickOnRegistrationLink();

        System.out.println("STEP 4: The user provides invalid registration data.");
        RegistrationPage registrationPage = new RegistrationPage(super.getWebDriver());
        registrationPage.fullRegistrationInputsAndActions(USERNAME, EMAIL, "123456");

        System.out.println("STEP 5: Verify the user cannot register with invalid data.");
        registrationPage.printRegistrationErrorMessage();
    }
}