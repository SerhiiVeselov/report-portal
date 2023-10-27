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

    protected static WebDriver driver;
    public static LoggerSingleton log;
    public static LoginAction loginAction;
    public static SidebarAction sidebarAction;
    public static FiltersAction filtersAction;
    public static FiltersPage filtersPage;
    public static LaunchesAction launchesAction;


    @BeforeMethod
    public static void setUp() {
        driver = DriverSingleton.getDriver();

        System.setProperty("env", "prod");

        loginAction = new LoginAction(driver);
        sidebarAction = new SidebarAction(driver);
        filtersAction = new FiltersAction(driver);
        filtersPage = new FiltersPage(driver);
        launchesAction = new LaunchesAction(driver);

        log = new LoggerSingleton();
    }

    @AfterMethod
    public static void stopBrowser() {
        DriverSingleton.closeDriver();
    }
}
