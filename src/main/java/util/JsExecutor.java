package util;

import core.DriverParallel;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.codeborne.selenide.SelenideElement;

public class JsExecutor {

	private WebDriver driver;
	private JavascriptExecutor jsExecutor;

	public JsExecutor(WebDriver driver) {
		this.driver = new DriverParallel().getDriver();
		this.jsExecutor = (JavascriptExecutor) driver;
	}

	public void scrollToElement(SelenideElement targetElement) {
		jsExecutor.executeScript("arguments[0].scrollIntoView();", targetElement);
	}

	public void isElementInView(SelenideElement targetElement) {
		boolean isInView = (boolean) jsExecutor.executeScript(
				"return (arguments[0].getBoundingClientRect().top >= 0) && " +
						"(arguments[0].getBoundingClientRect().left >= 0) && " +
						"(arguments[0].getBoundingClientRect().bottom <= (window.innerHeight || document.documentElement.clientHeight)) && " +
						"(arguments[0].getBoundingClientRect().right <= (window.innerWidth || document.documentElement.clientWidth));",
				targetElement);
	}

	public void clickElementUsingJs(SelenideElement targetElement) {
		jsExecutor.executeScript("arguments[0].click();", targetElement);
	}
}
