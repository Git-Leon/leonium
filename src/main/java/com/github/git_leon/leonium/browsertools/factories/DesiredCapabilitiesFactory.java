package com.github.git_leon.leonium.browsertools.factories;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
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
        options.setLogLevel(FirefoxDriverLogLevel.fromLevel(Level.OFF));
        desiredCapabilities.setCapability("moz:firefoxOptions", options);
        return desiredCapabilities;
    }


    public static Capabilities getFirefox() {
        WebDriverManager.firefoxdriver().setup();
        return getDefault().merge(new FirefoxOptions());
    }

    public static Capabilities getChrome() {
        WebDriverManager.chromedriver().setup();
        return getDefault().merge(new ChromeOptions());
    }

    public static Capabilities getHeadlessFirefox() {
        final Capabilities capabilities = getFirefox();
        final DesiredCapabilities caps = new DesiredCapabilities();
        final FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addPreference("network.automatic-ntlm-auth.trusted-uris", "http://,https://");
        firefoxOptions.addPreference("network.automatic-ntlm-auth.allow-non-fqdn", true);
        firefoxOptions.addPreference("network.negotiate-auth.delegation-uris", "http://,https://");
        firefoxOptions.addPreference("network.negotiate-auth.trusted-uris", "http://,https://");
        firefoxOptions.addPreference("network.http.phishy-userpass-length", 255);
        firefoxOptions.addPreference("security.csp.enable", false);
        firefoxOptions.addPreference("network.proxy.no_proxies_on", "");
        firefoxOptions.addArguments(
                "--headless",
                "--disable-gpu",
                "--window-size=1920,1200",
                "--ignore-certificate-errors",
                "--disable-extensions",
                "--no-sandbox",
                "--disable-dev-shm-usage");
        caps.merge(capabilities);
        caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        caps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        firefoxOptions.merge(capabilities);
        return firefoxOptions;
    }

    public static Capabilities getHeadlessChrome() {
        WebDriverManager.chromedriver().setup();
        final DesiredCapabilities capabilities = getDefault();
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(
                "--headless",
                "--disable-gpu",
                "--window-size=1920,1200",
                "--ignore-certificate-errors",
                "--disable-extensions",
                "--no-sandbox",
                "--disable-dev-shm-usage");
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        return capabilities;

    }
}