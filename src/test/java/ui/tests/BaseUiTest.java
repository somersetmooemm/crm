package ui.tests;

import com.codeborne.selenide.Selenide;
import configs.DriverConfiguration;
import extensions.TestLoggingExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

@Slf4j
@ExtendWith({TestLoggingExtension.class})
public abstract class BaseUiTest {


    protected final String login = "Admin";
    protected final String password = "admin";

    @BeforeAll
    public static void setup() {
        log.info("Инициализация тестового окружения");
        DriverConfiguration.setupDriver();
    }

    @AfterAll
    public static void close() {
        log.info("Закрытие тестового окружения");
        Selenide.closeWebDriver();
    }
}

