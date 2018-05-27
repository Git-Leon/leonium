package com.git_leon.selenium.tools.browsertools;

import com.git_leon.selenium.tools.ReflectionUtils;
import com.git_leon.selenium.tools.browsertools.browserhandler.BrowserHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class WebPage {
    protected final WebDriver driver;
    protected BrowserHandler browserHandler;

    public WebPage(WebDriver driver) {
        this.driver = driver;
        this.browserHandler = new BrowserHandler(driver);
    }

    public By[] getDeclaredBys() {
        return ReflectionUtils.getFieldValues(this, By.class).toArray(By[]::new);
    }

    public void highlightElements() {
        browserHandler.highlightElements(getDeclaredBys(), "yellow");
    }
}