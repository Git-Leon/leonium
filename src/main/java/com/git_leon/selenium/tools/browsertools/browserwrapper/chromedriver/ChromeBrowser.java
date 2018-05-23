package com.git_leon.selenium.tools.browsertools.browserwrapper.chromedriver;

import com.git_leon.selenium.tools.browsertools.browserwrapper.DesiredCapabilitiesFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.*;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.mobile.NetworkConnection;
import org.openqa.selenium.remote.FileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author leon on 5/23/18.
 */
public class ChromeBrowser extends RemoteWebDriver implements LocationContext, WebStorage, HasTouchScreen, NetworkConnection {
    private final ChromeDriver driver;

    public ChromeBrowser() {
        this(new ChromeDriver(DesiredCapabilitiesFactory.getChrome()));
    }

    public ChromeBrowser(ChromeDriver chromeDriver) {
        this.driver = chromeDriver;
    }

    public void setFileDetector(FileDetector detector) {
        driver.setFileDetector(detector);
    }

    public LocalStorage getLocalStorage() {
        return driver.getLocalStorage();
    }

    public SessionStorage getSessionStorage() {
        return driver.getSessionStorage();
    }

    public Location location() {
        return driver.location();
    }

    public void setLocation(Location location) {
        driver.setLocation(location);
    }

    public TouchScreen getTouch() {
        return driver.getTouch();
    }

    public ConnectionType getNetworkConnection() {
        return driver.getNetworkConnection();
    }

    public ConnectionType setNetworkConnection(ConnectionType type) {
        return driver.setNetworkConnection(type);
    }

    public void launchApp(String id) {
        driver.launchApp(id);
    }
}

