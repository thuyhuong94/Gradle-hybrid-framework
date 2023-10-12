package com.jQuery.dataTable;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjectsjQuery.HomePageObject;
import pageObjectsjQuery.PageGeneratorManager;

import java.util.List;
//@Listeners(commons.MethodListener.class)
public class Topic_09_DataTable_HandleTableGrid extends BaseTest {
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
        log.info("SelectPage - Step01: Open Dynamic Page " + pages[0]);
        homePage.openPagingByNumber(pages[0]);
        log.info("Verify opened Dynamic Page " + pages[0]);
        Assert.assertTrue(homePage.isPageNumberactived(pages[0]));

        log.info("SelectPage - Step02: Open Dynamic Page " + pages[1]);
        homePage.openPagingByNumber(pages[1]);
        log.info("Verify open Dynamic Page " + pages[1]);
        Assert.assertFalse(homePage.isPageNumberactived(pages[1]));

        log.info("SelectPage - Step01: Open Dynamic Page " + pages[2]);
        homePage.openPagingByNumber(pages[2]);
        log.info("Verify open Dynamic Page " + pages[2]);
        Assert.assertTrue(homePage.isPageNumberactived(pages[2]));
    }
    //@Test
    public void TC_02_SearchItemsByEnterToHeader(){
        homePage.refreshPage(driver);
        homePage.enterToHeaderTextBox("Country","Armenia");
        homePage.enterToHeaderTextBox("Females","15999");
        homePage.enterToHeaderTextBox("Males","16456");
        homePage.enterToHeaderTextBox("Total","32487");
        homePage.sleepInSecond(1);
        homePage.enterToHeaderTextBox("Country","Angola");
        homePage.enterToHeaderTextBox("Females","276880");
        homePage.enterToHeaderTextBox("Males","276472");
        homePage.enterToHeaderTextBox("Total","553353");
        homePage.sleepInSecond(1);

    }
    //@Test
    public void TC_03_GetAllValueInColumn(){
        homePage.refreshPage(driver);
        List<String> allvalue = homePage.getValueByColumnEachRowAtAllPage("country");
        System.out.println(allvalue);
    }
    @AfterClass
    public void afterClass(){
        driver.close();
    }
}
