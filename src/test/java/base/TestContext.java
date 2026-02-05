package base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestContext {
    private RequestSpecification requestSpec;
    private Response response;
    private String currentJokeId;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public RequestSpecification getRequestSpec() {
        return requestSpec;
    }

    public void setRequestSpec(RequestSpecification requestSpecification) {
        this.requestSpec = requestSpecification;
    }

    public String getCurrentJokeId() {
        return currentJokeId;
    }
    public void setCurrentJokeId(String id) {
        this.currentJokeId = id;
    }
}
