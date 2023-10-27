package actions;

import core.LoggerSingleton;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import pages.LaunchesPage;
import util.MyFluentWait;

public class LaunchesAction {

    FluentWait<WebDriver> fluentWait;
    LaunchesPage launchesPage;

    private final Logger log = LoggerSingleton.getLogger();

    public LaunchesAction(WebDriver driver) {
        fluentWait = MyFluentWait.create(driver);
        launchesPage = new LaunchesPage(driver);
    }

    public WebElement findFirstLaunchByStatus(String testStatusName) {
        WebElement firstLaunch = null;
        try {
            firstLaunch = launchesPage.getTestCasesCountByStatus(testStatusName);
        } catch (NoSuchElementException e) {
            log.info("No test found with status: " + testStatusName);
        }
        return firstLaunch;
    }

    public void selectTestsCountByStatus(WebDriver driver, String testStatusName) {
        WebElement statusElement = null;

        switch (testStatusName) {
            case "total":
                log.info("Checking launches with Total status");
                statusElement = launchesPage.getTestCasesCountByStatus(testStatusName);
                break;
            case "passed":
                log.info("Checking launches with Passed status");
                statusElement = launchesPage.getTestCasesCountByStatus(testStatusName);
                break;
            case "failed":
                log.info("Checking launches with Failed status");
                statusElement = launchesPage.getTestCasesCountByStatus(testStatusName);
                break;
            case "skipped":
                log.info("Checking launches with Skipped status");
                statusElement = launchesPage.getTestCasesCountByStatus(testStatusName);
                break;
        }
        if (statusElement != null) {
            log.info("Clicking the first launch with " + testStatusName + " tests count");

            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].scrollIntoView({block: 'center'});", statusElement);

            fluentWait.until(ExpectedConditions.visibilityOf(findFirstLaunchByStatus(testStatusName)));
            findFirstLaunchByStatus(testStatusName).click();
            fluentWait.until(ExpectedConditions.visibilityOf(launchesPage.getListViewBtn()));
        }
    }

    public String getStatusesListFromLaunchView() {
        fluentWait.until(ExpectedConditions.visibilityOf(launchesPage.getStatusesFilterList()));
        return launchesPage.getStatusesFilterList().getText();
    }

    public void countLaunchesAmount(String launchName, int launchesAmountOutput) {
        fluentWait.until(ExpectedConditions
                .numberOfElementsToBe(By.xpath("//td[2]//a//span[contains(., '" + launchName + "')]"), launchesAmountOutput));
    }
}
