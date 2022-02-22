package com.github.git_leon.leonium.browsertools.browserhandler.reporting;

import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandler;
import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerImpl;
import com.github.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerTimer;
import com.github.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWait;
import com.github.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitLogger;
import com.github.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitLoggerInterface;
import com.github.git_leon.leonium.extentreporting.ExtentTestLogger;
import com.github.git_leon.leonium.extentreporting.ExtentTestLoggerInterface;
import com.github.git_leon.logging.FunctionExecutionLoggerAndTimer;
import com.github.git_leon.logging.SimpleLoggerInterface;
import com.github.git_leon.logging.SimpleLoggerWarehouse;
import org.openqa.selenium.WebDriver;

/**
 * @author leonhunter
 * @created 05/04/2020 - 10:05 AM
 * default `BrowserHandlerInterface` leveraged by leonium.browsertools.WebPage
 */
public class BrowserHandlerLayeredLogger implements BrowserHandlerLoggerInterface {
    private final BrowserHandlerLoggerExtentReporter browserHandlerExtentReporter;
    private final ExtentTestLoggerInterface extentTestLogger;

    public BrowserHandlerLayeredLogger(WebDriver driver, ExtentTestLoggerInterface extentTestLogger) {
        final String testName = extentTestLogger.getExtentTest().getModel().getName();
        final SimpleLoggerInterface simpleLogger              = SimpleLoggerWarehouse.getLogger(testName);
        final SimpleLoggerInterface simpleLoggerAndTimer      = new FunctionExecutionLoggerAndTimer(simpleLogger);
        final SimpleLoggerInterface extentTestLoggerAndTimer  = new FunctionExecutionLoggerAndTimer(extentTestLogger);


        final BrowserWaitInterface browserWait                                       = new BrowserWait(5, driver);
        final BrowserWaitLoggerInterface browserWaitSimpleLogger                     = new BrowserWaitLogger(browserWait, simpleLoggerAndTimer);
        final BrowserWaitLoggerExtentReporter browserWaitExtentReporter              = new BrowserWaitLoggerExtentReporter(browserWaitSimpleLogger, extentTestLogger);

        final BrowserHandler browserHandlerImplementation                            = new BrowserHandler(driver, browserWaitExtentReporter);
        final BrowserHandlerLoggerInterface browserHandlerLoggerImplLogger           = new BrowserHandlerLoggerImpl(browserHandlerImplementation, extentTestLoggerAndTimer);
        final BrowserHandlerLoggerInterface browserHandlerLoggerTimer                = new BrowserHandlerLoggerTimer(browserHandlerLoggerImplLogger);
        final BrowserHandlerLoggerInterface browserHandlerLoggerImplExtentTestLogger = new BrowserHandlerLoggerImpl(browserHandlerLoggerTimer, simpleLoggerAndTimer);
        this.browserHandlerExtentReporter                                            = new BrowserHandlerLoggerExtentReporter(browserHandlerLoggerImplExtentTestLogger, extentTestLogger);
        this.extentTestLogger = extentTestLogger;
    }

    @Override
    public ExtentTestLoggerInterface getLogger() {
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
