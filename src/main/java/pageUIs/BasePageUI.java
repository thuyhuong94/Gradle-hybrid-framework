package pageUIs;

public class BasePageUI {
    public static final String DYNAMIC_PAGES_AT_MYACCOUNT_AREA = "//div[contains(@class,'block-account-navigation')]//a[text()='%s']";
    public static final String UPLOAD_FILE = "//input[@type='file']";
    public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
    public static final String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
    public static final String DYNAMIC_DROPDOWN_BY_NAME = "xpath=//select[@name='%s']";
    public static final String DYNAMIC_RADIO_BUTTON_BY_LABEL = "xpath=//label[text()='%s']/preceding-sibling::input";
    public static final String DYNAMIC_CHECKBOX_BY_LABEL = "xpath=//label[contains(text(),'%s')]/following-sibling::input";
}
