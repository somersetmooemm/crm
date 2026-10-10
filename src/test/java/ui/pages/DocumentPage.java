package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class    DocumentPage {

    private final SelenideElement downloadBtn = $x("//td[@data-name='file']//a[@href]");
    private final SelenideElement createDocumentBtn = $x ("//a[@data-name = 'quickCreate']");
    private final SelenideElement attachFileBtn  = $("input[type='file']");
    private final SelenideElement saveBtn = $x("//button[@data-name = 'save']");
    private final SelenideElement nameImp = $("input[data-name='name']");

    @Step ("Скачиваем документ")
    public File clickDownloadBtn() {
        File file =  downloadBtn.should(Condition.enabled).download();
        Assertions.assertNotNull(file, "Файл не скачался");
        Assertions.assertTrue(file.length() > 0, "Файл пустой");
        return file;
    }

    @Step ("Нажать на кнопку - Create Document")
    public DocumentPage clickCreateDocumentBtn() {
        createDocumentBtn.click();
        return this;
    }

    @Step ("Прикрепить файл")
    public DocumentPage attachFile(File file) {
        attachFileBtn.should(Condition.enabled).uploadFile(file);
       nameImp.should(Condition.value(file.getName()));
        return this;
    }


    @Step ("Нажать на кнопку - Save")
    public DocumentPage clickSaveBtn()  {
        saveBtn.click();
        return this;
    }
}
