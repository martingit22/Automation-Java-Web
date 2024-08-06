package com.myp.gui;

import com.myp.POM.*;
import org.testng.annotations.Test;
import org.testng.Assert;
import utils.ContentGeneration;

import java.io.File;

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
        homePage.clickOnNavigationLoginButton();
        System.out.println("STEP 3: The user has clicked Register");
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.clickOnRegistrationLink();
        System.out.println("STEP 4: The user provides registration data");
        RegistrationPage registrationPage = new RegistrationPage(super.getWebDriver());
        registrationPage.fullRegistrationInputsAndActions(USERNAME, EMAIL, "123456");
        System.out.println("STEP 5: Verify the user is successfully logged in after registration");
        HomePage loggedInHomePage = new HomePage(super.getWebDriver());
        ProfilePage profilePage = new ProfilePage(super.getWebDriver());
        profilePage.clickOnProfileButton();
        String actualUsername = loggedInHomePage.getLoggedInUsername();
        Assert.assertEquals(actualUsername, USERNAME, "The logged in username should match the registered username");
        System.out.println("User registration and login successful with username: " + actualUsername);
    }

    @Test
    public void verifyUserCannotRegisterWithInvalidData() {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** Verify user cannot register in the system with invalid data *** < ===");

        HomePage homePage = new HomePage(super.getWebDriver());
        final String USERNAME = ContentGeneration.createEmail();
        final String EMAIL = ContentGeneration.createInvalidEmail();

        System.out.println("STEP 1: Not logged in user has opened the ISkilo HomePage.");
        homePage.openHomePage();
        System.out.println("STEP 2: The user clicks on the login button.");
        homePage.clickOnNavigationLoginButton();
        System.out.println("STEP 3: The user clicks on the registration link.");
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.clickOnRegistrationLink();
        System.out.println("STEP 4: The user provides invalid registration data.");
        RegistrationPage registrationPage = new RegistrationPage(super.getWebDriver());
        registrationPage.fullRegistrationInputsAndActions(USERNAME, EMAIL, "123456");
        System.out.println("STEP 5: Checking if registration error message is displayed.");
        registrationPage.printRegistrationErrorMessage();
    }

    @Test
    public void verifyAlreadyRegisteredUserCanSuccessfullyLogin() {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** Verify already registered user can successfully login in the system  *** < ===");

        final String USERNAME = "megamare";
        final String PASSWORD = "ortoparisi";

        HomePage homePage = new HomePage(super.getWebDriver());
        System.out.println("STEP 1: Not logged in user has opened the ISkilo HomePage.");
        homePage.openHomePage();
        System.out.println("STEP 2: Verifying that home page is opened.");
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page is not opened");
        System.out.println("STEP 3: The user clicks on the login button.");
        homePage.clickOnNavigationLoginButton();
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        System.out.println("STEP 4: Verifying that login page is opened.");
        Assert.assertTrue(loginPage.isLoginPageOpened(), "Login page is not opened");
        System.out.println("STEP 5: The user provides username and password.");
        loginPage.provideUserName(USERNAME);
        loginPage.providePassword(PASSWORD);
        System.out.println("STEP 6: Submitting the login form.");
        loginPage.waitPageTobeFullLoaded();
        loginPage.clickSubmitButton();
        System.out.println("STEP 7: Verifying that the user is logged in.");
        Assert.assertFalse(loginPage.msgStatusAfterSubmitSuccessfulLogin(), "User is not logged in");
    }

    @Test
    public void verifyAlreadyRegisteredUserCanSuccessfullyLoginAndNavigateToEditProfile() {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** Verify already registered user can successfully login in the system navigate to edit profile and edit *** < ===");

        final String USERNAME = "megamare";
        final String PASSWORD = "ortoparisi";

        HomePage homePage = new HomePage(super.getWebDriver());
        System.out.println("STEP 1: Not logged in user has opened the ISkilo HomePage.");
        homePage.openHomePage();
        System.out.println("STEP 2: Verifying that home page is opened.");
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page is not opened");
        System.out.println("STEP 3: The user clicks on the login button.");
        homePage.clickOnNavigationLoginButton();
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        System.out.println("STEP 4: Verifying that login page is opened.");
        Assert.assertTrue(loginPage.isLoginPageOpened(), "Login page is not opened");
        System.out.println("STEP 5: The user provides username and password.");
        loginPage.provideUserName(USERNAME);
        loginPage.providePassword(PASSWORD);
        System.out.println("STEP 6: Submitting the login form.");
        loginPage.waitPageTobeFullLoaded();
        loginPage.clickSubmitButton();
        System.out.println("STEP 7: Verifying that the user is logged in.");
        Assert.assertFalse(loginPage.msgStatusAfterSubmitSuccessfulLogin(), "User is not logged in");

        ProfilePage profilePage = new ProfilePage(super.getWebDriver());
        System.out.println("STEP 8: The user clicks on the profile button.");
        profilePage.clickOnProfileButton();
        System.out.println("STEP 9: Verifying that profile page is opened.");
        Assert.assertFalse(profilePage.isProfilePicDisplayed(), "Profile page is not opened");
        System.out.println("STEP 10: The user clicks on the edit profile icon.");
        profilePage.clickOnEditProfileIcon();
    }

    @Test
    public void verifyAlreadyRegisteredUserCanNotSuccessfullyLoginWithWrongPassword() {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** Verify already registered user can not successfully login with wrong password *** < ===");

        final String USERNAME = "megamare";
        final String PASSWORD = "wrong_password";

        HomePage homePage = new HomePage(super.getWebDriver());
        System.out.println("STEP 1: Not logged in user has opened the ISkilo HomePage.");
        homePage.openHomePage();
        System.out.println("STEP 2: Verifying that home page is opened.");
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page is not opened");
        System.out.println("STEP 3: The user clicks on the login button.");
        homePage.clickOnNavigationLoginButton();
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        System.out.println("STEP 4: Verifying that login page is opened.");
        Assert.assertTrue(loginPage.isLoginPageOpened(), "Login page is not opened");
        System.out.println("STEP 5: The user provides username and wrong password.");
        loginPage.provideUserName(USERNAME);
        loginPage.providePassword(PASSWORD);
        System.out.println("STEP 6: Submitting the login form.");
        loginPage.clickSubmitButton();
        System.out.println("STEP 7: Verifying that the user is not logged in.");
        Assert.assertFalse(loginPage.msgStatusAfterSubmitSuccessfulLogin(), "User should not be logged in");
    }
}