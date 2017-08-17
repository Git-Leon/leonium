package com.zipcodewilmington.selenium.smartystreet.crawlpaths;

import com.zipcodewilmington.selenium.smartystreet.pages.DemoPage;
import com.zipcodewilmington.selenium.smartystreet.pages.LandingPage;
import com.zipcodewilmington.selenium.tools.browsertools.WebCrawl;

/**
 * Created by leon on 8/17/17.
 */
public abstract class AbstractSmartyStreetWebCrawl extends WebCrawl {
    protected final LandingPage landingPage = new LandingPage(this);
    protected final DemoPage demoPage = new DemoPage(this);
}