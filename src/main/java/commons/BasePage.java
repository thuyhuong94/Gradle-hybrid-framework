package commons;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.wordpress.PageGeneratorManager;
import pageObjects.wordpress.UserHomePO;
import pageUIs.BasePageUI;
import pageUIs.LogInPageUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    public static BasePage getBasePage(){
        return new BasePage();
    }
    public void openUrl(WebDriver driver, String url){
        driver.get(url);
    }
    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }
    @Step("Get current page url")
    public String getPageUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }
    public String getPageSourceCode(WebDriver driver){
        return driver.getPageSource();
    }
    public void backToPage(WebDriver driver){
        driver.navigate().back();
    }
    @Step("Reload Page")
    public void refreshPage(WebDriver driver){
        driver.navigate().refresh();
    }
    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }
    public Alert waitForAlertPresence(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.alertIsPresent());
    }
    public void acceptAlert(WebDriver driver){
        waitForAlertPresence(driver).accept();
    }
    public void cancelAlert(WebDriver driver){
        waitForAlertPresence(driver).dismiss();
    }
    public String getAlertText(WebDriver driver){
        return waitForAlertPresence(driver).getText();
    }
    public void sendKeyToAlert(WebDriver driver, String value){
        waitForAlertPresence(driver).sendKeys(value);
    }
    public void switchToWindowByID(WebDriver driver, String windowPageID){
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows){
            if (!window.equals(windowPageID)){
                driver.switchTo().window(window);
            }
        }
    }
    public void switchToWindowByPageTitle(WebDriver driver, String windowPageTitle){
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows){
            driver.switchTo().window(window);
            sleepInSecond(2);
            String actualPageTitle = driver.getTitle();
            if (actualPageTitle.equals(windowPageTitle)){
                break;
            }
        }
    }
    public void closeAllWindowsWithoutParent(WebDriver driver, String parentPageID){
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows){
            if (!window.equals(parentPageID)){
                driver.switchTo().window(window);
                sleepInSecond(1);
                driver.close();
            }
        }
        driver.switchTo().window(parentPageID);
        sleepInSecond(1);
    }
    private By getByLocator(String xpathExpression){
        By by = null;
        if (xpathExpression.startsWith("id=") || xpathExpression.startsWith("ID=") || xpathExpression.startsWith("Id=")){
            by = By.id(xpathExpression.substring(3));
        } else if (xpathExpression.startsWith("class=") || xpathExpression.startsWith("CLASS=") || xpathExpression.startsWith("Class=")){
            by = By.className(xpathExpression.substring(6));
        } else if (xpathExpression.startsWith("name=") || xpathExpression.startsWith("NAME=") || xpathExpression.startsWith("Name=")){
            by = By.name(xpathExpression.substring(5));
        } else if (xpathExpression.startsWith("css=") || xpathExpression.startsWith("CSS=") || xpathExpression.startsWith("Css=")){
            by = By.cssSelector(xpathExpression.substring(4));
        } else if (xpathExpression.startsWith("xpath=") || xpathExpression.startsWith("XPATH=") || xpathExpression.startsWith("Xpath=") || xpathExpression.startsWith("XPath=")){
            by = By.xpath(xpathExpression.substring(6));
        } else if (xpathExpression.startsWith("//")){
            by = By.xpath(xpathExpression);
        } else {
            throw new RuntimeException("Locator type is not supported!");
        }
        return by;
    }
    public WebElement getWebElement(WebDriver driver, String xpathExpression){
        return driver.findElement(getByLocator(xpathExpression));
    }
    public WebElement getWebElement(WebDriver driver, String xpathExpression, String... values){
        return driver.findElement(getByLocator(getDynamicXpath(xpathExpression,values)));
    }
    public List<WebElement> getWebElements(WebDriver driver, String xpathExpression){
        return driver.findElements(getByLocator(xpathExpression));
    }
    public List<WebElement> getWebElements(WebDriver driver, String xpathExpression, String... values){
        return driver.findElements(getByLocator(getDynamicXpath(xpathExpression,values)));
    }
    public void clickToElement(WebDriver driver, String xpathExpression){
        getWebElement(driver,xpathExpression).click();
    }
    public String getDynamicXpath(String xpathExpression, String... values){
        xpathExpression = String.format(xpathExpression, (Object[]) values);
        return xpathExpression;
    }
    public void clickToElement(WebDriver driver, String xpathExpression, String... values){
        getWebElement(driver,getDynamicXpath(xpathExpression,values)).click();
    }
    public void sendKeyToElement(WebDriver driver, String xpathExpression, String sendvalue){
        getWebElement(driver,xpathExpression).clear();
        getWebElement(driver,xpathExpression).sendKeys(sendvalue);
    }
    public void sendKeyToElement(WebDriver driver, String xpathExpression, String sendvalue, String... values){
        getWebElement(driver,xpathExpression,values).clear();
        getWebElement(driver,xpathExpression,values).sendKeys(sendvalue);
    }
    public String getElementText(WebDriver driver, String xpathExpression){
        return getWebElement(driver,xpathExpression).getText();
    }
    public void selectItemInDefaultDropdown(WebDriver driver, String xpathExpression, String itemText){
        new Select(getWebElement(driver,xpathExpression)).selectByVisibleText(itemText);
    }
    public void selectItemInDefaultDropdown(WebDriver driver, String xpathExpression, String itemText, String...dynamicValues){
        new Select(getWebElement(driver,xpathExpression, dynamicValues)).selectByVisibleText(itemText);
    }
    public String getSelectedTextInDefaultDropdown(WebDriver driver, String xpathExpression){
        return new Select(getWebElement(driver, xpathExpression)).getFirstSelectedOption().getText();
    }
    public boolean isDefaultDropdownMultiple(WebDriver driver, String xpathExpression){
        return new Select(getWebElement(driver, xpathExpression)).isMultiple();
    }
    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem){
        getWebElement(driver, parentLocator).click();
        sleepInSecond(1);
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemLocator)));
        for (WebElement item : allItems){
            if (item.getText().trim().equals(expectedItem)){
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("argument[0].scrollIntoView(True);", item);
                sleepInSecond(1);
                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }
    public String getElementAttribute(WebDriver driver, String xpathExpression, String attributeName){
        return getWebElement(driver,xpathExpression).getAttribute(attributeName);
    }
    public String getElementCssValue(WebDriver driver, String xpathExpression, String propertyName){
        return getWebElement(driver, xpathExpression).getCssValue(propertyName);
    }
    public String getHexaColorByRgbaColor(String rgbaColor){
        return Color.fromString(rgbaColor).asHex();
    }
    public int getElementsSize(WebDriver driver, String xpathExpression){
        return getWebElements(driver, xpathExpression).size();
    }
    public int getElementsSize(WebDriver driver, String xpathExpression, String... values){
        return getWebElements(driver, xpathExpression, values).size();
    }
    public void checkToRadioOrCheckbox(WebDriver driver, String xpathExpression){
        if (!getWebElement(driver, xpathExpression).isSelected()){
            getWebElement(driver, xpathExpression).click();
        }
    }
    public void checkToRadioOrCheckbox(WebDriver driver, String xpathExpression, String... values){
        if (!getWebElement(driver, xpathExpression, values).isSelected()){
            getWebElement(driver, xpathExpression, values).click();
        }
    }
    public void uncheckToCheckbox(WebDriver driver, String xpathExpression){
        if (getWebElement(driver, xpathExpression).isSelected()){
            getWebElement(driver, xpathExpression).click();
        }
    }
    public boolean isElementDisplayed(WebDriver driver, String xpathExpression){
        return getWebElement(driver, xpathExpression).isDisplayed();
    }
    public boolean isElementDisplayed(WebDriver driver, String xpathExpression, String... values){
        return getWebElement(driver, xpathExpression, values).isDisplayed();
    }
    public void overrideImplicitTimeout (WebDriver driver, long timeOut){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
    }
    public boolean isElementUndisplayed(WebDriver driver, String xpathExpression){
        overrideImplicitTimeout(driver,shortTimeout);
        List<WebElement> elements = getWebElements(driver, xpathExpression);
        overrideImplicitTimeout(driver, longTimeout);
        if (elements.size() == 0){
            return true;
        } else if (elements.size()>0 && !elements.get(0).isDisplayed()){
            return true;
        }else {
            return false;
        }
    }
    public boolean isElementUndisplayed(WebDriver driver, String xpathExpression, String... dynamicValues){
        overrideImplicitTimeout(driver,shortTimeout);
        List<WebElement> elements = getWebElements(driver, xpathExpression, dynamicValues);
        overrideImplicitTimeout(driver, longTimeout);
        if (elements.size() == 0){
            return true;
        } else if (elements.size()>0 && !elements.get(0).isDisplayed()){
            return true;
        }else {
            return false;
        }
    }
    public boolean isElementEnable(WebDriver driver, String xpathExpression){
        return getWebElement(driver, xpathExpression).isEnabled();
    }
    public boolean isElementEnable(WebDriver driver, String xpathExpression, String... values){
        return getWebElement(driver, xpathExpression, values).isEnabled();
    }
    public boolean isElementSelected(WebDriver driver, String xpathExpression){
        return getWebElement(driver, xpathExpression).isSelected();
    }
    public boolean isElementSelected(WebDriver driver, String xpathExpression, String... values){
        return getWebElement(driver, xpathExpression, values).isSelected();
    }
    public void switchToFrame(WebDriver driver, String xpathExpression){
        driver.switchTo().frame(getWebElement(driver,xpathExpression));
    }
    public void switchToDefaultContentPage(WebDriver driver){
        driver.switchTo().defaultContent();
    }
    public void hoverMouseToElement(WebDriver driver, String xpathExpression){
        new Actions(driver).moveToElement(getWebElement(driver,xpathExpression)).perform();
    }
    public void pressKeyToElement(WebDriver driver, String xpathExpression, Keys key){
        new Actions(driver).sendKeys(getWebElement(driver,xpathExpression),key).perform();
    }
    public void pressKeyboardToElement(WebDriver driver, String xpathExpression, Keys key){
        new Actions(driver).sendKeys(getWebElement(driver, xpathExpression)).perform();
    }
    public Object executeForBrowser(WebDriver driver, String javaScript){
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }
    public String getInnterText(WebDriver driver){
        return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
    }
    public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected){
        String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }
    public void scrollToBottomPage(WebDriver driver){
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }
    public void navigateToUrlByJS(WebDriver driver, String url){
        ((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
    }
    public void highlightElement(WebDriver driver, String xpathpression){
        WebElement element = getWebElement(driver, xpathpression);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], argument[2])", element, "style","border: 2x solid red; border-style: dashed;");
        sleepInSecond(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], argument[2])", element, "style", originalStyle);
    }
    public void clickToElementByJS(WebDriver driver, String xpathExpression){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, xpathExpression));
    }
    public void scrollToElement(WebDriver driver, String xpathExpression){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollToView(true);", getWebElement(driver, xpathExpression));
    }
    public void sendkeyToElementByJS(WebDriver driver, String xpathExpression, String value){
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value','" + value + "')",getWebElement(driver, xpathExpression));
    }
    public  void removeAttributeInDOM(WebDriver driver, String xpathExpression, String attributeRemove){
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathExpression));
    }
    public boolean areJQueryAndJSLoadedSucsess(WebDriver driver){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e){
                    return true;
                }
            }
        };
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }
    public boolean isImageLoaded(WebDriver driver, String xpathExpression){
        boolean status = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].natualWidth > 0", getWebElement(driver, xpathExpression));
        return status;
    }
    public void waitForElementVisible(WebDriver driver, String xpathExpression){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(xpathExpression)));
    }
    public void waitForElementVisible(WebDriver driver, String xpathExpression, String... value){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(xpathExpression,value))));
    }
    public void waitForElementClickable(WebDriver driver, String xpathExpression){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(getByLocator(xpathExpression)));
    }
    public void waitForElementClickable(WebDriver driver, String xpathExpression, String... values){
        new WebDriverWait(driver,Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(xpathExpression,values))));
    }
    public void waitForElementInvisible(WebDriver driver, String xpathExpression){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(xpathExpression)));
    }
    public void waitForElementInvisible(WebDriver driver, String xpathExpression, String... values){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(xpathExpression,values))));
    }
    public void waitForAllElementsVisible(WebDriver driver, String xpathExpression){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(xpathExpression)));
    }
    public void waitForAllElementsVisible(WebDriver driver, String xpathExpression, String... values){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(xpathExpression,values))));
    }
    public void waitForAllElementsInvisible(WebDriver driver, String xpathExpression){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfAllElements(getWebElements(driver,xpathExpression)));
    }
    public void waitForAllElementsInvisible(WebDriver driver, String xpathExpression, String... values){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfAllElements(getWebElements(driver, xpathExpression, values)));
    }
    public void openPageAtMyAccountByPageName(WebDriver driver, String... values){
        waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGES_AT_MYACCOUNT_AREA, values);
        clickToElement(driver,BasePageUI.DYNAMIC_PAGES_AT_MYACCOUNT_AREA, values);
    }
    public void uploadMultipleFiles(WebDriver driver, String... fileNames){
        String filePath = GlobalConstants.UPLOADFILE;
        String fullFileName = "";
        for (String file : fileNames){
            fullFileName = fullFileName + filePath + file + "\n";
        }
        fullFileName = fullFileName.trim();
        getWebElement(driver, BasePageUI.UPLOAD_FILE).sendKeys(fullFileName);
    }
    public void inputToTextboxByID(WebDriver driver, String textboxID, String value){
        waitForAllElementsVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID,textboxID);
        sendKeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID,value, textboxID);
    }
    public void clickToButtonByText(WebDriver driver, String buttonText){
        waitForElementClickable(driver, BasePageUI.DYNAMIC_BUTTON_BY_TEXT,buttonText);
        clickToElement(driver, BasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
    }
    public void selectToDropdownByName(WebDriver driver, String dropdownName, String itemValue){
        waitForElementClickable(driver, BasePageUI.DYNAMIC_DROPDOWN_BY_NAME,dropdownName);
        selectItemInDefaultDropdown(driver,BasePageUI.DYNAMIC_DROPDOWN_BY_NAME,itemValue, dropdownName);
    }
    public void clickToRadioButtonByLabel(WebDriver driver, String radioLabelName){
        waitForElementClickable(driver, BasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL,radioLabelName);
        checkToRadioOrCheckbox(driver, BasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL,radioLabelName);
    }
    public void clickToCheckboxByLabel(WebDriver driver, String checkboxLabelName){
        waitForElementClickable(driver, BasePageUI.DYNAMIC_CHECKBOX_BY_LABEL,checkboxLabelName);
        checkToRadioOrCheckbox(driver, BasePageUI.DYNAMIC_CHECKBOX_BY_LABEL,checkboxLabelName);
    }
    private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
    private long longTimeout = GlobalConstants.LONG_TIMEOUT;
    public void sleepInSecond(long timeoutInSecond){
        try {
            Thread.sleep(timeoutInSecond*1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void sleepInMillisecond(long timeoutInMillisecond){
        try {
            Thread.sleep(timeoutInMillisecond);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public UserHomePO openEndUserSite(WebDriver driver, String userUrl) {
        openUrl(driver,userUrl);
        return PageGeneratorManager.getUserHomePage(driver);
    }
}
