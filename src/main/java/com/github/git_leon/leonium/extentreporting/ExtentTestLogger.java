package com.github.git_leon.leonium.extentreporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.github.git_leon.stringutils.StringUtils;
import com.github.git_leon.logging.SimpleLoggerInterface;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;

/**
 * @author leonhunter
 * @created 05/04/2020 - 2:03 AM
 */
public class ExtentTestLogger implements SimpleLoggerInterface {
    private final ExtentReports extentReports;
    private final ExtentTest extentTest;
    private boolean isEnabled;

    public ExtentTestLogger(ExtentReports extentReports, String testName, String description) {
        this.extentReports = extentReports;
        this.extentTest = extentReports.createTest(testName, description);
        this.isEnabled = true;
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
    public void disble() {
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

    public ExtentReports getExtentReports() {
        return extentReports;
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
