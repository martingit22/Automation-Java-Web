package com.myp.POM;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends CommonMethodsForPOM {
    public static final String HOME_PAGE_SUFFIX = "posts/all";

    @FindBy(id = "nav-link-login")
    private WebElement navigationLoginButton;

    @FindBy(id = "nav-link-new-post")
    private WebElement navigationNewPostButton;

    @FindBy(xpath = "//i[@class='fas fa-sign-out-alt fa-lg']")
    private WebElement navigationLogOutButton;

    @FindBy(xpath = "//div[contains(@class, 'col-12')]//h2")
    private WebElement loggedInUsername;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openHomePage() {
        navigateTo(HOME_PAGE_SUFFIX);
    }

    public void clickOnNavigationLoginButton() {
        click(navigationLoginButton);
    }

    public String getLoggedInUsername() {
        return loggedInUsername.getText();
    }

    public void clickOnNewPostButton() {
        click(navigationNewPostButton);
    }

    public void clickOnLogOutButton() {
        click(navigationLogOutButton);
    }

    public boolean isHomePageOpened() {
        System.out.println("ACTION @ The user is verifying if the Home page is opened");
        try {
            wait.until(ExpectedConditions.urlContains(HOME_PAGE_SUFFIX));
            System.out.println("CONFIRM # Home page is opened");
            return true;
        } catch (TimeoutException e) {
            System.out.println("ERROR ! Home page is not opened");
            return false;
        }
    }

    public boolean isNewPostButtonShown() {
        System.out.println("ACTION @ The user is verifying if the navigation New Post button is presented");
        try {
            wait.until(ExpectedConditions.visibilityOf(navigationNewPostButton));
            System.out.println("CONFIRM # Navigation New Post button is presented to the user");
            return true;
        } catch (TimeoutException e) {
            System.out.println("ERROR ! The navigation New Post button was not presented to the user");
            return false;
        }
    }

    public boolean isLogOutButtonShown() {
        System.out.println("ACTION @ The user is verifying if the navigation log out button is presented");
        try {
            wait.until(ExpectedConditions.visibilityOf(navigationLogOutButton));
            System.out.println("CONFIRM # Navigation logout button is presented to the user");
            return true;
        } catch (TimeoutException e) {
            System.out.println("ERROR ! The navigation logout button was not presented to the user");
            return false;
        }
    }
}
