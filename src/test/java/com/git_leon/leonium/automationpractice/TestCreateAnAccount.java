package com.git_leon.leonium.automationpractice;

import com.git_leon.leonium.DirectoryReference;
import com.git_leon.leonium.automationpractice.webpages.HomePage;
import com.git_leon.leonium.automationpractice.webpages.SignInPage;
import com.git_leon.leonium.automationpractice.webpages.createanaccount.CreateAnAccountPage;
import com.git_leon.leonium.automationpractice.webpages.createanaccount.CreateAnAccountPageStateFactory;
import com.git_leon.leonium.browsertools.browserhandler.reporting.BrowserHandlerLayeredLogger;
import com.git_leon.leonium.browsertools.factories.BrowserHandlerFactory;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class TestCreateAnAccount {
    @Test
    public void test() {
        String testName = "test-" + Long.toHexString(System.nanoTime());
        String email = testName + "@leonium.com";
        WebDriver driver = BrowserHandlerFactory.CHROME.getDriver();
        BrowserHandlerLayeredLogger browserHandler = new BrowserHandlerLayeredLogger(driver, DirectoryReference
                .TARGET_DIRECTORY
                .getFileFromDirectory("Report-" + System.nanoTime() + ".html")
                .getAbsolutePath(),
                testName);
        browserHandler.getOptions().SCREENSHOT_ON_EVENT.setValue(false);
        HomePage homePage = new HomePage(browserHandler);
        try {
            homePage.navigateTo();
            SignInPage signInPage = homePage.clickSignIn();
            CreateAnAccountPage createAnAccountPage = signInPage.createAccount(email);
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
}