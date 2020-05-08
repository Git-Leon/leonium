package com.git_leon.leonium.browsertools.browserhandler.waiting;

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
public interface BrowserWaitLoggerInterfaceDecoratorInterface extends BrowserWaitLoggerInterface {
    BrowserWaitLoggerInterface getDecoratee();

    @Override
    default SimpleLoggerInterface getLogger() {
        return getDecoratee().getLogger();
    }

    @Override
    default BrowserWaitInterface getWait() {
        return getDecoratee().getWait();
    }

    @Override
    default WebElement forEnabled(By by, boolean isEnabled) {
        return getDecoratee().forEnabled(by, isEnabled);
    }

    @Override
    default WebElement forVisibility(By by) {
        return getDecoratee().forVisibility(by);
    }

    @Override
    default void forInvisibility(By by) {
        getDecoratee().forInvisibility(by);
    }

    @Override
    default WebElement forClickability(By by) {
        return getDecoratee().forClickability(by);
    }

    @Override
    default WebElement forPresence(By by) {
        return getDecoratee().forPresence(by);
    }

    @Override
    default WebElement forNotStale(By by) {
        return getDecoratee().forNotStale(by);
    }

    @Override
    default boolean forAlert() {
        return getDecoratee().forAlert();
    }

    @Override
    default boolean forUrlToContain(String... partUrls) {
        return getDecoratee().forUrlToContain(partUrls);
    }

    @Override
    default List<WebElement> forVisibilities(By by) {
        return getDecoratee().forVisibilities(by);
    }

    @Override
    default List<WebElement> forPresences(By by) {
        return getDecoratee().forPresences(by);
    }

    @Override
    default boolean forPageLoad() {
        return getDecoratee().forPageLoad();
    }

    @Override
    default boolean forPageState(String desiredState) {
        return getDecoratee().forPageState(desiredState);
    }

    @Override
    default WebElement forKeyable(By by) {
        return getDecoratee().forKeyable(by);
    }

    @Override
    default WebElement forConditions(By by, SelectorWaitCondition... waitConditions) {
        return getDecoratee().forConditions(by, waitConditions);
    }

    @Override
    default <T> boolean until(int waitSeconds, Function<WebDriver, T> condition) {
        return getDecoratee().until(waitSeconds, condition);
    }

    @Override
    default boolean isEnabled(WebElement we) {
        return getDecoratee().isEnabled(we);
    }

    @Override
    default boolean isDisplayed(WebElement we) {
        return getDecoratee().isDisplayed(we);
    }

    @Override
    default void info(String s, Object... args) {
        getDecoratee().info(s, args);
    }

    @Override
    default void error(String s, Object... args) {
        getDecoratee().error(s,args);
    }

    @Override
    default void warn(String s, Object... args) {
        getDecoratee().warn(s, args);
    }

    @Override
    default void throwable(Throwable t) {
        getDecoratee().throwable(t);
    }

    @Override
    default void throwable(Throwable t, Level level) {
        getDecoratee().throwable(t, level);
    }

    @Override
    default void log(Level level, String s, Object... objects) {
        getDecoratee().log(level, s, objects);
    }

    @Override
    default void enable() {
        getDecoratee().enable();
    }

    @Override
    default void disble() {
        getDecoratee().disble();
    }

    @Override
    default boolean isEnabled() {
        return getDecoratee().isEnabled();
    }

    @Override
    default int getWaitSeconds() {
        return getDecoratee().getWaitSeconds();
    }

    @Override
    default WebDriver getDriver() {
        return getDecoratee().getDriver();
    }
}
