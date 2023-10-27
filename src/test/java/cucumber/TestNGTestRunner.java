package cucumber;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features="src/test/java/cucumber", glue="test.stepDefinitions",
monochrome=true,plugin= {"html:target/cucumber.html"}) 
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
