package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
    private WebDriver driver;
    public RegisterPageObject(WebDriver driver){
        this.driver = driver;
    }
    public void clickToRegisterButton(){
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
    }
    public String getFirstNameErrorMessage(){
        waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
        return getElementText(driver, RegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
    }

    public String getLastNameErrorMessage() {
        waitForElementVisible(driver, RegisterPageUI.LASTNAME_ERROR_MESSAGE);
        return getElementText(driver, RegisterPageUI.LASTNAME_ERROR_MESSAGE);
    }

    public String getEmailErrorMessage() {
        waitForElementVisible(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
        return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
    }

    public String getPasswordErrorMessage() {
        waitForElementVisible(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
    }

    public String getConfirmPasswordErrorMessage() {
        waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
    }
    public String getRegisteredMessage() {
        waitForElementVisible(driver, RegisterPageUI.REGISTER_MESSAGE);
        return getElementText(driver, RegisterPageUI.REGISTER_MESSAGE);
    }
    public String getRegisteredResult() {
        waitForElementVisible(driver, RegisterPageUI.REGISTER_RESULT);
        return getElementText(driver, RegisterPageUI.REGISTER_RESULT);
    }

    public void sendkeyToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_TEXTBOX);
        sendKeyToElement(driver,RegisterPageUI.FIRSTNAME_TEXTBOX, firstName);
    }

    public void sendkeyToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, RegisterPageUI.LASTNAME_TEXTBOX);
        sendKeyToElement(driver,RegisterPageUI.LASTNAME_TEXTBOX, lastName);
    }

    public void sendkeyToEmailTextbox(String email) {
        waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver,RegisterPageUI.EMAIL_TEXTBOX, email);
    }

    public void sendkeyToPasswordTextbox(String password) {
        waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver,RegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    public void sendkeyToConfirmPasswordTextbox(String confirmPassword) {
        waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(driver,RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
    }
    public void registerNewAccount(String firstName, String lastName, String email, String passWord){
        sendkeyToFirstNameTextbox(firstName);
        sendkeyToLastNameTextbox(lastName);
        sendkeyToEmailTextbox(email);
        sendkeyToPasswordTextbox(passWord);
        sendkeyToConfirmPasswordTextbox(passWord);
        clickToRegisterButton();
    }
}
