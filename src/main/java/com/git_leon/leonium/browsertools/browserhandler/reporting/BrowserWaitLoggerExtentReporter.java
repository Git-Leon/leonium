package com.git_leon.leonium.browsertools.browserhandler.reporting;

import com.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitInterface;
import com.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitLoggerInterface;
import com.git_leon.leonium.extentreporting.ExtentTestLogger;
import com.git_leon.leonium.extentreporting.ExtentTestLoggerFactory;
import org.openqa.selenium.WebDriver;

/**
 * @author leonhunter
 * @created 05/04/2020 - 3:33 PM
 */
public class BrowserWaitLoggerExtentReporter implements BrowserWaitLoggerInterface {
    private final ExtentTestLoggerFactory extentTestLoggerFactory;
    private final BrowserWaitInterface wait;
    private final ExtentTestLogger logger;

    public BrowserWaitLoggerExtentReporter(BrowserWaitLoggerInterface browserHandlerImplementation, String filePath, String testName) {
        this.extentTestLoggerFactory = new ExtentTestLoggerFactory(filePath);
        this.wait = browserHandlerImplementation.getWait();
        this.logger = extentTestLoggerFactory.getExtentTestLogger(testName);
    }

    public BrowserWaitLoggerExtentReporter(ExtentTestLoggerFactory extentTestLoggerFactory, BrowserWaitInterface wait, ExtentTestLogger logger) {
        this.extentTestLoggerFactory = extentTestLoggerFactory;
        this.wait = wait;
        this.logger = logger;
    }

    public ExtentTestLoggerFactory getExtentTestLoggerFactory() {
        return extentTestLoggerFactory;
    }

    @Override
    public BrowserWaitInterface getWait() {
        return wait;
    }

    @Override
    public ExtentTestLogger getFunctionExecutionLogger() {
        return logger;
    }

    @Override
    public int getWaitSeconds() {
        return getWait().getWaitSeconds();
    }

    @Override
    public WebDriver getDriver() {
        return getWait().getDriver();
    }

}
