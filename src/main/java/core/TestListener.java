package core;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private Screenshotter screenshotter;
    private CucumberDriver driver;

    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Test execution is started");
    }

    public void onTestSuccess(ITestResult iTestResult) {
    }

    public void onTestFailure(ITestResult iTestResult) {
        screenshotter = new Screenshotter(driver);
        screenshotter.takeScreenshot();
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }
}
