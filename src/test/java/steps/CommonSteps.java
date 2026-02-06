package steps;

import io.cucumber.java.en.Given;
import base.BaseSteps;
import io.restassured.RestAssured;

public class CommonSteps extends BaseSteps {

    @Given("The Base URI is set")
    public void theBaseURIIsSet() {
        RestAssured.baseURI = BASE_URI;
    }
}
