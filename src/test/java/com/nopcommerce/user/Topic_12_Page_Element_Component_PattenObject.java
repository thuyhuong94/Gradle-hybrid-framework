package com.nopcommerce.user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;

public class Topic_12_Page_Element_Component_PattenObject extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LogInPageObject loginPage;
    private CustomerPageObject customerInfo;
    private RewardPointObject rewardPoint;
    private String firstName, lastName, email, passWord, date, month, year;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String urlValue){
        driver = getBrowserDriver(browserName);
        driver.get(urlValue);
        homePage = PageGeneratorManager.getHomePage(driver);
        firstName = "Automation";
        lastName = "FC";
        email = "test04@gmail.com";
        passWord ="12345r";
        date = "10";
        month = "August";
        year = "2000";
    }
    @Test
    public void TC_01_Register(){
        log.info("Register - Step01: Open Register link");
        registerPage= homePage.clickToRegisterLink();

        log.info("Register - Step02: select Gender = Female" );
        registerPage.clickToRadioButtonByLabel(driver, "Female");

        log.info("Register - Step02: Input firstname " + firstName );
        registerPage.inputToTextboxByID(driver, "FirstName",firstName);

        log.info("Register - Step02: Input lastname " + lastName );
        registerPage.inputToTextboxByID(driver, "LastName",lastName);

        log.info("Register - Step02: Input  DateOfBirthDay" + date );
        registerPage.selectToDropdownByName(driver, "DateOfBirthDay", date);

        log.info("Register - Step02: Input  DateOfBirthMonth" + month );
        registerPage.selectToDropdownByName(driver, "DateOfBirthMonth", month);

        log.info("Register - Step02: Input  DateOfBirthYear" + year );
        registerPage.selectToDropdownByName(driver, "DateOfBirthYear", year);

        log.info("Register - Step02: Input email " + email );
        registerPage.inputToTextboxByID(driver, "Email",email);

        log.info("Register - Step02: Select checkbox News Letter ");
        registerPage.clickToCheckboxByLabel(driver, "Newsletter");

        log.info("Register - Step02: Input passWord " + passWord);
        registerPage.inputToTextboxByID(driver, "Password",passWord);

        log.info("Register - Step02: Input confirm password " + passWord);
        registerPage.inputToTextboxByID(driver, "ConfirmPassword",passWord);

        log.info("Register - Step03: Click register button ");
        registerPage.clickToButtonByText(driver,"Register");

        log.info("Register - Step04: Verify Register result ");
        Assert.assertEquals(registerPage.getRegisteredResult(),"Your registration completed");
    }

    @Test
    public void TC_02_Login_Page_SwitchPageByDynamicXpath(){
        log.info("Login - Step01: Open Register link");
        loginPage = homePage.clickToLoginLink();

        log.info("Login - Step02: Input data to login" + email);
        loginPage.inputToTextboxByID(driver, "Email", email);
        log.info("Login - Step02: Input data to login" + passWord);
        loginPage.inputToTextboxByID(driver, "Password", passWord);

        log.info("Login - Step02: Click button Login");
        loginPage.clickToButtonByText(driver,"Log in");

        log.info("Login - Step03: Click button Login");
        loginPage.clickToMyAccount();

        log.info("Login - Step04: Open Page Customer info");
        loginPage.openPageAtMyAccountByPageName(driver,"Customer info");
        customerInfo = PageGeneratorManager.getCustomerInfoPage(driver);

        log.info("Login - Step05: Open Page Reward points");
        customerInfo.openPageAtMyAccountByPageName(driver, "Reward points");
        rewardPoint =PageGeneratorManager.getRewardPointObject(driver);

        log.info("Login - Step06: Open Page Customer info");
        rewardPoint.openPageAtMyAccountByPageName(driver,"Customer info");
        customerInfo = PageGeneratorManager.getCustomerInfoPage(driver);
    }

    @AfterClass
    public void afterClass(){
        driver.close();
    }
}
