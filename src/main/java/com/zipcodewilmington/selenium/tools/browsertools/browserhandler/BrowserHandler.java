package com.zipcodewilmington.selenium.tools.browsertools.browserhandler;

import com.zipcodewilmington.selenium.tools.StringUtils;
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
    public final BrowserHandlerOptions options;

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

        screenshot(options.screenshotOnClick, we);
        we.click();
        loggerHandler.info("Clicked [ %s ]", we.toString());
    }

    // select by byType
    public Select select(By by) {
        String[] waitConditions = {"visible", "enabled", "stale"};
        int defaultWait = options.defaultWait.getValue();

        wait.forConditions(by, defaultWait, waitConditions);
        WebElement we = getElement(by);
        Select select = new Select(we);
        loggerHandler.info("Selected [ %s ]", we.toString());
        screenshot(options.screenshotOnSelect, we);
        return select;
    }

    // select by WebElement and select index option
    public void selectByIndex(By by, int index) {
        WebElement we = getElement(by);
        select(by).selectByIndex(index);
        loggerHandler.eval(true, "selected index '%s' from [ %s ]", index, we.toString());
        screenshot(options.screenshotOnSelect, we);
    }

    public void selectByRandomIndex(By by) {
        String[] elementConditions = {"pres", "visible", "enabled", "stale"};
        wait.forConditions(by, options.defaultWait.getValue(), elementConditions);
        WebElement we = getElement(by);
        Select select = new Select(we); // only used to get options size
        selectByIndex(by, select.getOptions().size() - 1);
    }

    // select visible option by ByType
    public void selectByVisibleText(By by, String visibleText) {
        WebElement we = getElement(by);
        select(by).selectByVisibleText(visibleText);
        loggerHandler.eval(true, "selected index '%s' from [ %s ]", visibleText, we.toString());
        screenshot(options.screenshotOnSelect, we);
    }

    // send keys by byType
    public void sendKeys(By by, CharSequence... keys) {
        wait.forKeyable(by, options.defaultWait.getValue());
        WebElement we = getElement(by);

        if (keys != null) {
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
            screenshot(options.screenshotOnSendKeys, we);
        }
    }

    // private method
    private void screenshot(BrowserHandlerOptions.BrowserOption<Boolean> browserOption, WebElement we) {
        WebElementScreenshot screenshot = null;
        if (browserOption.getValue()) {
            screenshot = new WebElementScreenshot(driver, we);
            loggerHandler.info(screenshot.toString());
        }
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

    public void close() {
        loggerHandler.info("Closing browser...");
        try {
            driver.close();
        } catch (Exception e) { // TODO - Replace with explicit Exception type
        }

        try {
            driver.quit();
        } catch (Exception e) { // TODO - Replace with explicit Exception type
        }

    }
}
