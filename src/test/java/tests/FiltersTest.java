package tests;

import actions.FiltersAction;
import actions.LoginAction;
import actions.SidebarAction;
import core.LoggerSingleton;
import core.TestDataProvider;
import jdk.jfr.Description;
import org.slf4j.Logger;
import org.testng.annotations.Test;

public class FiltersTest extends CommonConditions {

    private final Logger log = LoggerSingleton.getLogger();

    @Test(priority = 1)
    @Description("Login testing")
    public void loginTest() {
        log.info("TestNG Test - Login test");
        LoginAction loginAction = new LoginAction(getDriver());
        SidebarAction sidebarAction = new SidebarAction(getDriver());

        loginAction.doLogin();
        sidebarAction.isLoginSuccessful();
    }

    @Test(priority = 2, dataProvider = "Launch name + Launch Number", dataProviderClass = TestDataProvider.class)
    @Description("Add filter: Launch name + Launch number")
    public void setLaunchNameAndLaunchNumber(String launchName, String filterInput, int expectedResult) throws InterruptedException {
        log.info("TestNG Test - Add filter: Launch name + Launch number");
        LoginAction loginAction = new LoginAction(getDriver());
        SidebarAction sidebarAction = new SidebarAction(getDriver());
        FiltersAction filtersAction = new FiltersAction(getDriver());

        loginAction.doLogin();
        sidebarAction.openLaunchesPage();
        filtersAction.activateFiltering();
        filtersAction.addLaunchNameFilter(launchName);
        filtersAction.addFilterFromDrpDwn("Launch Number", filterInput);
        filtersAction.switchLaunchNumberToEqual();
        Thread.sleep(3000);
        filtersAction.countLaunches(driver, launchName, expectedResult);
    }

    @Test(priority = 3, dataProvider = "Launch name + Passed", dataProviderClass = TestDataProvider.class)
    @Description("Add filter: Launch name + Passed")
    public void setLaunchNameAndPassed(String launchName, String filterInput, int expectedResult) throws InterruptedException {
        log.info("TestNG Test - Add filter: Launch name + Passed");
        LoginAction loginAction = new LoginAction(getDriver());
        SidebarAction sidebarAction = new SidebarAction(getDriver());
        FiltersAction filtersAction = new FiltersAction(getDriver());

        loginAction.doLogin();
        sidebarAction.openLaunchesPage();
        filtersAction.activateFiltering();
        filtersAction.addLaunchNameFilter(launchName);
        filtersAction.addFilterFromDrpDwn("Passed", filterInput);
        Thread.sleep(3000);
        filtersAction.countLaunches(driver, launchName, expectedResult);
    }
}
