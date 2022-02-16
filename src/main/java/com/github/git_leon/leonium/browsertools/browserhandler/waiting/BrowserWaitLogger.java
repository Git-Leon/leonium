package com.github.git_leon.leonium.browsertools.browserhandler.waiting;


import com.github.git_leon.logging.*;
import org.openqa.selenium.WebDriver;

public class BrowserWaitLogger extends AbstractBrowserWait implements BrowserWaitLoggerInterface {
    private final BrowserWaitInterface wait;
    private final FunctionExecutionLoggerInterface logger;

    public BrowserWaitLogger(BrowserWaitInterface browserWaitInterface, SimpleLoggerInterface simpleLogger) {
        super(browserWaitInterface.getWaitSeconds(), browserWaitInterface.getDriver());
        this.wait = browserWaitInterface;
        this.logger = new FunctionExecutionLoggerImpl(simpleLogger);
        this.logger.enable();
    }

    public BrowserWaitLogger(WebDriver driver, int seconds) {
        this(
                new BrowserWait(seconds, driver),
                new FunctionExecutionLoggerImpl(SimpleLoggerWarehouse.getLogger(BrowserWaitLogger.class.toString())));
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