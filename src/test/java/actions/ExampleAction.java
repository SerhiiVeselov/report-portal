package actions;

import core.LoggerSingleton;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

@Getter
public class ExampleAction extends AbstractAction {

    private static final Logger log = LoggerSingleton.getLogger();

    public ExampleAction(WebDriver driver) {super(driver);}

    public void testAction() {
        log.info("Test logger info");
        log.info("Some new info");
    }
}
