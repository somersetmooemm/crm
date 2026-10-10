package api.tests;

import configs.ConfigProvider;
import extensions.TestLoggingExtension;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.allure.CustomTpl;


@Slf4j
@ExtendWith({TestLoggingExtension.class})
public abstract class BaseCrmTest {

    @BeforeAll
    public static void setup() {
        log.info("Инициализация тестового окружения");
        RestAssured.baseURI = ConfigProvider.TEST_CONFIGURATION.crmApiUrl();
        RestAssured.filters(CustomTpl.customLogFilter().getFilter());
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
