package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AbstractPage {
    public WebDriver driver;
    public AbstractPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
