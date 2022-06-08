package com.github.git_leon.extentreporting;

import com.aventstack.extentreports.ExtentTest;

/**
 * @author leonhunter
 * @created 05/04/2020 - 2:03 AM
 */
public class ExtentTestLogger implements ExtentTestLoggerInterface {
    private final ExtentTest extentTest;
    private boolean isEnabled;

    ExtentTestLogger(ExtentTest extentTest, boolean isEnabled) {
        this.extentTest = extentTest;
        this.isEnabled = isEnabled;
    }

    ExtentTestLogger(ExtentTest extentTest) {
        this(extentTest, true);
    }

    @Override
    public void enable() {
        this.isEnabled = true;
    }

    @Override
    public void disable() {
        this.isEnabled = false;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public ExtentTest getExtentTest() {
        return extentTest;
    }
}
