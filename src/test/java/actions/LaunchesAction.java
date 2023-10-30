package actions;

import core.LoggerSingleton;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import pages.LaunchesPage;

public class LaunchesAction extends AbstractAction {
    LaunchesPage launchesPage;

    private final Logger log = LoggerSingleton.getLogger();

    public LaunchesAction(WebDriver driver) {
        super(driver);
        launchesPage = new LaunchesPage(driver);
    }

    public WebElement getTestCasesCountByStatus(String testStatusName) {
        String testStatusXpath = String.format("//div[contains(@class, '%s')]/div/a", testStatusName);
        WebElement testCasesCountByStatus = launchesPage.driver.findElement(By.xpath(testStatusXpath));
        return testCasesCountByStatus;
    }
    public WebElement findFirstLaunchByStatus(String testStatusName) {
        WebElement firstLaunch = null;
        try {
            firstLaunch = getTestCasesCountByStatus(testStatusName);
        } catch (NoSuchElementException e) {
            log.info("No test found with status: " + testStatusName);
        }
        return firstLaunch;
    }

    public void selectTestsCountByStatus(String testStatusName) {
        /*
        All these lines are doing the same instead let's change them to fluent wait
        WebElement statusElement = null;
        switch (testStatusName) {
            case "total":
                log.info("Checking launches with Total status");
                statusElement = getTestCasesCountByStatus(testStatusName);
                break;
            case "passed":
                log.info("Checking launches with Passed status");
                statusElement = getTestCasesCountByStatus(testStatusName);
                break;
            case "failed":
                log.info("Checking launches with Failed status");
                statusElement = getTestCasesCountByStatus(testStatusName);
                break;
            case "skipped":
                log.info("Checking launches with Skipped status");
                statusElement = getTestCasesCountByStatus(testStatusName);
                break;
        }
        if (statusElement != null) {
        }*/
        String testStatusXpath = String.format("//div[contains(@class, '%s')]/div/a", testStatusName);
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(testStatusXpath)));

        WebElement statusElement = launchesPage.driver.findElement(By.xpath(testStatusXpath));

        log.info("Clicking the first launch with " + testStatusName + " tests count");
        executor.executeScript("arguments[0].scrollIntoView({block: 'center'});", statusElement);
        fluentWait.until(ExpectedConditions.visibilityOf(findFirstLaunchByStatus(testStatusName)));
        findFirstLaunchByStatus(testStatusName).click();
        fluentWait.until(ExpectedConditions.visibilityOf(launchesPage.getListViewBtn()));

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
