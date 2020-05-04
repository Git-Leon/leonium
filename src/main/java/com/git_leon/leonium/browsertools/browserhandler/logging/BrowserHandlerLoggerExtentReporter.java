package com.git_leon.leonium.browsertools.browserhandler.logging;

import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import com.git_leon.leonium.extentreporting.ExtentTestLogger;
import com.git_leon.leonium.extentreporting.ExtentTestLoggerFactory;

/**
 * @author leonhunter
 * @created 05/03/2020 - 11:44 PM
 */
public class BrowserHandlerLoggerExtentReporter implements BrowserHandlerLoggerInterface {
    private final BrowserHandlerInterface decoratee;
    private final ExtentTestLoggerFactory extentTestLoggerFactory;
    private final String testName;
    private final String testDescription;

    public BrowserHandlerLoggerExtentReporter(BrowserHandlerInterface decoratee, String filePath, String testName, String testDescription) {
        this.extentTestLoggerFactory = new ExtentTestLoggerFactory(filePath);
        this.decoratee = decoratee;
        this.testName = testName;
        this.testDescription = testDescription;
    }

    public BrowserHandlerLoggerExtentReporter(BrowserHandlerInterface decoratee, String reportName, String testName) {
        this(decoratee, reportName, testName, "");
    }

    @Override
    public ExtentTestLogger getLogger() {
        return extentTestLoggerFactory.createExtentTestLogger(this.testName, testDescription);
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
        BrowserHandlerLoggerInterface.super.close();
    }
}
