package com.git_leon.leonium.browsertools.browserhandler;

import org.openqa.selenium.WebDriver;

/**
 * Created by leon on 5/25/17.
 */
public class BrowserHandler extends BrowserHandlerAbstractClass {
    public BrowserHandler(WebDriver driver) {
        this(driver, new BrowserWaitLogger(driver, 15));
    }

    public BrowserHandler(WebDriver driver, BrowserWaitInterface wait) {
        super(driver, wait);
    }
}
