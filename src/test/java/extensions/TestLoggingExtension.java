package extensions;

import logs.TestSuiteLogger;
import org.junit.jupiter.api.extension.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestLoggingExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, TestWatcher {

    private final List<String> passedTests = new CopyOnWriteArrayList<>();
    private final List<String> failedTests = new CopyOnWriteArrayList<>();
    private final List<String> skippedTests = new CopyOnWriteArrayList<>();
    private final ConcurrentMap<String, Long> startTimes = new ConcurrentHashMap<>();

    @Override
    public void beforeAll(ExtensionContext context) {
        TestSuiteLogger.logTestSuiteStart(context);
    }

    @Override
    public void afterAll(ExtensionContext context) {
        TestSuiteLogger.logTestSuiteFinish(
                context.getDisplayName(),
                passedTests,
                failedTests,
                skippedTests
        );
        startTimes.clear();
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        startTimes.put(context.getUniqueId(), System.nanoTime());
        TestSuiteLogger.logTestStart(context.getDisplayName(), null);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        String testName = context.getDisplayName();
        passedTests.add(testName);
        TestSuiteLogger.logTestSuccess(testName, elapsedMillis(context));
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();
        failedTests.add(testName);
        elapsedMillis(context);
        TestSuiteLogger.logTestFailure(testName, cause);
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        String testName = context.getDisplayName();
        skippedTests.add(testName);
        TestSuiteLogger.logTestSkipped(testName, reason.orElse("не указана"));
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();
        skippedTests.add(testName);
        elapsedMillis(context);
        String reason = cause == null ? "тест прерван" : cause.getMessage();
        TestSuiteLogger.logTestSkipped(testName, reason);
    }

    private long elapsedMillis(ExtensionContext context) {
        Long startedAt = startTimes.remove(context.getUniqueId());
        if (startedAt == null) {
            return 0L;
        }
        return (System.nanoTime() - startedAt) / 1_000_000L;
    }
}
