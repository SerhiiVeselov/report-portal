package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SidebarPage extends AbstractPage {
    @FindBy(xpath = "//div/p[contains(text(), 'Signed in successfully')]")
    private WebElement signInPopUp;

    @FindBy(xpath = "//a[contains(@href, '/launches')][substring-after(@href, '/launches')='']")
    private WebElement launchesSidebarBtn;

    public SidebarPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSignInPopUp() {
        return signInPopUp;
    }

    public WebElement getLaunchesSidebarBtn() {
        return launchesSidebarBtn;
    }
}
