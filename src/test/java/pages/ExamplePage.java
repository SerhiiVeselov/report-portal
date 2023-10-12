package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class ExamplePage extends AbstractPage {

    public ExamplePage(WebDriver driver) {super(driver);}

    @FindBy(xpath = "//someXpath")
    private WebElement testElement;
}
