package com.myp.gui;

import com.myp.POM.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class EndToEndTest extends TestObject {

    // File path for the post picture and caption text
    File postPicture = new File("src\\test\\resources\\uploads\\testImg.jpg");
    String caption = "Testing create post caption";

    @Test
    public void endToEndTest() {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** END TO END SCENARIO *** < ===");

        final String NEWUSERNAME = "yourUsername";
        final String EMAIL = "yourEmail";
        final String REGPASSWORD = "yourPassword";
        final String CONFIRMPASSWORD = "yourConfirmPassword";

        HomePage homePage = new HomePage(super.getWebDriver());
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        PostPage postPage = new PostPage(super.getWebDriver());
        ProfilePage profilePage = new ProfilePage(super.getWebDriver());
        PostModal postModal = new PostModal(super.getWebDriver());

        // Step 1: Open iSkilo site
        System.out.println("STEP 1: Open iSkilo site.");
        homePage.openHomePage();
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page is not opened.");

        // Step 2: Click on Login button
        System.out.println("STEP 2: Navigate to Login page.");
        homePage.clickOnNavigationLoginButton();
        Assert.assertTrue(homePage.isUrlLoaded("users/login"), "Login page is not opened.");

        // Step 3: Perform login
        System.out.println("STEP 3: Perform login.");
        loginPage.loginWithUserAndPassword(NEWUSERNAME, REGPASSWORD);
        Assert.assertTrue(homePage.isLogOutButtonShown(), "Logout button is not shown, login might have failed.");

        // Step 4: Click on New Post button
        System.out.println("STEP 4: Navigate to New Post page.");
        homePage.clickOnNewPostButton();
        Assert.assertTrue(postPage.isImageVisible(), "New post page is not opened properly.");

        // Step 5: Upload picture and provide caption
        System.out.println("STEP 5: Upload picture and provide caption.");
        postPage.uploadPicture(postPicture);
        Assert.assertTrue(postPage.isImageVisible(), "Uploaded image is not visible.");
        Assert.assertEquals(postPage.getImageName(), postPicture.getName(), "Image name is incorrect.");
        postPage.providePostCaption(caption);
        postPage.clickCreatePostButton();

        // Step 6: Verify post creation
        System.out.println("STEP 6: Verify the new post.");
        int expectedPostCount = 1;
        int actualPostCount = profilePage.getPostCount();
        Assert.assertEquals(actualPostCount, expectedPostCount, "Post count is incorrect.");

        // Step 7: Open and verify post details
        System.out.println("STEP 7: Open and verify post details.");
        profilePage.clickPost(0);
        Assert.assertTrue(postModal.isImageVisible(), "Image in the post modal is not visible.");
        Assert.assertEquals(postModal.getPostUser(), NEWUSERNAME, "Username in the post modal is incorrect.");

        // Step 8: Delete the new post
        System.out.println("STEP 8: Delete the new post.");
        postModal.clickOnBinIcon();
        postModal.confirmDeletingPost();
        Assert.assertEquals(profilePage.getPostCount(), 0, "Post was not deleted.");

        System.out.println("RESULT: The post is successfully created and deleted.");
    }
}
