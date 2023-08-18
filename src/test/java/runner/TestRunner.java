package runner;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import actions.BrowserActions;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/features",
    glue = {"stepdefinitions"},
    plugin = {"json:Execution_Report/cucumber_report.json"}
)

public class TestRunner {
    @AfterClass
    public static void showReport(){
        try {
            BrowserActions.closeBrowser();
            String[] cmd = {"cmd.exe", "/c", " node src/test/resources/book_stay-report.js"};
            Runtime.getRuntime().exec(cmd);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
