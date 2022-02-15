package com.github.git_leon.leonium.automationpractice.webpages;

import com.github.git_leon.leonium.automationpractice.webpages.createanaccount.CreateAnAccountPage;
import com.github.git_leon.leonium.automationpractice.webpages.createanaccount.CreateAnAccountPageStateBuilder;
import com.github.git_leon.leonium.browsertools.WebPage;
import com.github.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import org.openqa.selenium.By;

public class SignInPage extends WebPage {
    private By inputEmailLogin = By.id("email");
    private By inputPasswordLogin = By.id("passwd");
    private By buttonLogin = By.id("SubmitLogin");
    private By inputEmailCreateAccount = By.id("email_create");
    private By buttonCreateAccount = By.id("SubmitCreate");


    public SignInPage(BrowserHandlerInterface driver) {
        super(driver);
    }

    @Override
    public void navigateTo() {
        getBrowserHandler().navigateTo("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }

    public void signIn(String email, String password) {
        getBrowserHandler().sendKeys(inputEmailLogin, email);
        getBrowserHandler().sendKeys(inputPasswordLogin, password);
        getBrowserHandler().click(buttonLogin);
    }

    public CreateAnAccountPage createAccount(String email) {
        getBrowserHandler().sendKeys(inputEmailCreateAccount, email);
        getBrowserHandler().click(buttonCreateAccount);
        CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage(getBrowserHandler());
        createAnAccountPage.setPageState(new CreateAnAccountPageStateBuilder()
                .setPersonalInfoEmail(email)
                .build());
        return createAnAccountPage;
    }
}
