package com.github.git_leon.leonium.automationpractice;

import com.github.git_leon.leonium.automationpractice.webpages.HomePage;
import com.github.git_leon.leonium.automationpractice.webpages.SignInPage;
import com.github.git_leon.leonium.automationpractice.webpages.createanaccount.CreateAnAccountPage;
import com.github.git_leon.leonium.automationpractice.webpages.createanaccount.CreateAnAccountPageStateFactory;
import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.reporting.BrowserHandlerLayeredLogger;
import com.github.git_leon.leonium.browsertools.factories.BrowserHandlerFactory;
import com.github.git_leon.leonium.extentreporting.ExtentTestLoggerFactory;
import com.github.git_leon.leonium.extentreporting.ExtentTestLoggerFactoryManager;
import com.github.git_leon.leonium.extentreporting.ExtentTestLoggerInterface;
import org.junit.Test;

import java.io.File;

public class TestCreateAnAccount {
    private void test(String testName) {
        final String description = "Attempting to create an account on automationpractice.com";
        final String email = Long.toHexString(System.nanoTime()) + "@leonium.com";
        final ExtentTestLoggerFactory extentTestLoggerFactory = ExtentTestLoggerFactoryManager.TEST_REPORT_DIRECTORY.getExtentTestLoggerFactory();
        final ExtentTestLoggerInterface extentTestLogger = extentTestLoggerFactory.getExtentTestLoggerTimer(testName, description);
        final BrowserHandlerLayeredLogger browserHandler = BrowserHandlerFactory.CHROME.getBrowserHandlerLayeredLogger(extentTestLogger);
        final File screenshotDirectory = new File(extentTestLoggerFactory
                .getExtentHtmlReporter()
                .config()
                .getFilePath());
        browserHandler
                .getOptions()
                .SCREENSHOT_DIRECTORY
                .setValue(screenshotDirectory.getAbsolutePath());
        browserHandler
                .getOptions()
                .SCREENSHOT_ON_EVENT
                .setValue(true);

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
        } finally {
            browserHandler.screenshot().getFile();
            browserHandler.close();
            final BrowserHandlerInterface tempBrowser = BrowserHandlerFactory.CHROME.getBrowserHandler();
            extentTestLoggerFactory.getExtentHtmlReporter().flush();
            extentTestLoggerFactory.getExtentReports().flush();
            tempBrowser.navigateTo(extentTestLoggerFactory
                    .getExtentHtmlReporter()
                    .config()
                    .getFilePath());
        }
    }

    @Test
    public void test1() {
        test("create an account1");
    }

    @Test
    public void test2() {
        test("create an account2");
    }
}