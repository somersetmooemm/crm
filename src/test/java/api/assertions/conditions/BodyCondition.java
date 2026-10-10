package api.assertions.conditions;

import api.assertions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;
import org.hamcrest.Matcher;


@AllArgsConstructor
public class BodyCondition implements Condition {

    private final String jsonPath;
    private final Matcher<?> matcher;

    @Override
    public void check(ValidatableResponse response) {
        response.body(jsonPath, matcher);
    }
}
