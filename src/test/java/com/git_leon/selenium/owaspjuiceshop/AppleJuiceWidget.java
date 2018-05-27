package com.git_leon.selenium.owaspjuiceshop;

import com.git_leon.selenium.tools.browsertools.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author leon on 4/10/18.
 */
public class AppleJuiceWidget extends WebPage {
    public AppleJuiceWidget(WebDriver web) {
        super(web);
    }


    public void leaveProductReview(String reviewMessage) {
        By byInputProductReview = By.id("product_review");
        By byButtonSubmit = By.id("submitButton");
        browserHandler.sendKeys(byInputProductReview, reviewMessage);
        browserHandler.click(byButtonSubmit);
    }

    public void spam(Integer numberOfTimes) {
        for (int i = 0; i < numberOfTimes; i++) {
            leaveProductReview("This is such an awesome tool.");
            leaveProductReview("I am testing a selenium bot.");
        }
    }
}
