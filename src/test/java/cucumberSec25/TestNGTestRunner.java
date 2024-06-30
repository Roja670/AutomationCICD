package cucumberSec25;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumberSec25",glue="neevsystems.stepDefinationSec25",
			monochrome=true,tags= "@Regression",plugin= {"html:target/cucumber.html"})
	public class TestNGTestRunner extends AbstractTestNGCucumberTests{
}

		
	

