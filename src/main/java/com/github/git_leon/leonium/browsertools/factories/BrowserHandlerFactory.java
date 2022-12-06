package com.github.git_leon.leonium.browsertools.factories;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author leon on 5/24/18.
 * Produces new instances of
 */
public enum BrowserHandlerFactory implements BrowserHandlerFactoryInterface {
    CHROME(
            DesiredCapabilitiesFactory::getChrome,
            (caps) -> new ChromeDriver(new ChromeOptions().merge(caps)),
            WebDriverManager.chromedriver()),
    FIREFOX(
            DesiredCapabilitiesFactory::getFirefox,
            (caps) -> new FirefoxDriver(new FirefoxOptions().merge(caps)),
            WebDriverManager.firefoxdriver()),

    HEADLESS_CHROME(
            DesiredCapabilitiesFactory::getHeadlessChrome,
            (caps) -> new ChromeDriver(new ChromeOptions().merge(caps)),
            WebDriverManager.chromedriver()),

    HEADLESS_FIREFOX(
            DesiredCapabilitiesFactory::getHeadlessFirefox,
            (caps) -> new FirefoxDriver(new FirefoxOptions().merge(caps)),
            WebDriverManager.firefoxdriver());

    private final Function<Capabilities, WebDriver> webDriverConstructor;
    private final Supplier<Capabilities> capabilitiesSupplier;
    private final WebDriverManager webDriverManager;

    BrowserHandlerFactory(
            Supplier<Capabilities> capabilitiesSupplier,
            Function<Capabilities, WebDriver> constructor,
            WebDriverManager webDriverManager) {
        this.webDriverConstructor = constructor;
        this.capabilitiesSupplier = capabilitiesSupplier;
        this.webDriverManager = webDriverManager;
    }

    @Override
    public Function<Capabilities, WebDriver> getWebDriverConstructor() {
        return webDriverConstructor;
    }

    @Override
    public Supplier<Capabilities> getCapabilitiesSupplier() {
        return capabilitiesSupplier;
    }

    public WebDriverManager getWebDriverManager() {
        return webDriverManager;
    }
}
