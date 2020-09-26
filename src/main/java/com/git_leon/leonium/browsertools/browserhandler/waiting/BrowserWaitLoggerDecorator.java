package com.git_leon.leonium.browsertools.browserhandler.waiting;

import com.github.git_leon.logging.SimpleLoggerInterface;

/**
 * @author leonhunter
 * @created 05/08/2020 - 3:09 PM
 */
public class BrowserWaitLoggerDecorator implements BrowserWaitLoggerInterfaceDecoratorInterface {
    private final BrowserWaitInterface wait;
    private final SimpleLoggerInterface logger;

    public BrowserWaitLoggerDecorator(BrowserWaitInterface wait, SimpleLoggerInterface logger) {
        this.wait = wait;
        this.logger = logger;
    }

    @Override
    public SimpleLoggerInterface getLogger() {
        return logger;
    }

    @Override
    public BrowserWaitInterface getWait() {
        return wait;
    }
}
