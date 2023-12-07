package tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;
import core.ConfigReader;
import core.LoggerSingleton;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.slf4j.Logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SelenideTest extends JunitConditions {

	private final Logger log = LoggerSingleton.getLogger();

	@Test
	@Description("Verify sorting of launches list by TTL")
	public void sortLaunchesByTtl() {
		log.info("Selenide Test: Verify sorting of launches list by TTL");
		SelenideElement ttlSoringBtn = $(byXpath("//div/span[contains(text(), 'ttl')]"));
		ElementsCollection rows = $$(byXpath("//div[contains(@class, 'launchSuiteGrid__total')]//a[contains(@href, '/launches')]"));

		doLogin();
		openLaunchesPage();

		log.info("Sorting by ascending order");
		ttlSoringBtn.click();
		List<Integer> sortedAscending = getValuesFromCounters(rows);
		assertTrue(isSorted(sortedAscending, Comparator.naturalOrder()));

		log.info("Sorting by descending order");
		ttlSoringBtn.click();
		List<Integer> sortedDescending = getValuesFromCounters(rows);
		assertTrue(isSorted(sortedDescending, Comparator.reverseOrder()));
	}

	@Test
	@Description("Verify sorting of Launches by start date")
	public void sortLaunchesByStartDate() {
		log.info("Selenide Test: Verify sorting of launches list by Start Date");
		SelenideElement startDateSortingBtn = $(byXpath("//div[./span[contains(., 'start time')]]"));
		ElementsCollection rows = $$(byXpath("//div/span[contains(@class, 'absRelTime__absolute')]"));

		doLogin();
		openLaunchesPage();

		log.info("Checking if launches sorted by start date in ascending order");
		List<LocalDateTime> sortedAscending = getDateValuesFromRows(rows);
		assertTrue(isSorted(sortedAscending, Comparator.naturalOrder()));

		startDateSortingBtn.click();
		log.info("Checking if launches sorted by start date in descending order");
		List<LocalDateTime> sortedDescending = getDateValuesFromRows(rows);
		assertTrue(isSorted(sortedDescending, Comparator.reverseOrder()));
	}

	@Test
	@Description("Verify comparing of launches")
	public void compareLaunches() {
		log.info("Selenide Test: Verify comparing of launches");
		ElementsCollection checkBoxes = $$(byXpath("//div[contains(@class, 'checkIcon')]"));
		SelenideElement actionsDrpDwn = $(byXpath("//div[contains(@class, 'ghostMenuButton')][./span[contains(text(), 'Actions')]]"));
		SelenideElement compareBtn = $(byXpath("//div[contains(@class, 'ghostMenuButton')][./span[contains(text(), 'Compare')]]"));
		SelenideElement compareLaunchesTitle = $(byXpath("//div[contains(@class, 'modalHeader')][./span[contains(text(), 'Compare launches')]]"));

		doLogin();
		openLaunchesPage();

		log.info("Activating checkboxes of the two first launches");
		checkBoxes.get(1).click();
		checkBoxes.get(2).click();

		log.info("Selecting Compare option from the Actions drop-down menu");
		actionsDrpDwn.click();
		compareBtn.click();
		assertTrue(compareLaunchesTitle.isDisplayed());
	}

	private void doLogin() {
		String username = ConfigReader.getUsername();
		String password = ConfigReader.getPassword();
		SelenideElement usernameField = $(byXpath("//input[@name='login']"));
		SelenideElement passwordField = $(byXpath("//input[@name='password']"));
		SelenideElement loginBtn = $(byXpath("//button[contains(text(), 'Login')]"));

		log.info("Logging into the Report Portal");
		open("https://reportportal.epam.com/ui/#login");
		usernameField.setValue(username);
		passwordField.setValue(password);
		loginBtn.click();
	}

	private void openLaunchesPage() {
		log.info("Opening Launches page");
		SelenideElement launchesSidebarBtn = $(byXpath("//a[contains(@href, '/launches')][substring-after(@href, '/launches')='']"));
		launchesSidebarBtn.click();
		log.info("Launches page is opened");
	}
	private List<Integer> getValuesFromCounters(ElementsCollection rows) {
		return StreamSupport.stream(rows.spliterator(), false)
				.map(row -> Integer.parseInt(row.text()))
				.collect(Collectors.toList());
	}

	private <T> boolean isSorted(List<T> list, Comparator<T> comparator) {
		for (int i = 1; i < list.size(); i++) {
			if (comparator.compare(list.get(i - 1), list.get(i)) > 0) {
				return false;
			}
		}
		return true;
	}

	private List<LocalDateTime> getDateValuesFromRows(ElementsCollection rows) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return StreamSupport.stream(rows.spliterator(), false)
				.map(row -> row.text().trim())
				.filter(text -> !text.isEmpty())
				.map(text -> LocalDateTime.parse(text, formatter))
				.collect(Collectors.toList());
	}
}
