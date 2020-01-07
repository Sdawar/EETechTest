package hotel.booking.acceptance;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features/QuickRegression"},
    plugin = {"pretty",
    "html:target/cucumber-reports/html-reports/QuickRegression.html"})
public class acceptanceTest { }
