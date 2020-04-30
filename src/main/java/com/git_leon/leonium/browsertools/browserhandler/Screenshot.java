package com.git_leon.leonium.browsertools.browserhandler;

import com.github.git_leon.StringUtils;
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
public class Screenshot {
    protected final RemoteWebDriver remoteWebDriver;
    protected final BufferedImage bufferedImage;
    protected final String imageName;
    protected File file;

    public Screenshot(WebDriver driver, String imageName) {
        this.remoteWebDriver = (RemoteWebDriver) driver;
        this.imageName = StringUtils.removeCharacters(imageName, "[,;']}{/.|*!@#$%^&()~`:->");
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
            Long currentTime = System.currentTimeMillis();
            String currentProjectPath = System.getProperty("user.dir");
            String artifactFolder = currentProjectPath + "/target/screenshots";
            String filePath = String.format("%s/%s-%s.png", artifactFolder, imageName, currentTime);
            File outputFile = new File(filePath);
            try {
                outputFile.getParentFile().mkdirs();
                ImageIO.write(getFullBufferedImage(), "png", outputFile);
            } catch (IOException e) {
                throw new Error(e);
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
