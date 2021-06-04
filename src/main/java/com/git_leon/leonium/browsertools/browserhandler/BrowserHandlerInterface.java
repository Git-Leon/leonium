package com.git_leon.leonium.browsertools.browserhandler;

import com.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitInterface;
import com.git_leon.leonium.browsertools.browserhandler.waiting.SelectorWaitCondition;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.awt.image.RasterFormatException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by leon on 5/25/20
 * Interface which wraps each DOM-interaction via `WebDriver` with smart-waits
 */
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
            we.getScreenshot(getOptions().SCREENSHOT_DIRECTORY.getValue());
        }
        we.click();
    }


    default Select select(By by) {
        WebEntity we = getWebEntity(by);
        if (getOptions().SCREENSHOT_ON_SELECT.getValue()) {
            we.getScreenshot(getOptions().SCREENSHOT_DIRECTORY.getValue());
        }
        return we.toSelect();
    }


    default void selectByIndex(By by, int index) {
        WebEntity we = getWebEntity(by);
        if (getOptions().SCREENSHOT_ON_SELECT.getValue()) {
            we.getScreenshot(getOptions().SCREENSHOT_DIRECTORY.getValue());
        }
        we.selectByIndex(index);
    }


    default void selectByVisibleText(By by, String visibleText) {
        WebEntity we = getWebEntity(by);
        if (getOptions().SCREENSHOT_ON_SELECT.getValue()) {
            we.getScreenshot(getOptions().SCREENSHOT_DIRECTORY.getValue());
        }
        we.selectByVisibleText(visibleText);
    }


    default void sendKeys(By by, String keys) {
        WebEntity we = getWebEntity(by);
        if (getOptions().SCREENSHOT_ON_SENDKEYS.getValue()) {
            we.getScreenshot(getOptions().SCREENSHOT_DIRECTORY.getValue());
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
                throw new RuntimeException(ee);
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
        return new Screenshot(getDriver(), getOptions().SCREENSHOT_DIRECTORY.getValue(), getDriver().getTitle());
    }

    default Screenshot screenshot(By by) {
        return new WebEntity(by, getDriver()).getScreenshot(getOptions().SCREENSHOT_DIRECTORY.getValue());
    }

    default String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    default void setWindowSize(int width, int height) {
        getDriver()
                .manage()
                .window()
                .setSize(new Dimension(width, height));
    }

    default Collection<Cookie> getCookies() {
        return new ArrayList<Cookie>(getDriver().manage().getCookies()) {
            @Override
            public String toString() {
                final StringJoiner cookieString = new StringJoiner(";");
                this.forEach(cookie -> {
                    cookieString.add(cookie
                            .getName()
                            .concat("=")
                            .concat(cookie.getValue()));
                });
                return cookieString.toString();
            }
        };
    }
}
