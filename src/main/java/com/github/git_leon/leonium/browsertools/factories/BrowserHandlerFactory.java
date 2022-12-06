package com.github.git_leon.leonium.browsertools.factories;

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
public enum BrowserHandlerFactory implements BrowserHandlerFactoryInterface {
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

    public Function<Capabilities, WebDriver> getWebDriverConstructor() {
        return webDriverConstructor;
    }

    public Supplier<Capabilities> getCapabilitiesSupplier() {
        return capabilitiesSupplier;
    }
}
