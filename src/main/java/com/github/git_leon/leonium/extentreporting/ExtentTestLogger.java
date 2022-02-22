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
public class ExtentTestLogger implements SimpleLoggerInterface {
    private final ExtentTest extentTest;
    private boolean isEnabled;

    public ExtentTestLogger(ExtentTest extentTest, boolean isEnabled) {
        this.extentTest = extentTest;
        this.isEnabled = isEnabled;
    }

    public ExtentTestLogger(ExtentTest extentTest) {
        this(extentTest, true);
    }

    @Override
    public void log(Level level, String logMessage, Object... logMessageArgs) {
        if (isEnabled()) {
            getExtentTest().log(getStatus(level), String.format(logMessage, logMessageArgs));
        }
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

    @Override
    public void throwable(Throwable t, Level level) {
        StringWriter out = new StringWriter();
        t.printStackTrace(new PrintWriter(out));
        String description = out
                .toString()
                .replaceAll("\n", "<br>");
        this.error(description);
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
