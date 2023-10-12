package tests;

import actions.ExampleAction;
import core.ConfigReader;
import org.testng.annotations.Parameters;
import pages.ExamplePage;
import core.DriverSingleton;
import core.TestListener;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.time.Duration;

@Listeners({TestListener.class})
public class CommonConditions {

    protected static WebDriver driver;
    public static FluentWait<WebDriver> fluentWait;
    public static ExamplePage examplePage;
    public static ExampleAction exampleAction;
    private static String url;
    private static String username;
    private static String password;

    @BeforeSuite
    @Parameters("environment")
    public static void setUp(){
        driver = DriverSingleton.getDriver();

        examplePage = new ExamplePage(driver);

        exampleAction = new ExampleAction(driver);

        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
    }

    @AfterSuite
    public static void stopBrowser() {
        DriverSingleton.closeDriver();
        System.out.println("Closing the driver");
    }

}
