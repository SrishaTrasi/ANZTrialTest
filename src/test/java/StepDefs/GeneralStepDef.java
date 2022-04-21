package StepDefs;

import Cucumber.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class GeneralStepDef {

    TestContext testContext;

    public GeneralStepDef(TestContext context){
        testContext = context;
    }

    @Before
    public void beforeScenario() {

    }

    @After
    public void afterScenario(){
        testContext.getDriverManager().closeDriver();
    }
}
