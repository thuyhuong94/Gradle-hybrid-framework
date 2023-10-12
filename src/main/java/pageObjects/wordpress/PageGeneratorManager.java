package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    public static AdminLoginPO getAdminLoginPage(WebDriver driver){
        return new AdminLoginPO(driver);
    }
    public static AdminDashboardPO getAdminDashboardPage(WebDriver driver){
        return new AdminDashboardPO(driver);
    }
    public static AdminPostAddNewPO getAdminPostAddNewPage(WebDriver driver){
        return new AdminPostAddNewPO(driver);
    }
    public static AdminPostCategoriesPO getAdminPostCategoriesPage(WebDriver driver){
        return new AdminPostCategoriesPO(driver);
    }
    public static AdminPostSearchPO getAdminPostSearchPage(WebDriver driver){
        return new AdminPostSearchPO(driver);
    }
    public static AdminPostTagPO getAdminPostTagPage(WebDriver driver){
        return new AdminPostTagPO(driver);
    }
    public static UserHomePO getUserHomePage(WebDriver driver){
        return new UserHomePO(driver);
    }
    public static UserPostDetailsPO getUserPostDetaisPage(WebDriver driver){
        return new UserPostDetailsPO(driver);
    }
}
