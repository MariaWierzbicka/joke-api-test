package steps;

import context.TestContext;
import io.cucumber.java.en.Given;
import base.BaseSteps;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;

public class CommonSteps extends BaseSteps {

    private final TestContext context;

    public CommonSteps(TestContext context) {
        this.context = context;
    }

    @Given("The Base URI is set")
    public void theBaseURIIsSet() {
        RestAssured.baseURI = BASE_URI;
    }

    @Then("Status code should be {int}")
    public void statusCodeIs(int expectedStatus) {
        context.getResponse()
                .then().statusCode(expectedStatus);
    }
}
