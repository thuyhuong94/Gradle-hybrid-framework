package reportConfig;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import commons.BaseTest;

public class ReportNGListener implements ITestListener {

    @Override
    public void onFinish(ITestContext arg0) {

    }

    @Override
    public void onStart(ITestContext arg0) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            System.setProperty("org.uncommons.reportng.escape-output", "false");

            Object testClass = result.getInstance();
            WebDriver driver = ((BaseTest) testClass).getWebdriver();

            String screenshotPath = captureScreenshot(driver, result.getName());
            Reporter.getCurrentTestResult();

            Reporter.log("<br><a target=\"_blank\" href=\"data:image/png;base64," + screenshotPath + "\">" + "<img src=\"data:image/png;base64," + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
            Reporter.setCurrentTestResult(null);
        } catch (NoSuchSessionException e) {
            e.printStackTrace();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult arg0) {

    }

    @Override
    public void onTestStart(ITestResult arg0) {

    }

    @Override
    public void onTestSuccess(ITestResult arg0) {

    }

    public String captureScreenshot(WebDriver driver, String screenshotName) {
        String screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        return screenshotBase64;
    }
}
