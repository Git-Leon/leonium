package com.git_leon.leonium.browsertools.browserhandler;

import com.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public interface BrowserHandlerDecoratorInterface extends BrowserHandlerInterface {
    BrowserHandlerInterface getBrowserHandlerDecoratee();

    default WebDriver getDriver() {
        return getBrowserHandlerDecoratee().getDriver();
    }


    default BrowserWaitInterface getWait() {
        return getBrowserHandlerDecoratee().getWait();
    }


    default BrowserHandlerOptions getOptions() {
        return getBrowserHandlerDecoratee().getOptions();
    }


    default JavascriptExecutor getJavascriptExecutor() {
        return getBrowserHandlerDecoratee().getJavascriptExecutor();
    }


    default WebElement getElement(By by) {
        return getBrowserHandlerDecoratee().getElement(by);
    }


    default WebEntity getWebEntity(By by) {
        return getBrowserHandlerDecoratee().getWebEntity(by);
    }


    default List<WebElement> getElements(By by) {
        return getBrowserHandlerDecoratee().getElements(by);
    }


    default String getPageLoadState() {
        return getBrowserHandlerDecoratee().getPageLoadState();

    }


    default void navigateTo(String newUrl) {
        getBrowserHandlerDecoratee().navigateTo(newUrl);
    }


    default void click(By by) {
        getBrowserHandlerDecoratee().click(by);
    }


    default Select select(By by) {
        return getBrowserHandlerDecoratee().select(by);
    }


    default void selectByIndex(By by, int index) {
        getBrowserHandlerDecoratee().selectByIndex(by, index);
    }


    default void selectByVisibleText(By by, String visibleText) {
        getBrowserHandlerDecoratee().selectByVisibleText(by, visibleText);
    }


    default void sendKeys(By by, String keys) {
        getBrowserHandlerDecoratee().sendKeys(by, keys);
    }


    default void close() {
        getBrowserHandlerDecoratee().close();
    }


    default void highlightElement(By by, String color) {
        getBrowserHandlerDecoratee().highlightElement(by, color);
    }


    default void highlightElements(By[] bys, String color) {
        getBrowserHandlerDecoratee().highlightElements(bys, color);
    }


    default Screenshot screenshot() {
        return getBrowserHandlerDecoratee().screenshot();
    }
}
