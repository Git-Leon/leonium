package com.zipcodewilmington.selenium.tools.browsertools.browserhandler;

import org.openqa.selenium.*;
import java.awt.image.BufferedImage;

/**
 * Created by leon on 5/25/17.
 */
public class WebElementScreenshot extends Screenshot {
    private final WebElement webElement;

    public WebElementScreenshot(WebDriver driver, WebElement webElement) {
        super(driver, webElement.toString());
        this.webElement = webElement;
    }

    @Override
    public BufferedImage getFullBufferedImage() {
        return super.getSubImage(webElement.getLocation(), webElement.getSize());
    }
}
