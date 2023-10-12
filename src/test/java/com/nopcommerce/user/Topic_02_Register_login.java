package com.nopcommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_02_Register_login {
    WebDriver driver;
    BasePage basePage = BasePage.getBasePage();
    String projectPath = System.getProperty("user.dir");
    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
    @Test
    public void TC_01_Register_Emply_Data(){
        basePage.openUrl(driver,"https://demo.nopcommerce.com/");
        basePage.clickToElement(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver,"//button[@id='register-button']");
        Assert.assertEquals(basePage.getElementText(driver,"//span[@id='FirstName-error']"), "First name is required.");
        Assert.assertEquals(basePage.getElementText(driver,"//span[@id='LastName-error']"), "Last name is required.");
        Assert.assertEquals(basePage.getElementText(driver,"//span[@id='Email-error']"), "Email is required.");
        Assert.assertEquals(basePage.getElementText(driver,"//span[@id='Password-error']"), "Password is required.");
        Assert.assertEquals(basePage.getElementText(driver,"//span[@id='ConfirmPassword-error']"), "Password is required.");
    }
    @Test
    public void TC_02_Register_Invalid_Email(){
        basePage.clickToElement(driver, "//a[@class='ico-register']");
        basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
        basePage.sendKeyToElement(driver, "//input[@id='LastName']", "FC");
        basePage.sendKeyToElement(driver, "//input[@id='Email']", "1234$33");
        basePage.sendKeyToElement(driver, "//input[@id='Password']", "1234");
        basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "1234");
        basePage.clickToElement(driver,"//button[@id='register-button']");
        Assert.assertEquals(basePage.getElementText(driver,"//span[@id='Email-error']"), "Wrong email");
    }
    @AfterClass
    public void afterClass(){
        driver.close();
    }
}
