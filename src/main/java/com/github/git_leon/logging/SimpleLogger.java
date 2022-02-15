package com.github.git_leon.logging;


import com.github.git_leon.stringutils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

/**
 * Created by leon on 5/15/17.
 */
public final class SimpleLogger implements SimpleLoggerInterface {
    private final Logger logger;
    private boolean isEnabled;

    public SimpleLogger(String loggerName) {
        this(Logger.getLogger(loggerName));
    }

    public SimpleLogger(Logger logger) {
        this(logger, getFileHandler(logger.getName()));
    }

    public SimpleLogger(Logger logger, StreamHandler streamHandler) {
        this.logger = logger;
        logger.setUseParentHandlers(false);
        this.removeHandlers();
        this.logger.addHandler(streamHandler);
    }

    @Override
    public void log(Level level, String message, Object... messageArgs) {
        String info = String.format(message, messageArgs);
        if (isEnabled()) {
            System.out.println(info);
            logger.log(level, info);
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

    private void removeHandlers() {
        Handler[] handlers = logger.getHandlers();
        for (Handler handler : handlers) {
            logger.removeHandler(handler);
        }
    }

    private static FileHandler getFileHandler(String fileName) {
        String fileDirectory = "./target/logs/";
        String fullFilePath = fileDirectory + fileName;
        String invalidFileCharacters = ":;'`<>~!@#$%^&*()\\[\\]";
        fullFilePath = StringUtils.removeCharacters(fullFilePath, invalidFileCharacters);

        FileHandler fh = null;
        try {
            new File(fileDirectory).mkdirs();
            fh = new FileHandler(fullFilePath, true);
            fh.setFormatter(new SimpleFormatter());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return fh;
    }
}
