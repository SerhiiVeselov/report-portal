package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @FindBy(xpath = "//input[@name='login']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[contains(text(), 'Login')]")
    private WebElement loginBtn;

    @FindBy(xpath = "//div/p[contains(text(), 'An error occurred')]")
    private WebElement loginErrorPopUp;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getUsernameField() {
        return usernameField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getLoginBtn() {
        return loginBtn;
    }

    public WebElement getLoginErrorPopUp() {
        return loginErrorPopUp;
    }
}
