package users;

import io.karatelabs.core.Runner;
import io.karatelabs.core.SuiteResult;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestRun {
    private static final Logger logger = LogManager.getLogger(TestRun.class);

    @Test
    public void testAll() {
        logger.info("Testler başlatılıyor...");

        SuiteResult result = Runner.path("classpath:users")
                .outputCucumberJson(true)
                .outputHtmlReport(true)
                .parallel(1);

        generateReport(String.valueOf(result.getReportDir()));

        logger.info("Testler tamamlandı. Rapor oluşturuldu: " + result.getReportDir());

        Assert.assertEquals("Başarısız senaryo sayısı sıfır olmalı!", 0, result.getScenarioFailedCount());
    }

    public static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
        List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));

        File reportOutputDirectory = new File("target/cucumber-html-reports");
        Configuration config = new Configuration(reportOutputDirectory, "GoRest API Automation");

        config.addClassifications("Platform", "macOS");
        config.addClassifications("Branch", "main");
        config.addClassifications("Tester", "Hüseyin Akcan");

        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}