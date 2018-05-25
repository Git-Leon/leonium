package com.git_leon.selenium.tools.browsertools;

import com.git_leon.selenium.tools.browsertools.browserhandler.BrowserHandler;
import com.git_leon.selenium.tools.browsertools.browserwrapper.BrowserFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by leon on 5/25/17.
 */
public abstract class WebCrawl {
    protected final BrowserHandler browserHandler;

    public WebCrawl() {
        this(BrowserFactory.PHANTOMJS.getBrowserHandler());
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
