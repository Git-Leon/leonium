package com.git_leon.leonium.browsertools;

import com.git_leon.leonium.DirectoryReference;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandler;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import com.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerExtentReporter;
import com.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerImpl;
import com.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerTimeLogger;
import org.openqa.selenium.WebDriver;

public abstract class WebPage implements WebPageInterface {
    private BrowserHandlerInterface browserHandler;

    public WebPage(BrowserHandlerInterface browserHandler) {
        this.browserHandler = browserHandler;
    }

    public WebPage(WebDriver driver) {
        this(new BrowserHandlerTimeLogger(new BrowserHandlerLoggerImpl(new BrowserHandlerLoggerExtentReporter(new BrowserHandler(driver, 5),
                DirectoryReference
                        .TARGET_DIRECTORY
                        .getFileFromDirectory("Report " + System.nanoTime() + ".html")
                        .getAbsolutePath(),
                "test-" + Long.toHexString(System.nanoTime())))));
    }

    public BrowserHandlerInterface getBrowserHandler() {
        return browserHandler;
    }
}