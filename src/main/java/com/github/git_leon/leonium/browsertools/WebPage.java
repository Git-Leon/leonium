package com.github.git_leon.leonium.browsertools;

import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.reporting.BrowserHandlerLayeredLogger;
import com.github.git_leon.leonium.extentreporting.ExtentTestLoggerFactoryManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Collection;

public abstract class WebPage implements WebPageInterface {
    private final BrowserHandlerInterface browserHandler;

    public WebPage(BrowserHandlerInterface browserHandler) {
        this.browserHandler = browserHandler;
    }

    public BrowserHandlerInterface getBrowserHandler() {
        return browserHandler;
    }
}