package ui.tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.DocumentPage;
import ui.pages.LoginPage;
import utils.PDFUtils;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class DownloadPdfUiTest extends BaseUiTest {

    @Test
    @DisplayName("Скачивание файла PDF")
    public void downloadPDF() throws IOException {
        log.info("Процесс скачивание файла PDF");
        open("", LoginPage.class)
                .setLogin(login)
                .setPassword(password)
                .pressButtonWithoutError();

        File file = open("#Document", DocumentPage.class)
                .clickDownloadBtn();
        Assertions.assertTrue(PDFUtils.getText(file).contains("2. Скачивание файла через Selenide"));
        Assertions.assertEquals(9, PDFUtils.getCountPage(file));
    }
}