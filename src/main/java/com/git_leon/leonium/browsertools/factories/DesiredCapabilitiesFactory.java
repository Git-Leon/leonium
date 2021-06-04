package com.git_leon.leonium.browsertools.factories;

import io.github.bonigarcia.wdm.Config;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public static Capabilities getChrome() {
        WebDriverManager.chromedriver().setup();
        return getDefault().merge(DesiredCapabilities.chrome());
    }

    public static Capabilities getHeadlessFirefox() {
        final DesiredCapabilities capabilities = getDefault().merge(DesiredCapabilities.firefox());
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
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return capabilities.merge(firefoxOptions.toCapabilities());
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
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return capabilities;

    }

    public static Capabilities getPhantomJs() {
        WebDriverManager.phantomjs().setup();
        final DesiredCapabilities capabilities = getDefault().merge(DesiredCapabilities.phantomjs());
        final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36";
        capabilities.setCapability("takesScreenshot", true);
        capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        capabilities.setCapability(
                PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
                Arrays.asList(
                        "--web-security=false",
                        "--ssl-protocol=any",
                        "--ignore-ssl-errors=true"));
        capabilities.setCapability(
                PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS,
                new String[]{"--logLevel=2"});
        capabilities.setCapability(
                PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX.concat("userAgent"),
                userAgent);
        System.setProperty("phantomjs.page.settings.userAgent", userAgent);
        return capabilities;
    }

    public static Capabilities getHtmlUnit() {
        DesiredCapabilities capabilities = getDefault().merge(DesiredCapabilities.htmlUnit());
        capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability("takesScreenshot", true);
        return capabilities;
    }
}