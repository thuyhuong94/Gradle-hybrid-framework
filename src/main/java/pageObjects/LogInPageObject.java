package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.LogInPageUI;

public class LogInPageObject extends BasePage {
    private WebDriver driver;
    public LogInPageObject(WebDriver driver){
        this.driver = driver;
    }
    public void inputEmail(String value){
        waitForElementVisible(driver, LogInPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, LogInPageUI.EMAIL_TEXTBOX,value);
    }
    public void inputPassword(String value){
        waitForElementVisible(driver, LogInPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, LogInPageUI.PASSWORD_TEXTBOX, value);
    }
    public void clickToLoginButton(){
        waitForElementClickable(driver, LogInPageUI.LOGIN_BUTTON);
        clickToElement(driver,LogInPageUI.LOGIN_BUTTON);
    }
    public void loginToPage(String email, String password){
        inputEmail(email);
        inputPassword(password);
        clickToLoginButton();
    }
    public void clickToMyAccount(){
        waitForElementClickable(driver, LogInPageUI.MYACCOUNT);
        clickToElement(driver, LogInPageUI.MYACCOUNT);
    }
}
