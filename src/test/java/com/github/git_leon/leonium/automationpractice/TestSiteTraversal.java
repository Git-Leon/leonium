package com.github.git_leon.leonium.automationpractice;

import com.github.git_leon.leonium.automationpractice.webpages.HomePage;
import com.github.git_leon.leonium.automationpractice.webpages.SearchResultPage;
import com.github.git_leon.leonium.automationpractice.webpages.ShoppingCartSummaryPage;
import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.reporting.BrowserHandlerLayeredLogger;
import com.github.git_leon.leonium.browsertools.factories.BrowserHandlerFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * @author leonhunter
 * @created 05/03/2020 - 5:36 PM
 */
public class TestSiteTraversal {
    private WebDriver driver;

    @Before
    public void setup() {
        this.driver = BrowserHandlerFactory.CHROME.getDriver();
    }

    private void test(String searchText) {
        final BrowserHandlerLayeredLogger browserHandler = new BrowserHandlerLayeredLogger(driver);
        final HomePage homePage = new HomePage(browserHandler);
        browserHandler.getOptions().SCREENSHOT_ON_EVENT.setValue(true);
        try {
            homePage.navigateTo();
            final SearchResultPage searchResultPage = homePage.search(searchText);
            searchResultPage.clickProductImageContainer();
            searchResultPage.clickAddToCart();
            final ShoppingCartSummaryPage shoppingCartSummaryPage = searchResultPage.clickProceedToCheckout();
            shoppingCartSummaryPage.clickProceedToCheckout();
            shoppingCartSummaryPage.clickCreateAnAccountButton();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        } finally {
            browserHandler.screenshot().getFile();
            browserHandler.close();
            final BrowserHandlerInterface tempBrowser = BrowserHandlerFactory.CHROME.getBrowserHandler();
            tempBrowser.navigateTo(browserHandler.getReportFilePath());
        }

    }

    @Test
    public void test1() {
        test("shirt");
    }

    @Test
    public void test2() {
        test("pants");
    }
}