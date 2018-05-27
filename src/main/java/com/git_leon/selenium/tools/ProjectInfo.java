package com.git_leon.selenium.tools;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;



/**
 * Created by leon on 5/25/17.
 */
public class ProjectInfo {
    private static final String CURRENT_PROJECT_PATH = System.getProperty("user.dir");
    public static final String ARTIFACT_FOLDER = String.format("%s/target/artifacts", CURRENT_PROJECT_PATH);
    private static final String GECKO_DRIVER_PATH = CURRENT_PROJECT_PATH + "/src/main/resources/geckodriver";
    private static final String CHROME_DRIVER_PATH = CURRENT_PROJECT_PATH + "/src/main/resources/chromedriver";
    private static final String PHANTOM_DRIVER_PATH = CURRENT_PROJECT_PATH + "/src/main/resources/phantomjs-2.1.1-macosx/bin/phantomjs";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.0) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.41 Safari/535.1";

    static { // initialize system properties
        System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, GECKO_DRIVER_PATH);
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, CHROME_DRIVER_PATH);
        System.setProperty(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, PHANTOM_DRIVER_PATH);
        System.setProperty("phantomjs.page.settings.USER_AGENT", USER_AGENT);
        System.setProperty("org.openqa.selenium.remote.RemoteWebDriver", "info");
    }
}
