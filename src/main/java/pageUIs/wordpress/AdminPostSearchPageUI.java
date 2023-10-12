package pageUIs.wordpress;

public class AdminPostSearchPageUI {
    public static final String ADD_NEW_BUTTON = "css=.page-title-action";
    public static final String SEARCH_POST_TEXTBOX = "id=post-search-input";
    public static final String SEARCH_POST_BUTTON = "id=search-submit";
    public static final String TABLE_HEADER_INDEX_BY_HEADER_NAME = "//table[contains(@class,'table-view-list posts')]/thead//th[@id='%s']/preceding-sibling::*";
    public static final String TABLE_ROW_VALUE_BY_HEADER_INDEX = "//table[contains(@class,'table-view-list posts')]/tbody/tr/*[%s]//a[text()='%s']";
}
