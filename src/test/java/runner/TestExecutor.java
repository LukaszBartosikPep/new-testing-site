package runner;

import com.automatedtest.poc.config.SeleniumDriver;
import com.automatedtest.poc.runner.standalone.StandAloneReporter;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

import static com.automatedtest.core.logger.BFLogger.logDebug;


@RunWith(CustomTestRunner.class)
@CucumberOptions(features = {"src/test/java/features"},
        glue = {"com/automatedtest"},
        plugin = {"json:target/cucumber.json"},
//        name = {"ST-EOD-KPN-002"}, // here you can run a single getCurrentPageFromURL
        tags = {"@SU_One_Item"}, //here you can ran test by tags
        monochrome = true)
//Run this class with the proper environment
public class TestExecutor {

    public static void setUp() {
        logDebug("Before Suite setUp is running");
    }

    public static void tearDown() {
        SeleniumDriver.closeDriver();
        StandAloneReporter.createReport();
    }
}