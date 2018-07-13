import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class Listener implements ITestListener {

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    @Attachment
    private String additionalInfoForFailedTest(WebDriver webdriver) {
        if (webdriver instanceof HasCapabilities) {
            Capabilities capabilities = ((HasCapabilities) webdriver).getCapabilities();
            String a = "Browser = " + capabilities.getBrowserName() + " Version = " + capabilities.getVersion() + " Platform = " + capabilities.getPlatform();
            System.out.println(a);
            return a;
        } else
            return null;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        WebDriver dr = ((BaseClass) testClass).getDriver();
        saveScreenshot(((TakesScreenshot) dr).getScreenshotAs(OutputType.BYTES));
        additionalInfoForFailedTest(dr);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }
}
