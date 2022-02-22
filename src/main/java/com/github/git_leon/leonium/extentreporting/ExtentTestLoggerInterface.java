package com.github.git_leon.leonium.extentreporting;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.github.git_leon.logging.SimpleLoggerInterface;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;

import static java.util.logging.Level.FINER;

public interface ExtentTestLoggerInterface extends SimpleLoggerInterface {
    ExtentTest getExtentTest();

    @Override
    default void log(Level level, String logMessage, Object... logMessageArgs) {
        if (isEnabled()) {
            getExtentTest().log(getStatus(level), String.format(logMessage, logMessageArgs));
        }
    }
    @Override
    default void error(String s, Object... args) {
        log(FINER, s, args);
    }

    @Override
    default void throwable(Throwable t, Level level) {
        StringWriter out = new StringWriter();
        t.printStackTrace(new PrintWriter(out));
        String description = out
                .toString()
                .replaceAll("\n", "<br>");
        this.error(description);
    }

    default Status getStatus(Level level) {
        switch (level.getName().toUpperCase()) {
            case "INFO":
                return Status.PASS;
            case "WARNING":
                return Status.WARNING;
            case "FINE":
                return Status.INFO;
            case "FINER":
                return Status.ERROR;
            case "FINEST":
            case "SEVERE":
                return Status.FAIL;
        }
        return Status.PASS;
    }
}
