package com.git_leon.leonium.extentreporting;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.github.git_leon.logging.SimpleLoggerInterface;

import java.util.logging.Level;

/**
 * @author leonhunter
 * @created 05/04/2020 - 2:03 AM
 */
public class ExtentTestLogger implements SimpleLoggerInterface {
    private ExtentTest extentTest;
    private boolean isEnabled;

    public ExtentTestLogger(ExtentTest extentTest) {
        this.extentTest = extentTest;
        this.isEnabled = true;
    }

    @Override
    public void log(Level level, String logMessage, Object... logMessageArgs) {
        if (isEnabled) {
            extentTest.log(getStatus(level), String.format(logMessage, logMessageArgs));
        }
    }

    @Override
    public void enable() {
        this.isEnabled = true;
    }

    @Override
    public void disble() {
        this.isEnabled = false;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public ExtentTest getExtentTest() {
        return extentTest;
    }

    private Status getStatus(Level level) {
        switch (level.getName().toUpperCase()) {
            case "WARN":
                return Status.WARNING;
            case "SEVERE":
                return Status.FATAL;
            case "THROWABLE":
                return Status.ERROR;
        }
        return Status.INFO;
    }
}
