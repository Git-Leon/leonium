package com.github.git_leon.leonium.browsertools.factories;

import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandler;
import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerImpl;
import com.github.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.reporting.BrowserHandlerLayeredLogger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author leon on 5/24/18.
 * Produces new instances of
 */
public enum BrowserHandlerFactory {
    CHROME(DesiredCapabilitiesFactory::getChrome, ChromeDriver::new),
    FIREFOX(DesiredCapabilitiesFactory::getFirefox, FirefoxDriver::new),
    HEADLESS_CHROME(DesiredCapabilitiesFactory::getHeadlessChrome, ChromeDriver::new),
    HEADLESS_FIREFOX(DesiredCapabilitiesFactory::getHeadlessFirefox, FirefoxDriver::new),
    PHANTOMJS(DesiredCapabilitiesFactory::getPhantomJs, PhantomJSDriver::new),
    HTMLUNIT(DesiredCapabilitiesFactory::getHtmlUnit, HtmlUnitDriver::new);

    private final Function<Capabilities, WebDriver> webDriverConstructor;
    private final Supplier<Capabilities> capabilitiesSupplier;

    BrowserHandlerFactory(Supplier<Capabilities> capabilitiesSupplier, Function<Capabilities, WebDriver> constructor) {
        this.webDriverConstructor = constructor;
        this.capabilitiesSupplier = capabilitiesSupplier;
    }

    public BrowserHandlerInterface getBrowserHandler() {
        return new BrowserHandler(getDriver());
    }

    public BrowserHandlerInterface getBrowserHandler(Capabilities capabilities) {
        return new BrowserHandler(getDriver(capabilities));
    }

    public BrowserHandlerLoggerInterface getBrowserHandlerLogger() {
        return new BrowserHandlerLoggerImpl(getDriver());
    }

    public BrowserHandlerLayeredLogger getBrowserHandlerLayeredLogger(String reportFilePath, String testName) {
        return new BrowserHandlerLayeredLogger(getDriver(), reportFilePath, testName);
    }

    public BrowserHandlerLoggerInterface getBrowserHandlerLogger(Capabilities capabilities) {
        return new BrowserHandlerLoggerImpl(getDriver(capabilities));
    }

    public WebDriver getDriver() {
        return webDriverConstructor.apply(capabilitiesSupplier.get());
    }

    public WebDriver getDriver(Capabilities capabilities) {
        return webDriverConstructor.apply(this.capabilitiesSupplier.get().merge(capabilities));
    }

    public static BrowserHandlerFactory getValueOf(String userInput) {
        return valueOf(userInput
                .toUpperCase()
                .replaceAll("-", "_")
                .replaceAll(" ", "_"));
    }
}
