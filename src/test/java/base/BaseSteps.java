package base;

import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class BaseSteps {

    protected static final String BASE_URL = "https://official-joke-api.appspot.com/";
    protected static final String RANDOM_URL = "jokes/random";

    protected static String getNumberOfJokes(int number){
        return String.format("%s%s/%d", BASE_URL, RANDOM_URL, number);
    }

    protected static List<Map<String, Object>> getJokesAsList(Response response){
        return response.jsonPath().getList("$");
    }

    protected static String getValueByKey(Map<String, Object> joke, String keyName){
        return String.valueOf(joke.get(keyName));
    }
}
