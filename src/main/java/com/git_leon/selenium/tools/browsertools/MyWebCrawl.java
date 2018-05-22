package com.git_leon.selenium.tools.browsertools;

import com.git_leon.selenium.tools.browsertools.browserhandler.MyBrowserHandler;
import com.git_leon.selenium.tools.browsertools.browserwrapper.FirefoxBrowser;
import com.git_leon.selenium.tools.logging.LoggerHandler;
import com.git_leon.selenium.tools.logging.LoggerWarehouse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * Created by leon on 5/25/17.
 */
public abstract class MyWebCrawl {
    protected final MyBrowserHandler browserHandler;

    public MyWebCrawl() {
        this(new MyBrowserHandler(new FirefoxBrowser(), LoggerWarehouse.getLogger(MyWebCrawl.class)));
    }

    public MyWebCrawl(WebDriver browser, LoggerHandler loggerHandler) {
        this(new MyBrowserHandler(browser, loggerHandler));
    }

    public MyWebCrawl(MyBrowserHandler browserHandler) {
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
