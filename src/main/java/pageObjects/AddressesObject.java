package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class AddressesObject extends BasePage {
    private WebDriver driver;
    public AddressesObject(WebDriver driver){
        this.driver = driver;
    }
}
