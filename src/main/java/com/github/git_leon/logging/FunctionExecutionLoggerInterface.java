package com.github.git_leon.logging;

import java.util.function.*;
import java.util.logging.Level;

/**
 * @author leonhunter
 * created at 05/04/2020 - 7:51 PM
 */
public interface FunctionExecutionLoggerInterface extends SimpleLoggerInterface {

    SimpleLoggerInterface getLogger();


    /**
     * @param level           Level of logging
     * @param function        FunctionalInterface to be invoked
     * @param firstArg        first argument of FunctionalInterface to be invoked
     * @param secondArg       second argument of FunctionalInterface to be invoked
     * @param logMessage      message to be logged
     * @param <FirstArgType>  Parameter-Type of first argument
     * @param <SecondArgType> Parameter-Type of second argument
     * @param <ReturnType>    Return-Type of FunctionalInterface to be invoked
     * @return Return-Value of the invoked FunctionalInterface
     */
    default <FirstArgType, SecondArgType, ReturnType> ReturnType logAndInvoke(
            Level level, BiFunction<FirstArgType, SecondArgType, ReturnType> function,
            FirstArgType firstArg, SecondArgType secondArg, String logMessage) {
        getLogger().log(level, logMessage);
        return function.apply(firstArg, secondArg);
    }


    /**
     * @param level          Level of logging
     * @param function       FunctionalInterface to be invoked
     * @param firstArg       first argument of FunctionalInterface to be invoked
     * @param logMessage     message to be logged
     * @param <FirstArgType> Parameter-Type of first argument
     * @param <ReturnType>   Return-Type of FunctionalInterface to be invoked
     * @return Return-Value of the invoked FunctionalInterface
     */
    default <FirstArgType, ReturnType> ReturnType logAndInvoke(
            Level level, Function<FirstArgType, ReturnType> function,
            FirstArgType firstArg, String logMessage) {
        return logAndInvoke(level, (arg1, arg2) -> function.apply(firstArg), null, null, logMessage);
    }

    /**
     * @param level        Level of logging
     * @param function     FunctionalInterface to be invoked
     * @param logMessage   message to be logged upon invocation
     * @param <ReturnType> Return-Type of FunctionalInterface to be invoked
     * @return return-value of invocation
     */
    default <ReturnType> ReturnType logAndInvoke(
            Level level, Supplier<ReturnType> function, String logMessage) {
        return logAndInvoke(level, (arg1, arg2) -> function.get(), null, null, logMessage);
    }

    /**
     * @param level      Level of logging
     * @param logMessage message to be logged upon invocation
     * @param function   FunctionalInterface to be invoked
     */
    default void logAndInvoke(
            Level level, Runnable function, String logMessage) {
        logAndInvoke(level, (arg) -> {
            function.run();
            return null;
        }, null, logMessage);
    }


    /**
     * @param level      Level of logging
     * @param function   FunctionalInterface to be invoked
     * @param argument1  first argument to be passed upon invocation
     * @param argument2  second argument to be passed upon invocation
     * @param logMessage message to be logged upon invocation
     * @param <ArgType1> Parameter-Type of first argument of FunctionalInterface to be invoked
     * @param <ArgType2> Parameter-Type of second argument of FunctionalInterface to be invoked
     */
    default <ArgType1, ArgType2> void consumeAndLog(
            Level level, BiConsumer<ArgType1, ArgType2> function, ArgType1 argument1, ArgType2 argument2, String logMessage) {
        logAndInvoke(level, (arg1) -> {
            function.accept(argument1, argument2);
            return null;
        }, null, logMessage);
    }

    /**
     * @param level      Level of logging
     * @param function   FunctionalInterface to be invoked
     * @param argument   argument to be passed upon invocation
     * @param logMessage message to be logged upon invocation
     * @param <ArgType>  Parameter-Type of FunctionalInterface to be invoked
     */
    default <ArgType> void consumeAndLog(
            Level level, Consumer<ArgType> function, ArgType argument, String logMessage) {
        logAndInvoke(level, (arg1) -> {
            function.accept(argument);
            return null;
        }, null, logMessage);
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
    default <FirstArgType, SecondArgType, ReturnType> ReturnType logAndInvoke(
            BiFunction<FirstArgType, SecondArgType, ReturnType> function,
            FirstArgType firstArg, SecondArgType secondArg, String logMessage) {
        return logAndInvoke(Level.INFO, (arg1, arg2) -> function.apply(firstArg, secondArg), null, null, logMessage);
    }

    /**
     * @param function     FunctionalInterface to be invoked
     * @param arg          argument to be passed upon invocation
     * @param logMessage   message to be logged upon invocation
     * @param <ArgType>    Parameter-Type of FunctionalInterface to be invoked
     * @param <ReturnType> Return-Type of FunctionalInterface to be invoked
     * @return return-value of invocation
     */
    default <ArgType, ReturnType> ReturnType logAndInvoke(
            Function<ArgType, ReturnType> function, ArgType arg, String logMessage) {
        return logAndInvoke((arg1, arg2) -> function.apply(arg), null, null, logMessage);
    }

    /**
     * @param function   FunctionalInterface to be invoked
     * @param argument   argument to be passed upon invocation
     * @param logMessage message to be logged upon invocation
     * @param <ArgType>  Parameter-Type of FunctionalInterface to be invoked
     */
    default <ArgType> void consumeAndLog(
            Consumer<ArgType> function, ArgType argument, String logMessage) {
        logAndInvoke((arg1) -> {
            function.accept(argument);
            return null;
        }, null, logMessage);
    }


    /**
     * @param function   FunctionalInterface to be invoked
     * @param argument1  first argument to be passed upon invocation
     * @param argument2  second argument to be passed upon invocation
     * @param logMessage message to be logged upon invocation
     * @param <ArgType1> Parameter-Type of first argument of FunctionalInterface to be invoked
     * @param <ArgType2> Parameter-Type of second argument of FunctionalInterface to be invoked
     */
    default <ArgType1, ArgType2> void consumeAndLog(
            BiConsumer<ArgType1, ArgType2> function, ArgType1 argument1, ArgType2 argument2, String logMessage) {
        logAndInvoke((arg1) -> {
            function.accept(argument1, argument2);
            return null;
        }, null, logMessage);
    }

    /**
     * @param logMessage   message to be logged upon invocation
     * @param function     FunctionalInterface to be invoked
     * @param <ReturnType> Return-Type of FunctionalInterface to be invoked
     * @return Return-Value of the invoked FunctionalInterface
     */
    default <ReturnType> ReturnType logAndInvoke(
            Supplier<ReturnType> function, String logMessage) {
        return logAndInvoke((arg) -> {
            return function.get();
        }, null, logMessage);
    }


    /**
     * @param logMessage message to be logged upon invocation
     * @param function   FunctionalInterface to be invoked
     */
    default void logAndInvoke(
            Runnable function, String logMessage) {
        logAndInvoke((arg) -> {
            function.run();
            return null;
        }, null, logMessage);
    }

    @Override
    default void log(Level level, String message, Object... messageArgs) {
        getLogger().log(level, message, messageArgs);
    }

    @Override
    default void enable() {
        getLogger().enable();
    }

    @Override
    default void disable() {
        getLogger().disable();
    }

    @Override
    default boolean isEnabled() {
        return getLogger().isEnabled();
    }
}
