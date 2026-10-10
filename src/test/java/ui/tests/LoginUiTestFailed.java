package ui.tests;

import com.codeborne.selenide.Selenide;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.LoginPage;

@Slf4j
public class LoginUiTestFailed extends BaseUiTest {

    private final String loginWithError = "admin123";
    private final String passwordWithError = "admin123";

    @Test
    @DisplayName("Ошибка при входе - Логин и пароль неверные")
    public void testLoginWithError() {
        log.info("Процесс проверки логина и пароля");
        Selenide.open("", LoginPage.class)
                .setLogin(loginWithError)
                .setPassword(passwordWithError)
                .pressButtonWithError()
                .appearanceAlert();

    }
}
