package pageUIs.wordpress;

public class UserHomePageUI {
    public static final String POST_TITLE_TEXT = "//article//h2/a[text()='%s']";
    public static final String POST_DATE = "//article//a[text()='%s']/parent::h2/following-sibling::div//time[text()='%s']";
    public static final String POST_BODY_TEXT = "//article//a[text()='%s']/ancestor::header/following-sibling::div/p[text()='%s']";
    public static final String POST_AUTHOR = "//article//a[text()='%s']/parent::h2/following-sibling::div//span[@class='author vcard']/a[text()='%s']";
}
