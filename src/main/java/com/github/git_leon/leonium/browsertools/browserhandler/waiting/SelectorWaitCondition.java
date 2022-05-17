package com.github.git_leon.leonium.browsertools.browserhandler.waiting;

import com.github.git_leon.functionalinterface.TriFunction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author leon on 5/22/18.
 */
public enum SelectorWaitCondition {
    INVISIBILITY((By by, WebDriver driver, Integer waitSeconds) -> {
        new BrowserWait(driver, waitSeconds).forInvisibility(by);
        return null;
    }),
    VISIBILITY((By by, WebDriver driver, Integer waitSeconds) -> new BrowserWait(driver, waitSeconds).forVisibility(by)),
    CLICKABILITY((By by, WebDriver driver, Integer waitSeconds) -> new BrowserWait(driver, waitSeconds).forClickability(by)),
    ENABLED((By by, WebDriver driver, Integer waitSeconds) -> new BrowserWait(driver, waitSeconds).forEnabled(by, true)),
    DISABLED((By by, WebDriver driver, Integer waitSeconds) -> new BrowserWait(driver, waitSeconds).forEnabled(by, false)),
    PRESENT((By by, WebDriver driver, Integer waitSeconds) -> new BrowserWait(driver, waitSeconds).forPresence(by)),
    PRESENCES((By by, WebDriver driver, Integer waitSeconds) -> new BrowserWait(driver, waitSeconds).forPresences(by)),
    NOT_STALE((By by, WebDriver driver, Integer waitSeconds) -> new BrowserWait(driver, waitSeconds).forNotStale(by));
    private final TriFunction<By, WebDriver, Integer, Object> waitFunction;

    SelectorWaitCondition(TriFunction<By, WebDriver, Integer, Object> waitFunction) {
        this.waitFunction = waitFunction;
    }

    public void waitFor(By by, WebDriver driver, int waitSeconds) {
        waitFunction.apply(by, driver, waitSeconds);
    }
}
