package tests;

import core.ConfigReader;
import core.DriverParallel;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import util.GetCurrentTimeToString;
import java.io.File;
import java.io.IOException;

public class JunitConditions {
    WebDriver driver = null;
    public WebDriver getDriver() {
        return driver;
    }
    @BeforeEach
    public void setUp() {
        driver = new DriverParallel().getDriver();
        System.out.println("Opening the browser");
        System.setProperty("env", "prod");
        String url = ConfigReader.getUrl();
        getDriver().get(url);
    }

    @AfterEach
    public void closeWebDriver() {
        getDriver().quit();
    }

    public void saveScreenshot() {
        File screenCapture = ((TakesScreenshot)
                getDriver())
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, new File(
                    ".//target/screenshots/"
                            + getCurrentTimeAsString() + ".png"));
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

    private String getCurrentTimeAsString() {
        return new GetCurrentTimeToString().getCurrentTimeAsString();
    }
}
