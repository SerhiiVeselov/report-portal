package actions;

import core.LoggerSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import pages.FiltersPage;
import java.util.List;
import static org.testng.Assert.assertEquals;

public class FiltersAction extends AbstractAction {
    FiltersPage filtersPage;
    private final Logger log = LoggerSingleton.getLogger();
    public FiltersAction(WebDriver driver) {
        super(driver);
        filtersPage = new FiltersPage(driver);
    }

    public void activateFiltering() {
        log.info("Activating filters");
        fluentWait.until(ExpectedConditions.visibilityOf(filtersPage.getAddFilterBtn()));
        filtersPage.getAddFilterBtn().click();
    }

    public void addLaunchNameFilter(String launchName) {
        log.info("Adding Launch Name filter");
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Discard')]")));
        fluentWait.until(ExpectedConditions.visibilityOf(filtersPage.getLaunchNameFilter()));
        filtersPage.getLaunchNameFilter().sendKeys(launchName);
    }

    public void addFilterFromDrpDwn(String filterName, String filterInput) throws InterruptedException {
        log.info("Opening filters drop-down list");
        fluentWait.until(ExpectedConditions.elementToBeClickable(filtersPage.getMoreFiltersBtn()));
        filtersPage.getMoreFiltersBtn().click();
        fluentWait.until(ExpectedConditions.visibilityOf(filtersPage.getLaunchNumberFilterFromDrpDwn()));

        WebElement filterElement = null;

        switch (filterName) {
            case "Launch Number":
                log.info("Adding Launch Number filter");
                filtersPage.getLaunchNumberFilterFromDrpDwn().click();
                filterElement = filtersPage.getLaunchNumberFilter();
                break;
            case "Passed":
                log.info("Adding Passed filter");
                filtersPage.getPassedFilterFromDrpDwn().click();
                filterElement = filtersPage.getPassedFilter();
                break;
            case "Failed":
                log.info("Adding Failed filter");
                filtersPage.getFailedFilterFromDrpDwn().click();
                filterElement = filtersPage.getFailedFilter();
                break;
            case "Skipped":
                log.info("Adding Skipped filter");
                filtersPage.getSkippedFilterFromDrpDwn().click();
                filterElement = filtersPage.getSkippedFilter();
                break;
            case "Product bug":
                log.info("Adding Product Bug");
                filtersPage.getProductBugFilterFromDrpDwn().click();
                filterElement = filtersPage.getProductBugFilter();
                break;
            default:
                log.info("Invalid filter name specified");
                break;
        }

        if (filterElement != null) {
            log.info("Filling the filter input field with test data");
            fluentWait.until(ExpectedConditions.visibilityOf(filterElement));
            filterElement.sendKeys(filterInput);
        }
    }

    public void switchLaunchNumberToEqual() {
        log.info("Switching Launch Number filter condition from >= to 'equals'");
        filtersPage.getLaunchNumberConditionsDrpDwn().click();
        fluentWait.until(ExpectedConditions.visibilityOf(filtersPage.getLaunchNumberEquals()));
        filtersPage.getLaunchNumberEquals().click();
    }

    public void countLaunches(WebDriver driver, String launchName, int expectedResult) {
        List<WebElement> objectList = driver.findElements(By.xpath("//td[2]//a//span[contains(., '"+launchName+"')]"));
        assertEquals(expectedResult, objectList.size());
    }
}
