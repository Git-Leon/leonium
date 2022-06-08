package com.github.git_leon.leonium.automationpractice;

import com.github.git_leon.leonium.automationpractice.webpages.HomePage;
import com.github.git_leon.leonium.automationpractice.webpages.SearchResultPage;
import com.github.git_leon.leonium.automationpractice.webpages.ShoppingCartSummaryPage;
import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.reporting.BrowserHandlerLayeredLogger;
import com.github.git_leon.leonium.browsertools.factories.BrowserHandlerFactory;
import com.github.git_leon.extentreporting.ExtentTestLoggerFactory;
import com.github.git_leon.extentreporting.ExtentTestLoggerFactoryManager;
import com.github.git_leon.extentreporting.ExtentTestLoggerInterface;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.logging.Level;

/**
 * @author leonhunter
 * @created 05/03/2020 - 5:36 PM
 */
public class TestSiteTraversal {
    private BrowserHandlerLayeredLogger browserHandler;

    @BeforeEach
    public void instanceSetup() {
        final ExtentTestLoggerFactory extentTestLoggerFactory = ExtentTestLoggerFactoryManager.TEST_REPORT_DIRECTORY.getExtentTestLoggerFactory();
        final WebDriver driver = BrowserHandlerFactory.PHANTOMJS.getDriver();
        final String testName = driver.toString();
        final ExtentTestLoggerInterface extentTestLogger = extentTestLoggerFactory.getExtentTestLogger(testName);
        final BrowserHandlerLayeredLogger browserHandler = new BrowserHandlerLayeredLogger(driver, extentTestLogger);

        browserHandler
                .getWait()
                .setWaitSeconds(3);

        browserHandler
                .getOptions()
                .SCREENSHOT_DIRECTORY
                .setValue(extentTestLoggerFactory
                        .getExtentHtmlReporter()
                        .config()
                        .getFilePath());

        browserHandler
                .getOptions()
                .SCREENSHOT_ON_EVENT
                .setValue(true);

        this.browserHandler = browserHandler;
    }

    private void test(String searchText) {
        final HomePage homePage = new HomePage(browserHandler);

        try {
            homePage.navigateTo();
            final SearchResultPage searchResultPage = homePage.search(searchText);
            searchResultPage.clickProductImageContainer();
            searchResultPage.clickAddToCart();
            final ShoppingCartSummaryPage shoppingCartSummaryPage = searchResultPage.clickProceedToCheckout();
            shoppingCartSummaryPage.clickProceedToCheckout();
            shoppingCartSummaryPage.clickCreateAnAccountButton();
        } catch (Throwable t) {
            browserHandler.getLogger().log(Level.SEVERE, t.getMessage());
            throw new RuntimeException(t);
        } finally {
            browserHandler.screenshot().getFile();
            browserHandler.close();
        }
    }

    @AfterAll
    public static void tearDown() {
        final ExtentTestLoggerFactory extentTestLoggerFactory = ExtentTestLoggerFactoryManager.TEST_REPORT_DIRECTORY.getExtentTestLoggerFactory();
        final BrowserHandlerInterface tempBrowser = BrowserHandlerFactory.CHROME.getBrowserHandler();
        extentTestLoggerFactory.getExtentHtmlReporter().flush();
        extentTestLoggerFactory.getExtentReports().flush();
        tempBrowser.navigateTo(extentTestLoggerFactory
                .getExtentHtmlReporter()
                .config()
                .getFilePath());
    }

    @Test
    public void test1() {
        test("dress");
    }

    @Test
    public void test2() {
        test("pants");
    }
}