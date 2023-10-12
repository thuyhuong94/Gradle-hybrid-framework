package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import commons.PageGeneratorManager;
import pageObjects.RegisterPageObject;

public class Topic_07_Register_login_PageGeneratorManager_PartII extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String urlValue){
        driver = getBrowserDriver(browserName);
        driver.get(urlValue);
        homePage = PageGeneratorManager.getHomePage(driver);
    }
    @Test
    public void TC_01_Register_Emply_Data(){
        registerPage = homePage.clickToRegisterLink();
        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
        Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
        Assert.assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");
        Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "Password is required.");
    }
    @Test
    public void TC_02_Register_Invalid_Email(){
        homePage = PageGeneratorManager.getHomePage(driver);
        registerPage= homePage.clickToRegisterLink();
        registerPage.sendkeyToFirstNameTextbox("Automation");
        registerPage.sendkeyToLastNameTextbox("FC");
        registerPage.sendkeyToEmailTextbox("123@123");
        registerPage.sendkeyToPasswordTextbox("123456");
        registerPage.sendkeyToConfirmPasswordTextbox("123456");
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getRegisteredMessage(),"Wrong email");
    }
    @AfterClass
    public void afterClass(){
        driver.close();
    }
}
