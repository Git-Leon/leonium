package com.github.git_leon.leonium.browsertools.browserhandler.waiting;

import com.github.git_leon.logging.SimpleLoggerInterface;

/**
 * @author leonhunter
 * @created 05/08/2020 - 3:09 PM
 */
public class BrowserWaitLoggerDecorator implements BrowserWaitLoggerDecoratorInterface {
    private final BrowserWaitLoggerInterface wait;
    private final SimpleLoggerInterface logger;

    public BrowserWaitLoggerDecorator(BrowserWaitLoggerInterface wait, SimpleLoggerInterface logger) {
        this.wait = wait;
        this.logger = logger;
    }

    @Override
    public SimpleLoggerInterface getLogger() {
        return logger;
    }

    @Override
    public BrowserWaitLoggerInterface getWait() {
        return wait;
    }
}
