package com.myp.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.TimeoutException;

public class PostModal extends CommonMethodsForPOM {
    private final WebElement modalElement;

    public PostModal(WebDriver driver) {
        super(driver);
        this.modalElement = driver.findElement(By.className("post-modal"));
    }

    public boolean isImageVisible() {
        boolean isVisible = false;
        try {
            WebElement image = modalElement.findElement(By.cssSelector(".post-modal-img img"));
            isVisible = wait.until(ExpectedConditions.visibilityOf(image)).isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Element is not visible");
            isVisible = false;
        }
        return isVisible;
    }

    public String getPostUser() {
        WebElement postUserElement = driver.findElement(By.xpath("//div[contains(@class, 'profile-user-settings')]//h2"));
        return postUserElement.getText();
    }

    public void clickOnBinIcon() {

        WebDriverWait wait = new WebDriverWait(super.driver, Duration.ofSeconds(10));

            try {
                // Locate the label with class 'delete-ask' inside the div with class 'ng-star-inserted'
                WebElement deleteLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[contains(@class, 'ng-star-inserted')]//label[contains(@class, 'delete-ask')]")));

                // Locate the anchor element inside the label
                WebElement deleteLink = deleteLabel.findElement(By.xpath(".//a[text()='Delete post']"));

                // Debugging output
                System.out.println("Delete link is displayed: " + deleteLink.isDisplayed());
                System.out.println("Delete link is enabled: " + deleteLink.isEnabled());

                // Click the delete link
                deleteLink.click();

                // Wait for the page or modal to be fully loaded
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
            // Locate the confirm button within the delete-confirm div
            WebElement confirmDeletingPost = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'delete-confirm')]//button[contains(@class, 'btn') and contains(@class, 'btn-primary') and contains(@class, 'btn-sm')]")
            ));

            // Debugging output
            System.out.println("Confirm delete button is displayed: " + confirmDeletingPost.isDisplayed());
            System.out.println("Confirm delete button is enabled: " + confirmDeletingPost.isEnabled());

            // Click the confirm button
            confirmDeletingPost.click();

            // Wait for the page or modal to be fully loaded
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
        WebElement commentField = modalElement.findElement(By.xpath("//input[@placeholder='Comment here']"));
        wait.until(ExpectedConditions.visibilityOf(commentField));
        commentField.sendKeys(comment);
    }

    public void submitComment() {
        WebElement commentField = modalElement.findElement(By.xpath("//input[@placeholder='Comment here']"));
        wait.until(ExpectedConditions.visibilityOf(commentField));
        commentField.sendKeys(Keys.ENTER);
    }

    public boolean isCommentVisible(String comment) {
        try {
            WebElement commentElement = modalElement.findElement(By.xpath("//div[contains(@class, 'comment-content') and contains(text(), 'This is a test comment')]\n"));
            wait.until(ExpectedConditions.visibilityOf(commentElement));
            return commentElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
