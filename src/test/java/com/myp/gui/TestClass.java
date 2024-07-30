package com.myp.gui;

import com.myp.POM.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ContentGeneration;

import java.io.File;

public class TestClass extends TestObject {

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
        Assert.assertTrue(loginPage.isUserLoggedIn(), "User is not logged in");
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
        Assert.assertTrue(loginPage.isUserLoggedIn(), "User is not logged in");

        ProfilePage profilePage = new ProfilePage(super.getWebDriver());
        System.out.println("STEP 8: The user clicks on the profile button.");
        profilePage.clickOnProfileButton();
        System.out.println("STEP 9: Verifying that profile page is opened.");
        Assert.assertTrue(profilePage.isProfilePageOpened(), "Profile page is not opened");
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
        Assert.assertFalse(loginPage.isUserLoggedIn(), "User should not be logged in");
    }

    @Test
    public void verifyUserCanCreateNewPostAndDeleteIt() {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** Verify user can create a new post and delete the new post *** < ===");

        final String HOME_PAGE_URL = "posts/all";
        final String LOGIN_PAGE_URL = "users/login";
        final String USERNAME = "testingDemos";
        final String PASSWORD = "testing";
        final String CAPTION = "Testing create post caption";
        File postPicture = new File("src/test/resources/uploads/testUpload.jpg");

        HomePage homePage = new HomePage(super.getWebDriver());
        System.out.println("STEP 1: Not logged in user has opened the ISkilo HomePage.");
        homePage.openHomePage();
        System.out.println("STEP 2: Verifying that the correct URL is loaded.");
        homePage.isUrlLoaded(HOME_PAGE_URL);
        System.out.println("STEP 3: The user clicks on the login button.");
        homePage.clickOnNavigationLoginButton();
        System.out.println("STEP 4: Verifying that the login page URL is loaded.");
        homePage.isUrlLoaded(LOGIN_PAGE_URL);
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        System.out.println("STEP 5: Logging in with user credentials.");
        loginPage.loginWithUserAndPassword(USERNAME, PASSWORD);
        System.out.println("STEP 6: Verifying if the new post button is shown.");
        homePage.isNewPostButtonShown();
        System.out.println("STEP 7: The user clicks on the new post button.");
        homePage.clickOnNewPostButton();
        PostPage postPage = new PostPage(super.getWebDriver());
        System.out.println("STEP 8: Uploading picture for the new post.");
        postPage.uploadPicture(postPicture);
        System.out.println("STEP 9: Verifying if the image is visible and the name is correct.");
        Assert.assertTrue(postPage.isImageVisible(), "The image is visible!");
        Assert.assertEquals(postPicture.getName(), postPage.getImageName(), "The image name is correct");
        System.out.println("STEP 10: Providing post caption and creating the post.");
        postPage.providePostCaption(CAPTION);
        postPage.clickCreatePostButton();
        ProfilePage profilePage = new ProfilePage(super.getWebDriver());
        System.out.println("STEP 11: Verifying the number of posts.");
        int expectedPostCount = 1;
        int actualPostCount = profilePage.getPostCount();
        Assert.assertEquals(actualPostCount, expectedPostCount, "The number of Posts is incorrect!");
        System.out.println("STEP 12: Clicking on the newly created post.");
        profilePage.clickPost(0);
        PostModal postModal = new PostModal(super.getWebDriver());
        System.out.println("STEP 13: Verifying if the image is visible in the post modal.");
        Assert.assertTrue(postModal.isImageVisible(), "The image is visible!");
        System.out.println("STEP 14: Verifying the post user.");
        Assert.assertEquals(postModal.getPostUser(), USERNAME);
        System.out.println("STEP 15: Clicking on the bin icon to delete the post.");
        postModal.clickOnBinIcon();
        System.out.println("STEP 16: Confirming post deletion.");
        postModal.confirmDeletingPost();
    }
}