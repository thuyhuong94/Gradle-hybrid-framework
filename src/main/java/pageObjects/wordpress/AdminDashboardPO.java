package pageObjects.wordpress;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.AdminDashboardPageUI;

public class AdminDashboardPO extends BasePage {
    WebDriver driver;
    public AdminDashboardPO(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click to Post Menu link")
    public AdminPostSearchPO clickToPostMenuLink() {
        waitForElementClickable(driver, AdminDashboardPageUI.POST_MENU_LINK);
        clickToElement(driver, AdminDashboardPageUI.POST_MENU_LINK);
        return PageGeneratorManager.getAdminPostSearchPage(driver);
    }
}
