package com.zipcodewilmington.selenium.tools.browsertools.browserwrapper;

import com.zipcodewilmington.selenium.tools.SystemInfo;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @purpose utility class to centralize common BrowserWrapper functionality
 */
public final class BrowserUtilities {
    static { // initialize system properties
        String pathCurrentProject = SystemInfo.currentProjectPath;
        String pathGeckoDriver = pathCurrentProject + "/src/main/resources/geckodriver";
        String pathChromeDriver = pathCurrentProject + "/src/main/resources/chromedriver";
        String pathPhantomDriver = pathCurrentProject + "/src/main/resources/phantomdriver";
        String userAgent = "Mozilla/5.0 (Windows NT 6.0) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.41 Safari/535.1";

        System.setProperty("webdriver.gecko.driver", pathGeckoDriver);
        System.setProperty("webdriver.chrome.driver", pathChromeDriver);
        System.setProperty("webdriver.phantomjs.driver", pathPhantomDriver);
        System.setProperty("phantomjs.page.settings.userAgent", userAgent);
        System.setProperty("org.openqa.selenium.remote.RemoteWebDriver", "info");
    }

    public static DesiredCapabilities defaultCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setJavascriptEnabled(true);
        caps.setCapability("takesScreenshot", true);
        caps.setCapability("screen-resolution", "1280x1024");
        caps.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        caps.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        //caps.setCapability(CapabilityType.PROXY, getProxy(null));
        return caps;
    }

    private static Proxy getProxy(String proxyUrl) {
        return new Proxy()
                .setHttpProxy(proxyUrl)
                .setFtpProxy(proxyUrl)
                .setSslProxy(proxyUrl);
    }
}