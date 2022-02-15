package com.github.git_leon.leonium.browsertools.browserhandler.reporting;

import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerInterfaceDecorator;
import com.github.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitInterface;
import com.github.git_leon.leonium.extentreporting.ExtentTestLogger;
import com.github.git_leon.leonium.extentreporting.ExtentTestLoggerFactory;

/**
 * @author leonhunter
 * @created 05/03/2020 - 11:44 PM
 */
public class BrowserHandlerLoggerExtentReporter implements BrowserHandlerLoggerInterfaceDecorator {
    private final BrowserHandlerLoggerInterface decoratee;
    private final ExtentTestLoggerFactory extentTestLoggerFactory;
    private final String testName;
    private final String testDescription;

    public BrowserHandlerLoggerExtentReporter(BrowserHandlerLoggerInterface decoratee, ExtentTestLoggerFactory extentTestLoggerFactory, String testName, String testDescription) {
        this.decoratee = decoratee;
        this.extentTestLoggerFactory = extentTestLoggerFactory;
        this.testName = testName;
        this.testDescription = testDescription;
    }

    public BrowserHandlerLoggerExtentReporter(BrowserHandlerLoggerInterface decoratee, String filePath, String testName, String testDescription) {
        this(decoratee, new ExtentTestLoggerFactory(filePath), testName, testDescription);
    }

    public BrowserHandlerLoggerExtentReporter(BrowserHandlerLoggerInterface decoratee, String reportName, String testName) {
        this(decoratee, reportName, testName, "");
    }

    @Override
    public BrowserHandlerLoggerInterface getBrowserHandlerLoggerDecoratee() {
        return decoratee;
    }

    @Override
    public ExtentTestLogger getLogger() {
        return extentTestLoggerFactory.getExtentTestLogger(this.testName, testDescription);
    }

    @Override
    public BrowserWaitInterface getWait() {
        return getBrowserHandlerDecoratee().getWait();
    }

    @Override
    public BrowserHandlerInterface getBrowserHandlerDecoratee() {
        return decoratee;
    }

    @Override
    public void finalize() {
        close();
    }

    @Override
    public void close() {
        extentTestLoggerFactory.flush();
        BrowserHandlerLoggerInterfaceDecorator.super.close();
    }

    public ExtentTestLoggerFactory getExtentTestLoggerFactory() {
        return extentTestLoggerFactory;
    }
}
