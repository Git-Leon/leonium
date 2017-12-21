package com.git_leon.selenium.tools.browsertools;

import com.git_leon.selenium.tools.browsertools.browserhandler.BrowserHandler;
import com.git_leon.selenium.tools.browsertools.browserwrapper.FirefoxBrowser;
import com.git_leon.selenium.tools.logging.LoggerHandler;
import com.git_leon.selenium.tools.logging.LoggerWarehouse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by leon on 5/25/17.
 */
public abstract class WebCrawl {
    protected final BrowserHandler browserHandler;

    public WebCrawl() {
        this(new BrowserHandler(new FirefoxBrowser(), LoggerWarehouse.getLogger(WebCrawl.class)));
    }

    public WebCrawl(FirefoxDriver browser, LoggerHandler loggerHandler) {
        this(new BrowserHandler(browser, loggerHandler));
    }

    public WebCrawl(BrowserHandler browserHandler) {
        this.browserHandler = browserHandler;
    }

    @Before
    public void setup() {
    }

    @Test
    abstract public void test();

    @After
    public void tearDown() {
        try {
            browserHandler.close();
        } catch (NullPointerException npe) {

        }
    }
}
