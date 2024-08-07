package com.myp.POM;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends CommonMethodsForPOM {

    public static final String REGISTER_PAGE_URL = "users/register";

    @FindBy(xpath = "//div[contains(@class, 'app-register')]//h4")
    private WebElement registerPageHeaderTitle;
    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement usernameInputField;
    @FindBy(xpath = "//input[@placeholder='email']")
    private WebElement emailInputField;
    @FindBy(xpath = "//*[@id=\"defaultRegisterFormPassword\"]")
    private WebElement passwordInputField;
    @FindBy(xpath = "//*[@id=\"defaultRegisterPhonePassword\"]")
    private WebElement confirmPasswordInputField;
    @FindBy(css = "#sign-in-button")
    private WebElement registrationSignInButton;
    @FindBy(className = "invalid-feedback")
    private WebElement registrationErrorMessage;

    @FindBy(xpath = "//div[contains(text(), 'Successful login!')]")
    private WebElement registrationSuccessMessage;

    public String getRegistrationSuccessMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(registrationSuccessMessage));
            return registrationSuccessMessage.getText();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

//User Actions

    public void provideUsername(String username) {
        typeTextInField(usernameInputField, username);
    }

    public void provideEmail(String email) {
        typeTextInField(emailInputField, email);
    }

    public void providePassword(String password) {
        typeTextInField(passwordInputField, password);
    }

    public void provideConfirmPassword(String password) {
        typeTextInField(confirmPasswordInputField, password);
    }

    public void clickOnSignInButton() {
        click(registrationSignInButton);
    }

    public void fullRegistrationInputsAndActions(String username, String email, String password) {
        provideUsername(username);
        provideEmail(email);
        providePassword(password);
        provideConfirmPassword(password);
        clickOnSignInButton();
    }

    //Getters
    public String getUserNamePlaceHolder() {
        wait.until(ExpectedConditions.visibilityOf(usernameInputField));
        return usernameInputField.getAttribute("placeholder");
    }

    public String getEmailPlaceHolder() {
        wait.until(ExpectedConditions.visibilityOf(emailInputField));
        return emailInputField.getAttribute("placeholder");
    }

    public String getPasswordPlaceHolder() {
        wait.until(ExpectedConditions.visibilityOf(passwordInputField));
        return passwordInputField.getAttribute("placeholder");
    }

    public String getConfirmPasswordPlaceHolder() {
        wait.until(ExpectedConditions.visibilityOf(confirmPasswordInputField));
        return confirmPasswordInputField.getAttribute("placeholder");
    }

    public void printRegistrationErrorMessage() {
        try {
            String errorMessage = registrationErrorMessage.getText();
            System.out.println("Registration Error Message: " + errorMessage);
        } catch (NoSuchElementException e) {
            System.out.println("ERROR ! The registration error message element was not found.");
        }
    }
}