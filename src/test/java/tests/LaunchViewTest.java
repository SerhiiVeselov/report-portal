package tests;

import core.TestDataProvider;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;

public class LaunchViewTest extends CommonConditions {

    @Test(priority = 1, dataProvider = "Tests Statuses", dataProviderClass = TestDataProvider.class)
    @Description("Checking if user can open the launch Status view")
    public  void launchStatusView(String testStatusName, String expectedResult) throws InterruptedException {
        loginAction.doLogin(driver);
        sidebarAction.openLaunchesPage();
        Thread.sleep(400);
        launchesAction.selectTestsCountByStatus(driver, testStatusName);
        assertEquals(expectedResult, launchesAction.getStatusesListFromLaunchView());
    }
}
