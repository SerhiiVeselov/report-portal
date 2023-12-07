package util;

import com.codeborne.selenide.SelenideElement;
import core.CucumberDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsUtil {

	private WebDriver driver;

	Actions actions = new Actions((WebDriver) driver);

	public ActionsUtil(WebDriver driver) {
		this.driver = driver;
	}

	public void dragAndDrop(SelenideElement sourceElement, SelenideElement targetElement) {
		actions.dragAndDrop(sourceElement, targetElement);
	}

	public  void resizeElement(SelenideElement elementForResize, int xOffset, int yOffset) {
		actions.dragAndDropBy(elementForResize, xOffset, yOffset);
	}

}
