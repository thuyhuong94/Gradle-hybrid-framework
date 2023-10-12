package com.wordpress.admin;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.wordpress.*;

public class Post_01_Create_Read_Update_Delete_Search extends BaseTest {
    WebDriver driver;
    AdminLoginPO adminLoginPage;
    AdminDashboardPO adminDashboardPage;
    AdminPostSearchPO adminPostSearchPage;
    AdminPostAddNewPO adminPostAddNewPage;
    UserHomePO userHomePage;
    UserPostDetailsPO userPostDetailsPage;
    String adminName = "Huong";
    String adminPassWord = "12345";
    int number = generateFakeNumber();
    String postTitle = "Live Coding" + number;
    String postBody = "Tester Quality Control Automation Tester" + number;
    String postAuthor = "Huong";
    String searchPostUrl;
    String adminUrl,userUrl;
    String postDate;
    @Parameters({"browser", "urlAdmin", "urlUser"})
    @BeforeClass
    public void beforeClass(String browserName, String adminUrl, String userUrl){
        this.adminUrl = adminUrl;
        this.userUrl = userUrl;
        driver = getBrowserDriver(browserName, adminUrl);
        adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
        adminLoginPage.enterToNameTextbox(adminName);
        adminLoginPage.enterToPassWordTextbox(adminPassWord);
        adminDashboardPage= adminLoginPage.clickToLoginButton();
    }
    @Test
    public void Post_01_Create_New_Post(){
        adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
        searchPostUrl = adminPostSearchPage.getPageUrl(driver);
        adminPostAddNewPage = adminPostSearchPage.clickToAddNewPostButton();
        adminPostAddNewPage.enterToPostTitle(postTitle);
        adminPostAddNewPage.enterToPostBody(postBody);
        adminPostAddNewPage.clickToPublishButton();
        Assert.assertTrue(adminPostAddNewPage.isPostPublishMessageDisplayed("Post published."));
        postDate = getToday();
    }
    @Test
    public void Post_02_Search_View_Post(){
        adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostUrl);
        adminPostSearchPage.enterToSearchTextbox(postTitle);
        adminPostSearchPage.clickToSearchPostButton();
        Assert.assertTrue(adminPostSearchPage.isPostSearchTableDisplayed("title",postTitle));
        Assert.assertTrue(adminPostSearchPage.isPostSearchTableDisplayed("author",postAuthor));
        userHomePage = adminPostSearchPage.openEndUserSite(driver, userUrl);
        Assert.assertTrue(userHomePage.isPostTitleDisplayed(postTitle));
        Assert.assertTrue(userHomePage.isPostDateDisplayed(postTitle,postDate));
        Assert.assertTrue(userHomePage.isPostBodyDisplayed(postTitle,postBody));
        Assert.assertTrue(userHomePage.isPostAuthorDisplayed(postTitle,postAuthor));

        userPostDetailsPage = userHomePage.clickToPostTitle(postTitle);
        Assert.assertTrue(userPostDetailsPage.isPostTitleDisplayed(postTitle));
        Assert.assertTrue(userPostDetailsPage.isPostDateDisplayed(postTitle,postDate));
        Assert.assertTrue(userPostDetailsPage.isPostBodyDisplayed(postTitle,postBody));
        Assert.assertFalse(userPostDetailsPage.isPostAuthorDisplayed(postTitle,postAuthor));
    }
    @Test
    public void TC_03_GetAllValueInColumn(){

    }
    @AfterClass (alwaysRun = true)
    public void afterClass(){
        closeBrowserDriver();
    }
}
