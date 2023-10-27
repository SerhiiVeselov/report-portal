package tests;

import core.TestDataProvider;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.AssertJUnit.*;

public class FiltersTest extends CommonConditions {

    @Test(priority = 1)
    @Description("Login testing")
    public void loginTest() {
        loginAction.doLogin(driver);
        assertTrue(loginAction.isLoginSuccessful());
    }

    @Test(priority = 2, dataProvider = "Launch name + Launch Number", dataProviderClass = TestDataProvider.class)
    @Description("Add filter: Launch name + Launch number")
    public void setLaunchNameAndLaunchNumber(String launchName, String filterInput, int expectedResult) throws InterruptedException {
        loginAction.doLogin(driver);
        sidebarAction.openLaunchesPage();
        filtersAction.activateFiltering();
        filtersAction.addLaunchNameFilter(launchName);
        filtersAction.addFilterFromDrpDwn("Launch Number", filterInput);
        filtersAction.switchLaunchNumberToEqual();
        Thread.sleep(3000);
        List<WebElement> objectList = driver.findElements(By.xpath("//td[2]//a//span[contains(., '"+launchName+"')]"));
        assertEquals(expectedResult, objectList.size());
    }

    @Test(priority = 3, dataProvider = "Launch name + Passed", dataProviderClass = TestDataProvider.class)
    @Description("Add filter: Launch name + Passed")
    public void setLaunchNameAndPassed(String launchName, String filterInput, int expectedResult) throws InterruptedException {
        loginAction.doLogin(driver);
        sidebarAction.openLaunchesPage();
        filtersAction.activateFiltering();
        filtersAction.addLaunchNameFilter(launchName);
        filtersAction.addFilterFromDrpDwn("Passed", filterInput);
        Thread.sleep(3000);
        List<WebElement> objectList = driver.findElements(By.xpath("//td[2]//a//span[contains(., '"+launchName+"')]"));
        assertEquals(expectedResult, objectList.size());
    }
}
