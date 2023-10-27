package core;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    //Filters
    @DataProvider(name = "Launch name + Launch Number")
    public static Object[][] dataSetOne() {
        return new Object[][] {
                {"Demo", "1", 1},{"Demo", "2", 1},{"Demo", "3", 1},{"Demo", "4", 1},{"Demo", "5", 1}
        };
    }

    @DataProvider(name = "Launch name + Passed")
    public static Object[][] dataSetTwo() {
        return new Object[][] {
                {"Demo", "5", 4},{"Demo", "10", 3},{"Demo", "20", 2},{"Demo", "30", 1},{"Demo", "31", 0}
        };
    }

    @DataProvider(name = "Tests Statuses")
    public static Object[][] testsStatusesDataSet() {
        return new Object[][] {
                {"total", "Passed, Failed, Skipped, Interrupted"},{"passed", "Passed"},{"failed", "Failed, Interrupted"},{"skipped", "Skipped"}
        };
    }
}
