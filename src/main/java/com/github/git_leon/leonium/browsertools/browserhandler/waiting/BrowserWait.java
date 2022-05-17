package com.github.git_leon.leonium.browsertools.browserhandler.waiting;

import org.openqa.selenium.WebDriver;

/**
 * @author leon on 4/12/18.
 */
public class BrowserWait implements BrowserWaitInterface {
    private int waitSeconds;
    private final WebDriver driver;

    public BrowserWait(int waitSeconds, WebDriver driver) {
        this.waitSeconds = waitSeconds;
        this.driver = driver;
    }

    @Override
    public int getWaitSeconds() {
        return waitSeconds;
    }

    @Override
    public void setWaitSeconds(int waitSeconds) {
        this.waitSeconds = waitSeconds;
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }
}