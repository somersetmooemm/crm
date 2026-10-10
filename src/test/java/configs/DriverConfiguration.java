package configs;

import com.codeborne.selenide.Configuration;

public class DriverConfiguration {

    public static void setupDriver() {
        Configuration.baseUrl = ConfigProvider.TEST_CONFIGURATION.baseUiUrl();
        Configuration.browser = ConfigProvider.TEST_CONFIGURATION.browser();
        Configuration.browserSize = ConfigProvider.TEST_CONFIGURATION.browserSize();
        Configuration.pageLoadTimeout = ConfigProvider.TEST_CONFIGURATION.pageLoadTimeout();
        Configuration.pageLoadStrategy = ConfigProvider.TEST_CONFIGURATION.pageLoadStrategy();
        Configuration.headless = ConfigProvider.TEST_CONFIGURATION.headless();
        Configuration.screenshots = ConfigProvider.TEST_CONFIGURATION.screenshots();
        Configuration.savePageSource = ConfigProvider.TEST_CONFIGURATION.savePageSource();
        Configuration.timeout = ConfigProvider.TEST_CONFIGURATION.timeout();
        Configuration.downloadsFolder = "target/downloads";
    }
}
