package tests;

import core.ConfigReader;
import core.DriverParallel;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class CommonConditions {
    WebDriver driver = null;

    public WebDriver getDriver() {
        return driver;
    }
    @BeforeMethod
    public void setUp() {
        driver = new DriverParallel().getDriver();
        System.out.println("Opening the browser");
        System.setProperty("env", "prod");
        String url = ConfigReader.getUrl();
        getDriver().get(url);
    }

    @AfterMethod
    public void closeDriver() {
        getDriver().quit();
    }
}
