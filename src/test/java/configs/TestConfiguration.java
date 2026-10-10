package configs;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:configs/test.properties")
public interface TestConfiguration extends Config {
    String baseUiUrl();

    String browser();

    String browserSize();

    Integer pageLoadTimeout();

    String pageLoadStrategy();

    boolean headless();

    boolean screenshots();

    boolean savePageSource();

    Integer timeout();

    String crmApiUrl();

    String authLogin();

    String authPassword();
}