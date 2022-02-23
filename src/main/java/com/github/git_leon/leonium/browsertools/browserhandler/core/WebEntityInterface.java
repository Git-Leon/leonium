package com.github.git_leon.leonium.browsertools.browserhandler.core;

import com.github.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.waiting.SelectorWaitCondition;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.awt.image.RasterFormatException;
import java.io.File;

public interface WebEntityInterface {
    static String toString(WebElement webElement) {
        // By format = "[foundFrom] -> locator: term"
        // see RemoteWebElement toString() implementation
        String webElementStr = webElement.toString();
        return webElementStr
                .replaceAll("\\[.*?\\] -> ", "")
                .replaceAll("]", "")
                .replaceAll(":", "")
                .replaceAll(" ", "-");
    }

    static By getByValue(WebElement we) {
        // By format = "[foundFrom] -> locator: term"
        // see RemoteWebElement toString() implementation
        String[] data = we.toString().split(" -> ")[1].replace("]", "").split(": ");
        String locator = data[0];
        String term = data[1];

        switch (locator) {
            case "xpath":
                return By.xpath(term);
            case "css selector":
                return By.cssSelector(term);
            case "id":
                return By.id(term);
            case "tag name":
                return By.tagName(term);
            case "name":
                return By.name(term);
            case "link text":
                return By.linkText(term);
            case "class name":
                return By.className(term);
        }
        return (By) we;
    }

    WebDriver getDriver();

    BrowserWaitInterface getWait();

    WebElementScreenshot getScreenshot();

    By getSelector();

    default void click() {
        getWait().forConditions(getSelector(),
                SelectorWaitCondition.VISIBILITY,
                SelectorWaitCondition.ENABLED,
                SelectorWaitCondition.CLICKABILITY,
                SelectorWaitCondition.NOT_STALE);
        getElement().click();
    }

    // toSelect by byType
    default Select toSelect() {
        getWait().forConditions(getSelector(),
                SelectorWaitCondition.VISIBILITY,
                SelectorWaitCondition.ENABLED,
                SelectorWaitCondition.NOT_STALE);
        return new Select(getElement());
    }

    // toSelect by WebElement and toSelect index option
    default void selectByIndex(int index) {
        toSelect().selectByIndex(index);
    }

    // toSelect visible option by ByType
    default void selectByVisibleText(String visibleText) {
        toSelect().selectByVisibleText(visibleText);
    }

    // send keys by byType
    default void sendKeys(CharSequence... keys) {
        WebElement we = getElement();
        if (keys != null) {
            try {
                we.clear();
                we.sendKeys(Keys.HOME);
            } catch (InvalidElementStateException iese) {
                // NOTE** some input elements cannot be cleared
                // this exception is caught when an unclearable element
                // invokes the .clear() method
                getWait().forKeyable(getSelector());
            }

            we.sendKeys(keys);
        }
    }

    default WebElement getElement() {
        getWait().forPresence(getSelector());
        return getDriver().findElement(getSelector());
    }

    default WebElementScreenshot getScreenshot(String fileDirectory) {
        try {
            getWait().forConditions(getSelector(), SelectorWaitCondition.VISIBILITY);
            return new WebElementScreenshot(getDriver(), getSelector(), new File(fileDirectory).getParent().concat("/"));
        } catch (RasterFormatException rfe) {
            return null;
        }
    }

    String toString();
}
