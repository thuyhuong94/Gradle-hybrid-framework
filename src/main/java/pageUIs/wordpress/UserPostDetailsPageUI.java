package pageUIs.wordpress;

public class UserPostDetailsPageUI {
    public static final String POST_TITLE_TEXT = "//article//h1[text()='%s']";
    public static final String POST_DATE = "//article//h1[text()='%s']/following-sibling::div//time[text()='%s']";
    public static final String POST_BODY_TEXT = "//article//h1[text()='%s']/parent::header/following-sibling::div/p[text()='%s']";
    public static final String POST_AUTHOR = "//article//h1[text()='%s']/following-sibling::div//span[@class='author vcard']/a[text()='%s']";
}
