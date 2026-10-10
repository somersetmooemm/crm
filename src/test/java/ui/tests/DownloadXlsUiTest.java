package ui.tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.DocumentPage;
import ui.pages.LoginPage;
import utils.XlsUtils;

import java.io.File;

import static com.codeborne.selenide.Selenide.open;
    @Slf4j
    public class DownloadXlsUiTest extends BaseUiTest {

        @Test
        @DisplayName("Скачивание файла Xls")
        public void downloadXls() {
            log.info("Процесс скачивание файла Xls");
            open("", LoginPage.class)
                    .setLogin(login)
                    .setPassword(password)
                    .pressButtonWithoutError();

            File file = open("#Document", DocumentPage.class)
                    .clickDownloadBtn();
            String text = XlsUtils.getText(file);
            Assertions.assertTrue(text.contains("сюда. Интернет-банк"));

            String firstPage = XlsUtils.getSheetText(file, "Sheet1");
            Assertions.assertTrue(firstPage.contains("Выписка"));

            String thirdRowFirstColumnText = XlsUtils.getCellValue(file, "Sheet1",3,0);
            Assertions.assertTrue(thirdRowFirstColumnText.contains("Период"));

        }
    }