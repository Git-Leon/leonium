package com.git_leon.leonium.browsertools;

import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import com.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerImpl;
import com.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerTimeLogger;
import org.openqa.selenium.WebDriver;

public abstract class WebPage implements WebPageInterface {
    private BrowserHandlerInterface browserHandler;

    public WebPage(BrowserHandlerInterface browserHandler) {
        this.browserHandler = browserHandler;
        browserHandler
                .getOptions()
                .SCREENSHOT_ON_EVENT
                .setValue(true);
    }

    public WebPage(WebDriver driver) {
        this(new BrowserHandlerTimeLogger(new BrowserHandlerLoggerImpl(driver)));
    }

    public BrowserHandlerInterface getBrowserHandler() {
        return browserHandler;
    }
}