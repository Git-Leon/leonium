package com.github.git_leon.leonium.automationpractice.webpages;

import com.github.git_leon.leonium.browsertools.WebPage;
import com.github.git_leon.leonium.browsertools.With;
import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author leonhunter
 * @created 04/30/2020 - 8:51 AM
 */
public class HomePage extends WebPage {
    private By inputSearch = By.id("search_query_top");
    private By submitSearchButton = By.name("submit_search");
    private By buttonWomanCategory = With.tagAttributeValue("li", "title", "Women");
    private By buttonDressesCategory = With.tagAttributeValue("li", "title", "Dresses");
    private By buttonShirtCategory = With.tagAttributeValue("li", "title", "T-shirts");
    private By buttonSignIn = By.className("login");

    public HomePage(BrowserHandlerInterface browserHandler) {
        super(browserHandler);
    }

    public SearchResultPage search(String text) {
        getBrowserHandler().sendKeys(inputSearch, text);
        getBrowserHandler().click(submitSearchButton);
        getBrowserHandler().getWait().forPageLoad();
        return new SearchResultPage(getBrowserHandler());
    }

    public SignInPage clickSignIn() {
        getBrowserHandler().click(buttonSignIn);
        return new SignInPage(getBrowserHandler());
    }

    @Override
    public void navigateTo() {
        getBrowserHandler().navigateTo("http://automationpractice.com/index.php");
    }
}
