package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import base.BaseSteps;

import java.util.*;

import static io.restassured.RestAssured.when;

public class JokeSteps extends BaseSteps {

    private Response response;

    @When("Request for {} random jokes is sent")
    public void requestForNumberRandomJokesIsSent(int number) {
        response = when().get(getNumberOfJokes(number));
    }

    @And("Response contains a correct number {} of items")
    public void verifyNumberOfJokes(int expectedNumber) {
        Assert.assertEquals(getJokesAsList(response).size(), expectedNumber);
    }

    @Then("Each response item contains correct fields:")
    public void verifyJokeFields(DataTable fieldsTable) {
        SoftAssertions softAssertions = new SoftAssertions();

        List<String> expectedKeys = fieldsTable.asList();
        List<Map<String, Object>> jokesList = getJokesAsList(response);

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
        List<Map<String, Object>> jokesList = getJokesAsList(response);

        for (Map<String,Object> joke : jokesList){
            List<Object> jokeValuesList = joke.values().stream().toList();

            for (Object value : jokeValuesList){

                softAssertions.assertThat(value)
                        .as("Joke field value must contain text")
                        .asString()
                        .isNotBlank();
            }
        }
        softAssertions.assertAll();
    }
}
