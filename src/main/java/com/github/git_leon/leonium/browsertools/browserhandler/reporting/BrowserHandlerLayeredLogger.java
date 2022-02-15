package com.github.git_leon.leonium.browsertools.browserhandler.reporting;

import com.github.git_leon.leonium.DirectoryReference;
import com.github.git_leon.leonium.browsertools.browserhandler.BrowserHandler;
import com.github.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerImpl;
import com.github.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitLogger;
import com.github.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitLoggerDecorator;
import com.github.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitLoggerInterface;
import com.github.git_leon.logging.SimpleLoggerInterface;
import org.openqa.selenium.WebDriver;

import java.io.File;

/**
 * @author leonhunter
 * @created 05/04/2020 - 10:05 AM
 * default `BrowserHandlerInterface` leveraged by leonium.browsertools.WebPage
 */
public class BrowserHandlerLayeredLogger implements BrowserHandlerLoggerInterface {
    private final BrowserWaitLoggerInterface browserWaitLogger;
    private final BrowserWaitLoggerExtentReporter browserWaitExtentReporter;
    private final BrowserHandler browserHandlerImplementation;
    private final BrowserHandlerLoggerExtentReporter browserHandlerExtentReporter;
    private final String reportFilePath;
    private final String testName;
    private final BrowserHandlerLoggerImpl browserHandlerLoggerImpl;

    public BrowserHandlerLayeredLogger(WebDriver driver, String reportFilePath, String testName) {
        this.reportFilePath = reportFilePath;
        this.testName = testName;
        this.browserWaitLogger = new BrowserWaitLogger(driver, 5);
        this.browserWaitExtentReporter = new BrowserWaitLoggerExtentReporter(browserWaitLogger, reportFilePath, testName);
        this.browserHandlerImplementation = new BrowserHandler(driver, new BrowserWaitLoggerDecorator(browserWaitExtentReporter, browserWaitExtentReporter));
        this.browserHandlerLoggerImpl = new BrowserHandlerLoggerImpl(browserHandlerImplementation);
        this.browserHandlerExtentReporter = new BrowserHandlerLoggerExtentReporter(
                browserHandlerLoggerImpl,
                browserWaitExtentReporter.getExtentTestLoggerFactory(),
                testName, "");

        getOptions()
                .SCREENSHOT_DIRECTORY
                .setValue(new File(getReportFilePath())
                        .getParentFile()
                        .getAbsolutePath()
                        .concat("/"));
    }

    public BrowserHandlerLayeredLogger(WebDriver driver, String reportFilePath) {
        this(driver, reportFilePath, Long.toHexString(System.nanoTime()));
    }

    public BrowserHandlerLayeredLogger(WebDriver driver) {
        this(driver, DirectoryReference
                .TARGET_DIRECTORY
                .getFileFromDirectory("Report-" + System.nanoTime() + ".html")
                .getAbsolutePath());
    }

    @Override
    public SimpleLoggerInterface getLogger() {
        return getBrowserHandlerLogger().getLogger();
    }

    @Override
    public void finalize() {
        close();
    }

    @Override
    public void close() {
        getBrowserHandlerExtentReporter().getExtentTestLoggerFactory().flush();
        BrowserHandlerLoggerInterface.super.close();
    }

    public String getReportFilePath() {
        return reportFilePath;
    }

    public String getTestName() {
        return testName;
    }

    @Override
    public BrowserHandlerInterface getBrowserHandlerDecoratee() {
        return browserHandlerExtentReporter;
    }

    public BrowserHandlerLoggerExtentReporter getBrowserHandlerExtentReporter() {
        return browserHandlerExtentReporter;
    }

    public BrowserHandlerLoggerInterface getBrowserHandlerLogger() {
        return browserHandlerExtentReporter;
    }
}
