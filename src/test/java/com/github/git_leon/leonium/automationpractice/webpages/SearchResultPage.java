package com.github.git_leon.leonium.automationpractice.webpages;

import com.github.git_leon.leonium.browsertools.WebPage;
import com.github.git_leon.leonium.browsertools.With;
import com.github.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import org.openqa.selenium.By;

/**
 * @author leonhunter
 * @created 05/03/2020 - 5:28 PM
 */
public class SearchResultPage extends WebPage {
    private By productImageContainer = By.className("product-container");
    private By addToCartButton = With.text("Add to cart");
    private By proceedToCheckoutButton = With.text("Proceed to checkout");
    public SearchResultPage(BrowserHandlerInterface browserHandler) {
        super(browserHandler);
    }

    public void clickProductImageContainer() {
        getBrowserHandler().click(productImageContainer);
    }

    public void clickAddToCart() {
        getBrowserHandler().click(addToCartButton);
    }
    public ShoppingCartSummaryPage clickProceedToCheckout() {
        getBrowserHandler().click(proceedToCheckoutButton);
        getBrowserHandler().getWait().forPageLoad();
        return new ShoppingCartSummaryPage(getBrowserHandler());
    }

    @Override
    public void navigateTo() {

    }
}
