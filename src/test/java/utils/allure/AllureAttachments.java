package utils.allure;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;

public class AllureAttachments {

    @Attachment(value = "Текущий URL", type = "text/plain")
    public static String attachCurrentUrl() {
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }

    @Attachment(value = "Скриншот страницы", type = "image/png")
    public static byte[] attachScreenshot() {
        return Selenide.screenshot(OutputType.BYTES);
    }

    @Attachment(value = "Исходный код", type = "text/plain")
    public static String attachSourceCode() {
        return WebDriverRunner.getWebDriver().getPageSource();
    }
}
