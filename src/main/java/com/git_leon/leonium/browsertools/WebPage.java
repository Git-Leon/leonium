package com.git_leon.leonium.browsertools;

import com.git_leon.leonium.browsertools.browserhandler.BrowserHandler;
import org.openqa.selenium.WebDriver;

public abstract class WebPage implements WebPageInterface {
    private BrowserHandler browserHandler;

    public WebPage(BrowserHandler browserHandler) {
        this(browserHandler.getDriver());
    }

    public WebPage(WebDriver driver) {
        this.browserHandler = new BrowserHandler(driver);
    }

    public BrowserHandler getBrowserHandler() {
        return browserHandler;
    }
}