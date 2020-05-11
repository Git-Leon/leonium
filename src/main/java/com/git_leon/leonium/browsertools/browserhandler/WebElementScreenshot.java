package com.git_leon.leonium.browsertools.browserhandler;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import java.awt.image.BufferedImage;

/**
 * Created by leon on 5/25/17.
 */
class WebElementScreenshot extends Screenshot {
    private final WebEntity webEntity;

    public WebElementScreenshot(WebDriver driver, By by, String fileDirectory) {
        super(driver, fileDirectory, new WebEntity(by, driver).toString());
        this.webEntity = new WebEntity(by, driver);
    }

    @Override
    public BufferedImage getFullBufferedImage() {
        WebElement webElement = webEntity.getElement();
        Point p = webElement.getLocation();
        Dimension dim = webElement.getSize();

        int xCoord = p.getX();
        int yCoord = p.getY();
        int width = dim.getWidth();
        int height = dim.getHeight();
        return super.getFullBufferedImage().getSubimage(xCoord, yCoord, width, height);
    }
}
