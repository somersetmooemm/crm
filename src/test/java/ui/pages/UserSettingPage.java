package ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class UserSettingPage {

    private final SelenideElement userName = $("div[data-name='userName']");

    public UserSettingPage() {
        userName.shouldBe(visible);
    }

    @Step("Проверить, что поле 'expectedName' совпадает с {expectedName}")
    public UserSettingPage checkUserName (String expectedName) {
        String actualName = $("div[data-name='userName']").getText().toLowerCase();
        Assertions.assertTrue(actualName.contains(expectedName.toLowerCase()),
                "Имя пользователя не совпадает с %s".formatted(expectedName));
        return this;
    }

    @Step("Проверить, что поле 'Name' совпадает с {expectedName}")
    public UserSettingPage checkName (String expectedName) {
        Assertions.assertTrue($("div[data-name='name']").getText().contains(expectedName),
                "Имя пользователя не совпадает с " + expectedName);
        return this;
    }
}