package com.git_leon.leonium.automationpractice;

import com.git_leon.leonium.automationpractice.schema.HomePage;
import com.git_leon.leonium.automationpractice.schema.SearchResultPage;
import com.git_leon.leonium.automationpractice.schema.ShoppingCartSummaryPage;
import com.git_leon.leonium.browsertools.WebCrawl;
import com.git_leon.leonium.browsertools.factories.BrowserHandlerFactory;
import com.git_leon.leonium.owaspjuiceshop.AppleJuiceWidget;
import com.git_leon.leonium.owaspjuiceshop.SearchPage;

/**
 * @author leonhunter
 * @created 05/03/2020 - 5:36 PM
 */
public class TestSiteTraversal extends WebCrawl {
    public TestSiteTraversal() {
        super(BrowserHandlerFactory.CHROME.getDriver());
    }

    @Override
    public void setup() {
        super.setup();
        browserHandler
                .getOptions()
                .SCREENSHOT_ON_EVENT
                .setValue(true);
    }

    @Override
    public void test() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        SearchResultPage searchResultPage = homePage.search("shirt");
        searchResultPage.clickProductImageContainer();
        searchResultPage.clickAddToCart();
        ShoppingCartSummaryPage shoppingCartSummaryPage = searchResultPage.clickProceedToCheckout();
        shoppingCartSummaryPage.clickProceedToCheckout();
        shoppingCartSummaryPage.clickCreateAnAccountButton();
    }

}
