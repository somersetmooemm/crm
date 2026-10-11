package configs;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverConfiguration {

    public static void setupDriver() {
        boolean isCi = System.getProperty("os.name").toLowerCase().contains("linux");

        Configuration.baseUrl = isCi ? "http://espocrm/" : ConfigProvider.TEST_CONFIGURATION.baseUiUrl();
        Configuration.browser = ConfigProvider.TEST_CONFIGURATION.browser();
        Configuration.browserSize = ConfigProvider.TEST_CONFIGURATION.browserSize();
        Configuration.pageLoadTimeout = ConfigProvider.TEST_CONFIGURATION.pageLoadTimeout();
        Configuration.pageLoadStrategy = ConfigProvider.TEST_CONFIGURATION.pageLoadStrategy();
        Configuration.headless = ConfigProvider.TEST_CONFIGURATION.headless();
        Configuration.screenshots = ConfigProvider.TEST_CONFIGURATION.screenshots();
        Configuration.savePageSource = ConfigProvider.TEST_CONFIGURATION.savePageSource();
        Configuration.timeout = ConfigProvider.TEST_CONFIGURATION.timeout();
        Configuration.downloadsFolder = "target/downloads";

        if (isCi) {
            configureChromeForCi();
        }
    }

    private static void configureChromeForCi() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--headless=new",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--window-size=1920,1080"
        );
        Configuration.browserCapabilities = options;
    }
}