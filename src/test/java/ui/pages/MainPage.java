package ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    private final SelenideElement dropDownToggle = $("#nav-menu-dropdown");
    private final SelenideElement currentUserName =  $x("//ul[@class ='dropdown-menu']//a[contains(@href, 'User/')]");
    private final SelenideElement logoutBtn = $x ("//a[@data-name = 'logout']");

    public MainPage() {
        dropDownToggle.shouldBe(visible);
    }

    @Step("Нажать кнопку  - 'Выпадающий список 3 точки'")
    public MainPage pressDownToggle() {
        dropDownToggle.click();
        return this;
    }

    @Step ("Нажать кнопку -'Текущий пользователь'")
    public UserSettingPage pressUserName() {
        currentUserName.click();
        return Selenide.page(UserSettingPage.class);
    }

    @Step ("Нажать кнопку -'Log out'")
    public LoginPage pressLogout() {
        logoutBtn.click();
        return Selenide.page(LoginPage.class);
    }
}
