package com.git_leon.selenium.tools.browsertools;

import com.git_leon.selenium.tools.browsertools.browserhandler.BrowserHandler;
import com.git_leon.selenium.tools.browsertools.browserwrapper.BrowserHandlerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * Created by leon on 5/25/17.
 */
public abstract class WebCrawl {
    protected final BrowserHandler browserHandler;
    protected final WebDriver driver;

    public WebCrawl() {
        this(BrowserHandlerFactory.HTMLUNIT.getDriver());
    }

    public WebCrawl(WebDriver driver) {
        this.driver = driver;
        this.browserHandler = new BrowserHandler(driver);
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
