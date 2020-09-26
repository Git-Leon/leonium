package com.git_leon.leonium.automationpractice.webpages;

import com.git_leon.leonium.automationpractice.webpages.createanaccount.CreateAnAccountPage;
import com.git_leon.leonium.automationpractice.webpages.createanaccount.CreateAnAccountPageStateBuilder;
import com.git_leon.leonium.browsertools.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage extends WebPage {
    private By inputEmailLogin = By.id("email");
    private By inputPasswordLogin = By.id("passwd");
    private By buttonLogin = By.id("SubmitLogin");
    private By inputEmailCreateAccount = By.id("email_create");
    private By buttonCreateAccount = By.id("SubmitCreate");


    public SignInPage(WebDriver driver) {
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
        getBrowserHandler().sendKeys(inputEmailCreateAccount,email);
        getBrowserHandler().click(buttonCreateAccount);
        CreateAnAccountPage createAnAccountPage =  new CreateAnAccountPage(getBrowserHandler().getDriver());
        createAnAccountPage.setPageState(new CreateAnAccountPageStateBuilder()
                .setPersonalInfoEmail(email)
                .build());
        return createAnAccountPage;
    }
}
