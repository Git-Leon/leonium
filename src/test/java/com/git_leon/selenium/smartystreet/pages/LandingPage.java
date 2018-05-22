package com.git_leon.selenium.smartystreet.pages;


import com.git_leon.selenium.tools.browsertools.MyWebCrawl;
import com.git_leon.selenium.tools.browsertools.WebPage;
import org.openqa.selenium.By;

/**
 * Created by leon on 8/17/17.
 */
public class LandingPage extends WebPage {
    public final By byButtonDemo = By.id("#header-crawlpaths");

    public LandingPage(MyWebCrawl crawler) {
        super(crawler);
    }

}