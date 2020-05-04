package com.git_leon.leonium.extentreporting;

/**
 * @author leonhunter
 * @created 05/04/2020 - 2:18 AM
 */

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author leonhunter
 * @created 05/04/2020 - 12:31 AM
 */
public class ExtentTestLoggerFactory {
    private final ExtentHtmlReporter extentHtmlReporter;
    private final ExtentReports extentReports;
    private final List<ExtentTestLogger> extentTests;

    public ExtentTestLoggerFactory(ExtentReports extentReports, ExtentHtmlReporter extentHtmlReporter, List<ExtentTestLogger> extentTests) {
        this.extentReports = extentReports;
        this.extentHtmlReporter = extentHtmlReporter;
        this.extentTests = extentTests;
        extentReports.attachReporter(extentHtmlReporter);
        extentHtmlReporter.start();
    }

    public ExtentTestLoggerFactory(String filePath) {
        this(new ExtentReports(), new ExtentHtmlReporter(filePath), new ArrayList<>());
    }

    public ExtentReports getExtentReports() {
        return extentReports;
    }

    public ExtentHtmlReporter getExtentHtmlReporter() {
        return extentHtmlReporter;
    }

    public List<ExtentTestLogger> getExtentTestLoggers() {
        return extentTests;
    }

    public ExtentTestLogger createExtentTestLogger(String testName, String description) {
        return getExtentTestLogger(testName).orElse(new ExtentTestLogger(extentReports.createTest(testName, description)));
    }

    public Optional<ExtentTestLogger> getExtentTestLogger(String testName) {
        return getExtentTestLoggers()
                .stream()
                .filter(extentTest -> extentTest
                        .getExtentTest()
                        .getModel()
                        .getName()
                        .equals(testName))
                .findFirst();
    }

    public void flush() {
        extentHtmlReporter.flush();
        extentReports.flush();
    }
}
