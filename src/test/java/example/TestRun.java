package example;

import io.karatelabs.core.Runner;
import io.karatelabs.core.SuiteResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class TestRun {
    private static final Logger logger = LogManager.getLogger(TestRun.class);
    @Test
    public void testAll() {
        SuiteResult result = Runner.path("classpath:example")
                .outputHtmlReport(true)
                .parallel(1);
    }
}