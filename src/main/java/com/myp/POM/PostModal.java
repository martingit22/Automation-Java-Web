package com.myp.POM;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PostModal extends CommonMethodsForPOM {

    private WebDriverWait wait;

    @FindBy(className = "post-modal")
    private WebElement modalElement;

    @FindBy(css = ".post-modal-img img")
    private WebElement image;

    @FindBy(xpath = "//div[contains(@class, 'profile-user-settings')]//h2")
    private WebElement postUserElement;

    @FindBy(xpath = ".//div[contains(@class, 'ng-star-inserted')]//label[contains(@class, 'delete-ask')]//a[text()='Delete post']")
    private WebElement deleteLink;

    @FindBy(xpath = "//div[contains(@class, 'delete-confirm')]//button[contains(@class, 'btn') and contains(@class, 'btn-primary') and contains(@class, 'btn-sm')]")
    private WebElement confirmDeletingPost;

    @FindBy(xpath = "//input[@placeholder='Comment here']")
    private WebElement commentField;

    @FindBy(xpath = "//div[contains(@class, 'comment-content')]")
    private WebElement commentElement;

    public PostModal(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isImageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(image));
            return image.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Element is not visible");
            return false;
        }
    }

    public String getPostUser() {
        return postUserElement.getText();
    }

    public void clickOnBinIcon() {
        try {
            wait.until(ExpectedConditions.visibilityOf(deleteLink));
            System.out.println("Delete link is displayed: " + deleteLink.isDisplayed());
            System.out.println("Delete link is enabled: " + deleteLink.isEnabled());
            deleteLink.click();
            waitPageTobeFullLoaded();
        } catch (TimeoutException e) {
            System.out.println("Timed out waiting for delete post label or link to be visible: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Delete post label or link not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public void confirmDeletingPost() {
        try {
            wait.until(ExpectedConditions.visibilityOf(confirmDeletingPost));
            System.out.println("Confirm delete button is displayed: " + confirmDeletingPost.isDisplayed());
            System.out.println("Confirm delete button is enabled: " + confirmDeletingPost.isEnabled());
            confirmDeletingPost.click();
            waitPageTobeFullLoaded();
        } catch (TimeoutException e) {
            System.out.println("Timed out waiting for confirm delete button to be visible or clickable: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Confirm delete button not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public void addComment(String comment) {
        wait.until(ExpectedConditions.visibilityOf(commentField));
        commentField.sendKeys(comment);
    }

    public void submitComment() {
        wait.until(ExpectedConditions.visibilityOf(commentField));
        commentField.sendKeys(Keys.ENTER);
    }

    public boolean isCommentVisible(String comment) {
        try {
            WebElement commentElement = modalElement.findElement(By.xpath("//div[contains(@class, 'comment-content') and contains(text(), '" + comment + "')]"));
            wait.until(ExpectedConditions.visibilityOf(commentElement));
            return commentElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}