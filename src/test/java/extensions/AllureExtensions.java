package extensions;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import utils.allure.AllureAttachments;

public class AllureExtensions implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        if (WebDriverRunner.hasWebDriverStarted()) {
            AllureAttachments.attachCurrentUrl();
            AllureAttachments.attachScreenshot();
            AllureAttachments.attachSourceCode();
        }
    }

}
