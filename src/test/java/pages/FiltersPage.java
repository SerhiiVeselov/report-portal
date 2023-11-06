package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FiltersPage extends AbstractPage {
    @FindBy(xpath = "//div[contains(@class, 'add-filter-button')]")
    private WebElement addFilterBtn;

    @FindBy(xpath = "//div[contains(text(), 'More')]")
    private WebElement moreFiltersBtn;

    //Launch Name filter
    @FindBy(xpath = "//input[@placeholder='Enter name']")
    private WebElement launchNameFilter;

    //Launch Number filter
    @FindBy(xpath = "//span[contains(text(), 'Launch number')]")
    private WebElement filtersDrpDwnLaunchNumber;

    @FindBy(xpath = "//span[contains(text(), 'Launch number')]/following-sibling::div//input[@placeholder='Enter number']")
    private WebElement launchNumberFilter;

    @FindBy(xpath = "//span[contains(text(), '≥')]")
    private WebElement launchNumberConditionsDrpDwn;

    @FindBy(xpath = "(//div[contains(@class, 'fieldFilterEntity__field-filter-entity--PIyev')]//div[text()='Equals'])[2]")
    private WebElement launchNumberEquals;

    //Passed filter
    @FindBy(xpath = "//span[contains(text(), 'Passed')]")
    private WebElement filtersDrpDwnPassed;

    @FindBy(xpath = "//span[contains(text(), 'Passed')]/following-sibling::div//input[@placeholder='Enter quantity']")
    private WebElement passedFilter;

    //Failed filter
    @FindBy(xpath = "//span[contains(text(), 'Failed')]")
    private WebElement filtersDrpDwnFailed;

    @FindBy(xpath = "//span[contains(text(), 'Failed')]/following-sibling::div//input[@placeholder='Enter quantity']")
    private WebElement failedFilter;

    //Skipped filter
    @FindBy(xpath = "//span[contains(text(), 'Skipped')]")
    private WebElement filtersDrpDwnSkipped;

    @FindBy(xpath = "//span[contains(text(), 'Skipped')]/following-sibling::div//input[@placeholder='Enter quantity']")
    private WebElement skippedFilter;

    //Product Bug filter
    @FindBy(xpath = "//span[contains(text(), 'Product bug')]")
    private WebElement filtersDrpDwnProductBug;

    @FindBy(xpath = "//span[contains(text(), 'Product bug')]/following-sibling::div//input[@placeholder='Enter quantity']")
    private WebElement productBugFilter;

    //Loader spinner
    @FindBy(xpath = "//div[contains(@class, 'spinningPreloader')]")
    private WebElement spinnerPreloader;

    public FiltersPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getAddFilterBtn() {
        return addFilterBtn;
    }

    public WebElement getLaunchNameFilter(){
        return launchNameFilter;
    }

    public WebElement getLaunchNumberFilter() {
        return launchNumberFilter;
    }

    public WebElement getLaunchNumberConditionsDrpDwn() {
        return launchNumberConditionsDrpDwn;
    }

    public WebElement getMoreFiltersBtn() {
        return moreFiltersBtn;
    }

    public WebElement getLaunchNumberFilterFromDrpDwn() {
        return filtersDrpDwnLaunchNumber;
    }

    public WebElement getLaunchNumberEquals() {
        return launchNumberEquals;
    }

    public WebElement getPassedFilterFromDrpDwn() {
        return filtersDrpDwnPassed;
    }

    public WebElement getPassedFilter() {
        return passedFilter;
    }

    public WebElement getSpinnerPreloader() {
        return spinnerPreloader;
    }

    public WebElement getFailedFilterFromDrpDwn() {
        return filtersDrpDwnFailed;
    }

    public WebElement getFailedFilter() {
        return failedFilter;
    }

    public WebElement getProductBugFilterFromDrpDwn() {
        return filtersDrpDwnProductBug;
    }

    public WebElement getProductBugFilter() {
        return productBugFilter;
    }

    public WebElement getSkippedFilterFromDrpDwn() {
        return filtersDrpDwnSkipped;
    }

    public WebElement getSkippedFilter() {
        return skippedFilter;
    }
}
