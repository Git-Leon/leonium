package com.github.git_leon.logging;


import java.util.function.BiFunction;

/**
 * @author leon on 5/25/18.
 * The purpose of this class is to invoke methods and report their result and execution time
 */
public final class FunctionExecutionLoggerAndTimer implements FunctionExecutionLoggerInterface {
    private final SimpleLoggerInterface logger;

    public FunctionExecutionLoggerAndTimer(SimpleLoggerInterface logger) {
        this.logger = logger;
    }

    @Override
    public SimpleLoggerInterface getLogger() {
        return logger;
    }

    /**
     * @param function        FunctionalInterface to be invoked
     * @param firstArg        first argument of FunctionalInterface to be invoked
     * @param secondArg       second argument of FunctionalInterface to be invoked
     * @param logMessage      message to be logged
     * @param <FirstArgType>  Parameter-Type of first argument
     * @param <SecondArgType> Parameter-Type of second argument
     * @param <ReturnType>    Return-Type of FunctionalInterface to be invoked
     * @return Return-Value of the invoked FunctionalInterface
     */
    @Override
    public <FirstArgType, SecondArgType, ReturnType> ReturnType logAndInvoke(
            BiFunction<FirstArgType, SecondArgType, ReturnType> function,
            FirstArgType firstArg, SecondArgType secondArg, String logMessage) {
        String timeElapsedLog = "Execution time: %s seconds.";
        String resultValLog = "Resulted in %s";

        getLogger().info(logMessage);
        Long executionStartTime = System.currentTimeMillis();
        ReturnType returnValue = function.apply(firstArg, secondArg);
        double timeElapsedInSeconds = (System.currentTimeMillis() - executionStartTime) / 1000.0;

        getLogger().info(resultValLog, returnValue);
        getLogger().info(timeElapsedLog, timeElapsedInSeconds);

        return returnValue;
    }
}
