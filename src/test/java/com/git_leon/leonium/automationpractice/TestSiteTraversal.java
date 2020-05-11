package com.git_leon.leonium.automationpractice;

import com.git_leon.leonium.DirectoryReference;
import com.git_leon.leonium.automationpractice.schema.HomePage;
import com.git_leon.leonium.automationpractice.schema.SearchResultPage;
import com.git_leon.leonium.automationpractice.schema.ShoppingCartSummaryPage;
import com.git_leon.leonium.browsertools.WebCrawl;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandler;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import com.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerInterface;
import com.git_leon.leonium.browsertools.browserhandler.reporting.BrowserHandlerLayeredLogger;
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
    private static final String reportName = "Site Traversal Reports";

    @Before
    public void setup() {
        this.driver = BrowserHandlerFactory.CHROME.getDriver();
    }

    private BrowserHandlerInterface getBrowserHandler(String testName) {
        return new BrowserHandlerLayeredLogger(driver, DirectoryReference
            .TARGET_DIRECTORY
            .getFileFromDirectory("Report-" + System.nanoTime() + ".html")
            .getAbsolutePath(),
            "test-" + Long.toHexString(System.nanoTime()));
    }

    @Test
    public void test1() {
        BrowserHandlerInterface browserHandler = getBrowserHandler("test 1");
        HomePage homePage = new HomePage(browserHandler);
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

    @Test
    public void test2() {
        BrowserHandlerInterface browserHandler = getBrowserHandler("test 2");
        HomePage homePage = new HomePage(browserHandler);
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
