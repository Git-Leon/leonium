package com.github.git_leon.leonium.extentreporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author leonhunter
 * @created 05/04/2020 - 12:31 AM
 */
public class ExtentTestLoggerFactory {
    private final ExtentReports extentReports;
    private final Map<String, ExtentTestLogger> extentTestLoggerMap;
    private final ExtentHtmlReporter extentHtmlReporter;

    public ExtentTestLoggerFactory(String filePath) {
        this(new ExtentReports(), new ExtentHtmlReporter(filePath), new ConcurrentHashMap<>());
    }

    public ExtentTestLoggerFactory(ExtentReports extentReports, ExtentHtmlReporter extentHtmlReporter) {
        this(extentReports, extentHtmlReporter, new ConcurrentHashMap<>());
    }

    public ExtentTestLoggerFactory(ExtentReports extentReports, ExtentHtmlReporter extentHtmlReporter, Map<String, ExtentTestLogger> extentTestLoggerMap) {
        this.extentReports = extentReports;
        this.extentHtmlReporter = extentHtmlReporter;
        this.extentTestLoggerMap = extentTestLoggerMap;
        extentReports.attachReporter(extentHtmlReporter);
        extentHtmlReporter.start();
    }

    public ExtentHtmlReporter getExtentHtmlReporter() {
        return extentHtmlReporter;
    }

    public ExtentReports getExtentReports() {
        return extentReports;
    }

    public Map<String, ExtentTestLogger> getExtentTestLoggerMap() {
        return extentTestLoggerMap;
    }

    public ExtentTestLogger getExtentTestLogger(String testName) {
        return getExtentTestLogger(testName, "");
    }

    public ExtentTestLogger getExtentTestLogger(String testName, String description) {
        Optional<String> mapKey = getExtentTestLoggerMap()
                .keySet()
                .stream()
                .filter(key -> key.equalsIgnoreCase(testName))
                .findFirst();
        if (mapKey.isPresent()) {
            return getExtentTestLoggerMap().get(mapKey.get());
        } else {
            ExtentTest newTest = getExtentReports().createTest(testName, description);
            ExtentTestLogger extentTestLogger = new ExtentTestLogger(newTest);
            getExtentTestLoggerMap().put(testName, extentTestLogger);
            return extentTestLogger;
        }
    }
}
