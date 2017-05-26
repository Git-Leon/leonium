package com.zipcodewilmington.selenium;

import com.zipcodewilmington.selenium.tools.logging.LoggerHandler;
import com.zipcodewilmington.selenium.tools.logging.LoggerWarehouse;
import com.zipcodewilmington.selenium.tools.browsertools.BrowserWait;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by leon on 5/25/17.
 */
public class MyTest {

    LoggerHandler lh = LoggerWarehouse.getLogger(MyTest.class);

    public void run() {
        FirefoxDriver fd = null;
        try {
            fd = new FirefoxDriver();
            BrowserWait bw = new BrowserWait(fd, lh);
            fd.navigate().to("https://www.google.com/");
        } catch(Exception e) {

        } finally {
            try {
                fd.close();
            } catch(NullPointerException npe) {

            }
        }
    }
}
