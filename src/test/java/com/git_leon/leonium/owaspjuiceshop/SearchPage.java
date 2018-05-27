package com.git_leon.leonium.owaspjuiceshop;

import com.git_leon.leonium.browsertools.WebPage;
import com.git_leon.leonium.browsertools.With;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author leon on 4/10/18.
 */
public class SearchPage extends WebPage {
    public SearchPage(WebDriver web) {
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

    public AppleJuiceWidget clickAppleJuice() {
        By byImageAppleJuice = With.attributeValue("src", "/public/images/products/apple_juice.jpg");
        browserHandler.click(byImageAppleJuice);
        return new AppleJuiceWidget(super.driver);
    }
}
