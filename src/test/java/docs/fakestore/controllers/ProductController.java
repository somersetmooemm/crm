package docs.fakestore.controllers;

import api.enums.ApiEndPoints;
import docs.fakestore.pojo.request.ProductPost;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class ProductController {

    @Step("Отправка запроса на получение всех продуктов")
    public static ValidatableResponse getAllProduct() {
        return RestAssured.given()
                .get(ApiEndPoints.PRODUCTS.getPath())
                .then();
    }

    @Step("Отправка запроса на создание нового продукта")
    public static ValidatableResponse postCreateProduct(ProductPost newProduct) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(newProduct)
                .post(ApiEndPoints.PRODUCTS.getPath())
                .then();
    }
}
