package tests;

import jdk.jfr.Description;
import org.testng.annotations.Test;

public class ReportPortalTest extends CommonConditions {

    @Test(priority = 1)
    @Description("Example of the future test")
    public void newTest() {
        exampleAction.testAction();
    }
}
