package com.myp.gui;


import com.myp.POM.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class PostTest extends TestObject{
    @DataProvider(name = "PostTestDataProvider")
    public Object[][] getUsers() {
        File postPicture = new File("src\\test\\resources\\uploads\\testUpload.jpg");
        String caption = "Testing create post caption";

        return new Object[][]{{
                "megamare", "ortoparisi",
                "megamare", postPicture, caption},
        };
    };

        @Test
        public void verifyPostModalDetails() {
            System.out.println("\n _________________________________________________");
            System.out.println("=== > *** Verify post modal details *** < ===");

            // Initial setup and login
            HomePage homePage = new HomePage(super.getWebDriver());
            final String HOME_PAGE_URL = "posts/all";
            final String LOGIN_PAGE_URL = "users/login";
            final String USERNAME = "megamare";
            final String PASSWORD = "ortoparisi";

            System.out.println("STEP 1: Open Skillo website home page");
            homePage.openHomePage();
            homePage.isUrlLoaded(HOME_PAGE_URL);

            System.out.println("STEP 2: Click on Login button");
            homePage.clickOnNavigationLoginButton();

            System.out.println("STEP 3: Is URL loaded");
            homePage.isUrlLoaded(LOGIN_PAGE_URL);

            System.out.println("STEP 4: Log in with correct credentials");
            LoginPage loginPage = new LoginPage(super.getWebDriver());
            loginPage.loginWithUserAndPassword(USERNAME, PASSWORD);

            System.out.println("STEP 5: Waiting for New Post button to be visible");
            homePage.isNewPostButtonShown();

            System.out.println("STEP 6: Click on the first post");
            ProfilePage profilePage = new ProfilePage(super.getWebDriver());
            profilePage.clickOnProfileButton();
            profilePage.clickPost(1);

            System.out.println("STEP 7: Verifying post modal details");
            PostModal postModal = new PostModal(super.getWebDriver());
            Assert.assertTrue(postModal.isImageVisible(), "The image should be visible in the post modal");
            Assert.assertEquals(postModal.getPostUser(), USERNAME, "Username in the post modal is incorrect");

            System.out.println("Post modal details verified successfully.");
        }
}