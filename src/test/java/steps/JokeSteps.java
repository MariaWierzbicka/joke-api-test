package steps;

import base.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import base.BaseSteps;

import java.util.*;

import static io.restassured.RestAssured.*;

public class JokeSteps extends BaseSteps {

    private final TestContext context;

    public JokeSteps(TestContext context) {
        this.context = context;
    }

    @Given("The url is set to get {} random jokes")
    public void theUrlIsSetToGetNumberRandomJokes(String amount) {
        RequestSpecification requestSpecification = given()
                .basePath(JOKES + "/" + RANDOM)
                .pathParam("amount", amount);
        context.setRequestSpec(requestSpecification);
    }

    @When("Request for multiple random jokes is sent")
    public void requestForNumberRandomJokesIsSent() {
        Response response = context.getRequestSpec()
                .when().get("/{amount}");
        context.setResponse(response);
    }

    @Then("Each response item contains correct fields:")
    public void verifyJokeFields(DataTable fieldsTable) {
        SoftAssertions softAssertions = new SoftAssertions();

        List<String> expectedKeys = fieldsTable.asList();
        List<Map<String, Object>> jokesList = getJokesAsList(context.getResponse());

        for (Map<String, Object> joke : jokesList) {
            if (joke.get("id") == null || String.valueOf(joke.get("id")).isEmpty()) {
                softAssertions.fail("Id field is missing");
            } else {
                List<String> jokeKeysList = joke.keySet().stream().toList();
                Set<String> uniqueKeys = new HashSet<>(jokeKeysList);
                String jokeId = getValueByKey(joke, "id");

                softAssertions.assertThat(jokeKeysList.size())
                        .withFailMessage(String.format("Joke with id: %s has  a wrong number of fields or duplicate fields: %s", jokeId, jokeKeysList))
                        .isEqualTo(uniqueKeys.size()).isEqualTo(expectedKeys.size());

                for (Object key : jokeKeysList) {
                    String stringKey = String.valueOf(key);

                    softAssertions.assertThat(expectedKeys)
                            .withFailMessage(String.format("Joke with id: %s has incorrect field: %s", jokeId, stringKey))
                            .contains(stringKey);
                }
            }
        }
        softAssertions.assertAll();
    }

    @And("Values are not empty")
    public void valuesAreNotEmpty() {
        SoftAssertions softAssertions = new SoftAssertions();
        List<Map<String, Object>> jokesList = getJokesAsList(context.getResponse());

        for (Map<String, Object> joke : jokesList) {
            List<Object> jokeValuesList = joke.values().stream().toList();

            for (Object value : jokeValuesList) {

                softAssertions.assertThat(value)
                        .as("Joke field value must contain text")
                        .asString()
                        .isNotBlank();
            }
        }
        softAssertions.assertAll();
    }

    @And("Response contains a correct number {} of items")
    public void verifyNumberOfJokes(int expectedNumber) {
        Assert.assertEquals(getJokesAsList(context.getResponse()).size(), expectedNumber);
    }

    @Given("The url is set to a specific joke id {}")
    public void theUrlIsSetToASpecificJokeIdId(String stringId) {
        String id = stringId.replace("\"", "");
        RequestSpecification requestSpecification = given()
                .basePath(JOKES).pathParam("id", id);
        context.setRequestSpec(requestSpecification);
        context.setCurrentParam(id);
    }

    @When("Request containing joke id is sent")
    public void requestContainingJokeIdIsSent() {
        Response response = context.getRequestSpec().when().get("/{id}");
        context.setResponse(response);
    }

    @Then("Verify correctness of values: {string}, {string}, {string}, {string}")
    public void verifyCorrectnessOfValuesIdTypeSetupPunchline(String id, String type, String setup, String punchline) {
        Response response = context.getResponse();
        SoftAssertions softAssertions = new SoftAssertions();
        Map<String, Object> joke = response.jsonPath().get("");

        String[] keys = {"id", "type", "setup", "punchline"};
        String[] expectedValues = {id, type, setup, punchline};
        String jokeId = context.getCurrentParam();

        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            String expectedValue = expectedValues[i];
            String actualValue = getValueByKey(joke, key);

            softAssertions.assertThat(actualValue)
                    .as("Joke with id: " + jokeId + " has incorrect value in field: " + key)
                    .isEqualTo(expectedValue);
        }
        softAssertions.assertAll();
    }

    @Given("The url is set to a specific joke type {}")
    public void theUrlIsSetToASpecificJokeTypeType(String type) {
        RequestSpecification requestSpecification = given().baseUri(BASE_URI)
                .basePath(JOKES + "/{type}/" + RANDOM)
                .pathParam("type", type);
        context.setRequestSpec(requestSpecification);
        context.setCurrentParam(type);
    }

    @When("Request containing a specific type is sent")
    public void requestContainingASpecificTypeIsSent() {
        RequestSpecification requestSpecification = context.getRequestSpec();
        Response response = requestSpecification.when().get();
        context.setResponse(response);
    }

    @Then("Verify the joke contains correct type value")
    public void verifyTheJokeContainsCorrectTypeValue() {
        Response response = context.getResponse();
        String expectedType = context.getCurrentParam();
        String actualType = response.jsonPath().getString("[0].type");
        String id = response.jsonPath().getString("[0].id");

        Assert.assertEquals(String.format("Joke type doesn't match expected. Joke id: %s", id), expectedType, actualType);

    }

}
