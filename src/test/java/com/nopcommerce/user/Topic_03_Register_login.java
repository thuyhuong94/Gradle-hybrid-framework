package com.nopcommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_03_Register_login extends BasePage{
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
    @Test
    public void TC_01_Register_Emply_Data(){
        openUrl(driver,"https://demo.nopcommerce.com/");
        clickToElement(driver, "//a[@class='ico-register']");
        clickToElement(driver,"//button[@id='register-button']");
        Assert.assertEquals(getElementText(driver,"//span[@id='FirstName-error']"), "First name is required.");
        Assert.assertEquals(getElementText(driver,"//span[@id='LastName-error']"), "Last name is required.");
        Assert.assertEquals(getElementText(driver,"//span[@id='Email-error']"), "Email is required.");
        Assert.assertEquals(getElementText(driver,"//span[@id='Password-error']"), "Password is required.");
        Assert.assertEquals(getElementText(driver,"//span[@id='ConfirmPassword-error']"), "Password is required.");
    }
    @Test
    public void TC_02_Register_Invalid_Email(){
        clickToElement(driver, "//a[@class='ico-register']");
        sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
        sendKeyToElement(driver, "//input[@id='LastName']", "FC");
        sendKeyToElement(driver, "//input[@id='Email']", "1234$33");
        sendKeyToElement(driver, "//input[@id='Password']", "1234");
        sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "1234");
        clickToElement(driver,"//button[@id='register-button']");
        Assert.assertEquals(getElementText(driver,"//span[@id='Email-error']"), "Wrong email");
    }
    @AfterClass
    public void afterClass(){
        driver.close();
    }
}
