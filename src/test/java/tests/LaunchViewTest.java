package tests;

import actions.LaunchesAction;
import actions.LoginAction;
import actions.SidebarAction;
import core.LoggerSingleton;
import core.TestDataProvider;
import jdk.jfr.Description;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;

public class LaunchViewTest extends CommonConditions {

    private final Logger log = LoggerSingleton.getLogger();

    @Test(priority = 1, dataProvider = "Tests Statuses", dataProviderClass = TestDataProvider.class)
    @Description("Checking if user can open the launch Status view")
    public  void launchStatusView(String testStatusName, String expectedResult) throws InterruptedException {
        log.info("TestNG Test: If user can open the launch Status view");
        LoginAction loginAction = new LoginAction(getDriver());
        SidebarAction sidebarAction = new SidebarAction(getDriver());
        LaunchesAction launchesAction = new LaunchesAction(getDriver());

        loginAction.doLogin();
        sidebarAction.openLaunchesPage();
        Thread.sleep(400);
        launchesAction.selectTestsCountByStatus(testStatusName);
        assertEquals(expectedResult, launchesAction.getStatusesListFromLaunchView());
    }
}
