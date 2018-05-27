package com.git_leon.selenium.google;

import com.git_leon.selenium.tools.browsertools.WebCrawl;
import com.git_leon.selenium.tools.browsertools.browserhandler.BrowserHandler;
import com.git_leon.selenium.tools.browsertools.browserwrapper.BrowserHandlerFactory;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * Created by leon on 5/25/17.
 */
public class Demo0 extends WebCrawl {
    @Test
    @Override
    public void test() {
        BrowserHandler browserHandler = BrowserHandlerFactory.PHANTOMJS.getBrowserHandler();
//        browserHandler.options.SCREENSHOT_ON_EVENT.setValue(false);
        String url = "https://www.google.com/";
        browserHandler.navigateTo(url);
        By by = By.xpath(".//*[@id='lst-ib']");
        browserHandler.sendKeys(by, "hello world");
    }
}