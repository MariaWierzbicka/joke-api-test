package steps;

import io.cucumber.java.en.Given;
import base.BaseSteps;

import static io.restassured.RestAssured.given;

public class CommonSteps extends BaseSteps{

    @Given("Joke API is responsive")
    public void verifyResponseStatus(){
        given().baseUri(BASE_URL)
                .when().get(RANDOM_URL)
                .then().statusCode(200);
    }
}
