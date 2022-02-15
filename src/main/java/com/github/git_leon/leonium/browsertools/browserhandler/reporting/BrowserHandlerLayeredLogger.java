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
import com.github.git_leon.stringutils.StringUtils;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.time.LocalDate;
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
        BrowserWaitLoggerInterface browserWaitLogger = new BrowserWaitLogger(driver, 5);
        BrowserWaitLoggerExtentReporter browserWaitExtentReporter = new BrowserWaitLoggerExtentReporter(browserWaitLogger, reportFilePath, testName);
        BrowserHandler browserHandlerImplementation = new BrowserHandler(driver, new BrowserWaitLoggerDecorator(browserWaitExtentReporter, browserWaitExtentReporter));
        BrowserHandlerLoggerImpl browserHandlerLoggerImpl = new BrowserHandlerLoggerImpl(browserHandlerImplementation);
        this.testName = testName;
        this.reportFilePath = reportFilePath;
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
