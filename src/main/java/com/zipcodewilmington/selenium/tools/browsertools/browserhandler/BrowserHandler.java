package com.zipcodewilmington.selenium.tools.browsertools.browserhandler;

import com.zipcodewilmington.selenium.tools.browsertools.BrowserWait;
import com.zipcodewilmington.selenium.tools.logging.LoggerHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    // click by byType
    public void click(By by) {
        String[] waitConditions = {"presence", "visible", "enabled", "clickable", "stale"};
        wait.forConditions(by, options.defaultWait.getValue(), waitConditions);
        WebElement we = getElement(by);
        click(we);
    }

    // click by WebElement
    public void click(WebElement we) {
        WebElementScreenshot screenshot = null;
        if (options.screenshotOnClick.getValue()) {
            screenshot = new WebElementScreenshot(driver, we);
        }
        we.click();
        loggerHandler.info("Clicked [ %s ]", we.toString());
        loggerHandler.info("<img src = '%s'>", screenshot.getFile().getAbsolutePath());
    }
}
