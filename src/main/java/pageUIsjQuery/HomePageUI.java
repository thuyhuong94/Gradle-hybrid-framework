package pageUIsjQuery;

public class HomePageUI {
    public static final String PAGINATION_PAGE_BY_NUMBER = "//a[@class='qgrd-pagination-page-link' and text()='%s']";
    public static final String PAGINATION_PAGE_ACTIVED_BY_NUMBER = "//li[@class='qgrd-pagination-page']/a[@class='qgrd-pagination-page-link active' and text()='%s']";
    public static final String HEADER_TEXTBOX_BY_LABEL = "//div[@class='qgrd-header-text' and text()='%s']/parent::div/following-sibling::input";
    public static final String TOTAL_PAGINATION = "//ul[@class='qgrd-pagination-ul']/li";
    public static final String ALL_ROW_EACH_PAGE = "//tbody/tr";
    public static final String ALL_ROW_EACH_PAGE_BYCOLUMN = "//tbody/tr/td[@data-key='%s']";
}
