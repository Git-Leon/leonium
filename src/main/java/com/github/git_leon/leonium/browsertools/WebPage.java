package com.github.git_leon.leonium.browsertools;

import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerInterface;

public abstract class WebPage implements WebPageInterface {
    private final BrowserHandlerInterface browserHandler;

    public WebPage(BrowserHandlerInterface browserHandler) {
        this.browserHandler = browserHandler;
    }

    public BrowserHandlerInterface getBrowserHandler() {
        return browserHandler;
    }
}