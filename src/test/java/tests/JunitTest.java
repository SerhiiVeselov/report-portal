package tests;

import actions.FiltersAction;
import actions.LaunchesAction;
import actions.LoginAction;
import actions.SidebarAction;
import core.LoggerSingleton;
import jdk.jfr.Description;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;

import java.util.ArrayList;

import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

public class JunitTest extends JunitConditions {
        private final Logger log = LoggerSingleton.getLogger();

        @ParameterizedTest
        @Execution(CONCURRENT)
        @CsvSource({
                "Demo, 1, 4",
                "Demo, 6, 3",
                "Demo, 8, 3",
                "Demo, 9, 2"
        })
        @Description("Add filter: Launch name + Failed")
        public void setLaunchNameAndFailed(String launchName, String filterInput, int launchesAmountOutput) throws InterruptedException {
            log.info("JUnit Test: Add filter: Launch name + Failed");
            LoginAction loginAction = new LoginAction(getDriver());
            SidebarAction sidebarAction = new SidebarAction(getDriver());
            FiltersAction filtersAction = new FiltersAction(getDriver());
            LaunchesAction launchesAction = new LaunchesAction(getDriver());
            loginAction.doLogin();
            sidebarAction.openLaunchesPage();
            filtersAction.activateFiltering();
            filtersAction.addLaunchNameFilter(launchName);
            filtersAction.addFilterFromDrpDwn("Failed", filterInput);
            launchesAction.countLaunchesAmount(launchName, launchesAmountOutput);
        }

        @ParameterizedTest
        @Execution(CONCURRENT)
        @CsvSource({
                "Demo, 0, 5",
                "Demo, 1, 2",
                "Demo, 2, 1",
                "Demo, 3, 0"
        })
        @Description("Add Launch name + Skipped")
        public void setLaunchNameAndSkipped(String launchName, String filterInput, int launchesAmountOutput) throws InterruptedException {
            log.info("JUnit Test: Add Launch name + Skipped");
            LoginAction loginAction = new LoginAction(getDriver());
            SidebarAction sidebarAction = new SidebarAction(getDriver());
            FiltersAction filtersAction = new FiltersAction(getDriver());
            LaunchesAction launchesAction = new LaunchesAction(getDriver());
            loginAction.doLogin();
            sidebarAction.openLaunchesPage();
            filtersAction.activateFiltering();
            filtersAction.addLaunchNameFilter(launchName);
            filtersAction.addFilterFromDrpDwn("Skipped", filterInput);
            launchesAction.countLaunchesAmount(launchName, launchesAmountOutput);
        }

        @ParameterizedTest
        @Execution(CONCURRENT)
        @CsvSource({
                "Demo, 0, 5",
                "Demo, 1, 3",
                "Demo, 4, 2",
                "Demo, 5, 0"})
        @Description("Add Launch name + Product Bug")
        public void setLaunchNameAndProductBug(String launchName, String filterInput, int launchesAmountOutput) throws InterruptedException {
            log.info("JUnit Test: Add Launch name + Product Bug");
            LoginAction loginAction = new LoginAction(getDriver());
            SidebarAction sidebarAction = new SidebarAction(getDriver());
            FiltersAction filtersAction = new FiltersAction(getDriver());
            LaunchesAction launchesAction = new LaunchesAction(getDriver());
            loginAction.doLogin();
            sidebarAction.openLaunchesPage();
            filtersAction.activateFiltering();
            filtersAction.addLaunchNameFilter(launchName);
            filtersAction.addFilterFromDrpDwn("Product bug", filterInput);
            launchesAction.countLaunchesAmount(launchName, launchesAmountOutput);
        }
}
