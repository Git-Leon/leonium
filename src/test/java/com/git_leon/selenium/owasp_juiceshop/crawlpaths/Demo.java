package com.git_leon.selenium.owasp_juiceshop.crawlpaths;

import com.git_leon.selenium.owasp_juiceshop.pages.AppleJuiceWidget;
import com.git_leon.selenium.owasp_juiceshop.pages.SearchPage;
import com.git_leon.selenium.tools.browsertools.MyWebCrawl;

/**
 * @author leon on 4/10/18.
 */
public class Demo extends MyWebCrawl {
    private SearchPage searchPage;
    private AppleJuiceWidget appleJuiceWidget;

    @Override
    public void setup() {
        super.setup();
        this.searchPage = new SearchPage(this);
        this.appleJuiceWidget = new AppleJuiceWidget(this);
    }

    @Override
    public void test() {
        searchPage.navigateTo();
        searchPage.selectLanguage("English");
        searchPage.search("apple");
        searchPage.clickSearch();
        searchPage.clickAppleJuice();
        appleJuiceWidget.spam(500);
    }
}
