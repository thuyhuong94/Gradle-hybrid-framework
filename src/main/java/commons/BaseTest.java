package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Random;

public class BaseTest {
    private WebDriver driver;
    protected final Log log;

    @BeforeSuite
    public void initBeforeSuite(){
        deleteAllFileInFolder("allure-result");
    }
    protected BaseTest(){
        log = LogFactory.getLog(getClass());
    }
    @Step("Open WebBrowser {0}")
    protected WebDriver getBrowserDriver(String browserName){
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        switch (browser){
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case CHROME:
                WebDriverManager.chromedriver().driverVersion("117.0.5938.149").setup();
                driver = new ChromeDriver();
                break;
            default:
                throw new RuntimeException("This browser is not support");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        return driver;
    }
    @Step("Open page by Url {1}")
    protected WebDriver getBrowserDriver(String browserName, String urlValue){
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        switch (browser){
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("useAutomationExtension",false);
                options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                driver = new ChromeDriver(options);
                break;
            default:
                throw new RuntimeException("This browser is not support");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.get(urlValue);
        return driver;
    }

    protected void closeBrowserDriver() {
        String cmd = null;
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            //log.info("OS name = " + osName);

            String driverInstanceName = driver.toString().toLowerCase();
            //log.info("Driver instance name = " + driverInstanceName);

            String browserDriverName = null;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("internetexplorer")) {
                browserDriverName = "IEDriverServer";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else if (driverInstanceName.contains("opera")) {
                browserDriverName = "operadriver";
            } else {
                browserDriverName = "safaridriver";
            }

            if (osName.contains("window")) {
                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
            } else {
                cmd = "pkill " + browserDriverName;
            }

            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public WebDriver getWebdriver(){
        return this.driver;
    }
    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
            log.info("---------PASS---------");
        } catch (Throwable e) {
            pass = false;
            log.info("---------FAIL---------");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
            log.info("---------PASS---------");
        } catch (Throwable e) {
            pass = false;
            log.info("---------FAIL---------");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info("---------PASS---------");
        } catch (Throwable e) {
            pass = false;
            log.info("---------FAIL---------");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }
    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + File.separator + folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public int generateFakeNumber (){
        Random rand = new Random();
        return rand.nextInt(99999);
    }
    protected String getCurrentDay() {
        DateTime nowUTC = new DateTime(DateTimeZone.UTC);
        int day = nowUTC.getDayOfMonth();
        if (day < 10) {
            String dayValue = "0" + day;
            return dayValue;
        }
        return day + "";
    }

    protected String getCurrentMonth() {
        DateTime now = new DateTime(DateTimeZone.UTC);
        int month = now.getMonthOfYear();
        if (month < 10) {
            String monthValue = "0" + month;
            return monthValue;
        }
        return month + "";
    }

    protected String getCurrentYear() {
        DateTime now = new DateTime(DateTimeZone.UTC);
        return now.getYear() + "";
    }
    protected String getToday() {
        return getCurrentDay() + "/" + getCurrentMonth() + "/" + getCurrentYear();
    }
}
