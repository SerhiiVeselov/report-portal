package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LaunchesPage extends AbstractPage {
    public LaunchesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(text(), 'List view')]")
    private WebElement listViewBtn;

    @FindBy(xpath = "(//span[@class='inputDropdown__value--gzJN_'])[2]")
    private WebElement statusesFilterList;

    @FindBy(xpath = "//div[contains(@class, 'itemCounter__item-counter')]")
    private WebElement launchesCounter;

    public WebElement getTestCasesCountByStatus(String testStatusName) {
        String testStatusXpath = String.format("//div[contains(@class, '%s')]/div/a", testStatusName);
        WebElement testCasesCountByStatus = driver.findElement(By.xpath(testStatusXpath));
        return testCasesCountByStatus;
    }

    public WebElement getListViewBtn() {
        return listViewBtn;
    }

    public WebElement getStatusesFilterList() {
        return statusesFilterList;
    }

    public WebElement getLaunchesCounter() {
        return launchesCounter;
    }
}
