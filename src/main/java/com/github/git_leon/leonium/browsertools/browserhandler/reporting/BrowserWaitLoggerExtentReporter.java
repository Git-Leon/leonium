package com.github.git_leon.leonium.browsertools.browserhandler.reporting;

import com.github.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitLoggerInterface;
import com.github.git_leon.leonium.extentreporting.ExtentTestLogger;
import com.github.git_leon.leonium.extentreporting.ExtentTestLoggerFactory;
import com.github.git_leon.logging.SimpleLoggerInterface;
import org.openqa.selenium.WebDriver;

/**
 * @author leonhunter
 * @created 05/04/2020 - 3:33 PM
 */
public class BrowserWaitLoggerExtentReporter implements BrowserWaitLoggerInterface {
    private final BrowserWaitInterface wait;
    private final ExtentTestLogger logger;

    public BrowserWaitLoggerExtentReporter(BrowserWaitInterface wait, ExtentTestLogger logger) {
        this.wait = wait;
        this.logger = logger;
    }

    public BrowserWaitLoggerExtentReporter(BrowserWaitInterface wait, ExtentTestLoggerFactory extentTestLoggerFactory, String testName) {
        this(wait, extentTestLoggerFactory.getExtentTestLogger(testName));
    }

    @Override
    public ExtentTestLogger getLogger() {
        return logger;
    }

    @Override
    public BrowserWaitInterface getWait() {
        return wait;
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
