package com.github.git_leon.leonium.automationpractice.webpages;

import com.github.git_leon.leonium.browsertools.WebPage;
import com.github.git_leon.leonium.browsertools.With;
import com.github.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import org.openqa.selenium.By;

/**
 * @author leonhunter
 * @created 05/03/2020 - 5:33 PM
 */
public class ShoppingCartSummaryPage extends WebPage {
    private By proceedToCheckoutButton = With.text("Proceed to checkout");
    private By inputEmailAddress = By.id("email-create");
    private By createAccountButton = With.text("Create an account");

    public ShoppingCartSummaryPage(BrowserHandlerInterface browserHandler) {
        super(browserHandler);
    }

    public void clickProceedToCheckout() {
        getBrowserHandler().click(proceedToCheckoutButton);
        getBrowserHandler().getWait().forPageLoad();
    }
    public void clickCreateAnAccountButton() {
        getBrowserHandler().click(createAccountButton);
    }

    @Override
    public void navigateTo() {

    }
}
