package com.zipcodewilmington.selenium.tools.browsertools.browserhandler;

import com.zipcodewilmington.selenium.tools.StringUtils;
import com.zipcodewilmington.selenium.tools.browsertools.BrowserWait;
import com.zipcodewilmington.selenium.tools.logging.LoggerHandler;
import javafx.scene.web.WebEngine;
import org.eclipse.jetty.util.StringUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leon on 5/25/17.
 */
public class BrowserHandler {
    private final WebDriver driver;
    private final LoggerHandler loggerHandler;
    private final BrowserWait wait;
    private final BrowserHandlerOptions options;

    public BrowserHandler(WebDriver driver, LoggerHandler loggerHandler) {
        this.driver = driver;
        this.loggerHandler = loggerHandler;
        this.options = new BrowserHandlerOptions();
        this.wait = new BrowserWait(driver, loggerHandler);

        options.defaultWait.setValue(15);
        options.screenshotOnClick.setValue(true);
        options.screenshotOnSendKeys.setValue(true);
        options.screenshotOnSelect.setValue(true);
        options.screenshotOnEvent.setValue(true);
        options.continueOnNoSuchElement.setValue(false);
        options.continueOnTimeout.setValue(false);
    }

    // return non-stale WebElement specified by byType
    public WebElement getElement(By by) {
        WebElement we = wait.forConditions(by, options.defaultWait.getValue(), "presence", "stale");
        loggerHandler.info("Located element [ %s ]; %s", we.toString(), we != null);
        return we;
    }

    // return list of WebElements with specified byType
    public List<WebElement> getElements(By by) {
        List<WebElement> elements = wait.forPresences(options.defaultWait.getValue(), by);
        int count = elements.size();
        boolean outcome = count > 0;
        loggerHandler.eval(outcome, "located %s elements [ %s ]", count, by);
        return elements;
    }

    public String getPageLoadState() {
        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString();
    }

    public void navigateTo(String url) {
        driver.get(url);
        boolean outcome = wait.forPageLoad(options.defaultWait.getValue());
        loggerHandler.eval(outcome, "navigated to url '%s", url);
    }

    // click by byType
    public void click(By by) {
        String[] waitConditions = {"presence", "visible", "enabled", "clickable", "stale"};
        wait.forConditions(by, options.defaultWait.getValue(), waitConditions);
        WebElement we = getElement(by);
        click(we);
    }

    // click by WebElement
    private void click(WebElement we) {
        WebElementScreenshot screenshot = null;
        if (options.screenshotOnClick.getValue()) {
            screenshot = new WebElementScreenshot(driver, we);
        }
        we.click();
        loggerHandler.info("Clicked [ %s ]", we.toString());
        loggerHandler.info(screenshot.toString());
    }


    // select by byType
    public Select select(By by) {
        String[] waitConditions = {"visible", "enabled", "stale"};
        int defaultWait = options.defaultWait.getValue();

        wait.forConditions(by, defaultWait, waitConditions);
        WebElement we = getElement(by);
        return select(we);
    }

    // select by WebElement
    public Select select(WebElement we) {
        Select select = new Select(we);
        WebElementScreenshot screenshot = null;
        if (options.screenshotOnSelect.getValue()) {
            screenshot = new WebElementScreenshot(driver, we);
        }
        loggerHandler.info("Selected [ %s ]", we.toString());
        loggerHandler.info(screenshot.toString());
        return select;
    }

    public void selectByIndex(By by, int index) {
        selectByIndex(getElement(by), index);
    }

    // select by WebElement and select index option
    public void selectByIndex(WebElement we, int index) {
        select(we).selectByIndex(index);
        WebElementScreenshot screenshot = null;
        if (options.screenshotOnSelect.getValue()) {
            screenshot = new WebElementScreenshot(driver, we);
        }
        loggerHandler.eval(true, "selected index '%s' from [ %s ]", index, we.toString());
        loggerHandler.info(screenshot.toString());
    }

    public void selectByRandomIndex(By by) {
        selectByRandomIndex(wait.forConditions(by, options.defaultWait.getValue(), "visible", "enabled", "stale"));
    }

    public void selectByRandomIndex(WebElement we) {
        Select select = new Select(we);
        selectByIndex(we, select.getOptions().size() - 1);
    }

    // select visible option by ByType
    public void selectByVisibleText(By by, String visibleText) {
        selectByVisibleText(getElement(by), visibleText);
    }

    // select by WebElement and select visible text
    public void selectByVisibleText(WebElement we, String visibleText) {
        select(we).selectByVisibleText(visibleText);
        WebElementScreenshot screenshot = null;
        if (options.screenshotOnSelect.getValue()) {
            screenshot = new WebElementScreenshot(driver, we);
        }
        loggerHandler.eval(true, "selected index '%s' from [ %s ]", visibleText, we.toString());
        loggerHandler.info(screenshot.toString());
    }

    // send keys by byType
    public void sendKeys(By by, CharSequence... keys) {
        sendKeys(wait.forKeyable(by, options.defaultWait.getValue()), keys);
    }

    // send keys by WebElement
    public void sendKeys(WebElement we, CharSequence... keys) {
        if (keys == null) {
            return;
        }

        try {
            we.clear();
            we.sendKeys(Keys.HOME);
        } catch (InvalidElementStateException iese) {
            // NOTE** some input elements cannot be cleared
            // this exception is caught when an unclearable element
            // invokes the .clear() method
            wait.forKeyable(toByVal(we), options.defaultWait.getValue());
        }

        we.sendKeys(keys);
        loggerHandler.info("Sent keys '%s' to [ %s ]", StringUtils.toString(keys), we.toString());
        WebElementScreenshot screenshot = null;
        if (options.screenshotOnSelect.getValue()) {
            screenshot = new WebElementScreenshot(driver, we);
        }
        loggerHandler.info(screenshot.toString());
    }

    // return ByType of WebElement
    public By toByVal(WebElement we) {
        try {
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
        } catch (Exception e) {
        }
        return By.class.cast(we);
    }

}
