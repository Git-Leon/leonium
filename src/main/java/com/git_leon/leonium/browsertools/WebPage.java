package com.git_leon.leonium.browsertools;

import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerLoggerImpl;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerTimeLogger;
import org.openqa.selenium.WebDriver;

public abstract class WebPage implements WebPageInterface {
    private BrowserHandlerInterface browserHandler;

    public WebPage(BrowserHandlerInterface browserHandler) {
        this.browserHandler = browserHandler;
    }

    public WebPage(WebDriver driver) {
        this(new BrowserHandlerLoggerImpl(new BrowserHandlerTimeLogger(driver)));
    }

    public BrowserHandlerInterface getBrowserHandler() {
        return browserHandler;
    }
}