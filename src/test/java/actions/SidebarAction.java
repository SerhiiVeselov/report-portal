package actions;

import core.LoggerSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import pages.FiltersPage;
import pages.SidebarPage;
import util.MyFluentWait;

public class SidebarAction {

    FluentWait<WebDriver> fluentWait;
    SidebarPage sidebarPage;
    FiltersPage filtersPage;

    private final Logger log = LoggerSingleton.getLogger();

    public SidebarAction(WebDriver driver) {
        sidebarPage = new SidebarPage(driver);
        filtersPage = new FiltersPage(driver);
        fluentWait = MyFluentWait.create(driver);
    }

    public void openLaunchesPage() {
        log.info("Opening Launches page");
        fluentWait.until(ExpectedConditions.visibilityOf(sidebarPage.getLaunchesSidebarBtn()));
        sidebarPage.getLaunchesSidebarBtn().click();
    }
}
