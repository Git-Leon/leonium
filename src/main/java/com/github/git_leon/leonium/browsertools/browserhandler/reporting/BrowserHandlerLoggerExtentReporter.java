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
    private final BrowserHandlerLoggerInterface browserHandlerDecoratee;
    private final ExtentTestLoggerFactory extentTestLoggerFactory;
    private final String testName;
    private final String testDescription;
    private ExtentTestLogger extentTestLogger;

    public BrowserHandlerLoggerExtentReporter(BrowserHandlerLoggerInterface decoratee, String reportName, String testName) {
        this(decoratee, reportName, testName, "");
    }

    public BrowserHandlerLoggerExtentReporter(BrowserHandlerLoggerInterface decoratee, String filePath, String testName, String testDescription) {
        this(decoratee, new ExtentTestLoggerFactory(filePath), testName, testDescription);
    }

    public BrowserHandlerLoggerExtentReporter(BrowserHandlerLoggerInterface decoratee, ExtentTestLoggerFactory extentTestLoggerFactory, String testName, String testDescription) {
        this.browserHandlerDecoratee = decoratee;
        this.extentTestLoggerFactory = extentTestLoggerFactory;
        this.testName = testName;
        this.testDescription = testDescription;
    }

    @Override
    public BrowserHandlerLoggerInterface getBrowserHandlerLoggerDecoratee() {
        return browserHandlerDecoratee;
    }

    @Override
    public ExtentTestLogger getLogger() {
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
        extentTestLoggerFactory.getExtentReports().flush();
        BrowserHandlerLoggerInterfaceDecorator.super.close();
    }

    public ExtentTestLoggerFactory getExtentTestLoggerFactory() {
        return extentTestLoggerFactory;
    }

    public String getTestName() {
        return testName;
    }

    public String getTestDescription() {
        return testDescription;
    }
}
