package com.jQuery.dataTable;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjectsjQuery.HomePageObject;
import pageObjectsjQuery.PageGeneratorManager;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

//@Listeners(commons.MethodListener.class)
public class Topic_11_AllureReport extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String urlValue){
        driver = getBrowserDriver(browserName);
        driver.get(urlValue);
        homePage = PageGeneratorManager.getHomePage(driver);
    }
    @Test
    public void TC_01_SelectPageAndVerifyPageIsSelected(){
        String[] pages = {"10", "18", "8"};
        homePage.openPagingByNumber(pages[0]);
        Assert.assertTrue(homePage.isPageNumberactived(pages[0]));

        homePage.openPagingByNumber(pages[1]);
        Assert.assertFalse(homePage.isPageNumberactived(pages[1]));

        homePage.openPagingByNumber(pages[2]);
        Assert.assertTrue(homePage.isPageNumberactived(pages[2]));
    }
    @Test
    public void TC_02_SearchItemsByEnterToHeader(){
        homePage.refreshPage(driver);
        homePage.enterToHeaderTextBox("Country","Armenia");
        homePage.enterToHeaderTextBox("Females","15999");
        homePage.enterToHeaderTextBox("Males","16456");
        homePage.enterToHeaderTextBox("Total","32487");
        homePage.sleepInSecond(1);
    }
    @AfterClass (alwaysRun = true)
    public void afterClass(){
        closeBrowserDriver();
    }
}
