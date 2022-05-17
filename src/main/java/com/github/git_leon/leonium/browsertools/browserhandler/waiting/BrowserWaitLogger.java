package com.github.git_leon.leonium.browsertools.browserhandler.waiting;


import com.github.git_leon.logging.*;
import org.openqa.selenium.WebDriver;

public class BrowserWaitLogger implements BrowserWaitLoggerInterface {
    private final BrowserWaitInterface wait;
    private final SimpleLoggerInterface logger;

    public BrowserWaitLogger(BrowserWaitInterface browserWaitInterface, SimpleLoggerInterface simpleLogger) {
        this.wait = browserWaitInterface;
        this.logger = simpleLogger;
        this.logger.enable();
    }

    public BrowserWaitLogger(WebDriver driver, int seconds) {
        this(
                new BrowserWait(driver, seconds),
                new FunctionExecutionLoggerImpl(SimpleLoggerWarehouse.getLogger(BrowserWaitLogger.class.toString())));
    }


    public BrowserWaitLogger(BrowserWaitInterface browserWaitInterface) {
        this(browserWaitInterface.getDriver(), browserWaitInterface.getWaitSeconds());
    }

    public BrowserWaitLogger(WebDriver driver) {
        this(driver, 15);
    }

    @Override
    public SimpleLoggerInterface getLogger() {
        return logger;
    }

    @Override
    public BrowserWaitInterface getWait() {
        return wait;
    }
}