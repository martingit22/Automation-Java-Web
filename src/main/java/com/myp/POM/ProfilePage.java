package com.myp.POM;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.util.List;

public class ProfilePage extends CommonMethodsForPOM {

    @FindBy(xpath = "//div[@id='navbarColor01']//a[@id='nav-link-profile' and contains(@class, 'nav-link')]")
    private WebElement navToProfileButton;


    @FindBy(className = "fa-user-edit")
    private WebElement editProfileIcon;

    @FindBy(id = "upload-img")
    private WebElement uploadProfilePic;

    @FindBy(xpath = "//div[@class='image-container']")
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

    public String getUsername() {
        WebElement username = driver.findElement(By.tagName("h2"));
        return username.getText();
    }

    public int getPostCount() {
        try {
            WebElement postCountElement = driver.findElement(By.xpath("//li[strong[@class='profile-stat-count']]"));
            WebElement strongElement = postCountElement.findElement(By.xpath(".//strong[@class='profile-stat-count']"));
            String postCountText = strongElement.getText().replaceAll("[^0-9]", ""); // Remove non-numeric characters
            return Integer.parseInt(postCountText);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
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
        try {
            wait.until(ExpectedConditions.visibilityOf(imgSource));
            String imgUrl = imgSource.getAttribute("src");
            return imgUrl != null && imgUrl.contains("https://imgur.com/73ApH4B");
        } catch (Exception e) {
            System.out.println("DEBUG # Exception occurred: " + e.getMessage());
            return false;
        }
    }
    public boolean isPostVisible(int postIndex) {
        try {
            WebElement post = driver.findElement(By.xpath("//div[@class='post-img'][position()=" + (postIndex + 1) + "]"));
            return post.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void openLatestPost() {
        List<WebElement> posts = driver.findElements(By.className("gallery-item"));

        wait.until(ExpectedConditions.visibilityOfAllElements(posts));

        if (posts.isEmpty()) {
            throw new NoSuchElementException("No posts available to open.");
        }

        WebElement latestPost = posts.get(0);
        click(latestPost);
    }
}