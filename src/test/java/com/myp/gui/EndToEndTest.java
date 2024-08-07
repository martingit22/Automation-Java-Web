package com.myp.gui;

import com.myp.POM.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
public class EndToEndTest extends TestObject {

    private static final String BASE_URL = "http://training.skillo-bg.com:4200/";
    private static final String REGISTER_PAGE_URL = BASE_URL + "users/register";
    private static final String POST_URL = BASE_URL + "posts/all";
    private static final String NEW_USERNAME = "vinitsa";
    private static final String EMAIL = "ioniq5suv@abv.bg";
    private static final String REG_PASSWORD = "Ortoparisi";
    private static final String CONFIRM_PASSWORD = "Ortoparisi";
    private File postPicture = new File("src\\test\\resources\\uploads\\testImg.jpg");
    private String caption = "Testing create post caption";

    @DataProvider(name = "endToEndTest")
    public Object[][] getCommentPostData() {
        File postPicture = new File("src/test/resources/uploads/testUpload.jpg");
        String caption = "Testing create post caption";

        return new Object[][]{
                {postPicture, caption}
        };
    }
    @Test(dataProvider = "endToEndTest")
    public void endToEndTest(File postPicture, String caption) {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** THE IS A END TO END SCENARIO  *** < ===");
        WebDriver driver = super.getWebDriver();
        HomePage homePage = new HomePage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        System.out.println();
        System.out.println("STEP 1: Open iSkilo site.");
        homePage.openHomePage();
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page is not loaded.");
        System.out.println("Result: The website is open.");

        System.out.println("STEP 2: Navigate to Registration page.");
        driver.get(BASE_URL + RegistrationPage.REGISTER_PAGE_URL);

        System.out.println("STEP 3: Verify the registration page.");
        Assert.assertTrue(registrationPage.getUserNamePlaceHolder().equals("Username"), "Username placeholder is incorrect.");
        Assert.assertTrue(registrationPage.getEmailPlaceHolder().equals("email"), "Email placeholder is incorrect.");
        Assert.assertTrue(registrationPage.getPasswordPlaceHolder().equals("Password"), "Password placeholder is incorrect.");
        Assert.assertTrue(registrationPage.getConfirmPasswordPlaceHolder().equals("Confirm Password"), "Confirm Password placeholder is incorrect.");
        System.out.println("Result: Registration page is verified with correct placeholders.");

        System.out.println("STEP 4: Making a registration.");
        registrationPage.fullRegistrationInputsAndActions(NEW_USERNAME, EMAIL, REG_PASSWORD);
        System.out.println("STEP 5: Checking that the user is logged in after registration.");
        Assert.assertTrue(homePage.isUrlLoaded(POST_URL), "The user is not redirected to the posts page after registration.");

        System.out.println("STEP:6 Login out");
        homePage.clickOnLogOutButton();
        System.out.println("RESULT: The user is logged out");

        LoginPage loginPage = new LoginPage(super.getWebDriver());
        System.out.println("STEP 7: Verify that the user is on login page.");
        Assert.assertTrue(loginPage.isLoginPageOpened(), "Login page is not loaded.");
        System.out.println("STEP 8: Checking the placeholders of the login page.");
        System.out.println("STEP 9: Marking the 'remember me' check box.");
        loginPage.selectingRememberMeCheckBox();

        System.out.println("STEP 10: Entering credentials of the newly registered user and submitting.");
        loginPage.loginWithUserAndPassword(NEW_USERNAME, REG_PASSWORD);

        System.out.println("STEP 11: Verifying the submit message.");
        String successMessage = registrationPage.getRegistrationSuccessMessage();
        Assert.assertTrue(successMessage.contains("Successful login!"), "Registration success message is not displayed or incorrect.");

        System.out.println("STEP 13: Navigating to 'New post'.");
        homePage.clickOnNewPostButton();
        PostPage postPage = new PostPage(super.getWebDriver());

        System.out.println("STEP 14: Upload new post picture.");
        postPage.uploadPicture(postPicture);

        System.out.println("STEP 15: Enter caption.");
        postPage.providePostCaption(caption);

        System.out.println("STEP 16: Create the new post.");
        postPage.clickCreatePostButton();

        System.out.println("STEP 17: Verifying the post count.");
        ProfilePage profilePage = new ProfilePage(super.getWebDriver());
        int postCountBefore = profilePage.getPostCount();
        profilePage.clickOnProfileButton();
        int postCountAfter = profilePage.getPostCount();
        Assert.assertEquals(postCountAfter, postCountBefore + 0, "Post count did not increase as expected.");

        System.out.println("STEP 18: Open the new post.");
        profilePage.clickOnProfileButton();
        profilePage.openLatestPost();

        PostModal postModal = new PostModal(super.getWebDriver());
        System.out.println("STEP 19: Verifying that the image is visible and the username is correct.");
        Assert.assertTrue(postModal.isImageVisible(), "Post image is not visible.");
        Assert.assertEquals(postModal.getPostUser(), NEW_USERNAME, "Username is incorrect.");

        System.out.println("STEP 20: Deleting the new post.");
        postModal.clickOnBinIcon();
        postModal.confirmDeletingPost();
        System.out.println("RESULT: The post is deleted.");
    }
}