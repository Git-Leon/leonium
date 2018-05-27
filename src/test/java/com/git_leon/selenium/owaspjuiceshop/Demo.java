package com.git_leon.selenium.owaspjuiceshop;

import com.git_leon.selenium.tools.browsertools.WebCrawl;
import com.git_leon.selenium.tools.browsertools.browserwrapper.BrowserFactory;

/**
 * @author leon on 4/10/18.
 */
public class Demo extends WebCrawl {

    @Override
    public void setup() {
        super.setup();
    }

    @Override
    public void test() {
        SearchPage searchPage = new SearchPage(super.driver);
        searchPage.navigateTo();
        searchPage.selectLanguage("English");
        searchPage.search("apple");
        searchPage.clickSearch();
        AppleJuiceWidget appleJuiceWidget = searchPage.clickAppleJuice();
        appleJuiceWidget.leaveProductReview("This is a product review");
    }
}
