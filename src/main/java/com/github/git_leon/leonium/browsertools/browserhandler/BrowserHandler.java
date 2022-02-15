package com.github.git_leon.leonium.browsertools.browserhandler;

import com.github.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitLogger;
import org.openqa.selenium.WebDriver;

/**
 * Created by leon on 5/25/17.
 */
public class BrowserHandler extends BrowserHandlerAbstractClass {
    public BrowserHandler(WebDriver driver) {
        this(driver, 15);
    }

    public BrowserHandler(WebDriver driver, int waitSeconds) {
        this(driver, new BrowserWaitLogger(driver, waitSeconds));
    }

    public BrowserHandler(WebDriver driver, BrowserWaitInterface wait) {
        super(driver, wait);
    }
}
