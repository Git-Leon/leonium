package com.github.git_leon.leonium.browsertools.browserhandler.logging;

import com.github.git_leon.leonium.browsertools.browserhandler.core.Screenshot;
import com.github.git_leon.leonium.browsertools.browserhandler.core.WebEntity;
import com.github.git_leon.logging.FunctionExecutionLoggerAndTimer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author leonhunter
 * @created 04/29/2020 - 8:35 PM
 * Interface which decorates each composite `BrowserHandlerInterface` call with a log detailing execution time
 */
public class BrowserHandlerLoggerTimer implements BrowserHandlerLoggerInterfaceDecorator {
    private final BrowserHandlerLoggerInterface decoratee;

    public BrowserHandlerLoggerTimer(BrowserHandlerLoggerInterface decoratee) {
        this.decoratee = decoratee;
    }

    public BrowserHandlerLoggerTimer(WebDriver driver) {
        this(new BrowserHandlerLoggerImpl(driver));
    }

    @Override
    public BrowserHandlerLoggerInterface getBrowserHandlerLoggerDecoratee() {
        return this.decoratee;
    }

    @Override
    public FunctionExecutionLoggerAndTimer getLogger() {
        return new FunctionExecutionLoggerAndTimer(getBrowserHandlerLoggerDecoratee().getLogger());
    }

    @Override
    public void finalize() {

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
