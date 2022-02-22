package com.github.git_leon.leonium.owaspjuiceshop;

import com.github.git_leon.leonium.browsertools.WebPage;
import com.github.git_leon.leonium.browsertools.With;
import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author leon on 4/10/18.
 */
public class SearchPage extends WebPage {
    public SearchPage(BrowserHandlerInterface web) {
        super(web);
        getBrowserHandler().getOptions().SCREENSHOT_ON_EVENT.setValue(true);
    }

    public String getUrl() {
        return "https://juice-shop.herokuapp.com/#/search";
    }

    public void navigateTo() {
        getBrowserHandler().navigateTo(getUrl());
    }

    public void selectLanguage(String language) {
        By bySelectLanguage = By.id("languageMenu");
        By byLanguageNameHopefully = With.text(language);
        getBrowserHandler().click(bySelectLanguage);
        getBrowserHandler().click(byLanguageNameHopefully);
    }

    public void search(String keysToSend) {
        By byInputSearch = By.className("form-control");
        getBrowserHandler().sendKeys(byInputSearch, keysToSend);
    }

    public void clickSearch() {
        By byButtonSearch = By.id("searchButton");
        getBrowserHandler().click(byButtonSearch);
    }

    public AppleJuiceWidget clickAppleJuice() {
        By byImageAppleJuice = With.attributeValue("src", "/public/images/products/apple_juice.jpg");
        getBrowserHandler().click(byImageAppleJuice);
        return new AppleJuiceWidget(getBrowserHandler());
    }
}
