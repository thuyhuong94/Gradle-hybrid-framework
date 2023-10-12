package pageObjects.wordpress;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.AdminLoginPageUI;

public class AdminLoginPO extends BasePage {
    private WebDriver driver;
    public AdminLoginPO(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter AdminName textbox with value {0}")
    public void enterToNameTextbox(String adminName) {
        waitForElementVisible(driver, AdminLoginPageUI.USERNAME_TEXTBOX);
        sendKeyToElement(driver,AdminLoginPageUI.USERNAME_TEXTBOX,adminName);
    }

    @Step("Enter PassWord textbox with value {0}")
    public void enterToPassWordTextbox(String adminPassWord) {
        waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver,AdminLoginPageUI.PASSWORD_TEXTBOX,adminPassWord);
    }

    @Step("Click to Login button")
    public AdminDashboardPO clickToLoginButton() {
        waitForElementClickable(driver,AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver,AdminLoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getAdminDashboardPage(driver);
    }
}
