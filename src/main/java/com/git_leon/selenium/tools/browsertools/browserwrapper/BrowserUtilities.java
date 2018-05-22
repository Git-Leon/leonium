package com.git_leon.selenium.tools.browsertools.browserwrapper;

import com.git_leon.selenium.tools.SystemInfo;
import com.git_leon.selenium.tools.logging.LogUtils;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @purpose utility class to centralize common BrowserWrapper functionality
 */
public final class BrowserUtilities {
    static { // initialize system properties
        String pathCurrentProject = SystemInfo.currentProjectPath.toString() ;
        String pathGeckoDriver = pathCurrentProject + "/src/main/resources/geckodriver";
        String pathChromeDriver = pathCurrentProject + "/src/main/resources/chromedriver";
        String pathPhantomDriver = pathCurrentProject + "/src/main/resources/phantomdriver";
        String userAgent = "Mozilla/5.0 (Windows NT 6.0) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.41 Safari/535.1";

        System.setProperty("webdriver.gecko.driver", pathGeckoDriver);
        System.setProperty("webdriver.chrome.driver", pathChromeDriver);
        System.setProperty("webdriver.phantomjs.driver", pathPhantomDriver);
        System.setProperty("phantomjs.page.settings.userAgent", userAgent);
        System.setProperty("org.openqa.selenium.remote.RemoteWebDriver", "info");

        LogUtils.disableLogging(
                PhantomJSDriverService.class,
                RemoteWebDriver.class,
                Augmenter.class,
                GeckoDriverService.class,
                Logger.class);
    }

    public static DesiredCapabilities getDefaultCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setJavascriptEnabled(true);
        caps.setCapability("takesScreenshot", true);
        caps.setCapability("screen-resolution", "1280x1024");
        caps.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        caps.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        //caps.setCapability(CapabilityType.PROXY, getProxy(null));
        return caps;
    }

    public static DesiredCapabilities getLoglessCapabilities() {
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.BROWSER, Level.OFF);
        logs.enable(LogType.SERVER, Level.OFF);
        logs.enable(LogType.DRIVER, Level.OFF);
        logs.enable(LogType.PROFILER, Level.OFF);
        logs.enable(LogType.CLIENT, Level.OFF);

        DesiredCapabilities capabilities = getDefaultCapabilities();
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
        return capabilities;
    }

    public static DesiredCapabilities getLoglessFirefoxCapabilities() {
        DesiredCapabilities desiredCapabilities = getLoglessCapabilities();
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("log", "{level: info}");
        options.setLogLevel(Level.OFF);
        desiredCapabilities.setCapability("moz:firefoxOptions", options);
        return desiredCapabilities;
    }

    private static Proxy getProxy(String proxyUrl) {
        return new Proxy()
                .setHttpProxy(proxyUrl)
                .setFtpProxy(proxyUrl)
                .setSslProxy(proxyUrl);
    }

}