package com.zipcodewilmington.selenium.webcrawl;

import com.zipcodewilmington.selenium.tools.browsertools.browserhandler.BrowserHandler;
import com.zipcodewilmington.selenium.tools.browsertools.browserwrapper.FirefoxBrowser;
import com.zipcodewilmington.selenium.tools.logging.LoggerHandler;
import com.zipcodewilmington.selenium.tools.logging.LoggerWarehouse;
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

    public void setup() {
    }

    public void run() {
        try {
            setup();
            test();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            tearDown();
        }

    }

    abstract public void test();

    public void tearDown() {
        try {
            browserHandler.close();
        } catch (NullPointerException npe) {

        }
    }
}
