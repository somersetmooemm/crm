package api.contollers;

import api.assertions.AssertableResponse;
import api.enums.ApiEndPoints;
import api.request.UserCrmPost;
import api.specification.ApiSpec;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class CrmUserController {

    @ru.testit.annotations.Step()
    @Step("Отправка запроса на получение всех юзеров")
    public static AssertableResponse getAllUsers() {
        return new AssertableResponse(given()
                .spec(ApiSpec.authAdmin())
                .get(ApiEndPoints.USERS_CRM.getPath())
                .then());
    }

    @ru.testit.annotations.Step()
    @Step("Отправка запроса на создание новых юзеров")
    public static AssertableResponse postNewUsers(UserCrmPost newUser) {
        return new AssertableResponse(given()
                .spec(ApiSpec.authAdmin())
                .body(newUser)
                .post(ApiEndPoints.USERS_CRM.getPath())
                .then());
    }
}

