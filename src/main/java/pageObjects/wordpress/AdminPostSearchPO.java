package pageObjects.wordpress;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.AdminPostSearchPageUI;

public class AdminPostSearchPO extends BasePage {
    WebDriver driver;
    public AdminPostSearchPO(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click to Add New Post button")
    public AdminPostAddNewPO clickToAddNewPostButton() {
        waitForElementClickable(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
        clickToElement(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
        return PageGeneratorManager.getAdminPostAddNewPage(driver);
    }
    @Step("Enter to Search textbox: {0}")
    public void enterToSearchTextbox(String postTitle) {
        waitForElementVisible(driver, AdminPostSearchPageUI.SEARCH_POST_TEXTBOX);
        sendKeyToElement(driver, AdminPostSearchPageUI.SEARCH_POST_TEXTBOX, postTitle);
    }
    @Step("Click to 'Search Post' button")
    public void clickToSearchPostButton() {
        waitForElementClickable(driver, AdminPostSearchPageUI.SEARCH_POST_BUTTON);
        clickToElement(driver, AdminPostSearchPageUI.SEARCH_POST_BUTTON);
    }

    @Step("Verify Table result displayed {1}")
    public boolean isPostSearchTableDisplayed(String headerID, String cellValue) {
        int headerIndex = getElementsSize(driver, AdminPostSearchPageUI.TABLE_HEADER_INDEX_BY_HEADER_NAME, headerID) + 1;
        waitForElementVisible(driver, AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX,String.valueOf(headerIndex),cellValue);
        return isElementDisplayed(driver,AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX,String.valueOf(headerIndex),cellValue);
    }
}
