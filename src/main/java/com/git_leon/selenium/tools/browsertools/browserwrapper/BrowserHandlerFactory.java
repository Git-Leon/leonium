package com.git_leon.selenium.tools.browsertools.browserwrapper;

import com.git_leon.selenium.tools.browsertools.browserhandler.BrowserHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.function.Supplier;

/**
 * @author leon on 5/24/18.
 */
public enum BrowserHandlerFactory {
    CHROME(() -> {return new ChromeDriver(DesiredCapabilitiesFactory.getChrome());}),
    FIREFOX(() -> {return new FirefoxDriver(DesiredCapabilitiesFactory.getFirefox());}),
    PHANTOMJS(() -> {return new PhantomJSDriver(DesiredCapabilitiesFactory.getPhantomJs());}),
    HTMLUNIT(() -> {return new HtmlUnitDriver(DesiredCapabilitiesFactory.getHtmlUnit());});
    private final Supplier<WebDriver> webDriverConstructor;

    BrowserHandlerFactory(Supplier<WebDriver> constructor) {
        this.webDriverConstructor = constructor;
    }

    public BrowserHandler getBrowserHandler() {
        return new BrowserHandler(getDriver());
    }

    public WebDriver getDriver() {
        return webDriverConstructor.get();
    }
}
