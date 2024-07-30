package com.myp.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.util.List;

public class ProfilePage extends CommonMethodsForPOM {

    @FindBy(xpath = "//div[@id='navbarColor01']//a[@id='nav-link-profile' and contains(@class, 'nav-link')]")
    private WebElement navToProfileButton;


    @FindBy(className = "fa-user-edit") // Edit profile icon
    private WebElement editProfileIcon;

    @FindBy(id = "upload-img")
    private WebElement uploadProfilePic;

    @FindBy(className = "profile-image-source")
    private WebElement imgSource;

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnProfileButton() {
        click(navToProfileButton);
    }

    public void clickOnEditProfileIcon() {
        click(editProfileIcon);
    }

    public boolean isProfilePageOpened() {
        return driver.getCurrentUrl().contains("profile");
    }

    public String getUsername() {
        WebElement username = driver.findElement(By.tagName("h2"));
        return username.getText();
    }

    public int getPostCount() {
        List<WebElement> posts = driver.findElements(By.tagName("app-post"));
        return posts.size();
    }

    public void clickPost(int postIndex) {
        List<WebElement> posts = driver.findElements(By.tagName("app-post"));
        posts.get(postIndex).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").equals("complete");
    }

    public void uploadProfilePic(File file) {
        uploadProfilePic.sendKeys(file.getAbsolutePath());
        System.out.println("CONFIRMATION # The image was successfully uploaded");
    }

    public boolean isProfilePicDisplayed() {
        System.out.println("CONFIRMATION # The Profile pic is displayed");
        wait.until(ExpectedConditions.visibilityOf(imgSource));
        String imgUrl = imgSource.getAttribute("src");
        return imgUrl.contains("https://i.imgur.com");
    }

    public boolean isPostVisible(int postIndex) {
        // Code to check if a post is visible by index
        try {
            WebElement post = driver.findElement(By.xpath("//div[@class='post-img'][position()=" + (postIndex + 1) + "]"));
            return post.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}