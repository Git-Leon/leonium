package com.git_leon.leonium.browsertools.factories;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

/**
 * @purpose generates browser-capabilities and leverages bonigarcia.wdm.WebDriverManager to dynamically fetch Browser binaries
 */
public final class DesiredCapabilitiesFactory {

    private DesiredCapabilitiesFactory() {
    }

    public static DesiredCapabilities getDefault() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("takesScreenshot", true);
        caps.setCapability("screen-resolution", "1280x1024");
        caps.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        caps.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        caps.setJavascriptEnabled(true);
        return caps;
    }

    public static DesiredCapabilities getLogless() {
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.BROWSER, Level.OFF);
        logs.enable(LogType.SERVER, Level.OFF);
        logs.enable(LogType.DRIVER, Level.OFF);
        logs.enable(LogType.PROFILER, Level.OFF);
        logs.enable(LogType.CLIENT, Level.OFF);

        DesiredCapabilities capabilities = getDefault();
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
        return capabilities;
    }

    public static Capabilities getLoglessFirefox() {
        DesiredCapabilities desiredCapabilities = getLogless().merge(getFirefox());
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("log", "{level: info}");
        options.setLogLevel(Level.OFF);
        desiredCapabilities.setCapability("moz:firefoxOptions", options);
        return desiredCapabilities;
    }


    public static Capabilities getFirefox() {
        WebDriverManager.firefoxdriver().setup();
        return getDefault().merge(DesiredCapabilities.firefox());
    }

    public static Capabilities getHeadlessFirefox() {
        WebDriverManager.firefoxdriver().setup();
        final DesiredCapabilities capabilities = getDefault().merge(DesiredCapabilities.firefox());
        final FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments(
                "--headless",
                "--disable-gpu",
                "--window-size=1920,1200",
                "--ignore-certificate-errors",
                "--disable-extensions",
                "--no-sandbox",
                "--disable-dev-shm-usage");
        return capabilities.merge(firefoxOptions.toCapabilities());
    }

    public static Capabilities getPhantomJs() {
        WebDriverManager.phantomjs().setup();
        return getDefault().merge(DesiredCapabilities.phantomjs());
    }

    public static Capabilities getHeadlessChrome() {
        WebDriverManager.chromedriver().setup();
        final DesiredCapabilities capabilities = getDefault().merge(DesiredCapabilities.chrome());
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(
                "--headless",
                "--disable-gpu",
                "--window-size=1920,1200",
                "--ignore-certificate-errors",
                "--disable-extensions",
                "--no-sandbox",
                "--disable-dev-shm-usage");
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return capabilities;
    }

    public static Capabilities getChrome() {
        WebDriverManager.chromedriver().setup();
        return getDefault().merge(DesiredCapabilities.chrome());
    }

    public static Capabilities getHtmlUnit() {
        DesiredCapabilities capabilities = DesiredCapabilitiesFactory.getDefault();
        capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, false);
        capabilities.setBrowserName("htmlunit");
        return capabilities;
    }
}