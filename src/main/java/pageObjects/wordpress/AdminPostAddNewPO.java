package pageObjects.wordpress;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.AdminPostAddNewPageUI;

public class AdminPostAddNewPO extends BasePage {
    WebDriver driver;
    public AdminPostAddNewPO(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Input Post Title: {0}")
    public void enterToPostTitle(String postTitle) {
        waitForElementVisible(driver, AdminPostAddNewPageUI.POST_TITLE_TEXTBOX);
        sendKeyToElement(driver, AdminPostAddNewPageUI.POST_TITLE_TEXTBOX, postTitle);
    }

    @Step("Input Post Body: {0}")
    public void enterToPostBody(String postBody) {
        waitForElementClickable(driver, AdminPostAddNewPageUI.POST_BODY_BUTTON);
        clickToElement(driver, AdminPostAddNewPageUI.POST_BODY_BUTTON);
        waitForElementVisible(driver, AdminPostAddNewPageUI.POST_BODY_TEXTBOX);
        sendKeyToElement(driver, AdminPostAddNewPageUI.POST_BODY_TEXTBOX, postBody);
    }

    @Step("Click to Publish button")
    public void clickToPublishButton() {
        waitForElementClickable(driver, AdminPostAddNewPageUI.PUBLISH_BUTTON);
        clickToElement(driver, AdminPostAddNewPageUI.PUBLISH_BUTTON);
    }

    @Step("Verify post Publish message")
    public boolean isPostPublishMessageDisplayed(String postPublishMessage) {
        waitForElementVisible(driver, AdminPostAddNewPageUI.PUBLISH_MESSAGE, postPublishMessage);
        return isElementDisplayed(driver, AdminPostAddNewPageUI.PUBLISH_MESSAGE, postPublishMessage);
    }

    public AdminPostSearchPO openSearchPostPageUrl(String searchPostUrl) {
        openUrl(driver,searchPostUrl);
        return PageGeneratorManager.getAdminPostSearchPage(driver);
    }
}
