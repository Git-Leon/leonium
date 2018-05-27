package com.git_leon.selenium.tools.logging;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Created by leon on 5/15/17.
 */
public final class LoggerWarehouse {
    private static final LoggerHandler globalLogger = new LoggerHandler(Logger.getGlobal());
    private static final HashMap<Class, LoggerHandler> loggerMap = new HashMap<>();

    /**
     * Only one logger per class
     * @param c - class to generate a logger for
     * @return respective MyLogger object
     */ // TODO - Rethink the nature of the "one logger per class" rule
    public static final LoggerHandler getLogger(Class c) {
        addLogger(c);
        return loggerMap.get(c);
    }

    /**
     * Ensures each logger is only tied to a single class
     * @param c - class to generate a logger for
     */
    private static final void addLogger(Class c) {
        if (!loggerMap.containsKey(c)) {
            globalLogger.info(String.format("Instantiating logger for [ %s ] ... ", c.getName()));
            loggerMap.put(c, new LoggerHandler(c));
        }
    }
}
