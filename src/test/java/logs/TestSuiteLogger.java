package logs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static configs.ConfigProvider.TEST_CONFIGURATION;

@Slf4j
public class TestSuiteLogger {

    private static final String SECTION_SEPARATOR = "-".repeat(80);
    private static final String BOX_TOP = "=".repeat(98);
    private static final String BOX_BOTTOM = "=".repeat(98);
    private static final String BOX_MIDDLE = "-".repeat(98);
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void logTestStart(String testName, Object[] parameters) {
        String timestamp = LocalDateTime.now().format(TIME_FORMATTER);
        log.info(SECTION_SEPARATOR);

        if (parameters != null && parameters.length > 0) {
            String params = Arrays.stream(parameters).map(p -> p == null ? "null" : p.toString()).collect(Collectors.joining(", "));
            log.info("[RUN]  {} | {}", timestamp, testName);
            log.info("       Параметры: [{}]", params);
        } else {
            log.info("[RUN]  {} | {}", timestamp, testName);
        }
    }

    public static void logTestSuccess(String testName, long durationMs) {
        String duration = formatDuration(Duration.ofMillis(durationMs));
        String timestamp = LocalDateTime.now().format(TIME_FORMATTER);
        log.info("[OK]   {} | {} | {}", timestamp, testName, duration);
        log.info(SECTION_SEPARATOR);
    }

    public static void logTestFailure(String testName, Throwable throwable) {
        String timestamp = LocalDateTime.now().format(TIME_FORMATTER);
        log.error("");
        log.error("[FAIL] {} | {}", timestamp, testName);
        if (throwable != null) {
            log.error("       Исключение: {}", throwable.getClass().getSimpleName());
            log.error("       Сообщение:  {}", throwable.getMessage());
        }
        log.error(SECTION_SEPARATOR);
    }

    public static void logTestSkipped(String testName, String reason) {
        String timestamp = LocalDateTime.now().format(TIME_FORMATTER);
        log.info("[SKIP] {} | {}", timestamp, testName);
        if (reason != null && !reason.isEmpty()) {
            log.info("       Причина: {}", reason);
        }
        log.info(SECTION_SEPARATOR);
    }

    public static void logTestSuiteStart(ExtensionContext context) {
        String timestamp = LocalDateTime.now().format(DATETIME_FORMATTER);
        String displayName = context.getDisplayName();

        log.info("");
        log.info(BOX_TOP);
        log.info("| НАЧАЛО ТЕСТ-СЬЮТА{}", " ".repeat(80));
        log.info("| Набор тестов : {}", displayName);
        log.info("| Начало       : {}", timestamp);
        log.info(BOX_MIDDLE);

        logEnvironmentDetails(context);

        log.info(BOX_BOTTOM);
        log.info("");
        log.info("[INFO] Выполнение тестов началось...");
    }

    public static void logTestSuiteFinish(String suiteName, Collection<String> passedTests, Collection<String> failedTests, Collection<String> skippedTests) {
        String timestamp = LocalDateTime.now().format(DATETIME_FORMATTER);
        int total = passedTests.size() + failedTests.size() + skippedTests.size();
        double passRate = total > 0 ? (passedTests.size() * 100.0 / total) : 0;

        log.info("");
        log.info(BOX_TOP);
        log.info("| ЗАВЕРШЕНИЕ ТЕСТ-СЬЮТА{}", " ".repeat(76));
        log.info("| Набор тестов : {}", suiteName);
        log.info("| Завершено    : {}", timestamp);
        log.info(BOX_MIDDLE);
        log.info("| РЕЗУЛЬТАТЫ{}", " ".repeat(87));
        log.info("|   Всего     : {}", total);
        log.info("|   Успешно   : {}", passedTests.size());

        if (!failedTests.isEmpty()) {
            log.error("|   Провалено : {}", failedTests.size());
        } else {
            log.info("|   Провалено : {}", failedTests.size());
        }

        log.info("|   Пропущено : {}", skippedTests.size());
        log.info("|   Успешность: {}%", String.format("%.1f", passRate));
        log.info(BOX_MIDDLE);

        if (!failedTests.isEmpty()) {
            log.error("| [FAIL] Проваленные тесты:{}", " ".repeat(72));
            failedTests.stream().sorted().forEach(test -> log.error("|   - {}", test));
        }

        if (!skippedTests.isEmpty()) {
            log.info("| [SKIP] Пропущенные тесты:{}", " ".repeat(72));
            skippedTests.stream().sorted().forEach(test -> log.info("|   - {}", test));
        }

        if (failedTests.isEmpty() && total > 0) {
            log.info("| [OK] Все тесты прошли успешно!{}", " ".repeat(67));
        }

        log.info(BOX_BOTTOM);
    }

    private static void logEnvironmentDetails(ExtensionContext context) {
        if (context.getTags().contains("UI")) {
            String osName = System.getProperty("os.name");
            String osVersion = System.getProperty("os.version");
            String osArch = System.getProperty("os.arch");
            String browserName = TEST_CONFIGURATION.browser();

            log.info("| ОКРУЖЕНИЕ{}", " ".repeat(88));
            log.info("|   ОС          : {} {} ({})", osName, osVersion, osArch);
            log.info("|   Браузер     : {}", browserName);
        }
    }

    private static String formatDuration(Duration duration) {
        long totalSeconds = duration.getSeconds();
        long millis = duration.toMillis() % 1000;

        if (totalSeconds < 1) {
            return String.format("%d ms", millis);
        } else if (totalSeconds < 60) {
            return String.format("%d.%03d s", totalSeconds, millis);
        } else if (totalSeconds < 3600) {
            long minutes = totalSeconds / 60;
            long seconds = totalSeconds % 60;
            return String.format("%d m %d s", minutes, seconds);
        } else {
            long hours = totalSeconds / 3600;
            long minutes = (totalSeconds % 3600) / 60;
            long seconds = totalSeconds % 60;
            return String.format("%d h %d m %d s", hours, minutes, seconds);
        }
    }
}