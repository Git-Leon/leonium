package com.git_leon.selenium.tools.logging;

import com.git_leon.selenium.tools.TimeUtils;
import com.google.common.base.Function;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * @author leon on 5/25/18.
 */
public class InvokeAndLogger {
    private final LoggerHandler logger;

    public InvokeAndLogger(Logger logger) {
        this.logger = new LoggerHandler(logger);
    }

    public <FirstArgType, SecondArgType, ReturnType> ReturnType invokeAndLog(
            BiFunction<FirstArgType, SecondArgType, ReturnType> forCondition,
            FirstArgType firstArg, SecondArgType secondArg, String logMessage) {
        String timeElapsedLog = "Execution time: %s seconds.";
        String resultValLog = "Resulted in %s";

        logger.info(logMessage);
        Long t0 = System.currentTimeMillis();
        ReturnType returnValue = forCondition.apply(firstArg, secondArg);
        double timeElapsed = TimeUtils.getElapsedTime(t0);

        logger.info(resultValLog, returnValue);
        logger.info(timeElapsedLog, timeElapsed);

        return returnValue;
    }


    public <ArgType, ReturnType> ReturnType invokeAndLog(
            Function<ArgType, ReturnType> forCondition, ArgType arg, String logMessage) {
        return invokeAndLog((arg1, arg2) -> forCondition.apply(arg), null, null, logMessage);
    }

    public <ArgType> void consumeAndLog(
            Consumer<ArgType> forCondition, ArgType argument, String logMessage) {
        invokeAndLog((arg1) -> {
            forCondition.accept(argument);
            return null;
        }, null, logMessage);
    }


    public <ReturnType> ReturnType invokeAndLog(
            Supplier<ReturnType> forCondition, String logMessage) {
        return invokeAndLog((arg) -> {
            return forCondition.get();
        }, null, logMessage);
    }

}
