package com.git_leon.leonium.automationpractice;

import com.git_leon.leonium.DirectoryReference;
import com.git_leon.leonium.automationpractice.webpages.HomePage;
import com.git_leon.leonium.automationpractice.webpages.SignInPage;
import com.git_leon.leonium.automationpractice.webpages.createanaccount.CreateAnAccountPage;
import com.git_leon.leonium.automationpractice.webpages.createanaccount.CreateAnAccountPageState;
import com.git_leon.leonium.automationpractice.webpages.createanaccount.CreateAnAccountPageStateBuilder;
import com.git_leon.leonium.automationpractice.webpages.createanaccount.CreateAnAccountPageStateFactory;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import com.git_leon.leonium.browsertools.browserhandler.reporting.BrowserHandlerLayeredLogger;
import com.git_leon.leonium.browsertools.factories.BrowserHandlerFactory;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class TestCreateAnAccount {
    private WebDriver driver;

    @Before
    public void setup() {
        this.driver = BrowserHandlerFactory.CHROME.getDriver();
    }

    private void test(String searchText) {
        String testName = "test-" + Long.toHexString(System.nanoTime());
        String email = testName + "@leonium.com";
        BrowserHandlerInterface browserHandler = new BrowserHandlerLayeredLogger(driver, DirectoryReference
                .TARGET_DIRECTORY
                .getFileFromDirectory("Report-" + System.nanoTime() + ".html")
                .getAbsolutePath(),
                testName);

        HomePage homePage = new HomePage(browserHandler);
        browserHandler.getOptions().SCREENSHOT_ON_EVENT.setValue(false);
        try {

            homePage.navigateTo();
            SignInPage signInPage = homePage.clickSignIn();
            CreateAnAccountPage createAnAccountPage = signInPage.createAccount(email);
            createAnAccountPage.setPageState(CreateAnAccountPageStateFactory
                    .createRandomCreateAnAccountPageStateBuilder()
                    .setPersonalInfoEmail(email)
                    .build());
            createAnAccountPage.inputData();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            browserHandler.screenshot().getFile();
            browserHandler.close();
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