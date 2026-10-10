package ui.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class UsersListPage {

    private final ElementsCollection allUserRows = $$(".list-row");

    @Step("Проверить созданного пользователя")
    public UsersListPage checkUserFromRow(String user) {
        allUserRows.findBy(text(user)).shouldBe(visible);
        return this;
    }
}
