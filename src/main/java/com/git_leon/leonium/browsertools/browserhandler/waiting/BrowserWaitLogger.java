package com.git_leon.leonium.browsertools.browserhandler.waiting;


import com.github.git_leon.logging.FunctionExecutionTimeLogger;
import com.github.git_leon.logging.SimpleLogger;
import com.github.git_leon.logging.SimpleLoggerInterface;
import com.github.git_leon.logging.SimpleLoggerWarehouse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class BrowserWaitLogger extends AbstractBrowserWait implements BrowserWaitLoggerInterface {
    private final BrowserWaitInterface wait;
    private final FunctionExecutionTimeLogger logger;

    public BrowserWaitLogger(BrowserWaitInterface browserWaitInterface, SimpleLoggerInterface simpleLogger) {
        super(browserWaitInterface.getWaitSeconds(), browserWaitInterface.getDriver());
        simpleLogger.enable();
        this.wait = browserWaitInterface;
        this.logger = new FunctionExecutionTimeLogger(simpleLogger);
    }

    public BrowserWaitLogger(WebDriver driver, int seconds) {
        super(seconds, driver);
        SimpleLogger simpleLogger = SimpleLoggerWarehouse.getLogger(toString());
        simpleLogger.enable();
        this.wait = new BrowserWait(seconds, driver);
        this.logger = new FunctionExecutionTimeLogger(simpleLogger);
    }

    public BrowserWaitLogger(WebDriver driver) {
        this(driver, 15);
    }

    @Override
    public FunctionExecutionTimeLogger getLogger() {
        return logger;
    }

    @Override
    public BrowserWaitInterface getWait() {
        return wait;
    }
}