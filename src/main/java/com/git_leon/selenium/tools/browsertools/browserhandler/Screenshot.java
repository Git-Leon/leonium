package com.git_leon.selenium.tools.browsertools.browserhandler;

import com.git_leon.selenium.tools.StringUtils;
import com.git_leon.selenium.tools.SystemInfo;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by leon on 5/25/17.
 */
class Screenshot {
    protected final RemoteWebDriver remoteWebDriver;
    protected final BufferedImage bufferedImage;
    protected final String imageName;
    protected File file;

    public Screenshot(WebDriver driver, String imageName) {
        this.remoteWebDriver = (RemoteWebDriver) driver;
        this.imageName = StringUtils.removeChars(imageName, "[,;']}{/.|*!@#$%^&()~`:->");
        this.bufferedImage = createBufferedImage();
    }

    protected BufferedImage getFullBufferedImage() {
        return bufferedImage;
    }

    private BufferedImage createBufferedImage() {
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


    protected BufferedImage getSubImage(Point p, Dimension dim) {
        int xCoord = p.getX();
        int yCoord = p.getY();
        int width = dim.getWidth();
        int height = dim.getHeight();

        return bufferedImage.getSubimage(xCoord, yCoord, width, height);
    }


    public File getFile() {
        if (file == null) {
            String filePath = String.format("%s/%s-%s.png", SystemInfo.artifactFolder, imageName, System.currentTimeMillis());
            File outputFile = new File(filePath);
            try {
                ImageIO.write(bufferedImage, "png", outputFile);
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
