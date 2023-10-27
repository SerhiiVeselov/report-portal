package actions;

import core.ConfigReader;
import core.LoggerSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import pages.SidebarPage;
import pages.LoginPage;
import tests.JunitConditions;
import util.MyFluentWait;

public class LoginAction {

    FluentWait<WebDriver> fluentWait;
    LoginPage loginPage;
    SidebarPage sidebarPage;

    private final Logger log = LoggerSingleton.getLogger();

    public LoginAction(WebDriver driver) {
        loginPage = new LoginPage(driver);
        sidebarPage = new SidebarPage(driver);
        fluentWait = MyFluentWait.create(driver);
    }

    public void doLogin(WebDriver driver) {
        openTestEnvironment(driver);
        enterUsername();
        enterPassword();
        clickLoginBtn();
    }

    public void openTestEnvironment(WebDriver driver) {
        log.info("Opening environment under test");
        String url = ConfigReader.getUrl();
        driver.get(url);
        fluentWait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameField()));
    }

    public void enterUsername() {
        log.info("Entering username");
        String username = ConfigReader.getUsername();
        loginPage.getUsernameField().sendKeys(username);
    }

    public void enterPassword() {
        log.info("Entering password");
        String password = ConfigReader.getPassword();
        loginPage.getPasswordField().sendKeys(password);
    }

    public void clickLoginBtn() {
        log.info("Clicking the Login button");
        loginPage.getLoginBtn().click();
    }

    public boolean isLoginSuccessful() {
        log.info("Waiting for 'Successful login' notification pop-up");
        fluentWait.until(ExpectedConditions.visibilityOf(sidebarPage.getSignInPopUp()));
        if (sidebarPage.getSignInPopUp().isDisplayed()) {
            log.info("Login is successful");
            return true;
        } else {
            return false;
        }
    }
}
