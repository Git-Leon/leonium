package com.github.git_leon.leonium.browsertools;

import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerInterface;
import com.github.git_leon.leonium.browsertools.factories.BrowserHandlerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by leon on 5/25/17.
 */
@Deprecated
public abstract class WebCrawl {
    protected final BrowserHandlerInterface browserHandler;

    public WebCrawl() {
        this(BrowserHandlerFactory.PHANTOMJS.getBrowserHandler());
    }

    public WebCrawl(BrowserHandlerInterface browserHandler) {
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
