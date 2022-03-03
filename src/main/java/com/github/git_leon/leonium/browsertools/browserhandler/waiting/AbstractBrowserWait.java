package com.github.git_leon.leonium.browsertools.browserhandler.waiting;

import org.openqa.selenium.WebDriver;

/**
 * @author leonhunter
 * @created 05/03/2020 - 6:01 PM
 */
public class AbstractBrowserWait implements BrowserWaitInterface {
    private int waitSeconds;
    private WebDriver driver;

    public AbstractBrowserWait(int waitSeconds, WebDriver driver) {
        this.waitSeconds = waitSeconds;
        this.driver = driver;
    }

    @Override
    public void setWaitSeconds(int waitSeconds) {
        this.waitSeconds = waitSeconds;
    }

    @Override
    public int getWaitSeconds() {
        return waitSeconds;
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }
}
