package api.specification;

import configs.ConfigProvider;
import db.CrmDbClient;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiSpec {

    public static RequestSpecification authApi() {
        return new RequestSpecBuilder()
                .addHeader("X-Api-Key", CrmDbClient.getApiKeyFromDb().getApiKey())
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification authAdmin() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAuth(RestAssured.preemptive().basic(
                        ConfigProvider.TEST_CONFIGURATION.authLogin(),
                        ConfigProvider.TEST_CONFIGURATION.authPassword()))
                .build();
    }
}
