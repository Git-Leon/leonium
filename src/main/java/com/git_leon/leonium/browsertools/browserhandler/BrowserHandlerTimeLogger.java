package com.git_leon.leonium.browsertools.browserhandler;

import com.github.git_leon.logging.FunctionExecutionTimeLogger;
import com.github.git_leon.logging.SimpleLoggerWarehouse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * @author leonhunter
 * @created 04/29/2020 - 8:35 PM
 */
public class BrowserHandlerTimeLogger extends BrowserHandlerDecorateeAbstractClass implements BrowserHandlerLoggerInterface {
    public BrowserHandlerTimeLogger(BrowserHandlerInterface decoratee) {
        super(decoratee);
    }

    public BrowserHandlerTimeLogger(WebDriver driver) {
        super(driver);
    }

    @Override
    public FunctionExecutionTimeLogger getLogger() {
        return new FunctionExecutionTimeLogger(SimpleLoggerWarehouse.getLogger(
                getBrowserHandlerDecoratee().getDriver().toString()));
    }


    @Override
    public WebElement getElement(By by) {
        return getLogger().invokeAndLog(getBrowserHandlerDecoratee()::getElement, by, "");
    }

    @Override
    public WebEntity getWebEntity(By by) {
        return getLogger().invokeAndLog(getBrowserHandlerDecoratee()::getWebEntity, by, "");
    }


    @Override
    public List<WebElement> getElements(By by) {
        return getLogger().invokeAndLog(getBrowserHandlerDecoratee()::getElements, by, "");
    }


    @Override
    public String getPageLoadState() {
        return getLogger().invokeAndLog(getBrowserHandlerDecoratee()::getPageLoadState, "");
    }


    @Override
    public void navigateTo(String newUrl) {
        getLogger().consumeAndLog(getBrowserHandlerDecoratee()::navigateTo, newUrl, "");

    }


    @Override
    public void click(By by) {
        getLogger().consumeAndLog(getBrowserHandlerDecoratee()::click, by, "");
    }


    @Override
    public Select select(By by) {
        return getLogger().invokeAndLog(getBrowserHandlerDecoratee()::select, by, "");
    }


    @Override
    public void selectByIndex(By by, int index) {
        getLogger().consumeAndLog(getBrowserHandlerDecoratee()::selectByIndex, by, index, "");
    }


    @Override
    public void selectByVisibleText(By by, String visibleText) {
        getLogger().consumeAndLog(getBrowserHandlerDecoratee()::selectByVisibleText, by, visibleText, "");
    }


    @Override
    public void sendKeys(By by, String keys) {
        getLogger().consumeAndLog(getBrowserHandlerDecoratee()::sendKeys, by, keys, "");
    }


    @Override
    public void close() {
        getLogger().invokeAndLog(getBrowserHandlerDecoratee()::close, "");
    }


    @Override
    public void highlightElement(By by, String color) {
        getLogger().consumeAndLog(getBrowserHandlerDecoratee()::highlightElement, by, color, "");
    }


    @Override
    public void highlightElements(By[] bys, String color) {
        getLogger().consumeAndLog(getBrowserHandlerDecoratee()::highlightElements, bys, color, "");
    }


    @Override
    public Screenshot screenshot() {
        return getLogger().invokeAndLog(getBrowserHandlerDecoratee()::screenshot, "");
    }
}
