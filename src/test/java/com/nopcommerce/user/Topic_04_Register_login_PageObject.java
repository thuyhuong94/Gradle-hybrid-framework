package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

import java.time.Duration;

public class Topic_04_Register_login_PageObject {
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    String projectPath = System.getProperty("user.dir");
    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://demo.nopcommerce.com/");
    }
    @Test
    public void TC_01_Register_Emply_Data(){
        homePage = new HomePageObject(driver);
        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);
        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
        Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
        Assert.assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");
        Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "Password is required.");
    }
    @Test
    public void TC_02_Register_Invalid_Email(){
        homePage = new HomePageObject(driver);
        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);
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
