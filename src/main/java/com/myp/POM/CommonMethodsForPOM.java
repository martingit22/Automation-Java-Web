package com.myp.POM;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonMethodsForPOM {
    private final String BASE_URL = "http://training.skillo-bg.com:4200/";
    WebDriver driver;
    WebDriverWait wait;

    public CommonMethodsForPOM(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    };

    public void navigateTo(String pageURLsufix) {
        String currentURL = BASE_URL + pageURLsufix;
        driver.get(currentURL);

        waitPageTobeFullLoaded();
    };

    public void click(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        element.click();
        System.out.println("The user has clicked on element");

        //this is not a very good idea to be used in a real life application due to the fact that sometimes js can be heavy
        //waitPageTobeFullLoaded();
    };

    public void typeTextInField(WebElement element, String inputText) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(inputText);
    };

    //This waiting strategy is very appropriate for a small demo test sites
    //However if we want to execute quickly scripts a waiting utils will be needed
    public void waitPageTobeFullLoaded() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").equals("complete");
        System.out.println("DOM tree is fully loaded");
    };

    public boolean isUrlLoaded(String pageURL) {
        waitPageTobeFullLoaded();
        return wait.until(ExpectedConditions.urlContains(pageURL));
    };

    public String getPlaceholder(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getAttribute("placeholder");
    };

    public boolean isPlaceholderCorrect(WebElement element, String expectedPlaceholder) {
        try {
            String actualPlaceholder = getPlaceholder(element);
            return expectedPlaceholder.equals(actualPlaceholder);
        } catch (NoSuchElementException e) {
            System.out.println("ERROR! The placeholder for the element is not correct or element is not found.");
            return false;
        }
    };

    public boolean isTitleShown(WebElement element) {
        boolean isTitleShown = false;
        System.out.println("ACTION @ The user is verifying if the Registration title is shown");
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            System.out.println("CONFIRM # The Registration title is shown to the user");
            isTitleShown = true;
        } catch (NoSuchElementException e) {
            System.out.println("ERROR ! The title is not presented the user is not on Registration page");
        }
        return isTitleShown;
    };

}