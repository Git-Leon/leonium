package com.github.git_leon.leonium.browsertools.browserhandler.logging;

import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandler;
import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerInterface;
import com.github.git_leon.logging.SimpleLoggerInterface;
import com.github.git_leon.logging.SimpleLoggerWarehouse;
import org.openqa.selenium.WebDriver;

/**
 * @author leonhunter
 * @created 04/29/2020 - 5:03 PM
 * default implementation of `BrowserHandlerLoggerInterface`
 */
public class BrowserHandlerLoggerImpl implements BrowserHandlerLoggerInterface {
    private final BrowserHandlerInterface decoratee;
    private final SimpleLoggerInterface logger;

    public BrowserHandlerLoggerImpl(BrowserHandlerInterface browserHandler, SimpleLoggerInterface logger) {
        this.decoratee = browserHandler;
        this.logger = logger;
        logger.enable();
    }

    public BrowserHandlerLoggerImpl(BrowserHandlerInterface browserHandler) {
        this(browserHandler, SimpleLoggerWarehouse.getLogger(browserHandler.getDriver().toString()));
    }

    public BrowserHandlerLoggerImpl(BrowserHandlerLoggerInterface browserHandler) {
        this(browserHandler, browserHandler.getLogger());
    }

    @Override
    public SimpleLoggerInterface getLogger() {
        return logger;
    }


    @Override
    public BrowserHandlerInterface getBrowserHandlerDecoratee() {
        return decoratee;
    }

    @Override
    public void finalize() {
        getBrowserHandlerDecoratee().finalize();
    }
}
