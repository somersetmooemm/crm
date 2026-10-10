package ui.tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.LoginPage;
import ui.pages.MainPage;
import ui.pages.UserPage;
import ui.pages.UsersListPage;

import java.io.File;

import static com.codeborne.selenide.Selenide.open;
import static utils.RandomUtils.FAKER;

@Slf4j
public class CreateUserUiTest extends BaseUiTest {

    private final File file = new File("src/test/resources/777.jpg");
    private final String userName = FAKER.internet().username();
    private final String newPassword = FAKER.internet().password();
    private final String firstName = FAKER.name().firstName();
    private final String lastName = FAKER.name().lastName();
    private final String gender = "Mr.";

    @Test
    @DisplayName("Создание нового пользователя")
    public void CreateNewUser() {
        log.info("Процесс создания нового пользователя");
        open("", LoginPage.class)
                .setLogin(login)
                .setPassword(password)
                .pressButtonWithoutError();

        open("#User/create", UserPage.class)
                .setUserName(userName)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .attachFile(file)
                .setPassword(newPassword)
                .pressSaveBtn();

        open("#User", UsersListPage.class)
                .checkUserFromRow(userName);

        open("", MainPage.class)
                .pressDownToggle()
                .pressLogout();

        open("", LoginPage.class)
                .setLogin(userName)
                .setPassword(newPassword)
                .pressButtonWithoutError()
                .pressDownToggle().pressUserName()
                .checkUserName(userName)
                .checkName("%s %s %s".formatted(gender, firstName, lastName));
    }
}