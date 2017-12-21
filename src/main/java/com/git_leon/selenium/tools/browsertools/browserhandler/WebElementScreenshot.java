package com.git_leon.selenium.tools.browsertools.browserhandler;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import java.awt.image.BufferedImage;

/**
 * Created by leon on 5/25/17.
 */
class WebElementScreenshot extends Screenshot {
    private final WebElement webElement;

    public WebElementScreenshot(WebDriver driver, WebElement webElement) {
        super(driver, webElement.toString());
        this.webElement = webElement;
    }

    @Override
    public BufferedImage getFullBufferedImage() {
        Point p = webElement.getLocation();
        Dimension dim = webElement.getSize();

        int xCoord = p.getX();
        int yCoord = p.getY();
        int width = dim.getWidth();
        int height = dim.getHeight();
        return super.getFullBufferedImage().getSubimage(xCoord, yCoord, width, height);
    }
}
