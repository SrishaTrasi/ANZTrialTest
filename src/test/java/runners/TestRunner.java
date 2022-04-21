package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features/Loans",
        glue = "StepDefs",
        plugin ={"json:target/cucumber-reports/Cucumber.json",
                "html:target/cucumber-reports/Cucumber.html"}
)

public class TestRunner {
}
