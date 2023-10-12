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
import java.util.List;

//@Listeners(commons.MethodListener.class)
public class Topic_10_ExtentReportV5 extends BaseTest {
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
    public void TC_01_SelectPageAndVerifyPageIsSelected(Method method){
        String[] pages = {"10", "18", "8"};
        ExtentTestManager.startTest(method.getName(),"Select Pages");
        ExtentTestManager.getTest().log(Status.INFO, "SelectPage - Step01: Open Dynamic Page " + pages[0]);
        homePage.openPagingByNumber(pages[0]);
        ExtentTestManager.getTest().log(Status.INFO, "Verify opened Dynamic Page " + pages[0]);
        Assert.assertTrue(homePage.isPageNumberactived(pages[0]));

        ExtentTestManager.getTest().log(Status.INFO, "SelectPage - Step01: Open Dynamic Page " + pages[1]);
        homePage.openPagingByNumber(pages[1]);
        ExtentTestManager.getTest().log(Status.INFO, "Verify opened Dynamic Page " + pages[1]);
        Assert.assertFalse(homePage.isPageNumberactived(pages[1]));

        ExtentTestManager.getTest().log(Status.INFO, "SelectPage - Step01: Open Dynamic Page " + pages[2]);
        homePage.openPagingByNumber(pages[2]);
        ExtentTestManager.getTest().log(Status.INFO, "Verify opened Dynamic Page " + pages[2]);
        Assert.assertTrue(homePage.isPageNumberactived(pages[2]));
    }
    @Test
    public void TC_02_SearchItemsByEnterToHeader(Method method){
        homePage.refreshPage(driver);
        ExtentTestManager.startTest(method.getName(),"Select ItemHeader");
        ExtentTestManager.getTest().log(Status.INFO, "Country");
        homePage.enterToHeaderTextBox("Country","Armenia");
        ExtentTestManager.getTest().log(Status.INFO, "Females");
        homePage.enterToHeaderTextBox("Females","15999");
        ExtentTestManager.getTest().log(Status.INFO, "Males");
        homePage.enterToHeaderTextBox("Males","16456");
        ExtentTestManager.getTest().log(Status.INFO, "Total");
        homePage.enterToHeaderTextBox("Total","32487");
        homePage.sleepInSecond(1);
    }
    @AfterClass
    public void afterClass(){
        driver.close();
    }
}
