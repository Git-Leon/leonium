package com.github.git_leon.leonium.automationpractice;

import com.github.git_leon.leonium.DirectoryReference;
import com.github.git_leon.leonium.automationpractice.webpages.HomePage;
import com.github.git_leon.leonium.automationpractice.webpages.SignInPage;
import com.github.git_leon.leonium.automationpractice.webpages.createanaccount.CreateAnAccountPage;
import com.github.git_leon.leonium.automationpractice.webpages.createanaccount.CreateAnAccountPageStateFactory;
import com.github.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.reporting.BrowserHandlerLayeredLogger;
import com.github.git_leon.leonium.browsertools.factories.BrowserHandlerFactory;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class TestCreateAnAccount {
    @Test
    public void test() {
        final String testName = "test-" + Long.toHexString(System.nanoTime());
        final String email = testName + "@leonium.com";
        final WebDriver driver = BrowserHandlerFactory.PHANTOMJS.getDriver();
        final BrowserHandlerLayeredLogger browserHandler = new BrowserHandlerLayeredLogger(driver);
        final HomePage homePage = new HomePage(browserHandler);
        homePage
                .getBrowserHandler()
                .getOptions()
                .SCREENSHOT_ON_EVENT
                .setValue(false);
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
            final String reportFilePath = browserHandler.getReportFilePath();
            final BrowserHandlerInterface tempBrowser = BrowserHandlerFactory.CHROME.getBrowserHandler();
            tempBrowser.navigateTo(reportFilePath);
            System.out.println(tempBrowser.getDriver().getPageSource());
        }
    }
}