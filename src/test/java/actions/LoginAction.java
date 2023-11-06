package actions;

import core.ConfigReader;
import core.LoggerSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import pages.LoginPage;

public class LoginAction extends AbstractAction {
    LoginPage loginPage;

    private final Logger log = LoggerSingleton.getLogger();

    public LoginAction(WebDriver driver) {
        super(driver);
        loginPage = new LoginPage(driver);
    }

    public void doLogin() {
        openTestEnvironment();
        enterUsername();
        enterPassword();
        clickLoginBtn();
    }

    public void openTestEnvironment() {
        log.info("Checking environment under test is opened");
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
}
