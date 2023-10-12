package commons;

import java.io.File;

public class GlobalConstants {
    public static final String PORTAL_DEV_URL = "https://demo.nopcommerce.com/";
    public static final String ADMIN_DEV_URL = "https://admin-demo.nopcommerce.com";
    public static final String PORTAL_TESTING_URL = "https://demo.nopcommerce.com/";
    public static final String ADMIN_TESTING_URL = "https://admin-demo.nopcommerce.com/";
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String UPLOADFILE = PROJECT_PATH  + File.separator + "uploadFiles" + File.separator;
    public static final String DOWNLOADFILE = PROJECT_PATH  + File.separator + "downloadFiles";
    public static final String BROWSER_LOG = PROJECT_PATH  + File.separator + "browserLogs";
    public static final String DRAG_DROP_HTML5 = PROJECT_PATH  + File.separator + "dragDropHTML5";
    public static final String DB_DEV_URL = "32.18.252.185:9860";
    public static final String DB_DEV_USER = "AUTOMATION";
    public static final String DB_DEV_PASS = "Password!1";
    public static final String DB_TEST_URL = "32.18.195.23:9860";
    public static final String DB_TEST_USER = "AUTOMATION";
    public static final String DB_TEST_PASS = "Password!1";
    public static final long SHORT_TIMEOUT = 5;
    public static final long LONG_TIMEOUT = 30;
    public static final long RETRY_TEST_FAIL = 3;
    public static final String JAVA_VERSION = System.getProperty("java.version");
}
