package com.git_leon.selenium.owasp_juiceshop.pages;

import com.git_leon.selenium.tools.browsertools.MyWebCrawl;
import com.git_leon.selenium.tools.browsertools.WebPage;
import com.git_leon.selenium.tools.browsertools.With;
import org.openqa.selenium.By;

/**
 * @author leon on 4/10/18.
 */
public class SearchPage extends WebPage {
    public SearchPage(MyWebCrawl web) {
        super(web);
    }

    public String getUrl() {
        return "https://juice-shop.herokuapp.com/#/search";
    }

    public void navigateTo() {
        browserHandler.navigateTo(getUrl());
    }

    public void selectLanguage(String language) {
        By bySelectLanguage = By.id("languageMenu");
        By byLanguageNameHopefully = With.text(language);
        browserHandler.click(bySelectLanguage);
        browserHandler.click(byLanguageNameHopefully);
    }

    public void search(String keysToSend) {
        By byInputSearch = By.className("form-control");
        browserHandler.sendKeys(byInputSearch, keysToSend);
    }

    public void clickSearch() {
        By byButtonSearch = By.id("searchButton");
        browserHandler.click(byButtonSearch);
    }

    public void clickAppleJuice() {
        By byImageAppleJuice = With.attributeValue("src", "/public/images/products/apple_juice.jpg");
        browserHandler.click(byImageAppleJuice);
    }
}
