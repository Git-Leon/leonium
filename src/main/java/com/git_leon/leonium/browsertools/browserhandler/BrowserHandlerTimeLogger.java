package com.git_leon.leonium.browsertools.browserhandler;

import com.github.git_leon.logging.FunctionExecutionTimeLogger;
import com.github.git_leon.logging.SimpleLoggerWarehouse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author leonhunter
 * @created 04/29/2020 - 8:35 PM
 */
public class BrowserHandlerTimeLogger extends BrowserHandlerDecorateeAbstractClass implements BrowserHandlerLoggerInterface {
    public BrowserHandlerTimeLogger(BrowserHandlerInterface decoratee) {
        super(decoratee);
    }

    @Override
    public FunctionExecutionTimeLogger getLogger() {
        return new FunctionExecutionTimeLogger(SimpleLoggerWarehouse.getLogger(
                getBrowserHandlerDecoratee().getDriver().toString()));
    }


    @Override
    public WebElement getElement(By by) {
        String attemptMessage = "Attempting to get `WebElement`, using selector [ %s ]";
        String successMessage = "Successfully retrieved `WebElement` [ %s ], using selector [ %s ]";

        attemptMessage = String.format(attemptMessage, by);
        WebElement we = getLogger().invokeAndLog(getBrowserHandlerDecoratee()::getElement, by, attemptMessage);
        getLogger().info(successMessage, WebEntity.toString(we), by);

        return we;
    }

    @Override
    public WebEntity getWebEntity(By by) {
        String attemptMessage = "Attempting to wrap `WebElement` with `WebEntity`, using selector [ %s ]";
        String successMessage = "Successfully retrieved `WebEntity` [ %s ], using selector [ %s ]";

        attemptMessage = String.format(attemptMessage, by);
        WebEntity we = getLogger().invokeAndLog(getBrowserHandlerDecoratee()::getWebEntity, by, attemptMessage);
        getLogger().info(successMessage, we.toString(), by);

        return we;
    }


    @Override
    public List<WebElement> getElements(By by) {
        String attemptMessage = "Attempting to wrap `WebElement` with `WebEntity`, using selector [ %s ]";
        String successMessage = "Successfully retrieved `WebEntity` [ %s ], using selector [ %s ]";

        attemptMessage = String.format(attemptMessage, by);
        getLogger().info(attemptMessage, by);
        List<WebElement> elements = getLogger().invokeAndLog(getBrowserHandlerDecoratee()::getElements, by, attemptMessage);
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
        String currentUrl = getBrowserHandlerDecoratee().getCurrentUrl();
        String pageLoadState = getBrowserHandlerDecoratee().getPageLoadState();
        String successMessage = "Successfully retrieved page-load-state of [ %s ] from [ %s ]";


        getLogger().info(attemptMessage, currentUrl);
        getLogger().info(successMessage, pageLoadState, currentUrl);

        return pageLoadState;
    }


    @Override
    public void navigateTo(String newUrl) {
        String attemptMessage = "Attempting to navigate to [ %s ] from [ %s ]";
        String successMessage = "Successfully navigated to [ %s ] from [ %s ]";

        String formerUrl = getBrowserHandlerDecoratee().getCurrentUrl();
        attemptMessage = String.format(attemptMessage, newUrl, formerUrl);
        getLogger().consumeAndLog(getBrowserHandlerDecoratee()::navigateTo, newUrl, attemptMessage);
        getLogger().info(successMessage, newUrl, formerUrl);
    }


    @Override
    public void click(By by) {
        String attemptMessage = "Attempting to click `WebElement`, using selector [ %s ]";
        String successMessage = "Successfully clicked `WebElement` [ %s ], using selector [ %s ]";
        WebEntity we = new WebEntity(by, getDriver());

        attemptMessage = String.format(attemptMessage, by);
        getLogger().invokeAndLog((nil) -> {
            getBrowserHandlerDecoratee().click(by);
            return null;
        }, null, attemptMessage);
        getLogger().info(successMessage, we.toString(), by);
    }


    @Override
    public Select select(By by) {
        String attemptMessage = "Attempting to select dropdown, using selector [ %s ]";
        String successMessage = "Successfully selected dropdown `WebElement` [ %s ], using selector [ %s ]";
        WebEntity we = new WebEntity(by, getDriver());

        attemptMessage = String.format(attemptMessage, by);
        Select select = getLogger().invokeAndLog(getBrowserHandlerDecoratee()::select, by, attemptMessage);
        getLogger().info(successMessage, we.toString(), by);
        return select;
    }


    @Override
    public void selectByIndex(By by, int index) {
        String attemptMessage = "Attempting to select index [ %s ], using selector [ %s ]";
        String successMessage = "Successfully selected index [ %s ], using selector [ %s ]";

        attemptMessage = String.format(attemptMessage, by);
        getLogger().invokeAndLog((nil) -> {
            getBrowserHandlerDecoratee().selectByIndex(by, index);
            return null;
        }, null, attemptMessage);
        getLogger().info(successMessage, index, by);
    }


    @Override
    public void selectByVisibleText(By by, String visibleText) {
        String attemptMessage = "Attempting to select text [ %s ], using selector [ %s ]";
        String successMessage = "Successfully selected text [ %s ], using selector [ %s ]";

        attemptMessage = String.format(attemptMessage, visibleText, by);
        getLogger().invokeAndLog((nil) -> {
            getBrowserHandlerDecoratee().selectByVisibleText(by, visibleText);
            return null;
        }, null, attemptMessage);
        getLogger().info(successMessage, visibleText, by);
    }


    @Override
    public void sendKeys(By by, String keys) {
        String attemptMessage = "Attempting to send keys [ '%s' ], using selector [ %s ]";
        String successMessage = "Successfully sent keys [ '%s' ], using selector [ %s ]";

        attemptMessage = String.format(attemptMessage, keys, by);
        getLogger().invokeAndLog((nil) -> {
            getBrowserHandlerDecoratee().sendKeys(by, keys);
            return null;
        }, null, attemptMessage);
        getLogger().info(successMessage, keys, by);
    }


    @Override
    public void close() {
        String attemptMessage = "Attempting to close `BrowserHandler` instance, [ %s ]";
        String successMessage = "Successfully closed `BrowserHandler` instance, [ %s ]";

        getLogger().info(attemptMessage, getBrowserHandlerDecoratee().toString());
        getBrowserHandlerDecoratee().close();
        getLogger().info(successMessage, getBrowserHandlerDecoratee().toString());
    }


    @Override
    public void highlightElement(By by, String color) {
        String attemptMessage = "Attempting to highlight element with color [ %s ], using selector [ %s ]";
        String successMessage = "Successfully highlighted element with color [ %s ], using selector [ %s ]";

        attemptMessage = String.format(attemptMessage, color, by);
        getLogger().invokeAndLog((nil) -> {
            getBrowserHandlerDecoratee().highlightElement(by, color);
            return null;
        }, null, attemptMessage);
        getLogger().info(successMessage, color, by);
    }


    @Override
    public void highlightElements(By[] bys, String color) {
        String attemptMessage = "Attempting to highlight elements with color [ %s ], using selectors [ %s ]";
        String successMessage = "Successfully highlighted elements with color [ %s ], using selectors [ %s ]";
        String selectors = Arrays.toString(bys);

        attemptMessage = String.format(attemptMessage, color, selectors);
        getLogger().invokeAndLog((nil) -> {
            getBrowserHandlerDecoratee().highlightElements(bys, color);
            return null;
        }, null, attemptMessage);
        getLogger().info(successMessage, color, selectors);
    }


    @Override
    public Screenshot screenshot() {
        String attemptMessage = "Attempting to screenshot from [ %s ]";
        String successMessage = "Successfully retrieved screenshot of [ %s ]";

        attemptMessage = String.format(attemptMessage, getCurrentUrl());
        String currentUrl = getBrowserHandlerDecoratee().getCurrentUrl();
        Supplier<Screenshot> supplier = getBrowserHandlerDecoratee()::screenshot;
        Screenshot screenshot = getLogger().invokeAndLog(supplier, attemptMessage);
        getLogger().info(successMessage, screenshot, currentUrl);

        return screenshot;
    }
}
