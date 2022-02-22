package com.github.git_leon.leonium.owaspjuiceshop;

import com.github.git_leon.leonium.browsertools.WebPage;
import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.reporting.BrowserHandlerLayeredLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author leon on 4/10/18.
 */
public class AppleJuiceWidget extends WebPage {
    public AppleJuiceWidget(BrowserHandlerInterface web) {
        super(web);
    }


    public void leaveProductReview(String reviewMessage) {
        By byInputProductReview = By.id("product_review");
        By byButtonSubmit = By.id("submitButton");
        getBrowserHandler().sendKeys(byInputProductReview, reviewMessage);
        getBrowserHandler().click(byButtonSubmit);
    }

    public void spam(Integer numberOfTimes) {
        for (int i = 0; i < numberOfTimes; i++) {
            leaveProductReview("This is such an awesome tool.");
            leaveProductReview("I am testing a selenium bot.");
        }
    }

    @Override
    public void navigateTo() {

    }
}
