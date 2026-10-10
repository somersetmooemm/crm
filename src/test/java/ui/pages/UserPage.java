package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class UserPage {

    private final SelenideElement UsersBtn = $x("//a[@class='action']");
    private final SelenideElement UserNameField = $x("//input[@data-name='userName']");
    private final SelenideElement genderDropDownToggle = $x("//div[contains(@class, 'selectize-input')]");
    private final SelenideElement firstNameField = $x("//input[@data-name='firstName']");
    private final SelenideElement lastNameField = $x("//input[@data-name='lastName']");
    private final SelenideElement saveBtn = $x("//button[@data-name='save']");
    private final SelenideElement passwordField = $x("//input[@data-name='password']");
    private final SelenideElement passwordConfirmField = $x("//input[@data-name='passwordConfirm']");
    private final SelenideElement attachFileBtn = $("input[type='file']");
    private final SelenideElement submitBtn = $x("//button[@data-name='crop']");
    private final String genderDropDown = ".option[data-value='%s']";

    public UserPage() {
        UsersBtn.shouldBe(visible);
    }

    @Step("Заполнить поле - userName")
    public UserPage setUserName(String userName) {
        UserNameField.setValue(userName);
        return this;
    }

    @Step("Заполнить поле - Gender")
    public UserPage setGender(String value) {
        genderDropDownToggle.click();
        $(genderDropDown.formatted(value))
                .shouldBe(visible, Duration.ofSeconds(10))
                .click();
        return this;
    }

    @Step("Заполнить поле - First Name")
    public UserPage setFirstName(String firstName) {
        firstNameField.setValue(firstName);
        return this;
    }

    @Step("Заполнить поле - Last Name")
    public UserPage setLastName(String lastName) {
        lastNameField.setValue(lastName);
        return this;
    }

    @Step("Нажать кнопку - Сохранить")
    public UserPage pressSaveBtn() {
        saveBtn.click();
        sleep(1000);
        return this;
    }

    @Step("Заполнить поле - Password")
    public UserPage setPassword(String password) {
        passwordField.setValue(password);
        sleep(1000);
        passwordConfirmField.setValue(password);
        return this;
    }

    @Step("Прикрепить файл")
    public UserPage attachFile(File file) {
        attachFileBtn.should(Condition.enabled).uploadFile(file);
        sleep(1000);
        submitBtn.click();
        return this;
    }
}
