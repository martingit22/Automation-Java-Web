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

        System.out.println("STEP 1: Open home page");
        HomePage homePage = new HomePage(super.getWebDriver());
        homePage.openHomePage();
        homePage.isUrlLoaded(HOME_PAGE_URL);

        System.out.println("STEP 2: Click on navigation login button");
        homePage.clickOnNavigationLoginButton();
        homePage.isUrlLoaded(LOGIN_PAGE_URL);

        System.out.println("STEP 3: Log in with credentials - User: " + user);
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.loginWithUserAndPassword(user, password);

        System.out.println("STEP 4: Verify new post button is shown");
        homePage.isNewPostButtonShown();

        System.out.println("STEP 5: Click on new post button");
        homePage.clickOnNewPostButton();
        PostPage postPage = new PostPage(super.getWebDriver());

        System.out.println("STEP 6: Upload picture from file: " + file.getAbsolutePath());
        postPage.uploadPicture(file);

        System.out.println("STEP 7: Provide post caption: " + caption);
        postPage.providePostCaption(caption);

        System.out.println("STEP 8: Click create post button");
        postPage.clickCreatePostButton();

        System.out.println("STEP 9: Click on the first post in profile page");
        ProfilePage profilePage = new ProfilePage(super.getWebDriver());
        profilePage.clickPost(0);
        PostModal postModal = new PostModal(super.getWebDriver());

        System.out.println("STEP 10: Verify the image is visible");
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");

        System.out.println("STEP 11: Verify the post username: " + username);
        Assert.assertEquals(postModal.getPostUser(), username, "The username does not match!");

        System.out.println("STEP 12: Add comment: " + comment);
        postModal.addComment(comment);

        System.out.println("STEP 13: Submit comment");
        postModal.submitComment();

        System.out.println("STEP 14: Verify the comment is visible");
        Assert.assertTrue(postModal.isCommentVisible(comment), "The comment is not visible!");
    }

    @Test(dataProvider = "CommentPostDataProvider", dependsOnMethods = {"verifyUserCanCreateAndCommentOnPost"})
    public void verifyUserCanDeletePost(String user, String password, String username, File file, String caption, String comment) {
        System.out.println("\n _________________________________________________");
        System.out.println("=== > *** Verify user can create, comment on, and delete a post *** < ===");

        final String HOME_PAGE_URL = "posts/all";
        final String LOGIN_PAGE_URL = "users/login";

        System.out.println("STEP 1: Open home page");
        HomePage homePage = new HomePage(super.getWebDriver());
        homePage.openHomePage();
        homePage.isUrlLoaded(HOME_PAGE_URL);

        System.out.println("STEP 2: Click on navigation login button");
        homePage.clickOnNavigationLoginButton();
        homePage.isUrlLoaded(LOGIN_PAGE_URL);

        System.out.println("STEP 3: Log in with credentials - User: " + user);
        LoginPage loginPage = new LoginPage(super.getWebDriver());
        loginPage.loginWithUserAndPassword(user, password);

        System.out.println("STEP 4: Click on profile button");
        ProfilePage profilePage = new ProfilePage(super.getWebDriver());
        profilePage.clickOnProfileButton();

        System.out.println("STEP 5: Click on the first post in profile page");
        profilePage.clickPost(0);
        PostModal postModal = new PostModal(super.getWebDriver());

        System.out.println("STEP 6: Click on bin icon to delete post");
        postModal.clickOnBinIcon();

        System.out.println("STEP 7: Confirm post deletion");
        postModal.confirmDeletingPost();

        System.out.println("STEP 8: Verify the post has been deleted");
        Assert.assertFalse(profilePage.isPostVisible(0), "The post was not deleted successfully!");
    }
}