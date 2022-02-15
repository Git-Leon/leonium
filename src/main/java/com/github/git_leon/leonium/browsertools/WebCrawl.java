package com.github.git_leon.leonium.browsertools;

import com.github.git_leon.leonium.browsertools.browserhandler.BrowserHandler;
import com.github.git_leon.leonium.browsertools.factories.BrowserHandlerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * Created by leon on 5/25/17.
 */
public abstract class WebCrawl {
    protected final BrowserHandler browserHandler;

    public WebCrawl() {
        this(BrowserHandlerFactory.PHANTOMJS.getDriver());
    }

    public WebCrawl(WebDriver driver) {
        this(new BrowserHandler(driver));
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
