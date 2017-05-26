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
public class Screenshot {
    protected final RemoteWebDriver remoteWebDriver;
    protected final BufferedImage bufferedImage;
    protected final String imageName;
    protected File file;

    public Screenshot(WebDriver driver, String imageName) {
        this.remoteWebDriver = (RemoteWebDriver) driver;
        this.imageName = imageName;
        this.bufferedImage = getFullBufferedImage();
    }

    protected BufferedImage getFullBufferedImage() {
        if (this.bufferedImage == null) {
            byte[] imageInBytes = remoteWebDriver.getScreenshotAs(OutputType.BYTES);
            InputStream in = new ByteArrayInputStream(imageInBytes);
            BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bufferedImage;
        }
        return bufferedImage;
    }

    protected BufferedImage getSubImage(Point p, Dimension dim) {
        int xCoord = p.getX();
        int yCoord = p.getY();
        int width = dim.getWidth();
        int height = dim.getHeight();

        return getFullBufferedImage().getSubimage(xCoord, yCoord, width, height);
    }

    public File getFile() {
        if (file == null) {
            String filePath = String.format("%s/%s-%s.png", SystemInfo.artifactFolder, imageName, System.currentTimeMillis());
            File outputFile = new File(filePath);
            try {
                ImageIO.write(getFullBufferedImage(), "png", outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.file = outputFile;
        }
        return file;
    }

    @Override
    public String toString() {
        return String.format("<img src = '%s'>", getFile().getAbsoluteFile());
    }
}
