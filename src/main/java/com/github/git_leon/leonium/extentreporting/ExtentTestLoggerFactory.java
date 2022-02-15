package com.github.git_leon.leonium.extentreporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author leonhunter
 * @created 05/04/2020 - 12:31 AM
 */
public class ExtentTestLoggerFactory {
    private final ExtentHtmlReporter extentHtmlReporter;
    private final ExtentReports extentReports;
    private final Map<String, ExtentTestLogger> extentTestLoggerMap;

    public ExtentTestLoggerFactory(ExtentReports extentReports, ExtentHtmlReporter extentHtmlReporter, Map<String, ExtentTestLogger> extentTestLoggerMap) {
        this.extentReports = extentReports;
        this.extentHtmlReporter = extentHtmlReporter;
        this.extentTestLoggerMap = extentTestLoggerMap;
        extentReports.attachReporter(extentHtmlReporter);
        extentHtmlReporter.start();
    }

    public ExtentTestLoggerFactory(String filePath) {
        this(new ExtentReports(), new ExtentHtmlReporter(filePath), new ConcurrentHashMap<>());
        System.out.println("extenthtmlreporter = " + filePath);
    }

    public ExtentReports getExtentReports() {
        return extentReports;
    }

    public ExtentHtmlReporter getExtentHtmlReporter() {
        return extentHtmlReporter;
    }

    public Map<String, ExtentTestLogger> getExtentTestLoggerMap() {
        return extentTestLoggerMap;
    }

    public ExtentTestLogger getExtentTestLogger(String testName) {
        return getExtentTestLogger(testName, "");
    }

    public ExtentTestLogger getExtentTestLogger(String testName, String description) {
        Optional<String> mapKey = getExtentTestLoggerMap()
                .entrySet()
                .stream()
                .map(Map.Entry::getKey)
                .filter(key -> key.equalsIgnoreCase(testName))
                .findFirst();
        if (mapKey.isPresent()) {
            return getExtentTestLoggerMap().get(mapKey.get());
        } else {
            ExtentTestLogger extentTestLogger = new ExtentTestLogger(getExtentReports(), testName, description);
            getExtentTestLoggerMap().put(testName, extentTestLogger);
            return extentTestLogger;
        }
    }

    public void flush() {
        getExtentHtmlReporter().flush();
        getExtentReports().flush();
    }
}
