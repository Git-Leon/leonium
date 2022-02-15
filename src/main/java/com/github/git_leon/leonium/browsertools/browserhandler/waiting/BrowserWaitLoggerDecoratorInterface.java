package com.github.git_leon.leonium.browsertools.browserhandler.waiting;

import com.github.git_leon.logging.SimpleLoggerInterface;
import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.logging.Level;

/**
 * @author leonhunter
 * @created 05/08/2020 - 3:04 PM
 */
public interface BrowserWaitLoggerDecoratorInterface extends BrowserWaitLoggerInterface {
    SimpleLoggerInterface getLogger();

    BrowserWaitLoggerInterface getWait();

    @Override
    default WebElement forEnabled(By by, boolean isEnabled) {
        return getWait().forEnabled(by, isEnabled);
    }

    @Override
    default WebElement forVisibility(By by) {
        return getWait().forVisibility(by);
    }

    @Override
    default void forInvisibility(By by) {
        getWait().forInvisibility(by);
    }

    @Override
    default WebElement forClickability(By by) {
        return getWait().forClickability(by);
    }

    @Override
    default WebElement forPresence(By by) {
        return getWait().forPresence(by);
    }

    @Override
    default WebElement forNotStale(By by) {
        return getWait().forNotStale(by);
    }

    @Override
    default boolean forAlert() {
        return getWait().forAlert();
    }

    @Override
    default boolean forUrlToContain(String... partUrls) {
        return getWait().forUrlToContain(partUrls);
    }

    @Override
    default List<WebElement> forVisibilities(By by) {
        return getWait().forVisibilities(by);
    }

    @Override
    default List<WebElement> forPresences(By by) {
        return getWait().forPresences(by);
    }

    @Override
    default boolean forPageLoad() {
        return getWait().forPageLoad();
    }

    @Override
    default boolean forPageState(String desiredState) {
        return getWait().forPageState(desiredState);
    }

    @Override
    default WebElement forKeyable(By by) {
        return getWait().forKeyable(by);
    }

    @Override
    default WebElement forConditions(By by, SelectorWaitCondition... waitConditions) {
        return getWait().forConditions(by, waitConditions);
    }

    @Override
    default <T> boolean until(int waitSeconds, Function<WebDriver, T> condition) {
        return getWait().until(waitSeconds, condition);
    }

    @Override
    default boolean isEnabled(WebElement we) {
        return getWait().isEnabled(we);
    }

    @Override
    default boolean isDisplayed(WebElement we) {
        return getWait().isDisplayed(we);
    }

    @Override
    default void info(String s, Object... args) {
        getLogger().info(s, args);
    }

    @Override
    default void error(String s, Object... args) {
        getLogger().error(s, args);
    }

    @Override
    default void warn(String s, Object... args) {
        getLogger().warn(s, args);
    }

    @Override
    default void throwable(Throwable t) {
        getLogger().throwable(t);
    }

    @Override
    default void throwable(Throwable t, Level level) {
        getLogger().throwable(t, level);
    }

    @Override
    default void log(Level level, String s, Object... objects) {
        getLogger().log(level, s, objects);
    }

    @Override
    default void enable() {
        getLogger().enable();
    }

    @Override
    default void disable() {
        getLogger().disable();
    }

    @Override
    default boolean isEnabled() {
        return getLogger().isEnabled();
    }

    @Override
    default int getWaitSeconds() {
        return getWait().getWaitSeconds();
    }

    @Override
    default WebDriver getDriver() {
        return getWait().getDriver();
    }
}
