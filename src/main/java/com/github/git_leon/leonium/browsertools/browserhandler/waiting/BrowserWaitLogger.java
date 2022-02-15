package com.github.git_leon.leonium.browsertools.browserhandler.waiting;


import com.github.git_leon.logging.*;
import org.openqa.selenium.WebDriver;

public class BrowserWaitLogger extends AbstractBrowserWait implements BrowserWaitLoggerInterface {
    private final BrowserWaitInterface wait;
    private final FunctionExecutionLoggerInterface logger;

    public BrowserWaitLogger(BrowserWaitInterface browserWaitInterface, SimpleLoggerInterface simpleLogger) {
        super(browserWaitInterface.getWaitSeconds(), browserWaitInterface.getDriver());
        simpleLogger.enable();
        this.wait = browserWaitInterface;
        this.logger = new FunctionExecutionLoggerImpl(simpleLogger);
    }

    public BrowserWaitLogger(WebDriver driver, int seconds) {
        super(seconds, driver);
        SimpleLogger simpleLogger = SimpleLoggerWarehouse.getLogger(toString());
        simpleLogger.enable();
        this.wait = new BrowserWait(seconds, driver);
        this.logger = new FunctionExecutionLoggerImpl(simpleLogger);
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