package com.git_leon.leonium.browsertools.browserhandler.waiting;

import org.openqa.selenium.WebDriver;

/**
 * @author leon on 4/12/18.
 */
public class BrowserWait extends AbstractBrowserWait {
    public BrowserWait(int waitSeconds, WebDriver driver) {
        super(waitSeconds, driver);
    }
}
