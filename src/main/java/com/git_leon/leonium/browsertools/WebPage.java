package com.git_leon.leonium.browsertools;

import com.git_leon.leonium.browsertools.browserhandler.BrowserHandler;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerLogger;
import org.openqa.selenium.WebDriver;

public abstract class WebPage implements WebPageInterface {
    private BrowserHandlerInterface browserHandler;

    public WebPage(BrowserHandlerInterface browserHandler) {
        this.browserHandler = browserHandler;
    }

    public WebPage(WebDriver driver) {
        this.browserHandler = new BrowserHandlerLogger(driver);
    }

    public BrowserHandlerInterface getBrowserHandler() {
        return browserHandler;
    }
}