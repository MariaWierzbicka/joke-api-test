package base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestContext {
    private RequestSpecification requestSpec;
    private Response response;
    private String currentParam;

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

    public String getCurrentParam() {
        return currentParam;
    }

    public void setCurrentParam(String param) {
        this.currentParam = param;
    }
}
