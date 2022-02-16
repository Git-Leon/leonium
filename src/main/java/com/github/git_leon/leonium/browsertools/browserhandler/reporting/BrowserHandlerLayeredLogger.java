package com.github.git_leon.leonium.browsertools.browserhandler.reporting;

import com.github.git_leon.leonium.DirectoryReference;
import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandler;
import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerImpl;
import com.github.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerTimer;
import com.github.git_leon.leonium.browsertools.browserhandler.waiting.*;
import com.github.git_leon.logging.SimpleLogger;
import com.github.git_leon.logging.SimpleLoggerInterface;
import com.github.git_leon.logging.SimpleLoggerWarehouse;
import com.github.git_leon.stringutils.StringUtils;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.time.LocalDateTime;

/**
 * @author leonhunter
 * @created 05/04/2020 - 10:05 AM
 * default `BrowserHandlerInterface` leveraged by leonium.browsertools.WebPage
 */
public class BrowserHandlerLayeredLogger implements BrowserHandlerLoggerInterface {
    private final BrowserHandlerLoggerExtentReporter browserHandlerExtentReporter;
    private final String reportFilePath;
    private final String testName;

    public BrowserHandlerLayeredLogger(WebDriver driver, String reportFilePath, String testName) {
        SimpleLogger simpleLogger = SimpleLoggerWarehouse.getLogger(toString());
        BrowserWaitInterface browserWait = new BrowserWait(5, driver);
        BrowserWaitLoggerInterface browserWaitLogger = new BrowserWaitLogger(browserWait, simpleLogger);
        BrowserWaitLoggerExtentReporter browserWaitExtentReporter = new BrowserWaitLoggerExtentReporter(browserWaitLogger, reportFilePath, testName);
        BrowserWaitLoggerDecoratorInterface browserWaitLoggerDecorator = new BrowserWaitLoggerDecorator(browserWaitExtentReporter, browserWaitExtentReporter);

        BrowserHandler browserHandlerImplementation = new BrowserHandler(driver, browserWaitLoggerDecorator);
        BrowserHandlerLoggerInterface browserHandlerLoggerImpl = new BrowserHandlerLoggerImpl(browserHandlerImplementation);
        BrowserHandlerLoggerExtentReporter browserHandlerExtentReporter = new BrowserHandlerLoggerExtentReporter(
                browserHandlerLoggerImpl,
                browserWaitExtentReporter.getExtentTestLoggerFactory(),
                testName, "");
        BrowserHandlerLoggerInterface browserHandlerLoggerTimer = new BrowserHandlerLoggerTimer(browserHandlerExtentReporter);
        this.testName = testName;
        this.reportFilePath = reportFilePath;
        this.browserHandlerExtentReporter =  new BrowserHandlerLoggerExtentReporter(
                browserHandlerLoggerTimer,
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
                .getFileFromDirectory("reports/"
                        .concat(StringUtils.removeCharacters(LocalDateTime.now().toString(), ":_"))
                        .concat("/index.html"))
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
