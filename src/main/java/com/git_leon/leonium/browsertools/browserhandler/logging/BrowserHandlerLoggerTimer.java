package com.git_leon.leonium.browsertools.browserhandler.logging;

import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerDecoratorAbstractClass;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import com.git_leon.leonium.browsertools.browserhandler.Screenshot;
import com.git_leon.leonium.browsertools.browserhandler.WebEntity;
import com.github.git_leon.logging.FunctionExecutionLoggerAndTimer;
import com.github.git_leon.logging.SimpleLoggerWarehouse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author leonhunter
 * @created 04/29/2020 - 8:35 PM
 */
public class BrowserHandlerLoggerTimer extends BrowserHandlerDecoratorAbstractClass implements BrowserHandlerLoggerInterface {
    public BrowserHandlerLoggerTimer(BrowserHandlerInterface decoratee) {
        super(decoratee);
    }

    public BrowserHandlerLoggerTimer(WebDriver driver) {
        super(driver);
    }

    @Override
    public FunctionExecutionLoggerAndTimer getLogger() {
        return new FunctionExecutionLoggerAndTimer(SimpleLoggerWarehouse.getLogger(
                getBrowserHandlerDecoratee().getDriver().toString()));
    }


    @Override
    public WebElement getElement(By by) {
        return getLogger().logAndInvoke(getBrowserHandlerDecoratee()::getElement, by, "");
    }

    @Override
    public WebEntity getWebEntity(By by) {
        return getLogger().logAndInvoke(getBrowserHandlerDecoratee()::getWebEntity, by, "");
    }


    @Override
    public List<WebElement> getElements(By by) {
        return getLogger().logAndInvoke(getBrowserHandlerDecoratee()::getElements, by, "");
    }


    @Override
    public String getPageLoadState() {
        return getLogger().logAndInvoke(getBrowserHandlerDecoratee()::getPageLoadState, "");
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
        return getLogger().logAndInvoke(getBrowserHandlerDecoratee()::select, by, "");
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
        getLogger().logAndInvoke(getBrowserHandlerDecoratee()::close, "");
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
        Supplier<Screenshot> screenshotSupplier = getBrowserHandlerDecoratee()::screenshot;
        return getLogger().logAndInvoke(screenshotSupplier, "");
    }
}
