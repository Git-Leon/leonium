package com.git_leon.leonium.browsertools.factories;

import com.git_leon.leonium.browsertools.browserhandler.BrowserHandler;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author leon on 5/24/18.
 * Produces new instances of
 */
public enum BrowserHandlerFactory {
    CHROME(DesiredCapabilitiesFactory.getChrome(), ChromeDriver::new),
    FIREFOX(DesiredCapabilitiesFactory.getFirefox(), FirefoxDriver::new),
    HEADLESS_CHROME(DesiredCapabilitiesFactory.getChrome().merge(((Supplier<Capabilities>) (() -> {
        final DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return capabilities;
    })).get()), ChromeDriver::new),
    HEADLESS_FIREFOX(DesiredCapabilitiesFactory.getFirefox().merge(((Supplier<Capabilities>) (() -> {
        final FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--headless");
        return firefoxOptions.toCapabilities();
    })).get()), FirefoxDriver::new),

    PHANTOMJS(DesiredCapabilitiesFactory.getPhantomJs(), PhantomJSDriver::new),
    HTMLUNIT(DesiredCapabilitiesFactory.getHtmlUnit(), HtmlUnitDriver::new);

    private final Function<Capabilities, WebDriver> webDriverConstructor;
    private final Capabilities capabilities;

    BrowserHandlerFactory(Capabilities capabilities, Function<Capabilities, WebDriver> constructor) {
        this.webDriverConstructor = constructor;
        this.capabilities = capabilities;
    }

    public BrowserHandler getBrowserHandler() {
        return new BrowserHandler(getDriver());
    }

    public WebDriver getDriver() {
        return webDriverConstructor.apply(capabilities);
    }

    public WebDriver getDriver(Capabilities capabilities) {
        return webDriverConstructor.apply(this.capabilities.merge(capabilities));
    }
}
