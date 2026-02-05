package steps;

import base.TestContext;
import io.cucumber.java.en.Given;
import base.BaseSteps;
import io.restassured.RestAssured;

public class CommonSteps extends BaseSteps {

    private TestContext context;

    public CommonSteps(TestContext context) {
        this.context = context;
    }

    @Given("The Base URI is set")
    public void theBaseURIIsSet() {
        RestAssured.baseURI = BASE_URI;
    }
}
