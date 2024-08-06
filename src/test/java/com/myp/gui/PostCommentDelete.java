package com.myp.gui;

import com.myp.POM.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.io.File;

public class PostCommentDelete extends TestObject {

    @DataProvider(name = "CommentPostDataProvider")
    public Object[][] getUsers() {
        File postPicture = new File("src/test/resources/uploads/testUpload.jpg");
        String caption = "Testing create post caption";
        String comment = "This is a test comment";

        return new Object[][]{{
                "megamare", "ortoparisi",
                "megamare", postPicture, caption, comment},
        };
    }

    @Test(dataProvider = "CommentPostDataProvider")
    public void verifyUserCanCreateAndCommentOnPost(String user, String password, String username, File file, String caption, String comment) {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** Verify user can create and comment on a post *** < ===");

        final String HOME_PAGE_URL = "posts/all";
        final String LOGIN_PAGE_URL = "users/login";

        HomePage homePage = new HomePage(super.getWebDriver());

        homePage.openHomePage();
        homePage.isUrlLoaded(HOME_PAGE_URL);
        homePage.clickOnNavigationLoginButton();
        homePage.isUrlLoaded(LOGIN_PAGE_URL);
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.loginWithUserAndPassword(user, password);

        homePage.isNewPostButtonShown();
        homePage.clickOnNewPostButton();
        PostPage postPage = new PostPage(super.getWebDriver());
        postPage.uploadPicture(file);
        postPage.providePostCaption(caption);
        postPage.clickCreatePostButton();

        ProfilePage profilePage = new ProfilePage(super.getWebDriver());
        profilePage.clickPost(0);
        PostModal postModal = new PostModal(super.getWebDriver());
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postModal.getPostUser(), username, "The username does not match!");

        postModal.addComment(comment);
        postModal.submitComment();

        Assert.assertTrue(postModal.isCommentVisible(comment), "The comment is not visible!");
    }

    @Test(dataProvider = "CommentPostDataProvider", dependsOnMethods = {"verifyUserCanCreateAndCommentOnPost"})
    public void verifyUserCanDeletePost(String user, String password, String username, File file, String caption, String comment) {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** Verify user can create, comment on, and delete a post *** < ===");

        final String HOME_PAGE_URL = "posts/all";
        final String LOGIN_PAGE_URL = "users/login";

        HomePage homePage = new HomePage(super.getWebDriver());

        homePage.openHomePage();
        homePage.isUrlLoaded(HOME_PAGE_URL);
        homePage.clickOnNavigationLoginButton();
        homePage.isUrlLoaded(LOGIN_PAGE_URL);
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.loginWithUserAndPassword(user, password);
        ProfilePage profilePage = new ProfilePage(super.getWebDriver());

        profilePage.clickOnProfileButton();
        profilePage.clickPost(0);
        PostModal postModal = new PostModal(super.getWebDriver());
        postModal.clickOnBinIcon();
        postModal.confirmDeletingPost();
        Assert.assertFalse(profilePage.isPostVisible(0), "The post was not deleted successfully!");
    }
    }