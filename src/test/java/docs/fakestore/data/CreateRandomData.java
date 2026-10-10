package docs.fakestore.data;

import api.request.UserCrmPost;
import docs.fakestore.pojo.request.UserPost;

import static utils.RandomUtils.FAKER;

public class CreateRandomData {

    public static UserPost getRandomBodyUserPost() {
        return new UserPost(FAKER.internet().username(), FAKER.internet().emailAddress(),
                FAKER.internet().password());
    }

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
}
