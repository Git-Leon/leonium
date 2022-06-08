package com.github.git_leon.extentreporting;

import com.aventstack.extentreports.ExtentTest;
import com.github.git_leon.logging.FunctionExecutionLoggerAndTimer;

import java.util.logging.Level;

public class ExtentTestLoggerTimer implements ExtentTestLoggerInterface {
    private final FunctionExecutionLoggerAndTimer logger;
    private final ExtentTest extentTest;

    public ExtentTestLoggerTimer(ExtentTestLoggerInterface extentTestLoggerInterface) {
        this.logger = new FunctionExecutionLoggerAndTimer(extentTestLoggerInterface);
        this.extentTest = extentTestLoggerInterface.getExtentTest();
    }

    @Override
    public ExtentTest getExtentTest() {
        return extentTest;
    }

    @Override
    public void enable() {
        logger.enable();
    }

    @Override
    public void disable() {
        logger.disable();
    }

    @Override
    public boolean isEnabled() {
        return logger.isEnabled();
    }

    @Override
    public void log(Level level, String logMessage, Object... logMessageArgs) {
        logger.log(level, logMessage, logMessageArgs);
    }
}
