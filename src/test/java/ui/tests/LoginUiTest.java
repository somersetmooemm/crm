package ui.tests;

import com.codeborne.selenide.Selenide;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.LoginPage;
@Slf4j
public class LoginUiTest extends BaseUiTest {

    @Test
    @DisplayName("Проверка заполнения полей")
    public void testLogin() {
        log.info("Процесс проверки логина и пароля");
        Selenide.open("",LoginPage.class)
               .setLogin(login)
               .setPassword(password)
               .pressButtonWithoutError()
               .pressDownToggle().pressUserName().checkName(login);
    }
}