package com.github.git_leon.leonium.browsertools.browserhandler.reporting;

import com.aventstack.extentreports.ExtentTest;
import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerInterfaceDecorator;
import com.github.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitInterface;
import com.github.git_leon.extentreporting.ExtentTestLoggerInterface;

/**
 * @author leonhunter
 * @created 05/03/2020 - 11:44 PM
 */
public class BrowserHandlerLoggerExtentReporter implements BrowserHandlerLoggerInterfaceDecorator {
    private final BrowserHandlerLoggerInterface browserHandlerDecoratee;
    private final ExtentTestLoggerInterface extentTestLogger;

    public BrowserHandlerLoggerExtentReporter(BrowserHandlerLoggerInterface browserHandlerLoggerTimer, ExtentTestLoggerInterface extentTestLogger) {
        this.browserHandlerDecoratee = browserHandlerLoggerTimer;
        this.extentTestLogger = extentTestLogger;
    }

    @Override
    public BrowserHandlerLoggerInterface getBrowserHandlerLoggerDecoratee() {
        return browserHandlerDecoratee;
    }

    @Override
    public ExtentTestLoggerInterface getLogger() {
        return this.extentTestLogger;
    }

    @Override
    public BrowserWaitInterface getWait() {
        return getBrowserHandlerDecoratee().getWait();
    }

    @Override
    public BrowserHandlerInterface getBrowserHandlerDecoratee() {
        return browserHandlerDecoratee;
    }

    @Override
    public void finalize() {
        close();
    }

    @Override
    public void close() {
        BrowserHandlerLoggerInterfaceDecorator.super.close();
    }

    public ExtentTest getExtentTest() {
        return getLogger().getExtentTest();
    }
}
