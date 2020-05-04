package com.git_leon.leonium.browsertools.browserhandler.reporting;

import com.git_leon.leonium.DirectoryReference;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandler;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import com.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerImpl;
import com.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerInterface;
import com.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerTimer;
import com.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitLogger;
import com.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitLoggerInterface;
import com.github.git_leon.logging.SimpleLoggerInterface;
import org.openqa.selenium.WebDriver;

/**
 * @author leonhunter
 * @created 05/04/2020 - 10:05 AM
 */
public class BrowserHandlerLayeredLogger implements BrowserHandlerLoggerInterface {
    private final BrowserWaitLoggerInterface browserWaitLogger;
    private final BrowserWaitLoggerExtentReporter browserWaitExtentReporter;
    private final BrowserHandler browserHandlerImplementation;
    private final BrowserHandlerLoggerExtentReporter browserHandlerExtentReporter;
    private final BrowserHandlerLoggerImpl browserHandlerLogger;
    private final BrowserHandlerLoggerTimer browserHandlerTimeLogger;
    private final String directoryName;
    private final String testName;

    public BrowserHandlerLayeredLogger(WebDriver driver, String reportFilePath, String testName) {
        this.directoryName = reportFilePath;
        this.testName = testName;
        this.browserWaitLogger = new BrowserWaitLogger(driver, 15);
        this.browserWaitExtentReporter = new BrowserWaitLoggerExtentReporter(browserWaitLogger, reportFilePath, testName);
        this.browserHandlerImplementation = new BrowserHandler(driver, browserWaitLogger);
        this.browserHandlerExtentReporter = new BrowserHandlerLoggerExtentReporter(browserHandlerImplementation, browserWaitExtentReporter.getExtentTestLoggerFactory(), testName, "");
        this.browserHandlerLogger = new BrowserHandlerLoggerImpl(browserHandlerExtentReporter);
        this.browserHandlerTimeLogger = new BrowserHandlerLoggerTimer(browserHandlerLogger);
    }

    public BrowserHandlerLayeredLogger(WebDriver driver) {
        this(driver, DirectoryReference
                .TARGET_DIRECTORY
                .getFileFromDirectory("Report " + System.nanoTime() + ".html")
                .getAbsolutePath(),
                "test-" + Long.toHexString(System.nanoTime()));
    }

    @Override
    public SimpleLoggerInterface getLogger() {
        return getBrowserHandlerTimeLogger().getLogger();
    }

    @Override
    public BrowserHandlerInterface getBrowserHandlerDecoratee() {
        return getBrowserHandlerTimeLogger();
    }

    @Override
    public void finalize() {
        close();
    }

    @Override
    public void close() {
        browserHandlerExtentReporter.getExtentTestLoggerFactory().flush();
        BrowserHandlerLoggerInterface.super.close();
    }

    public BrowserHandler getBrowserHandlerImplementation() {
        return browserHandlerImplementation;
    }

    public BrowserHandlerLoggerExtentReporter getBrowserHandlerExtentReporter() {
        return browserHandlerExtentReporter;
    }

    public BrowserHandlerLoggerImpl getBrowserHandlerLogger() {
        return browserHandlerLogger;
    }

    public BrowserHandlerLoggerTimer getBrowserHandlerTimeLogger() {
        return browserHandlerTimeLogger;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public String getTestName() {
        return testName;
    }
}
