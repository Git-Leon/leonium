package com.git_leon.leonium.automationpractice;

import com.git_leon.leonium.automationpractice.schema.HomePage;
import com.git_leon.leonium.automationpractice.schema.SearchResultPage;
import com.git_leon.leonium.automationpractice.schema.ShoppingCartSummaryPage;
import com.git_leon.leonium.browsertools.WebCrawl;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandler;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import com.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWait;
import com.git_leon.leonium.browsertools.factories.BrowserHandlerFactory;
import com.git_leon.leonium.owaspjuiceshop.AppleJuiceWidget;
import com.git_leon.leonium.owaspjuiceshop.SearchPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * @author leonhunter
 * @created 05/03/2020 - 5:36 PM
 */
public class TestSiteTraversal   {
    private WebDriver driver;

    @Before
    public void setup() {
        this.driver = BrowserHandlerFactory.CHROME.getDriver();
    }

    @Test
    public void test() {
        HomePage homePage = new HomePage(driver);
        BrowserHandlerInterface browserHandler = homePage.getBrowserHandler();
        browserHandler.getOptions().SCREENSHOT_ON_EVENT.setValue(true);
        try {

            homePage.navigateTo();
            SearchResultPage searchResultPage = homePage.search("shirt");
            searchResultPage.clickProductImageContainer();
            searchResultPage.clickAddToCart();
            ShoppingCartSummaryPage shoppingCartSummaryPage = searchResultPage.clickProceedToCheckout();
            shoppingCartSummaryPage.clickProceedToCheckout();
            shoppingCartSummaryPage.clickCreateAnAccountButton();
        } finally {
            browserHandler.screenshot().getFile();
            browserHandler.close();
        }
    }

}
