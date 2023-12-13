package core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import util.GetCurrentTimeToString;
import java.io.File;
import java.io.IOException;

public class Screenshotter {

	private CucumberDriver driver;

	public Screenshotter(CucumberDriver driver) {
		this.driver = driver;
	}

    /* this is part of test abstract class because it uses the driver to take the screenshot*/
//    public void saveScreenshot() {
//        File screenCapture = ((TakesScreenshot) CucumberDriver
//                .getDriver())
//                .getScreenshotAs(OutputType.FILE);
//        try {
//            FileUtils.copyFile(screenCapture, new File(
//                    ".//target/screenshots/"
//                            + getCurrentTimeAsString() + ".png"));
//        } catch (IOException e) {
//            System.out.println("Failed to save screenshot: " + e.getLocalizedMessage());
//        }
//    }

	public static void takeScreenshot() {
		TakesScreenshot takesScreenshot = (TakesScreenshot) CucumberDriver.getDriver();
		File screenshotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(screenshotFile, new File(
					".//target/screenshots/"
							+ getCurrentTimeAsString() + ".png"));
		} catch (IOException e) {
			System.out.println("Failed to save screenshot: " + e.getLocalizedMessage());
		}
	}


    private static String getCurrentTimeAsString() {
        return new GetCurrentTimeToString().getCurrentTimeAsString();
    }
}
