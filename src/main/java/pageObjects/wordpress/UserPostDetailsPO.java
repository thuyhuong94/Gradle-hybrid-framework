package pageObjects.wordpress;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.UserHomePageUI;
import pageUIs.wordpress.UserPostDetailsPageUI;

public class UserPostDetailsPO extends BasePage {
    WebDriver driver;
    public UserPostDetailsPO(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Verify postTitle is displayed on UserPostDetailsPage: {0}")
    public boolean isPostTitleDisplayed(String postTitle) {
        waitForElementVisible(driver, UserPostDetailsPageUI.POST_TITLE_TEXT,postTitle);
        return isElementDisplayed(driver,UserPostDetailsPageUI.POST_TITLE_TEXT,postTitle);
    }
    @Step("Verify postDate is displayed on UserPostDetailsPage: {1}")
    public boolean isPostDateDisplayed(String postTitle, String postDate) {
        waitForElementVisible(driver, UserPostDetailsPageUI.POST_DATE,postTitle, postDate);
        return isElementDisplayed(driver,UserPostDetailsPageUI.POST_DATE,postTitle, postDate);
    }
    @Step("Verify postBody is displayed on UserPostDetailsPage: {1}")
    public boolean isPostBodyDisplayed(String postTitle,String postBody) {
        waitForElementVisible(driver, UserPostDetailsPageUI.POST_BODY_TEXT,postTitle, postBody);
        return isElementDisplayed(driver,UserPostDetailsPageUI.POST_BODY_TEXT,postTitle, postBody);
    }
    @Step("Verify postAuthor is displayed on UserPostDetailsPage: {1}")
    public boolean isPostAuthorDisplayed(String postTitle, String postAuthor) {
        waitForElementVisible(driver, UserPostDetailsPageUI.POST_AUTHOR,postTitle, postAuthor);
        return isElementDisplayed(driver,UserPostDetailsPageUI.POST_AUTHOR,postTitle, postAuthor);
    }
}
