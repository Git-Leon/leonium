package com.git_leon.leonium.browsertools.browserhandler.reporting;

import com.git_leon.leonium.DirectoryReference;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandler;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerDecoratorAbstractClass;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerDecoratorInterface;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import com.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerImpl;
import com.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerInterface;
import com.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitLogger;
import com.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitLoggerDecorator;
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
        this.browserHandlerExtentReporter = new BrowserHandlerLoggerExtentReporter(browserHandlerLoggerImpl, browserWaitExtentReporter.getExtentTestLoggerFactory(), testName, "");
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
