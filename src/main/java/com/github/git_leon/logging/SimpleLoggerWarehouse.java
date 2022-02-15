package com.github.git_leon.logging;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Created by leon on 5/15/17.
 */
public final class SimpleLoggerWarehouse {
    private static volatile SimpleLoggerInterface logger = new SimpleLogger(Logger.getGlobal());
    private static volatile Map<String, SimpleLogger> loggerMap = new ConcurrentHashMap<>();

    public static synchronized void setLogger(SimpleLoggerInterface logger) {
        SimpleLoggerWarehouse.logger = logger;
    }

    /**
     * Only one logger per class
     * @param name - class to generate a logger for
     * @return respective MyLogger object
     */ // TODO - Rethink the nature of the "one logger per class" rule
    public static synchronized SimpleLogger getLogger(String name) {
        addLogger(name);
        return loggerMap.get(name);
    }

    /**
     * Ensures each logger is only tied to a single class
     * @param name - class to generate a logger for
     */
    private static synchronized void addLogger(String name) {
        if (!loggerMap.containsKey(name)) {
            loggerMap.put(name, new SimpleLogger(name));
            logger.info(String.format("Created logger with name [ %s ]", name));
        }
    }
}
