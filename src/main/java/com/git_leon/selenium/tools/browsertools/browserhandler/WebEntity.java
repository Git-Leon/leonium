package com.git_leon.selenium.tools.browsertools.browserhandler;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

/**
 * @author leon on 4/12/18.
 */
public class WebEntity {
    private final By selector;
    private final WebDriver driver;
    private final BrowserWait wait;
    private int waitTime;

    public WebEntity(By by, WebDriver driver) {
        this(by, driver, 5);
    }

    public WebEntity(By by, WebDriver driver, Integer waitTime) {
        this.selector = by;
        this.driver = driver;
        this.waitTime = waitTime;
        this.wait = new BrowserWait(driver);
    }

    public void setWaitTime(int newWaitTime) {
        this.waitTime = newWaitTime;
    }

    public void click() {
        BrowserWait browserWait = new BrowserWait(driver);
        String[] waitConditions = {"visible", "enabled", "clickable", "stale"};
        wait.forConditions(selector, waitTime, waitConditions);
        getElement().click();
    }


    // toSelect by byType
    public Select toSelect() {
        String[] waitConditions = {"visible", "enabled", "stale"};
        wait.forConditions(selector, waitTime, waitConditions);
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
                wait.forKeyable(selector, waitTime);
            }

            we.sendKeys(keys);
        }
    }

    private WebElement getElement() {
        wait.forConditions(selector, waitTime, "present");
        return driver.findElement(selector);
    }

    public File getScreenshot() {
        WebElementScreenshot screenshot = new WebElementScreenshot(driver, getElement());
        return screenshot.getFile();
    }

    public By getSelector() {
        return this.selector;
    }

    @Override
    public String toString() {
        // By format = "[foundFrom] -> locator: term"
        // see RemoteWebElement toString() implementation
        String webElementStr = getElement().toString();
        return webElementStr
                .replaceAll("\\[.*?\\] -> ", "")
                .replaceAll("]", "");
    }
}
