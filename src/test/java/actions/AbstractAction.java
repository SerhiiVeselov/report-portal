package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractAction {

    public WebDriver driver;

    public AbstractAction(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
