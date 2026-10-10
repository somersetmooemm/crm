package docs.fakestore.controllers;

import api.enums.ApiEndPoints;
import docs.fakestore.pojo.request.UserPost;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class UserController {

    @Step("Отправка запроса на получение всех юзеров")
    public static ValidatableResponse getAllUsers() {
        return RestAssured.given()
                .get(ApiEndPoints.USERS.getPath())
                .then();
    }

    @Step("Отправка запроса на получение юзера по id ={id}")
    public static ValidatableResponse getSingleUser(int id) {
        return RestAssured.given()
                .pathParam("id", id)
                .get(ApiEndPoints.USER_BY_ID.getPath())
                .then();
    }

    @Step("Отправка запроса на создание нового юзера")
    public static ValidatableResponse postCreateUser(UserPost user) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user)
                .post(ApiEndPoints.USERS.getPath())
                .then();
    }
}
