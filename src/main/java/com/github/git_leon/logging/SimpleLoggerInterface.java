package com.github.git_leon.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;

import static java.util.logging.Level.*;

public interface SimpleLoggerInterface {

    default void info(String s, Object... args) {
        log(INFO, s, args);
    }

    default void error(String s, Object... args) {
        log(SEVERE, s, args);
    }

    default void warn(String s, Object... args) {
        log(WARNING, s, args);
    }

    default void throwable(Throwable t) {
        throwable(t, WARNING);
    }

    default void throwable(Throwable t, Level level) {
        StringWriter out = new StringWriter();
        t.printStackTrace(new PrintWriter(out));
        String description = out.toString();
        error(description);
    }

    void log(Level level, String message, Object... messageArgs);

    void enable();

    void disble();

    boolean isEnabled();

}
