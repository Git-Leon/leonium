package com.github.git_leon.leonium.browsertools.browserhandler;

import com.github.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author leon on 4/12/18.
 * wraps each DOM-interaction via `WebElement` with smart-waits
 */
public class WebEntity implements WebEntityInterface {
    private final By selector;
    private final WebDriver driver;
    private BrowserWaitInterface wait;
    private WebElementScreenshot screenshot;

    public WebEntity(By by, WebDriver driver) {
        this(by, driver, new BrowserWaitLogger(driver));
    }

    public WebEntity(By by, WebDriver driver, BrowserWaitInterface browserWait) {
        this.selector = by;
        this.driver = driver;
        this.wait = browserWait;
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
    public By getSelector() {
        return selector;
    }

    @Override
    public String toString() {
        return WebEntityInterface.toString(getElement());
    }

    @Override
    public WebElementScreenshot getScreenshot() {
        return screenshot;
    }

    @Override
    public WebElementScreenshot getScreenshot(String fileDirectory) {
        this.screenshot = WebEntityInterface.super.getScreenshot(fileDirectory);
        return this.screenshot;
    }
}
