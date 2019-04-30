package com.git_leon.leonium.browsertools.factories;

import com.git_leon.leonium.browsertools.browserhandler.BrowserHandler;
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
 */
public enum BrowserHandlerFactory {
    CHROME(DesiredCapabilitiesFactory.getChrome(), ChromeDriver::new),
    FIREFOX(DesiredCapabilitiesFactory.getFirefox(), FirefoxDriver::new),
    PHANTOMJS(DesiredCapabilitiesFactory.getPhantomJs(), PhantomJSDriver::new),
    HTMLUNIT(DesiredCapabilitiesFactory.getHtmlUnit(), HtmlUnitDriver::new);

    private final Supplier<WebDriver> webDriverConstructor;

    BrowserHandlerFactory(Capabilities capabilities, Function<Capabilities, WebDriver> constructor) {
        this.webDriverConstructor = () -> constructor.apply(capabilities);
    }

    public BrowserHandler getBrowserHandler() {
        return new BrowserHandler(getDriver());
    }

    public WebDriver getDriver() {
        return webDriverConstructor.get();
    }
}
