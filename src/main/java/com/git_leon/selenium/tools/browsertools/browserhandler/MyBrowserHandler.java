package com.git_leon.selenium.tools.browsertools.browserhandler;

import com.git_leon.selenium.tools.logging.LoggerHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created by leon on 5/25/17.
 */
public class MyBrowserHandler {
    private final WebDriver driver;
    private final JavascriptExecutor javascriptExecutor;
    public final BrowserWaitLogger wait;
    public final BrowserHandlerOptions options;

    public MyBrowserHandler(WebDriver driver, LoggerHandler loggerHandler) {
        this.driver = driver;
        this.javascriptExecutor = (JavascriptExecutor) driver;
        this.options = new BrowserHandlerOptions();
        this.wait = new BrowserWaitLogger(driver);

        options.defaultWait.setValue(15);
        options.screenshotOnClick.setValue(true);
        options.screenshotOnSendKeys.setValue(true);
        options.screenshotOnSelect.setValue(true);
        options.screenshotOnEvent.setValue(true);
        options.continueOnNoSuchElement.setValue(false);
        options.continueOnTimeout.setValue(false);
        options.logOnScreenshot.setValue(false);
    }

    // return non-stale WebElement specified by byType
    public WebElement getElement(By by) {
        WebElement we = wait.forConditions(by, options.defaultWait.getValue(), "presence", "stale");
        return we;
    }

    // gets rid of the `by, driver` redundancy
    public WebEntity getWebEntity(By by) {
        return new WebEntity(by, driver);
    }

    // return list of WebElements with specified byType
    public List<WebElement> getElements(By by) {
        List<WebElement> elements = wait.forPresences(options.defaultWait.getValue(), by);
        int count = elements.size();
        boolean outcome = count > 0;
        return elements;
    }

    public String getPageLoadState() {
        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString();
    }

    public void navigateTo(String url) {
        driver.get(url);
        boolean outcome = wait.forPageLoad(options.defaultWait.getValue());
    }


    // click by byType
    public void click(By by) {
        WebEntity we = getWebEntity(by);
        String weStr = we.toString();
        we.click();
    }

    // toSelect by byType
    public Select select(By by) {
        WebEntity we = getWebEntity(by);
        return we.toSelect();
    }

    // toSelect by WebElement and toSelect index option
    public void selectByIndex(By by, int index) {
        WebEntity we = getWebEntity(by);
        String weStr = we.toString();
        we.selectByIndex(index);
    }

    // toSelect visible option by ByType
    public void selectByVisibleText(By by, String visibleText) {
        WebEntity we = getWebEntity(by);
        String weStr = we.toString();
        we.selectByVisibleText(visibleText);
    }

    // send keys by byType
    public void sendKeys(By by, CharSequence... keys) {
        WebEntity we = getWebEntity(by);
        String weStr = we.toString();
        we.sendKeys(keys);
    }

    public void close() {
        try {
            driver.close();
        } catch (Exception e) { // TODO - Replace with explicit Exception type
        }

        try {
            driver.quit();
        } catch (Exception e) { // TODO - Replace with explicit Exception type
        }

    }

    public void highlightElement(By by, String color) {
        WebElement we = wait.forConditions(by, options.defaultWait.getValue(), "presence", "visible");
        String script = "arguments[0].style.border='3px solid %s'";
        javascriptExecutor.executeScript(String.format(script, color), we);
    }

    public void highlightElements(By[] bys, String color) {
        for (By by : bys) {
            WebElement we = wait.forConditions(by, options.defaultWait.getValue(), "presence", "visible");
            String script = "arguments[0].style.border='3px solid %s'";
            javascriptExecutor.executeScript(String.format(script, color), we);
        }
    }
}
