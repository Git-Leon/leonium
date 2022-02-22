package com.github.git_leon.leonium.extentreporting;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.github.git_leon.logging.SimpleLoggerInterface;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;

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
