package tests;

import actions.FiltersAction;
import actions.LaunchesAction;
import actions.LoginAction;
import actions.SidebarAction;
import core.DriverSingleton;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.FiltersPage;

public class JunitConditions {

    public WebDriver driver;
    public static LoginAction loginAction;
    public static SidebarAction sidebarAction;
    public static FiltersAction filtersAction;
    public static FiltersPage filtersPage;
    public static LaunchesAction launchesAction;

    @BeforeEach
    public void setUp() {
        System.out.println("Opening the browser");

        driver = DriverSingleton.getDriver();

        System.setProperty("env", "prod");

        loginAction = new LoginAction(driver);
        sidebarAction = new SidebarAction(driver);
        filtersAction = new FiltersAction(driver);
        launchesAction = new LaunchesAction(driver);

        filtersPage = new FiltersPage(driver);
    }

    @AfterEach
    public void closeWebDriver() {
        DriverSingleton.closeDriver();
    }
}
