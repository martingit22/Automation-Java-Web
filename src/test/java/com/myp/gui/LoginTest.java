package com.myp.gui;

import com.myp.POM.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestObject {

    @Test
    public void verifyAlreadyRegisteredUserCanSuccessfullyLogin() {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** Verify already registered user can successfully login in the system *** < ===");

        final String USERNAME = "megamare";
        final String PASSWORD = "ortoparisi";

        System.out.println("STEP 1: Open home page");
        HomePage homePage = new HomePage(super.getWebDriver());
        homePage.openHomePage();
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page is not opened");

        System.out.println("STEP 2: Click on navigation login button");
        homePage.clickOnNavigationLoginButton();

        System.out.println("STEP 3: Verify login page is opened");
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        Assert.assertTrue(loginPage.isLoginPageOpened(), "Login page is not opened");

        System.out.println("STEP 4: Provide username: " + USERNAME);
        loginPage.provideUserName(USERNAME);

        System.out.println("STEP 5: Provide password");
        loginPage.providePassword(PASSWORD);

        System.out.println("STEP 6: Wait for the page to fully load");
        loginPage.waitPageTobeFullLoaded();

        System.out.println("STEP 7: Click submit button");
        loginPage.clickSubmitButton();

        System.out.println("STEP 8: Verify user is logged in");
        Assert.assertFalse(loginPage.msgStatusAfterSubmitSuccessfulLogin(), "User is not logged in");
    }

    @Test
    public void verifyAlreadyRegisteredUserCanNotSuccessfullyLoginWithWrongPassword() {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** Verify already registered user cannot successfully login with wrong password *** < ===");

        final String USERNAME = "megamare";
        final String PASSWORD = "wrong_password";

        System.out.println("STEP 1: Open home page");
        HomePage homePage = new HomePage(super.getWebDriver());
        homePage.openHomePage();
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page is not opened");

        System.out.println("STEP 2: Click on navigation login button");
        homePage.clickOnNavigationLoginButton();

        System.out.println("STEP 3: Verify login page is opened");
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        Assert.assertTrue(loginPage.isLoginPageOpened(), "Login page is not opened");

        System.out.println("STEP 4: Provide username: " + USERNAME);
        loginPage.provideUserName(USERNAME);

        System.out.println("STEP 5: Provide incorrect password");
        loginPage.providePassword(PASSWORD);

        System.out.println("STEP 6: Click submit button");
        loginPage.clickSubmitButton();

        System.out.println("STEP 7: Verify user is not logged in");
        Assert.assertFalse(loginPage.msgStatusAfterSubmitSuccessfulLogin(), "User should not be logged in");
    }

    @Test
    public void verifyAlreadyRegisteredUserCanNotSuccessfullyLoginWithWrongUsername() {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** Verify already registered user cannot successfully login with wrong username *** < ===");

        final String USERNAME = "WRONG";
        final String PASSWORD = "ortoparisi";

        System.out.println("STEP 1: Open login page directly");
        LoginPage loginPage = new LoginPage(super.getWebDriver());

        System.out.println("STEP 2: Provide incorrect username: " + USERNAME);
        loginPage.provideUserName(USERNAME);

        System.out.println("STEP 3: Provide password");
        loginPage.providePassword(PASSWORD);

        System.out.println("STEP 4: Click submit button");
        loginPage.clickSubmitButton();

        System.out.println("STEP 5: Verify user is not logged in with wrong username");
        Assert.assertFalse(loginPage.msgStatusAfterSubmitSuccessfulLogin(), "User should not be logged in with wrong username");
    }

    @Test
    public void verifyUserCanSuccessfullyLogout() {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** Verify user can successfully logout from the system *** < ===");

        final String USERNAME = "megamare";
        final String PASSWORD = "ortoparisi";

        System.out.println("STEP 1: Open home page");
        HomePage homePage = new HomePage(super.getWebDriver());
        homePage.openHomePage();

        System.out.println("STEP 2: Click on navigation login button");
        homePage.clickOnNavigationLoginButton();

        System.out.println("STEP 3: Provide username: " + USERNAME);
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.provideUserName(USERNAME);

        System.out.println("STEP 4: Provide password");
        loginPage.providePassword(PASSWORD);

        System.out.println("STEP 5: Wait for the page to fully load");
        loginPage.waitPageTobeFullLoaded();

        System.out.println("STEP 6: Click submit button");
        loginPage.clickSubmitButton();

        System.out.println("STEP 7: Verify user is logged in");
        Assert.assertFalse(loginPage.msgStatusAfterSubmitSuccessfulLogin(), "User is not logged in");

        System.out.println("STEP 8: Click on logout button");
        homePage.clickOnLogOutButton();

        System.out.println("STEP 9: Verify home page is opened after logout");
        Assert.assertFalse(homePage.isHomePageOpened(), "Home page is not opened after logout");
    }

    @Test
    public void verifyUnregisteredUserCannotLogin() {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** Verify unregistered user cannot login in the system *** < ===");

        final String USERNAME = "unregisteredUser";
        final String PASSWORD = "somepassword";

        System.out.println("STEP 1: Open home page");
        HomePage homePage = new HomePage(super.getWebDriver());
        homePage.openHomePage();
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page is not opened");

        System.out.println("STEP 2: Click on navigation login button");
        homePage.clickOnNavigationLoginButton();

        System.out.println("STEP 3: Verify login page is opened");
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        Assert.assertTrue(loginPage.isLoginPageOpened(), "Login page is not opened");

        System.out.println("STEP 4: Provide username: " + USERNAME);
        loginPage.provideUserName(USERNAME);

        System.out.println("STEP 5: Provide password");
        loginPage.providePassword(PASSWORD);

        System.out.println("STEP 6: Click submit button");
        loginPage.clickSubmitButton();

        System.out.println("STEP 7: Verify unregistered user cannot login");
        Assert.assertFalse(loginPage.msgStatusAfterSubmitSuccessfulLogin(), "Unregistered user should not be able to login");
    }

    @Test
    public void verifyUserCannotLoginWithEmptyCredentials() {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** Verify user cannot login with empty credentials *** < ===");

        System.out.println("STEP 1: Open home page");
        HomePage homePage = new HomePage(super.getWebDriver());
        homePage.openHomePage();
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page is not opened");

        System.out.println("STEP 2: Click on navigation login button");
        homePage.clickOnNavigationLoginButton();

        System.out.println("STEP 3: Verify login page is opened");
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        Assert.assertTrue(loginPage.isLoginPageOpened(), "Login page is not opened");

        System.out.println("STEP 4: Provide empty username");
        loginPage.provideUserName("");

        System.out.println("STEP 5: Provide empty password");
        loginPage.providePassword("");

        System.out.println("STEP 6: Click submit button");
        loginPage.clickSubmitButton();

        System.out.println("STEP 7: Verify user cannot login with empty credentials");
        Assert.assertFalse(loginPage.msgStatusAfterSubmitSuccessfulLogin(), "User should not be able to login with empty credentials");
    }
}
