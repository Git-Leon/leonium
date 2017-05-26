package com.zipcodewilmington.selenium.tools.browsertools.browserhandler;

import com.zipcodewilmington.selenium.tools.StringUtils;
import com.zipcodewilmington.selenium.tools.SystemInfo;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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
