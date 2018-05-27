package com.git_leon.selenium.tools.browsertools.browserhandler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.function.BiFunction;

/**
 * @author leon on 5/22/18.
 */
public enum SelectorWaitCondition {
    INVISIBILITY((By by, WebDriver driver) -> {
        new BrowserWait(driver).forInvisibility(by);
        return null;
    }),
    VISIBILITY((By by, WebDriver driver) -> {
        return new BrowserWait(driver).forVisibility(by);
    }),
    CLICKABILITY((By by, WebDriver driver) -> {
        return new BrowserWait(driver).forClickability(by);
    }),
    ENABLED((By by, WebDriver driver) -> {
        return new BrowserWait(driver).forEnabled(by, true);
    }),
    DISABLED((By by, WebDriver driver) -> {
        return new BrowserWait(driver).forEnabled(by, false);
    }),
    PRESENT((By by, WebDriver driver) -> {
        return new BrowserWait(driver).forPresence(by);
    }),
    PRESENCES((By by, WebDriver driver) -> {
        return new BrowserWait(driver).forPresences(by);
    }),
    NOT_STALE((By by, WebDriver driver) -> {
        return new BrowserWait(driver).forNotStale(by);
    });
    private final BiFunction<By, WebDriver, Object> waitFunction;

    SelectorWaitCondition(BiFunction<By, WebDriver, Object> waitFunction) {
        this.waitFunction = waitFunction;
    }

    public void waitFor(By by, WebDriver driver) {
        waitFunction.apply(by, driver);
    }
}
