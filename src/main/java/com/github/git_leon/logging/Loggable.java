package com.github.git_leon.logging;

public interface Loggable {
    default SimpleLoggerInterface getLogger() {
        return SimpleLoggerWarehouse.getLogger(getClass().getName());
    }
}
