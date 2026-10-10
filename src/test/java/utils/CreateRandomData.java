package utils;

import api.request.ApiUserPost;
import api.request.UserCrmPost;

import java.util.List;

import static utils.RandomUtils.FAKER;

public class CreateRandomData {

    public static UserCrmPost getRandomCrmUser() {
        return UserCrmPost.builder()
                .userName(FAKER.internet().username())
                .firstName(FAKER.name().firstName())
                .lastName(FAKER.name().lastName())
                .salutationName("Mr.")
                .type("regular")
                .isActive(true)
                .build();
    }

    public static ApiUserPost getRandomApiUser() {
        return ApiUserPost.builder()
                .userName(FAKER.internet().username())
                .type("api")
                .authMethod("ApiKey")
                .isActive(true)
                .rolesIds(List.of())
                .build();
    }
}
