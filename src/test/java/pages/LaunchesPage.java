package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LaunchesPage extends AbstractPage {
    @FindBy(xpath = "//a[contains(text(), 'List view')]")
    private WebElement listViewBtn;

    @FindBy(xpath = "(//span[@class='inputDropdown__value--gzJN_'])[2]")
    private WebElement statusesFilterList;

    @FindBy(xpath = "//div[contains(@class, 'itemCounter__item-counter')]")
    private WebElement launchesCounter;

    public LaunchesPage(WebDriver driver) {
        super(driver);
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
