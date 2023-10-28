package tests;

import actions.FiltersAction;
import actions.LaunchesAction;
import actions.LoginAction;
import actions.SidebarAction;
import core.DriverSingleton;
import core.LoggerSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.FiltersPage;

public class CommonConditions {
    WebDriver driver = null;
    public static LoggerSingleton log;
    public static LoginAction loginAction;
    public static SidebarAction sidebarAction;
    public static FiltersAction filtersAction;
    public static FiltersPage filtersPage;
    public static LaunchesAction launchesAction;

    public WebDriver getDriver() {
        return driver;
    }
    @BeforeMethod
    public void setUp() {
        driver = new DriverSingleton().getDriver();
        System.setProperty("env", "prod");
        loginAction = new LoginAction(getDriver());
        sidebarAction = new SidebarAction(getDriver());
        filtersAction = new FiltersAction(getDriver());
        launchesAction = new LaunchesAction(getDriver());
        log = new LoggerSingleton();
    }

    @AfterMethod
    public void stopBrowser() {
        getDriver().quit();
    }
}
