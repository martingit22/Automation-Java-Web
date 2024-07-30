package com.myp.POM;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends CommonMethodsForPOM {

    public static final String REGISTER_PAGE_URL = "users/register";

    @FindBy(xpath = "/html/body/app-root/div[2]/app-register/div/div/form/h4")
    private WebElement registerPageHeaderTitle;
    @FindBy(xpath = "/html/body/app-root/div[2]/app-register/div/div/form/div[1]/input")
    private WebElement usernameInputField;
    @FindBy(xpath = "/html/body/app-root/div[2]/app-register/div/div/form/div[2]/input")
    private WebElement emailInputField;
    @FindBy(xpath = "//*[@id=\"defaultRegisterFormPassword\"]")
    private WebElement passwordInputField;
    @FindBy(xpath = "//*[@id=\"defaultRegisterPhonePassword\"]")
    private WebElement confirmPasswordInputField;
    @FindBy(css = "#sign-in-button")
    private WebElement registrationSignInButton;
    @FindBy(className = "invalid-feedback")
    private WebElement registrationErrorMessage;

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
        return usernameInputField.getAttribute("value");
    }

    public boolean isUserNamePlaceHolderCorrect(String expectedUserNamePlaceHolder) {
        boolean isPerRequirments = false;
        try {
            String actualUserNamePlaceHolder = getUserNamePlaceHolder();
            isPerRequirments = expectedUserNamePlaceHolder.equals(actualUserNamePlaceHolder);

        } catch (NoSuchElementException e) {
            System.out.println("ERROR ! The username placeHolder is not correct");
            isPerRequirments = false;
        }
        return isPerRequirments;
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