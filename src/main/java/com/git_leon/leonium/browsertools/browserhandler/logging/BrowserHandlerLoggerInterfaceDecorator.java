package com.git_leon.leonium.browsertools.browserhandler.logging;

import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerOptions;
import com.git_leon.leonium.browsertools.browserhandler.Screenshot;
import com.git_leon.leonium.browsertools.browserhandler.WebEntity;
import com.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitInterface;
import com.github.git_leon.logging.SimpleLoggerInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * @author leonhunter
 * @created 05/04/2020 - 10:17 PM
 */
public interface BrowserHandlerLoggerDecoratorInterface extends BrowserHandlerLoggerInterface {
    BrowserHandlerLoggerInterface getDecoratee();

    @Override
    default SimpleLoggerInterface getLogger() {
        return getDecoratee().getLogger();
    }

    @Override
    default WebElement getElement(By by) {
        return getDecoratee().getElement(by);
    }

    @Override
    default WebEntity getWebEntity(By by) {
        return getDecoratee().getWebEntity(by);
    }

    @Override
    default List<WebElement> getElements(By by) {
        return getDecoratee().getElements(by);
    }

    @Override
    default String getPageLoadState() {
        return getDecoratee().getPageLoadState();
    }

    @Override
    default void navigateTo(String newUrl) {
        getDecoratee().navigateTo(newUrl);
    }

    @Override
    default void click(By by) {
        getDecoratee().click(by);
    }

    @Override
    default Select select(By by) {
        return getDecoratee().select(by);
    }

    @Override
    default void selectByIndex(By by, int index) {
        getDecoratee().selectByIndex(by, index);
    }

    @Override
    default void selectByVisibleText(By by, String visibleText) {
        getDecoratee().selectByVisibleText(by, visibleText);
    }

    @Override
    default void sendKeys(By by, String keys) {
        getDecoratee().sendKeys(by, keys);
    }

    @Override
    default void close() {
        getDecoratee().close();
    }

    @Override
    default void highlightElement(By by, String color) {
        getDecoratee().highlightElement(by, color);
    }

    @Override
    default void highlightElements(By[] bys, String color) {
        getDecoratee().highlightElements(bys, color);
    }

    @Override
    default Screenshot screenshot() {
        return getDecoratee().screenshot();
    }

    @Override
    default Screenshot screenshot(By by) {
        return getDecoratee().screenshot(by);
    }

    @Override
    default String getCurrentUrl() {
        return getDecoratee().getCurrentUrl();
    }

    @Override
    default BrowserHandlerInterface getBrowserHandlerDecoratee() {
        return getDecoratee().getBrowserHandlerDecoratee();
    }

    @Override
    default WebDriver getDriver() {
        return getDecoratee().getDriver();
    }

    @Override
    default BrowserWaitInterface getWait() {
        return getDecoratee().getWait();
    }

    @Override
    default BrowserHandlerOptions getOptions() {
        return getDecoratee().getOptions();
    }

    @Override
    default JavascriptExecutor getJavascriptExecutor() {
        return getDecoratee().getJavascriptExecutor();
    }

    @Override
    default void finalize() {

    }
}
