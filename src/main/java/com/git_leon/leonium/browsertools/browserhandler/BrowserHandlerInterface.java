package com.git_leon.leonium.browsertools.browserhandler;

import com.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitInterface;
import com.git_leon.leonium.browsertools.browserhandler.waiting.SelectorWaitCondition;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.awt.image.RasterFormatException;
import java.util.List;

public interface BrowserHandlerInterface {
    void finalize();

    WebDriver getDriver();

    BrowserWaitInterface getWait();

    BrowserHandlerOptions getOptions();

    default JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) getDriver();
    }

    // return non-stale WebElement specified by byType

    default WebElement getElement(By by) {
        return getWebEntity(by).getElement();
    }


    default WebEntity getWebEntity(By by) {
        return new WebEntity(by, getDriver(), getWait());
    }

    // return list of WebElements with specified byType

    default List<WebElement> getElements(By by) {
        return getWait().forPresences(by);
    }


    default String getPageLoadState() {
        return getJavascriptExecutor().executeScript("return document.readyState").toString();
    }


    default void navigateTo(String url) {
        getDriver().get(url);
        getWait().forPageLoad();
    }


    default void click(By by) {
        WebEntity we = getWebEntity(by);
        if (getOptions().SCREENSHOT_ON_CLICK.getValue()) {
            we.getScreenshot();
        }
        we.click();
    }


    default Select select(By by) {
        WebEntity we = getWebEntity(by);
        if (getOptions().SCREENSHOT_ON_SELECT.getValue()) {
            we.getScreenshot();
        }
        return we.toSelect();
    }


    default void selectByIndex(By by, int index) {
        WebEntity we = getWebEntity(by);
        if (getOptions().SCREENSHOT_ON_SELECT.getValue()) {
            we.getScreenshot();
        }
        we.selectByIndex(index);
    }


    default void selectByVisibleText(By by, String visibleText) {
        WebEntity we = getWebEntity(by);
        if (getOptions().SCREENSHOT_ON_SELECT.getValue()) {
            we.getScreenshot();
        }
        we.selectByVisibleText(visibleText);
    }


    default void sendKeys(By by, String keys) {
        WebEntity we = getWebEntity(by);
        if (getOptions().SCREENSHOT_ON_SENDKEYS.getValue()) {
            we.getScreenshot();
        }
        we.sendKeys(keys);
    }


    default void close() {
        try {
            getDriver().close();
        } catch (WebDriverException e) {
            try {
                getDriver().quit();
            } catch (WebDriverException ee) {
                throw new Error(ee);
            }
        }
    }


    default void highlightElement(By by, String color) {
        WebElement we = getWait().forConditions(by,
                SelectorWaitCondition.PRESENT,
                SelectorWaitCondition.VISIBILITY);
        String script = "arguments[0].style.border='3px solid %s'";
        getJavascriptExecutor().executeScript(String.format(script, color), we);
    }


    default void highlightElements(By[] bys, String color) {
        for (By by : bys) {
            highlightElement(by, color);
        }
    }


    default Screenshot screenshot() {
        return new Screenshot(getDriver(), getDriver().getTitle());
    }

    default Screenshot screenshot(By by) {
        return new WebEntity(by, getDriver()).getScreenshot();
    }

    default String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }
}
