package com.git_leon.leonium.browsertools.browserhandler;

import org.openqa.selenium.WebDriver;

/**
 * @author leonhunter
 * @created 04/29/2020 - 5:12 PM
 */
public class BrowserHandlerAbstractClass implements BrowserHandlerInterface {
    private final WebDriver driver;
    private final BrowserWaitInterface wait;
    private final BrowserHandlerOptions options = new BrowserHandlerOptions();

    public BrowserHandlerAbstractClass(WebDriver driver, BrowserWaitInterface wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public BrowserHandlerAbstractClass(BrowserHandlerInterface browserHandler) {
        this(browserHandler.getDriver(), browserHandler.getWait());
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public BrowserWaitInterface getWait() {
        return wait;
    }

    @Override
    public BrowserHandlerOptions getOptions() {
        return options;
    }

    @Override
    public void finalize() {
        close();
    }
}
