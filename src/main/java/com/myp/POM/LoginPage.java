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
    @FindBy(xpath = "//div[@class='toast-message ng-star-inserted']")
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

    public boolean msgStatusAfterSubmitSuccessfulLogin() {
        String expectedMsgText = "Successful login!";
        String msgText = popUpMsg.getText();
        wait.until(ExpectedConditions.visibilityOf(popUpMsg));
        Assert.assertEquals(msgText, expectedMsgText);
        return false;
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