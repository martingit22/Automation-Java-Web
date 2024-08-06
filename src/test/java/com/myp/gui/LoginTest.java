package com.myp.gui;

import com.myp.POM.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestObject {

    @Test
    public void verifyAlreadyRegisteredUserCanSuccessfullyLogin() {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** Verify already registered user can successfully login in the system  *** < ===");

        final String USERNAME = "megamare";
        final String PASSWORD = "ortoparisi";

        HomePage homePage = new HomePage(super.getWebDriver());
        homePage.openHomePage();
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page is not opened");
        homePage.clickOnNavigationLoginButton();
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        Assert.assertTrue(loginPage.isLoginPageOpened(), "Login page is not opened");
        loginPage.provideUserName(USERNAME);
        loginPage.providePassword(PASSWORD);
        loginPage.waitPageTobeFullLoaded();
        loginPage.clickSubmitButton();
        Assert.assertFalse(loginPage.msgStatusAfterSubmitSuccessfulLogin(), "User is not logged in");
    }

    @Test
    public void verifyAlreadyRegisteredUserCanNotSuccessfullyLoginWithWrongPassword() {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** Verify already registered user can not successfully login with wrong password *** < ===");

        final String USERNAME = "megamare";
        final String PASSWORD = "wrong_password";

        HomePage homePage = new HomePage(super.getWebDriver());
        homePage.openHomePage();
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page is not opened");
        homePage.clickOnNavigationLoginButton();
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        Assert.assertTrue(loginPage.isLoginPageOpened(), "Login page is not opened");
        loginPage.provideUserName(USERNAME);
        loginPage.providePassword(PASSWORD);
        loginPage.clickSubmitButton();
        Assert.assertFalse(loginPage.msgStatusAfterSubmitSuccessfulLogin(), "User should not be logged in");
    }

    @Test
    public void verifyAlreadyRegisteredUserCanNotSuccessfullyLoginWithWrongUsername() {
        final String USERNAME = "WRONG";
        final String PASSWORD = "ortoparisi";
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.provideUserName(USERNAME);
        loginPage.providePassword(PASSWORD);
        loginPage.clickSubmitButton();
        Assert.assertFalse(loginPage.msgStatusAfterSubmitSuccessfulLogin(), "User should not be logged in with wrong username");

    }
    @Test
    public void verifyUserCanSuccessfullyLogout() {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** Verify user can successfully logout from the system *** < ===");

        final String USERNAME = "megamare";
        final String PASSWORD = "ortoparisi";

        HomePage homePage = new HomePage(super.getWebDriver());
        homePage.openHomePage();
        homePage.clickOnNavigationLoginButton();
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.provideUserName(USERNAME);
        loginPage.providePassword(PASSWORD);
        loginPage.waitPageTobeFullLoaded();
        loginPage.clickSubmitButton();
        Assert.assertFalse(loginPage.msgStatusAfterSubmitSuccessfulLogin(), "User is not logged in");
        homePage.clickOnLogOutButton();
        Assert.assertFalse(homePage.isHomePageOpened(), "Home page is not opened after logout");
    }

    @Test
    public void verifyUnregisteredUserCannotLogin() {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** Verify unregistered user cannot login in the system *** < ===");

        final String USERNAME = "unregisteredUser";
        final String PASSWORD = "somepassword";

        HomePage homePage = new HomePage(super.getWebDriver());
        homePage.openHomePage();
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page is not opened");
        homePage.clickOnNavigationLoginButton();
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        Assert.assertTrue(loginPage.isLoginPageOpened(), "Login page is not opened");
        loginPage.provideUserName(USERNAME);
        loginPage.providePassword(PASSWORD);
        loginPage.clickSubmitButton();
        Assert.assertFalse(loginPage.msgStatusAfterSubmitSuccessfulLogin(), "Unregistered user should not be able to login");
    }

    @Test
    public void verifyUserCannotLoginWithEmptyCredentials() {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** Verify user cannot login with empty credentials *** < ===");

        HomePage homePage = new HomePage(super.getWebDriver());
        homePage.openHomePage();
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page is not opened");
        homePage.clickOnNavigationLoginButton();
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        Assert.assertTrue(loginPage.isLoginPageOpened(), "Login page is not opened");
        loginPage.provideUserName("");
        loginPage.providePassword("");
        loginPage.clickSubmitButton();
        Assert.assertFalse(loginPage.msgStatusAfterSubmitSuccessfulLogin(), "User should not be able to login with empty credentials");
    }
}
