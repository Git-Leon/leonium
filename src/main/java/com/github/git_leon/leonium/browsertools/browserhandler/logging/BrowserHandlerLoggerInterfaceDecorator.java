package com.github.git_leon.leonium.browsertools.browserhandler.logging;

import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerOptions;
import com.github.git_leon.leonium.browsertools.browserhandler.core.Screenshot;
import com.github.git_leon.leonium.browsertools.browserhandler.core.WebEntity;
import com.github.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitInterface;
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
 * Interface which decorates each composite `BrowserHandlerLoggerInterface` call
 */
public interface BrowserHandlerLoggerInterfaceDecorator extends BrowserHandlerLoggerInterface {
    BrowserHandlerLoggerInterface getBrowserHandlerLoggerDecoratee();

    @Override
    default SimpleLoggerInterface getLogger() {
        return getBrowserHandlerLoggerDecoratee().getLogger();
    }

    @Override
    default WebElement getElement(By by) {
        return getBrowserHandlerLoggerDecoratee().getElement(by);
    }

    @Override
    default WebEntity getWebEntity(By by) {
        return getBrowserHandlerLoggerDecoratee().getWebEntity(by);
    }

    @Override
    default List<WebElement> getElements(By by) {
        return getBrowserHandlerLoggerDecoratee().getElements(by);
    }

    @Override
    default String getPageLoadState() {
        return getBrowserHandlerLoggerDecoratee().getPageLoadState();
    }

    @Override
    default void navigateTo(String newUrl) {
        getBrowserHandlerLoggerDecoratee().navigateTo(newUrl);
    }

    @Override
    default void click(By by) {
        getBrowserHandlerLoggerDecoratee().click(by);
    }

    @Override
    default Select select(By by) {
        return getBrowserHandlerLoggerDecoratee().select(by);
    }

    @Override
    default void selectByIndex(By by, int index) {
        getBrowserHandlerLoggerDecoratee().selectByIndex(by, index);
    }

    @Override
    default void selectByVisibleText(By by, String visibleText) {
        getBrowserHandlerLoggerDecoratee().selectByVisibleText(by, visibleText);
    }

    @Override
    default void sendKeys(By by, String keys) {
        getBrowserHandlerLoggerDecoratee().sendKeys(by, keys);
    }

    @Override
    default void close() {
        getBrowserHandlerLoggerDecoratee().close();
    }

    @Override
    default void highlightElement(By by, String color) {
        getBrowserHandlerLoggerDecoratee().highlightElement(by, color);
    }

    @Override
    default void highlightElements(By[] bys, String color) {
        getBrowserHandlerLoggerDecoratee().highlightElements(bys, color);
    }

    @Override
    default Screenshot screenshot() {
        return getBrowserHandlerLoggerDecoratee().screenshot();
    }

    @Override
    default Screenshot screenshot(By by) {
        return getBrowserHandlerLoggerDecoratee().screenshot(by);
    }

    @Override
    default String getCurrentUrl() {
        return getBrowserHandlerLoggerDecoratee().getCurrentUrl();
    }

    @Override
    default BrowserHandlerInterface getBrowserHandlerDecoratee() {
        return getBrowserHandlerLoggerDecoratee().getBrowserHandlerDecoratee();
    }

    @Override
    default WebDriver getDriver() {
        return getBrowserHandlerLoggerDecoratee().getDriver();
    }

    @Override
    default BrowserWaitInterface getWait() {
        return getBrowserHandlerLoggerDecoratee().getWait();
    }

    @Override
    default BrowserHandlerOptions getOptions() {
        return getBrowserHandlerLoggerDecoratee().getOptions();
    }

    @Override
    default JavascriptExecutor getJavascriptExecutor() {
        return getBrowserHandlerLoggerDecoratee().getJavascriptExecutor();
    }
}
