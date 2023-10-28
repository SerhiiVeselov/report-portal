package actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import util.MyFluentWait;

public class AbstractAction {
    FluentWait<WebDriver> fluentWait;
    JavascriptExecutor executor;
    public AbstractAction(WebDriver driver) {
        this.fluentWait = MyFluentWait.create(driver);
        this.executor = (JavascriptExecutor) driver;
    }


}
