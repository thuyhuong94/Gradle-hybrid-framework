package pageObjectsjQuery;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIsjQuery.HomePageUI;

import java.util.ArrayList;
import java.util.List;

public class HomePageObject extends BasePage {
    private WebDriver driver;
    public HomePageObject(WebDriver driver){
        this.driver = driver;
    }

    @Step ("Open Page by Number {0}")
    public void openPagingByNumber(String pageNumber) {
        waitForElementClickable(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER,pageNumber);
        clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER,pageNumber);
    }
    public boolean isPageNumberactived(String pageNumber) {
        waitForAllElementsVisible(driver, HomePageUI.PAGINATION_PAGE_ACTIVED_BY_NUMBER, pageNumber);
        return isElementDisplayed(driver, HomePageUI.PAGINATION_PAGE_ACTIVED_BY_NUMBER, pageNumber);
    }
    @Step("Input value {1} to header {0}")
    public void enterToHeaderTextBox(String header, String sendvalue) {
        waitForAllElementsVisible(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, header);
        sendKeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL,sendvalue, header);
    }
    public List<String> getValueEachRowAtAllPage(){
        int totalPage = getElementsSize(driver, HomePageUI.TOTAL_PAGINATION);
        List<String> allRowValueAllPage = new ArrayList<String>();
        for (int i = 1; i <=totalPage; i++){
            clickToElement(driver,HomePageUI.PAGINATION_PAGE_BY_NUMBER,String.valueOf(i));
            sleepInSecond(1);
            List<WebElement> allRowElementEachPage = getWebElements(driver, HomePageUI.ALL_ROW_EACH_PAGE);
            for (WebElement eachRow: allRowElementEachPage){
                allRowValueAllPage.add(eachRow.getText());
            }
        }
        return allRowValueAllPage;
    }
    public List<String> getValueByColumnEachRowAtAllPage(String columnName){
        int totalPage = getElementsSize(driver, HomePageUI.TOTAL_PAGINATION);
        List<String> allRowValueAllPage = new ArrayList<String>();
        if (isPageNumberactived("1"))
            clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, String.valueOf(2));
        for (int i = 1; i <=totalPage; i++){
            clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, String.valueOf(i));
            sleepInSecond(1);
            List<WebElement> allRowElementEachPage = getWebElements(driver, HomePageUI.ALL_ROW_EACH_PAGE_BYCOLUMN, columnName);
            for (WebElement eachRow: allRowElementEachPage){
                allRowValueAllPage.add(eachRow.getText());
            }
        }
        return allRowValueAllPage;
    }
}
