package com.git_leon.leonium.browsertools.browserhandler;

import com.github.git_leon.logging.SimpleLoggerInterface;
import com.github.git_leon.logging.SimpleLoggerWarehouse;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author leonhunter
 * @created 04/29/2020 - 5:03 PM
 */
public class BrowserHandlerLogger implements BrowserHandlerInterface {
    private final BrowserHandlerInterface decoratee;
    private final SimpleLoggerInterface logger;

    public BrowserHandlerLogger(BrowserHandlerInterface browserHandler, SimpleLoggerInterface logger) {
        this.decoratee = browserHandler;
        this.logger = logger;
    }

    public BrowserHandlerLogger(BrowserHandlerInterface browserHandler) {
        this(browserHandler, SimpleLoggerWarehouse.getLogger(browserHandler.getDriver().toString()));
    }

    public SimpleLoggerInterface getLogger() {
        return logger;
    }

    @Override
    public WebDriver getDriver() {
        return decoratee.getDriver();
    }

    @Override
    public BrowserWaitInterface getWait() {
        return decoratee.getWait();
    }

    @Override
    public BrowserHandlerOptions getOptions() {
        return decoratee.getOptions();
    }

    @Override
    public JavascriptExecutor getJavascriptExecutor() {
        return decoratee.getJavascriptExecutor();
    }

    @Override
    public WebElement getElement(By by) {
        String attemptMessage = "Attempting to get `WebElement`, using selector [ %s ]";
        String successMessage = "Successfully retrieved `WebElement` [ %s ], using selector [ %s ]";

        getLogger().info(attemptMessage, by);
        WebElement we = decoratee.getElement(by);
        getLogger().info(successMessage, WebEntity.toString(we), by);

        return we;
    }

    @Override
    public WebEntity getWebEntity(By by) {
        String attemptMessage = "Attempting to wrap `WebElement` with `WebEntity`, using selector [ %s ]";
        String successMessage = "Successfully retrieved `WebEntity` [ %s ], using selector [ %s ]";

        getLogger().info(attemptMessage, by);
        WebEntity we = decoratee.getWebEntity(by);
        getLogger().info(successMessage, we.toString(), by);

        return we;
    }

    @Override
    public List<WebElement> getElements(By by) {
        String attemptMessage = "Attempting to wrap `WebElement` with `WebEntity`, using selector [ %s ]";
        String successMessage = "Successfully retrieved `WebEntity` [ %s ], using selector [ %s ]";

        getLogger().info(attemptMessage, by);
        List<WebElement> elements = decoratee.getElements(by);
        List<String> webEntityStringsToBeLogged = elements
                .stream()
                .map(WebEntity::toString)
                .collect(Collectors.toList());
        getLogger().info(successMessage, webEntityStringsToBeLogged, by);

        return elements;
    }

    @Override
    public String getPageLoadState() {
        String attemptMessage = "Attempting to get page-load-state from [ %s ]";
        String currentUrl = decoratee.getCurrentUrl();
        String pageLoadState = decoratee.getPageLoadState();
        String successMessage = "Successfully retrieved page-load-state of [ %s ] from [ %s ]";


        getLogger().info(attemptMessage, currentUrl);
        getLogger().info(successMessage, pageLoadState, currentUrl);

        return pageLoadState;
    }

    @Override
    public void navigateTo(String newUrl) {
        String attemptMessage = "Attempting to navigate to [ %s ] from [ %s ]";
        String formerUrl = decoratee.getCurrentUrl();
        decoratee.navigateTo(newUrl);
        String successMessage = "Successfully navigated to [ %s ] from [ %s ]";


        getLogger().info(attemptMessage, newUrl, formerUrl);
        getLogger().info(successMessage, newUrl, formerUrl);
    }

    @Override
    public void click(By by) {
        String attemptMessage = "Attempting to click `WebElement`, using selector [ %s ]";
        String successMessage = "Successfully clicked `WebElement` [ %s ], using selector [ %s ]";
        WebEntity we = new WebEntity(by, getDriver());

        getLogger().info(attemptMessage, by);
        decoratee.click(by);
        getLogger().info(successMessage, we.toString(), by);
    }

    @Override
    public Select select(By by) {
        String attemptMessage = "Attempting to select dropdown, using selector [ %s ]";
        String successMessage = "Successfully selected dropdown `WebElement` [ %s ], using selector [ %s ]";
        WebEntity we = new WebEntity(by, getDriver());

        getLogger().info(attemptMessage, by);
        Select select = decoratee.select(by);
        getLogger().info(successMessage, we.toString(), by);
        return select;
    }

    @Override
    public void selectByIndex(By by, int index) {
        String attemptMessage = "Attempting to select index [ %s ], using selector [ %s ]";
        String successMessage = "Successfully selected index [ %s ], using selector [ %s ]";

        getLogger().info(attemptMessage, index, by);
        decoratee.selectByIndex(by, index);
        getLogger().info(successMessage, index, by);
    }

    @Override
    public void selectByVisibleText(By by, String visibleText) {
        String attemptMessage = "Attempting to select text [ %s ], using selector [ %s ]";
        String successMessage = "Successfully selected text [ %s ], using selector [ %s ]";

        getLogger().info(attemptMessage, visibleText, by);
        decoratee.selectByVisibleText(by, visibleText);
        getLogger().info(successMessage, visibleText, by);
    }

    @Override
    public void sendKeys(By by, CharSequence... keys) {
        String attemptMessage = "Attempting to send keys [ %s ], using selector [ %s ]";
        String successMessage = "Successfully sent keys [ %s ], using selector [ %s ]";

        getLogger().info(attemptMessage, keys, by);
        decoratee.sendKeys(by, keys);
        getLogger().info(successMessage, keys, by);
    }

    @Override
    public void close() {
        String attemptMessage = "Attempting to close `BrowserHandler` instance, [ %s ]";
        String successMessage  = "Successfully closed `BrowserHandler` instance, [ %s ]";

        getLogger().info(attemptMessage);
        decoratee.close();
        getLogger().info(successMessage);
    }

    @Override
    public void highlightElement(By by, String color) {
        String attemptMessage = "Attempting to highlight element with color [ %s ], using selector [ %s ]";
        String successMessage = "Successfully highlighted element with color [ %s ], using selector [ %s ]";

        getLogger().info(attemptMessage, color, by);
        decoratee.highlightElement(by, color);
        getLogger().info(successMessage, color, by);
    }

    @Override
    public void highlightElements(By[] bys, String color) {
        String attemptMessage = "Attempting to highlight elements with color [ %s ], using selectors [ %s ]";
        String successMessage = "Successfully highlighted elements with color [ %s ], using selectors [ %s ]";
        String selectors = Arrays.toString(bys);

        getLogger().info(attemptMessage, color, selectors);
        decoratee.highlightElements(bys, color);
        getLogger().info(successMessage, color, selectors);
    }

    @Override
    public Screenshot screenshot() {
        String attemptMessage = "Attempting to screenshot from [ %s ]";
        String currentUrl = decoratee.getCurrentUrl();
        Screenshot screenshot = decoratee.screenshot();
        String successMessage = "Successfully retrieved screenshot of [ %s ]";

        getLogger().info(attemptMessage, currentUrl);
        getLogger().info(successMessage, screenshot, currentUrl);

        return screenshot;
    }

    @Override
    public void finalize() {
        decoratee.finalize();
    }
}
