package com.zipcodewilmington.selenium.webcrawl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by leon on 5/26/17.
 */
public class WebCrawlDemo0 extends WebCrawl {
    @Override
    public void test() {
//        browserHandler.options.screenshotOnEvent.setValue(false);
        String url = "https://www.google.com/";
        browserHandler.navigateTo(url);
        By by = By.xpath(".//*[@id='lst-ib']");
        browserHandler.sendKeys(by, "hello world");
    }
}
