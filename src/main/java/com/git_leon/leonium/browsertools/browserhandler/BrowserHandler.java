package com.git_leon.leonium.browsertools.browserhandler;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created by leon on 5/25/17.
 */
public class BrowserHandler {
    private final WebDriver driver;
    private final JavascriptExecutor javascriptExecutor;
    public final BrowserWaitInterface wait;
    public final BrowserHandlerOptions options;

    public BrowserHandler(WebDriver driver) {
        this(driver, new BrowserWaitLogger(driver, 15));
    }


    public BrowserHandler(WebDriver driver, BrowserWaitInterface wait) {
        this.driver = driver;
        this.javascriptExecutor = (JavascriptExecutor) driver;
        this.options = new BrowserHandlerOptions();
        this.wait = wait;
    }

    // return non-stale WebElement specified by byType
    public WebElement getElement(By by) {
        return getWebEntity(by).getElement();
    }

    public WebEntity getWebEntity(By by) {
        return new WebEntity(by, driver, wait);
    }

    // return list of WebElements with specified byType
    public List<WebElement> getElements(By by) {
        return wait.forPresences(by);
    }

    public String getPageLoadState() {
        return javascriptExecutor.executeScript("return document.readyState").toString();
    }

    public void navigateTo(String url) {
        driver.get(url);
        wait.forPageLoad();
    }


    public void click(By by) {
        WebEntity we = getWebEntity(by);
        if (options.SCREENSHOT_ON_CLICK.getValue()) {
            we.getScreenshot();
        }
        we.click();
    }

    public Select select(By by) {
        WebEntity we = getWebEntity(by);
        if (options.SCREENSHOT_ON_SELECT.getValue()) {
            we.getScreenshot();
        }
        return we.toSelect();
    }

    public void selectByIndex(By by, int index) {
        WebEntity we = getWebEntity(by);
        if (options.SCREENSHOT_ON_SELECT.getValue()) {
            we.getScreenshot();
        }
        we.selectByIndex(index);
    }

    public void selectByVisibleText(By by, String visibleText) {
        WebEntity we = getWebEntity(by);
        if (options.SCREENSHOT_ON_SELECT.getValue()) {
            we.getScreenshot();
        }
        we.selectByVisibleText(visibleText);
    }

    public void sendKeys(By by, CharSequence... keys) {
        WebEntity we = getWebEntity(by);
        if (options.SCREENSHOT_ON_SENDKEYS.getValue()) {
            we.getScreenshot();
        }
        we.sendKeys(keys);
    }

    public void close() {
        try {
            driver.close();
        } catch (WebDriverException e) {
            try {
                driver.quit();
            } catch (WebDriverException ee) {
                throw new Error(ee);
            }
        }
    }

    public void highlightElement(By by, String color) {
        WebElement we = wait.forConditions(by,
                SelectorWaitCondition.PRESENT,
                SelectorWaitCondition.VISIBILITY);
        String script = "arguments[0].style.border='3px solid %s'";
        javascriptExecutor.executeScript(String.format(script, color), we);
    }

    public void highlightElements(By[] bys, String color) {
        for (By by : bys) {
            highlightElement(by, color);
        }
    }

    @Override
    public void finalize() {
        close();
    }
}
