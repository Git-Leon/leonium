package com.git_leon.leonium.owaspjuiceshop;

import com.git_leon.leonium.browsertools.WebCrawl;

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
        SearchPage searchPage = new SearchPage(this.browserHandler.getDriver());
        searchPage.navigateTo();
        searchPage.selectLanguage("English");
        searchPage.search("apple");
        searchPage.clickSearch();
        AppleJuiceWidget appleJuiceWidget = searchPage.clickAppleJuice();
        appleJuiceWidget.leaveProductReview("This is a product review");
    }
}
