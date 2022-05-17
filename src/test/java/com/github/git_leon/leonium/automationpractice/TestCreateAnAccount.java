package com.github.git_leon.leonium.automationpractice;

import com.github.git_leon.leonium.automationpractice.webpages.HomePage;
import com.github.git_leon.leonium.automationpractice.webpages.SearchResultPage;
import com.github.git_leon.leonium.automationpractice.webpages.ShoppingCartSummaryPage;
import com.github.git_leon.leonium.automationpractice.webpages.SignInPage;
import com.github.git_leon.leonium.automationpractice.webpages.createanaccount.CreateAnAccountPage;
import com.github.git_leon.leonium.automationpractice.webpages.createanaccount.CreateAnAccountPageStateFactory;
import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.reporting.BrowserHandlerLayeredLogger;
import com.github.git_leon.leonium.browsertools.factories.BrowserHandlerFactory;
import com.github.git_leon.leonium.extentreporting.ExtentTestLoggerFactory;
import com.github.git_leon.leonium.extentreporting.ExtentTestLoggerFactoryManager;
import com.github.git_leon.leonium.extentreporting.ExtentTestLoggerInterface;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.logging.Level;

public class TestCreateAnAccount {
    private ExtentTestLoggerFactory extentTestLoggerFactory;
    private BrowserHandlerLayeredLogger browserHandler;

    @BeforeEach
    public void instanceSetup() {
        final ExtentTestLoggerFactory extentTestLoggerFactory = ExtentTestLoggerFactoryManager.TEST_REPORT_DIRECTORY.getExtentTestLoggerFactory();
        final WebDriver driver = BrowserHandlerFactory.CHROME.getDriver();
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
        this.extentTestLoggerFactory = extentTestLoggerFactory;
    }

    private void test(String searchText) {
        final String email = Long.toHexString(System.nanoTime()) + "@leonium.com";
        final HomePage homePage = new HomePage(browserHandler);
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

    public void test1() {
        test("create an account1");
    }

    public void test2() {
        test("create an account2");
    }
}