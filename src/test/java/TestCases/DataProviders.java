package TestCases;

import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;

public class DataProviders {

    @DataProvider(name = "addNewComputer")
    public static Object[][] addNewComputerTestData() {
        Map<String, String> object = new HashMap<>();
        object.put("computerName", "BackbaseComputer");
        object.put("introducedDate", "2008-04-25");
        object.put("discontinuedDate", "2012-04-22");
        object.put("companyName", "IBM");
        return new Object[][]{{object}};
    }
}
