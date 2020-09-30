package com.git_leon.leonium.browsertools.browserhandler;

import com.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitInterface;
import com.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitLogger;
import com.git_leon.leonium.browsertools.browserhandler.waiting.SelectorWaitCondition;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.awt.image.RasterFormatException;

/**
 * @author leon on 4/12/18.
 * wraps each DOM-interaction via `WebElement` with smart-waits
 */
public class WebEntity {
    private final By selector;
    private final WebDriver driver;
    private BrowserWaitInterface wait;
    private WebElementScreenshot screenshot;

    public WebEntity(By by, WebDriver driver) {
        this(by, driver, new BrowserWaitLogger(driver));
    }

    public WebEntity(By by, WebDriver driver, BrowserWaitInterface browserWait) {
        this.selector = by;
        this.driver = driver;
        this.wait = browserWait;
    }

    public void click() {
        wait.forConditions(selector,
                SelectorWaitCondition.VISIBILITY,
                SelectorWaitCondition.ENABLED,
                SelectorWaitCondition.CLICKABILITY,
                SelectorWaitCondition.NOT_STALE);
        getElement().click();
    }


    // toSelect by byType
    public Select toSelect() {
        wait.forConditions(selector,
                SelectorWaitCondition.VISIBILITY,
                SelectorWaitCondition.ENABLED,
                SelectorWaitCondition.NOT_STALE);
        WebElement we = getElement();
        Select select = new Select(we);
        return select;
    }

    // toSelect by WebElement and toSelect index option
    public void selectByIndex(int index) {
        toSelect().selectByIndex(index);
    }

    // toSelect visible option by ByType
    public void selectByVisibleText(String visibleText) {
        toSelect().selectByVisibleText(visibleText);
    }

    // send keys by byType
    public void sendKeys(CharSequence... keys) {
        WebElement we = getElement();
        if (keys != null) {
            try {
                we.clear();
                we.sendKeys(Keys.HOME);
            } catch (InvalidElementStateException iese) {
                // NOTE** some input elements cannot be cleared
                // this exception is caught when an unclearable element
                // invokes the .clear() method
                wait.forKeyable(selector);
            }

            we.sendKeys(keys);
        }
    }

    public WebElement getElement() {
        wait.forPresence(selector);
        return driver.findElement(selector);
    }

    public Screenshot getScreenshot(String fileDirectory) {
        if (screenshot == null) {
            try {
                wait.forConditions(selector, SelectorWaitCondition.VISIBILITY);
                WebElementScreenshot screenshot = new WebElementScreenshot(driver, selector, fileDirectory);
                this.screenshot = screenshot;
            } catch (RasterFormatException rfe) {
                // TODO - Identify how to elegantly avoid this
                return null;
            }
        }
        return screenshot;
    }

    public By getSelector() {
        return this.selector;
    }

    @Override
    public String toString() {
        return toString(getElement());
    }

    public static String toString(WebElement webElement) {
        // By format = "[foundFrom] -> locator: term"
        // see RemoteWebElement toString() implementation
        String webElementStr = webElement.toString();
        return webElementStr
                .replaceAll("\\[.*?\\] -> ", "")
                .replaceAll("]", "");
    }

    public static By getByValue(WebElement we) {
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
}
