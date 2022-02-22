package com.github.git_leon.leonium.browsertools.browserhandler.reporting;

import com.github.git_leon.leonium.DirectoryReference;
import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandler;
import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerImpl;
import com.github.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerTimer;
import com.github.git_leon.leonium.browsertools.browserhandler.waiting.*;
import com.github.git_leon.leonium.extentreporting.ExtentTestLogger;
import com.github.git_leon.leonium.extentreporting.ExtentTestLoggerFactory;
import com.github.git_leon.logging.*;
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
    private final ExtentTestLogger extentTestLogger;

    public BrowserHandlerLayeredLogger(WebDriver driver, ExtentTestLogger extentTestLogger) {
        final String testName = extentTestLogger.getExtentTest().getModel().getName();
        final SimpleLoggerInterface simpleLogger              = SimpleLoggerWarehouse.getLogger(testName);
        final SimpleLoggerInterface simpleLoggerAndTimer      = new FunctionExecutionLoggerAndTimer(simpleLogger);
        final SimpleLoggerInterface extentTestLoggerAndTimer  = new FunctionExecutionLoggerAndTimer(extentTestLogger);


        final BrowserWaitInterface browserWait                                       = new BrowserWait(5, driver);
        final BrowserWaitLoggerInterface browserWaitLogger                           = new BrowserWaitLogger(browserWait, simpleLoggerAndTimer);
        final BrowserWaitLoggerExtentReporter browserWaitExtentReporter              = new BrowserWaitLoggerExtentReporter(browserWaitLogger, extentTestLogger);
        final BrowserWaitLoggerInterface browserWaitExtentReporterTimer              = new BrowserWaitLogger(browserWaitExtentReporter, extentTestLoggerAndTimer);

        final BrowserHandler browserHandlerImplementation                            = new BrowserHandler(driver, browserWaitExtentReporterTimer);
        final BrowserHandlerLoggerInterface browserHandlerLoggerImplLogger           = new BrowserHandlerLoggerImpl(browserHandlerImplementation, extentTestLoggerAndTimer);
        final BrowserHandlerLoggerInterface browserHandlerLoggerTimer                = new BrowserHandlerLoggerTimer(browserHandlerLoggerImplLogger);
        final BrowserHandlerLoggerInterface browserHandlerLoggerImplExtentTestLogger = new BrowserHandlerLoggerImpl(browserHandlerLoggerTimer, simpleLoggerAndTimer);
        this.browserHandlerExtentReporter                                            = new BrowserHandlerLoggerExtentReporter(browserHandlerLoggerImplExtentTestLogger, extentTestLogger);
        this.extentTestLogger = extentTestLogger;
    }

    public BrowserHandlerLayeredLogger(WebDriver driver, String reportFilePath, String testName) {
        this(driver, new ExtentTestLoggerFactory(reportFilePath).getExtentTestLogger(testName));
    }

    public BrowserHandlerLayeredLogger(WebDriver driver, String reportFilePath) {
        this(driver, reportFilePath, driver.toString());
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
    public ExtentTestLogger getLogger() {
        return extentTestLogger;
    }

    @Override
    public void finalize() {
        close();
    }

    @Override
    public void close() {
        BrowserHandlerLoggerInterface.super.close();
        getLogger()
                .getExtentTest()
                .getModel()
                .getExtentInstance()
                .flush();
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
