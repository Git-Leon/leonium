package com.github.git_leon.leonium.automationpractice;

import com.github.git_leon.leonium.DirectoryReference;
import com.github.git_leon.leonium.automationpractice.webpages.HomePage;
import com.github.git_leon.leonium.automationpractice.webpages.SearchResultPage;
import com.github.git_leon.leonium.automationpractice.webpages.ShoppingCartSummaryPage;
import com.github.git_leon.leonium.automationpractice.webpages.SignInPage;
import com.github.git_leon.leonium.automationpractice.webpages.createanaccount.CreateAnAccountPage;
import com.github.git_leon.leonium.automationpractice.webpages.createanaccount.CreateAnAccountPageStateFactory;
import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.reporting.BrowserHandlerLayeredLogger;
import com.github.git_leon.leonium.browsertools.factories.BrowserHandlerFactory;
import com.github.git_leon.leonium.extentreporting.ExtentTestLogger;
import com.github.git_leon.leonium.extentreporting.ExtentTestLoggerFactory;
import com.github.git_leon.leonium.extentreporting.ExtentTestLoggerFactoryManager;
import com.github.git_leon.stringutils.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.time.LocalDateTime;

public class TestCreateAnAccount {
    private ExtentTestLoggerFactory extentTestLoggerFactory;
    private BrowserHandlerLayeredLogger browserHandler;

    @Before
    public void instanceSetup() {
        final ExtentTestLoggerFactory extentTestLoggerFactory = ExtentTestLoggerFactoryManager.TEST_REPORT_DIRECTORY.getExtentTestLoggerFactory();
        final WebDriver driver = BrowserHandlerFactory.CHROME.getDriver();
        final String testName = driver.toString();
        final ExtentTestLogger extentTestLogger = extentTestLoggerFactory.getExtentTestLogger(testName);
        final BrowserHandlerLayeredLogger browserHandler = new BrowserHandlerLayeredLogger(driver, extentTestLogger);
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
        this.extentTestLoggerFactory = extentTestLoggerFactory;
    }

    private void test(String arg) {
        final String testName = "test-" + Long.toHexString(System.nanoTime());
        final String email = testName + "@leonium.com";
        final HomePage homePage = new HomePage(browserHandler);
        homePage
                .getBrowserHandler()
                .getOptions()
                .SCREENSHOT_ON_EVENT
                .setValue(true);
        try {
            homePage.navigateTo();
            final SignInPage signInPage = homePage.clickSignIn();
            final CreateAnAccountPage createAnAccountPage = signInPage.createAccount(email);
            createAnAccountPage.setPageState(CreateAnAccountPageStateFactory
                    .createRandomCreateAnAccountPageStateBuilder()
                    .setPersonalInfoEmail(email)
                    .setPersonalInfoPassword(email)
                    .build());
            createAnAccountPage.inputData();
        } finally {
            browserHandler.screenshot().getFile();
            browserHandler.close();
        }
    }

    @After
    public void tearDown() {
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
        test("shirt");
    }

    @Test
    public void test2() {
        test("pants");
    }
}