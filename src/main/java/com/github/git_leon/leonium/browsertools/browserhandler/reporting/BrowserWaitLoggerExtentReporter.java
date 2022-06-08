package com.github.git_leon.leonium.browsertools.browserhandler.reporting;

import com.github.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitLoggerInterface;
import com.github.git_leon.extentreporting.ExtentTestLoggerInterface;
import org.openqa.selenium.WebDriver;

/**
 * @author leonhunter
 * @created 05/04/2020 - 3:33 PM
 */
public class BrowserWaitLoggerExtentReporter implements BrowserWaitLoggerInterface {
    private final BrowserWaitInterface wait;
    private final ExtentTestLoggerInterface logger;

    public BrowserWaitLoggerExtentReporter(BrowserWaitInterface wait, ExtentTestLoggerInterface logger) {
        this.wait = wait;
        this.logger = logger;
    }

    @Override
    public ExtentTestLoggerInterface getLogger() {
        return logger;
    }

    @Override
    public BrowserWaitInterface getWait() {
        return wait;
    }

    @Override
    public void setWaitSeconds(int waitSeconds) {
        getWait().setWaitSeconds(waitSeconds);
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
