package pageObjects.wordpress;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.UserHomePageUI;
import pageUIs.wordpress.UserPostDetailsPageUI;

public class UserHomePO extends BasePage {
    WebDriver driver;
    public UserHomePO(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Verify postTitle is displayed on UserHomePage: {0}")
    public boolean isPostTitleDisplayed(String postTitle) {
        waitForElementVisible(driver, UserHomePageUI.POST_TITLE_TEXT,postTitle);
        return isElementDisplayed(driver,UserHomePageUI.POST_TITLE_TEXT,postTitle);
    }
    @Step("Verify postDate is displayed on UserHomePage: {1}")
    public boolean isPostDateDisplayed(String postTitle, String postDate) {
        waitForElementVisible(driver, UserHomePageUI.POST_DATE,postTitle, postDate);
        return isElementDisplayed(driver,UserHomePageUI.POST_DATE,postTitle, postDate);
    }
    @Step("Verify postBody is displayed on UserHomePage: {1}")
    public boolean isPostBodyDisplayed(String postTitle,String postBody) {
        waitForElementVisible(driver, UserHomePageUI.POST_BODY_TEXT,postTitle, postBody);
        return isElementDisplayed(driver,UserHomePageUI.POST_BODY_TEXT,postTitle, postBody);
    }
    @Step("Verify postAuthor is displayed on UserHomePage: {1}")
    public boolean isPostAuthorDisplayed(String postTitle, String postAuthor) {
        waitForElementVisible(driver, UserHomePageUI.POST_AUTHOR,postTitle, postAuthor);
        return isElementDisplayed(driver,UserHomePageUI.POST_AUTHOR,postTitle, postAuthor);
    }
    @Step("Click to postTitle to go to UserPostDetail")
    public UserPostDetailsPO clickToPostTitle(String postTitle) {
        waitForElementClickable(driver, UserHomePageUI.POST_TITLE_TEXT,postTitle);
        clickToElement(driver, UserHomePageUI.POST_TITLE_TEXT,postTitle);
        return PageGeneratorManager.getUserPostDetaisPage(driver);
    }
}
