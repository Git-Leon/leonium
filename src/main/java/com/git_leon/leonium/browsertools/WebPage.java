package com.git_leon.leonium.browsertools;

import com.git_leon.leonium.DirectoryReference;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import com.git_leon.leonium.browsertools.browserhandler.reporting.BrowserHandlerLayeredLogger;
import org.openqa.selenium.WebDriver;

public abstract class WebPage implements WebPageInterface {
    private BrowserHandlerInterface browserHandler;

    public WebPage(WebDriver driver) {
        this(new BrowserHandlerLayeredLogger(driver,
                DirectoryReference
                        .TARGET_DIRECTORY
                        .getFileFromDirectory("Report " + System.nanoTime() + ".html")
                        .getAbsolutePath(),
                "test-" + Long.toHexString(System.nanoTime())));
    }

    public WebPage(BrowserHandlerInterface browserHandler) {
        this.browserHandler = browserHandler;
        getBrowserHandler()
                .getOptions()
                .SCREENSHOT_ON_EVENT
                .setValue(true);
    }

    public WebPage(WebDriver driver, String reportName, String testName) {
        this(new BrowserHandlerLayeredLogger(driver,reportName, testName));
    }

    public BrowserHandlerInterface getBrowserHandler() {
        return browserHandler;
    }
}