package actions;

import core.LoggerSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import pages.SidebarPage;

public class SidebarAction extends AbstractAction {
    SidebarPage sidebarPage;
    private final Logger log = LoggerSingleton.getLogger();

    public SidebarAction(WebDriver driver) {
        super(driver);
        sidebarPage = new SidebarPage(driver);
    }

    public void openLaunchesPage() {
        log.info("Opening Launches page");
        fluentWait.until(ExpectedConditions.visibilityOf(sidebarPage.getLaunchesSidebarBtn()));
        sidebarPage.getLaunchesSidebarBtn().click();
    }

    public boolean isLoginSuccessful() {
        log.info("Waiting for 'Successful login' notification pop-up");
        fluentWait.until(ExpectedConditions.visibilityOf(sidebarPage.getSignInPopUp()));
        if (sidebarPage.getSignInPopUp().isDisplayed()) {
            log.info("Login is successful");
            return true;
        } else {
            return false;
        }
    }
}
