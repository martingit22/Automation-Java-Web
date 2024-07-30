package com.myp.POM;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage extends CommonMethodsForPOM {
    public static final String LOGIN_PAGE_SUFIX = "users/login";

    @FindBy(css = "p.h4")
    private WebElement loginPageHeaderTitle;
    @FindBy(name = "usernameOrEmail")
    private WebElement usernameInputField;
    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordInputField;
    @FindBy(xpath = "//span[contains(text(),'Remember me')]")
    private WebElement rememberMeLabelText;
    @FindBy(xpath = "//input[contains(@formcontrolname,'rememberMe')]")
    private WebElement rememberMeCheckBox;
    @FindBy(id = "sign-in-button")
    private WebElement loginFormSubmitButton;
    @FindBy(xpath = "//a[contains(.,'Register')]")
    private WebElement loginFormRegistrationLink;
    @FindBy(xpath = "//div[@class=\"toast-message ng-star-inserted\"]")
    private WebElement popUpMsg;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isLoginPageOpened() {
        try {
            wait.until(ExpectedConditions.urlContains(LOGIN_PAGE_SUFIX));
            WebElement userNameElement = wait.until(ExpectedConditions.visibilityOf(usernameInputField));
            WebElement passwordElement = wait.until(ExpectedConditions.visibilityOf(passwordInputField));
            WebElement submitButtonElement = wait.until(ExpectedConditions.visibilityOf(loginFormSubmitButton));
            return userNameElement.isDisplayed() && passwordElement.isDisplayed() && submitButtonElement.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void verifyUnsuccessfulLogin() {
        msgStatusAfterInvalidLogin();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("profile"), "User should not be redirected to profile page");

        try {
            WebElement profileLink = driver.findElement(By.id("profileLinkId"));
            Assert.fail("Profile page should not be accessible with incorrect login");
        } catch (NoSuchElementException e) {
            System.out.println("CONFIRM # Profile page is not accessible with incorrect login");
        }
    }

    public boolean isWelcomeMessageDisplayed() {
        try {
            WebElement welcomeMessage = driver.findElement(By.id("welcomeMessageId")); // Заменете с реалния идентификатор
            return welcomeMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isUserLoggedIn() {
        boolean isUserLoggedIn = false;
        System.out.println("ACTION @ Verifying if the user is logged in");

        try {
            wait.until(ExpectedConditions.visibilityOf(popUpMsg));
            if (popUpMsg.getText().equals("Successful login!")) {
                System.out.println("CONFIRM # User is logged in");
                isUserLoggedIn = true;

                String currentUrl = driver.getCurrentUrl();
                boolean isUserIdInUrl = currentUrl.contains("userId");
                if (isUserIdInUrl) {
                    System.out.println("CONFIRM # URL contains user ID");
                } else {
                    System.out.println("ERROR ! URL does not contain user ID");
                    isUserLoggedIn = false;
                }

                try {
                    WebElement profileLink = driver.findElement(By.id("profileLinkId"));
                    profileLink.click();
                    System.out.println("CONFIRM # Profile can be opened");
                } catch (NoSuchElementException e) {
                    System.out.println("ERROR ! Profile cannot be opened or not found");
                    isUserLoggedIn = false;
                }
            } else {
                System.out.println("ERROR ! User is not logged in");
            }
        } catch (TimeoutException e) {
            System.out.println("ERROR ! User is not logged in");
        }
        return isUserLoggedIn;
    }


    public void provideUserName(String userName) {
        typeTextInField(usernameInputField, userName);
    }

    public void providePassword(String userPassword) {
        typeTextInField(passwordInputField, userPassword);
    }

    public void clickSubmitButton() {
        click(loginFormSubmitButton);
    }

    public void loginWithUserAndPassword(String userName, String password) {
        provideUserName(userName);
        providePassword(password);
        clickSubmitButton();
    }

    public void msgStatusAfterSubmitSuccessfulLogin() {
        String expectedMsgText = "Successful login!";
        String msgText = popUpMsg.getText();
        wait.until(ExpectedConditions.visibilityOf(popUpMsg));
        Assert.assertEquals(msgText, expectedMsgText);
    }

    public void clickOnRegistrationLink() {
        click(loginFormRegistrationLink);
    }

    public void msgStatusAfterInvalidLogin() {
        String expectedMsgText = "Wrong username or password!";
        String msgText = popUpMsg.getText();
        wait.until(ExpectedConditions.visibilityOf(popUpMsg));
        Assert.assertEquals(msgText, expectedMsgText);
    }

    public void selectingRememberMeCheckBox() {
        rememberMeCheckBox.click();
        System.out.println("Remember me is selected");
    }

    public boolean isLoginFormTitleShown() {
        boolean isShown = false;
        if (isTitleShown(loginPageHeaderTitle)) {
            isShown = true;
        }

        return isShown;
    }
}
