package com.github.git_leon.leonium.browsertools.browserhandler.logging;

import com.github.git_leon.leonium.browsertools.browserhandler.core.Screenshot;
import com.github.git_leon.leonium.browsertools.browserhandler.core.WebEntity;
import com.github.git_leon.logging.FunctionExecutionLoggerAndTimer;
import com.github.git_leon.logging.SimpleLogger;
import com.github.git_leon.logging.SimpleLoggerInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
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
        SimpleLoggerInterface logger = getBrowserHandlerLoggerDecoratee().getLogger();
        return new FunctionExecutionLoggerAndTimer(logger);
    }

    @Override
    public void finalize() {

    }

    @Override
    public WebElement getElement(By by) {
        final String logMessage = String.format("Starting timer for `getElement(%s)`", by);
        return getLogger().logAndInvoke(getBrowserHandlerDecoratee()::getElement, by, logMessage);
    }

    @Override
    public WebEntity getWebEntity(By by) {
        final String logMessage = String.format("Starting timer for `getWebEntity(%s)`", by);
        return getLogger().logAndInvoke(getBrowserHandlerDecoratee()::getWebEntity, by, logMessage);
    }


    @Override
    public List<WebElement> getElements(By by) {
        final String logMessage = String.format("Starting timer for `getElements(%s)`", by);
        return getLogger().logAndInvoke(getBrowserHandlerDecoratee()::getElements, by, logMessage);
    }


    @Override
    public String getPageLoadState() {
        final String logMessage = String.format("Starting timer for `getPageLoadState()`");
        return getLogger().logAndInvoke(getBrowserHandlerDecoratee()::getPageLoadState, logMessage);
    }


    @Override
    public void navigateTo(String newUrl) {
        final String logMessage = String.format("Starting timer for `getWebEntity(%s)`", newUrl);
        getLogger().consumeAndLog(getBrowserHandlerDecoratee()::navigateTo, newUrl, logMessage);
    }

    @Override
    public void click(By by) {
        final String logMessage = String.format("Starting timer for `click(%s)`", by);
        getLogger().consumeAndLog(getBrowserHandlerDecoratee()::click, by, logMessage);
    }


    @Override
    public Select select(By by) {
        final String logMessage = String.format("Starting timer for `select(%s)`", by);
        return getLogger().logAndInvoke(getBrowserHandlerDecoratee()::select, by, logMessage);
    }


    @Override
    public void selectByIndex(By by, int index) {
        final String logMessage = String.format("Starting timer for `selectByIndex(%s, %s)`", by, index);
        getLogger().consumeAndLog(getBrowserHandlerDecoratee()::selectByIndex, by, index, logMessage);
    }


    @Override
    public void selectByVisibleText(By by, String visibleText) {
        final String logMessage = String.format("Starting timer for `selectByVisibleText(%s, %s)`", by, visibleText);
        getLogger().consumeAndLog(getBrowserHandlerDecoratee()::selectByVisibleText, by, visibleText, logMessage);
    }


    @Override
    public void sendKeys(By by, String keys) {
        final String logMessage = String.format("Starting timer for `sendKeys(%s,%s)`", by, keys);
        getLogger().consumeAndLog(getBrowserHandlerDecoratee()::sendKeys, by, keys, logMessage);
    }


    @Override
    public void close() {
        final String logMessage = String.format("Starting timer for `close()`");
        getLogger().logAndInvoke(getBrowserHandlerDecoratee()::close, logMessage);
    }


    @Override
    public void highlightElement(By by, String color) {
        final String logMessage = String.format("Starting timer for `highlightElement(%s)`", by, color);
        getLogger().consumeAndLog(getBrowserHandlerDecoratee()::highlightElement, by, color, logMessage);
    }


    @Override
    public void highlightElements(By[] bys, String color) {
        final String logMessage = String.format("Starting timer for `highlightElements(%s, %s)`", Arrays.toString(bys), color);
        getLogger().consumeAndLog(getBrowserHandlerDecoratee()::highlightElements, bys, color, logMessage);
    }


    @Override
    public Screenshot screenshot() {
        final String logMessage = String.format("Starting timer for `screenshot()`");
        Supplier<Screenshot> screenshotSupplier = getBrowserHandlerDecoratee()::screenshot;
        return getLogger().logAndInvoke(screenshotSupplier, logMessage);
    }
}
