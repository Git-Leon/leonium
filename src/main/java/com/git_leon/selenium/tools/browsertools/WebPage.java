package com.git_leon.selenium.tools.browsertools;

import com.git_leon.selenium.tools.ReflectionUtils;
import com.git_leon.selenium.tools.browsertools.browserhandler.BrowserHandler;
import org.openqa.selenium.By;

public abstract class WebPage {
    protected WebCrawl crawler;
    protected BrowserHandler browserHandler;

    public WebPage(WebCrawl web) {
        this.crawler = web;
        this.browserHandler = crawler.browserHandler;
    }

    public By[] getDeclaredBys() {
        return ReflectionUtils.getFieldValues(this, By.class).toArray(By[]::new);
    }

    public void highlightElements() {
        browserHandler.highlightElements(getDeclaredBys(), "yellow");
    }
}