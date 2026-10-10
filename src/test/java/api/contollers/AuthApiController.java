package api.contollers;

import api.assertions.AssertableResponse;
import api.enums.ApiEndPoints;
import api.enums.StatusCode;
import api.request.ApiUserPost;
import api.specification.ApiSpec;
import io.qameta.allure.Step;
import utils.CreateRandomData;

import java.util.List;

import static api.specification.ApiSpec.authAdmin;
import static io.restassured.RestAssured.given;

public class AuthApiController {
    @ru.testit.annotations.Step()
    @Step("Отправка запроса на авторизацию под API юзером")
    public static AssertableResponse getUser() {
        return new AssertableResponse(given()
                .spec(ApiSpec.authApi())
                .get(ApiEndPoints.AUTH_CRM.getPath())
                .then());
    }

    @ru.testit.annotations.Step()
    @Step("Отправка запроса на создание нового API юзера")
    public static String createApiUserAndGetKey() {
        ApiUserPost apiUser = CreateRandomData.getRandomApiUser();

        String roleId = getRoleIdByName("Admin");
        apiUser.setRolesIds(List.of(roleId));

        return given()
                .spec(authAdmin())
                .body(apiUser)
                .post(ApiEndPoints.USERS_CRM.getPath())
                .then()
                .statusCode(StatusCode.OK.getCode())
                .extract().path("apiKey");
    }

    @ru.testit.annotations.Step()
    @Step("Создание роли для API юзера")
    private static String getRoleIdByName(String roleName) {
        String id = given()
                .spec(authAdmin())
                .queryParam("where[0][type]", "equals")
                .queryParam("where[0][attribute]", "name")
                .queryParam("where[0][value]", roleName)
                .get(ApiEndPoints.ROLE_CRM.getPath())
                .then()
                .statusCode(StatusCode.OK.getCode())
                .extract().path("list[0].id");

        if (id == null) {
            throw new IllegalStateException("Роль '" + roleName + "' не найдена");
        }
        return id;
    }
}
