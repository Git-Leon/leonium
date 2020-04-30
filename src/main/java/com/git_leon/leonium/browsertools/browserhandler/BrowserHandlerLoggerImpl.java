package com.git_leon.leonium.browsertools.browserhandler;

import com.github.git_leon.logging.SimpleLoggerInterface;
import com.github.git_leon.logging.SimpleLoggerWarehouse;
import org.openqa.selenium.WebDriver;

/**
 * @author leonhunter
 * @created 04/29/2020 - 5:03 PM
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

    public BrowserHandlerLoggerImpl(WebDriver driver) {
        this(new BrowserHandler(driver));
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
        decoratee.finalize();
    }
}
