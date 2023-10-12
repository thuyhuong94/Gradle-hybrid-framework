package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.*;

public class PageGeneratorManager {
    public static HomePageObject getHomePage(WebDriver driver){
        return new HomePageObject(driver);
    }
    public static RegisterPageObject getRegisterPage(WebDriver driver){
        return new RegisterPageObject(driver);
    }
    public static LogInPageObject getLoginPage(WebDriver driver){
        return new LogInPageObject(driver);
    }
    public static CustomerPageObject getCustomerInfoPage(WebDriver driver){
        return new CustomerPageObject(driver);
    }
    public static AddressesObject getAddressesPage(WebDriver driver){
        return new AddressesObject(driver);
    }
    public static RewardPointObject getRewardPointObject(WebDriver driver){
        return new RewardPointObject(driver);
    }
}
