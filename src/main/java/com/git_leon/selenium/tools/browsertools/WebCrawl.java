package com.git_leon.selenium.tools.browsertools;

import com.git_leon.selenium.tools.browsertools.browserhandler.BrowserHandler;
import com.git_leon.selenium.tools.browsertools.browserwrapper.chromedriver.ChromeBrowser;
import com.git_leon.selenium.tools.browsertools.browserwrapper.phantomjs.PhantomJSBrowser;
import com.git_leon.selenium.tools.logging.LoggerHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * Created by leon on 5/25/17.
 */
public abstract class WebCrawl {
    protected final BrowserHandler browserHandler;

    public WebCrawl() {
        this(new BrowserHandler(new PhantomJSBrowser()));
    }

    public WebCrawl(WebDriver browser, LoggerHandler loggerHandler) {
        this(new BrowserHandler(browser));
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
