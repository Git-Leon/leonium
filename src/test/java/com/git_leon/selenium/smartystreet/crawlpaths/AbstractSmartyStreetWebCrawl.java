package com.git_leon.selenium.smartystreet.crawlpaths;

import com.git_leon.selenium.smartystreet.pages.DemoPage;
import com.git_leon.selenium.smartystreet.pages.LandingPage;
import com.git_leon.selenium.tools.browsertools.WebCrawl;

/**
 * Created by leon on 8/17/17.
 */
public abstract class AbstractSmartyStreetWebCrawl extends WebCrawl {
    protected final LandingPage landingPage = new LandingPage(this);
    protected final DemoPage demoPage = new DemoPage(this);
}