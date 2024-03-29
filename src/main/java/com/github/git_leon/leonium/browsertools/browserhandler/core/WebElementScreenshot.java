package com.github.git_leon.leonium.browsertools.browserhandler.core;

import com.github.git_leon.leonium.browsertools.browserhandler.waiting.BrowserWaitInterface;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import java.awt.image.BufferedImage;

/**
 * Created by leon on 5/25/17.
 */
public class WebElementScreenshot extends Screenshot {
    private final WebEntity webEntity;

    public WebElementScreenshot(BrowserWaitInterface wait, By by, String fileDirectory) {
        super(wait.getDriver(), fileDirectory, new WebEntity(by, wait.getDriver(), wait.getWaitSeconds())
                .toString()
                .concat("@")
                .concat(Long.toHexString(System.nanoTime())));
        this.webEntity = new WebEntity(by, wait.getDriver(), wait.getWaitSeconds());
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
