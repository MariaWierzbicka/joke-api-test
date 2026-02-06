package base;

import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class BaseSteps {

    protected static final String BASE_URI = "https://official-joke-api.appspot.com/";
    protected static final String RANDOM = "random";
    protected static final String JOKES = "jokes";

    protected static List<Map<String, Object>> getJokesAsList(Response response) {
        return response.jsonPath().getList("$");
    }

    protected static String getValueByKey(Map<String, Object> joke, String keyName) {
        return String.valueOf(joke.get(keyName));
    }
}