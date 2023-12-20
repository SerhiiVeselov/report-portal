package stepdefs;

import actions.FiltersAction;
import actions.LaunchesAction;
import actions.LoginAction;
import actions.SidebarAction;
import core.ConfigReader;
import core.CucumberDriver;
import core.LoggerSingleton;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

public class FiltersSteps {

    private final Logger log = LoggerSingleton.getLogger();
    private static WebDriver driver;

    @Before
    public static void driverSetUp() {

        driver = CucumberDriver.getDriver();
        System.setProperty("env", "prod");
        String url = ConfigReader.getUrl();
        driver.get(url);
    }

    @After
    public static void closeDriver() {
        driver.quit();
        CucumberDriver.closeDriver();
    }

    SidebarAction sidebarAction = new SidebarAction(driver);
    FiltersAction filtersAction = new FiltersAction(driver);
    LaunchesAction launchesAction = new LaunchesAction(driver);

    public FiltersSteps() {
    }

    @Given("User is logged in")
    public void userIsLoggedIn() {
        driverSetUp();
        log.info("Cucumber Test");
        log.info("Logging into the app");
        LoginAction loginAction = new LoginAction(driver);
        loginAction.doLogin();
    }

    @And("Launches page is opened")
    public void launchesPageIsOpened() {
        sidebarAction.openLaunchesPage();
    }

    @And("Filters section is opened")
    public void filtersSectionIsOpened() {
        log.info("Opening filters section");
        filtersAction.activateFiltering();
    }

    @When("User select Launch Name filter as {}")
    public void selectLaunchNameFilterAs(String launchName) {
        log.info("Selecting Launch Name filter");
        filtersAction.addLaunchNameFilter(launchName);
    }

    @And("User add Failed filter and enter {}")
    public void addFailedFilterAndEnter(String filterInput) throws InterruptedException {
        log.info("Selecting Failed filter and entering filter value");
        filtersAction.addFilterFromDrpDwn("Failed", filterInput);
    }

    @Then("Launches list refreshes and displays {} launches that contains {}")
    public void countLaunchesAmount(int launchesAmountOutput, String launchName) {
        launchesAction.countLaunchesAmount(launchName, launchesAmountOutput);
    }

    @And("User add Skipped filter and enter {}")
    public void userAddSkippedFilterAndEnter(String filterInput) throws InterruptedException {
        log.info("Selecting Skipped filter and entering filter value");
        filtersAction.addFilterFromDrpDwn("Skipped", filterInput);
    }

    @And("User add Product bug filter and enter {}")
    public void userAddProductBugFilterAndEnter(String filterInput) throws InterruptedException {
        log.info("Selecting Product bug filter and entering filter value");
        filtersAction.addFilterFromDrpDwn("Product bug", filterInput);
    }
}
