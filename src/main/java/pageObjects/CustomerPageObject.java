package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class CustomerPageObject extends BasePage {
    private WebDriver driver;
    public CustomerPageObject(WebDriver driver){
        this.driver = driver;
    }
}
