package com.zipcodewilmington.selenium;

import com.zipcodewilmington.selenium.tools.browsertools.WebCrawl;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * Created by leon on 5/25/17.
 */
public class Demo0 extends WebCrawl {
    @Test
    @Override
    public void test() {
//        browserHandler.options.screenshotOnEvent.setValue(false);
        String url = "https://www.google.com/";
        browserHandler.navigateTo(url);
        By by = By.xpath(".//*[@id='lst-ib']");
        browserHandler.sendKeys(by, "hello world");
    }
}