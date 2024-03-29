package com.github.git_leon.leonium.browsertools.browserhandler.logging;

import com.github.git_leon.leonium.browsertools.browserhandler.core.*;
import com.github.git_leon.logging.SimpleLoggerInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.awt.image.RasterFormatException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 * Interface which decorates each composite `BrowserHandlerInterface` call with a log statement
 */
public interface BrowserHandlerLoggerInterface extends BrowserHandlerDecoratorInterface {
    SimpleLoggerInterface getLogger();

    @Override
    default WebElement getElement(By by) {
        String attemptMessage = "Attempting to get `WebElement`, using selector [ %s ]";
        String successMessage = "Successfully retrieved `WebElement` [ %s ], using selector [ %s ]";

        getLogger().log(Level.FINE, attemptMessage, by);
        WebElement we = getBrowserHandlerDecoratee().getElement(by);
        getLogger().info(successMessage, WebEntityInterface.toString(we), by);
        return we;
    }

    @Override
    default WebEntity getWebEntity(By by) {
        String attemptMessage = "Attempting to wrap `WebElement` with `WebEntity`, using selector [ %s ]";
        String successMessage = "Successfully retrieved `WebEntity` [ %s ], using selector [ %s ]";

        getLogger().log(Level.FINE, attemptMessage, by);
        WebEntity we = getBrowserHandlerDecoratee().getWebEntity(by);
        getLogger().info(successMessage, we.toString(), by);

        return we;
    }


    @Override
    default List<WebElement> getElements(By by) {
        String attemptMessage = "Attempting to wrap `WebElement` with `WebEntity`, using selector [ %s ]";
        String successMessage = "Successfully retrieved `WebEntity` [ %s ], using selector [ %s ]";

        getLogger().log(Level.FINE, attemptMessage, by);
        List<WebElement> elements = getBrowserHandlerDecoratee().getElements(by);
        List<String> webEntityStringsToBeLogged = elements
                .stream()
                .map(WebEntityInterface::toString)
                .collect(Collectors.toList());
        getLogger().info(successMessage, webEntityStringsToBeLogged, by);

        return elements;
    }


    @Override
    default String getPageLoadState() {
        String attemptMessage = "Attempting to get page-load-state from [ %s ]";
        String currentUrl = getBrowserHandlerDecoratee().getCurrentUrl();
        String pageLoadState = getBrowserHandlerDecoratee().getPageLoadState();
        String successMessage = "Successfully retrieved page-load-state of [ %s ] from [ %s ]";


        getLogger().log(Level.FINE, attemptMessage, currentUrl);
        getLogger().info(successMessage, pageLoadState, currentUrl);

        return pageLoadState;
    }


    @Override
    default void navigateTo(String newUrl) {
        String attemptMessage = "Attempting to navigate to [ %s ] from [ %s ]";
        String successMessage = "Successfully navigated to [ %s ] from [ %s ]";

        String formerUrl = getBrowserHandlerDecoratee().getCurrentUrl();
        getLogger().log(Level.FINE, attemptMessage, newUrl, formerUrl);
        getBrowserHandlerDecoratee().navigateTo(newUrl);
        getLogger().info(successMessage, newUrl, formerUrl);
    }


    @Override
    default void click(By by) {
        String attemptMessage = "Attempting to click `WebElement`, using selector [ %s ]";
        String successMessage = "Successfully clicked `WebElement` [ %s ], using selector [ %s ]\n";
        WebEntity we = new WebEntity(by, getDriver(), getWait().getWaitSeconds());
        Consumer<String> screenshotLog = (String preposition) -> {
            if (getBrowserHandlerDecoratee().getOptions().SCREENSHOT_ON_CLICK.getValue()) {
                try {
                    String screenshotDirectory = getBrowserHandlerDecoratee().getOptions().SCREENSHOT_DIRECTORY.getValue();
                    String screenshotFilePath = we.getScreenshot(screenshotDirectory).toString();
                    getLogger().log(Level.FINE, "screenshot " + preposition + " execution<br> %s", screenshotFilePath);
                } catch (RasterFormatException rfe) {
                    getLogger().error("Failed to get screenshot of interaction.");
                    getLogger().throwable(rfe);
                }
            }
        };

        getLogger().log(Level.FINE, attemptMessage, by);
        screenshotLog.accept("before");
        getBrowserHandlerDecoratee().click(by);
        getLogger().info(successMessage, we.toString(), by);
        screenshotLog.accept("after");
    }


    @Override
    default Select select(By by) {
        String attemptMessage = "Attempting to select dropdown, using selector [ %s ]";
        String successMessage = "Successfully selected dropdown `WebElement` [ %s ], using selector [ %s ]";
        WebEntity we = new WebEntity(by, getDriver(), getWait().getWaitSeconds());
        Consumer<String> screenshotLog = (String preposition) -> {
            if (getBrowserHandlerDecoratee().getOptions().SCREENSHOT_ON_CLICK.getValue()) {
                try {
                    String screenshotDirectory = getBrowserHandlerDecoratee().getOptions().SCREENSHOT_DIRECTORY.getValue();
                    String screenshotFilePath = we.getScreenshot(screenshotDirectory).toString();
                    getLogger().log(Level.FINE, "screenshot " + preposition + " execution<br> %s", screenshotFilePath);
                } catch (RasterFormatException rfe) {
                    getLogger().error("Failed to get screenshot of interaction.");
                    getLogger().throwable(rfe);
                }
            }
        };

        getLogger().log(Level.FINE, attemptMessage, by);
        screenshotLog.accept("before");
        Select select = getBrowserHandlerDecoratee().select(by);
        getLogger().info(successMessage, we.toString(), by);
        screenshotLog.accept("after");
        return select;
    }


    @Override
    default void selectByIndex(By by, int index) {
        String attemptMessage = "Attempting to select index [ %s ], using selector [ %s ]";
        String successMessage = "Successfully selected index [ %s ], using selector [ %s ]";
        Consumer<String> screenshotLog = (String preposition) -> {
            WebEntity we = new WebEntity(by, getDriver(), getWait().getWaitSeconds());
            if (getBrowserHandlerDecoratee().getOptions().SCREENSHOT_ON_CLICK.getValue()) {
                try {
                    String screenshotDirectory = getBrowserHandlerDecoratee().getOptions().SCREENSHOT_DIRECTORY.getValue();
                    String screenshotFilePath = we.getScreenshot(screenshotDirectory).toString();
                    getLogger().log(Level.FINE, "screenshot " + preposition + " execution<br> %s", screenshotFilePath);
                } catch (RasterFormatException rfe) {
                    getLogger().error("Failed to get screenshot of interaction.");
                    getLogger().throwable(rfe);
                }
            }
        };

        getLogger().log(Level.FINE, attemptMessage, index, by);
        screenshotLog.accept("before");
        getBrowserHandlerDecoratee().selectByIndex(by, index);
        getLogger().info(successMessage, index, by);
        screenshotLog.accept("after");
    }


    @Override
    default void selectByVisibleText(By by, String visibleText) {
        String attemptMessage = "Attempting to select text [ %s ], using selector [ %s ]";
        String successMessage = "Successfully selected text [ %s ], using selector [ %s ]";
        Consumer<String> screenshotLog = (String preposition) -> {
            WebEntity we = new WebEntity(by, getDriver(), getWait().getWaitSeconds());
            if (getBrowserHandlerDecoratee().getOptions().SCREENSHOT_ON_CLICK.getValue()) {
                try {
                    String screenshotDirectory = getBrowserHandlerDecoratee().getOptions().SCREENSHOT_DIRECTORY.getValue();
                    String screenshotFilePath = we.getScreenshot(screenshotDirectory).toString();
                    getLogger().log(Level.FINE, "screenshot " + preposition + " execution<br> %s", screenshotFilePath);
                } catch (RasterFormatException rfe) {
                    getLogger().error("Failed to get screenshot of interaction.");
                    getLogger().throwable(rfe);
                }
            }
        };


        getLogger().log(Level.FINE, attemptMessage, visibleText, by);
        screenshotLog.accept("before");
        getBrowserHandlerDecoratee().selectByVisibleText(by, visibleText);
        getLogger().info(successMessage, visibleText, by);
        screenshotLog.accept("after");
    }


    @Override
    default void sendKeys(By by, String keys) {
        String attemptMessage = "Attempting to send keys [ %s ], using selector [ %s ]";
        String successMessage = "Successfully sent keys [ %s ], using selector [ %s ]";
        Consumer<String> screenshotLog = (String preposition) -> {
            WebEntity we = new WebEntity(by, getDriver(), getWait().getWaitSeconds());
            if (getBrowserHandlerDecoratee().getOptions().SCREENSHOT_ON_CLICK.getValue()) {
                try {
                    String screenshotDirectory = getBrowserHandlerDecoratee().getOptions().SCREENSHOT_DIRECTORY.getValue();
                    WebElementScreenshot screenshotFilePath = we.getScreenshot(screenshotDirectory);
                    getLogger().log(Level.FINE, "screenshot " + preposition + " execution<br> %s", screenshotFilePath);
                } catch (RasterFormatException rfe) {
                    getLogger().error("Failed to get screenshot of interaction.");
                    getLogger().throwable(rfe);
                }
            }
        };

        getLogger().log(Level.FINE, attemptMessage, keys, by);
        screenshotLog.accept("before");
        getBrowserHandlerDecoratee().sendKeys(by, keys);
        getLogger().info(successMessage, keys, by);
        screenshotLog.accept("after");
    }


    @Override
    default void close() {
        String attemptMessage = "Attempting to close `BrowserHandler` instance, [ %s ]";
        String successMessage = "Successfully closed `BrowserHandler` instance, [ %s ]";

        getLogger().log(Level.FINE, attemptMessage, getBrowserHandlerDecoratee().toString());
        getBrowserHandlerDecoratee().close();
        getLogger().info(successMessage, getBrowserHandlerDecoratee().toString());
    }


    @Override
    default void highlightElement(By by, String color) {
        String attemptMessage = "Attempting to highlight element with color [ %s ], using selector [ %s ]";
        String successMessage = "Successfully highlighted element with color [ %s ], using selector [ %s ]";

        getLogger().log(Level.FINE, attemptMessage, color, by);
        getBrowserHandlerDecoratee().highlightElement(by, color);
        getLogger().info(successMessage, color, by);
    }


    @Override
    default void highlightElements(By[] bys, String color) {
        String attemptMessage = "Attempting to highlight elements with color [ %s ], using selectors [ %s ]";
        String successMessage = "Successfully highlighted elements with color [ %s ], using selectors [ %s ]";
        String selectors = Arrays.toString(bys);

        getLogger().log(Level.FINE, attemptMessage, color, selectors);
        getBrowserHandlerDecoratee().highlightElements(bys, color);
        getLogger().info(successMessage, color, selectors);
    }


    @Override
    default Screenshot screenshot() {
        String attemptMessage = "Attempting to screenshot from [ %s ]";
        String successMessage = "Successfully retrieved screenshot of [ %s ]";
        String currentUrl = getBrowserHandlerDecoratee().getCurrentUrl();
        getLogger().log(Level.FINE, attemptMessage, currentUrl);
        Screenshot screenshot = getBrowserHandlerDecoratee().screenshot();

        if (screenshot.getFile().exists()) {
            try {
                getLogger().info(successMessage, screenshot, currentUrl);
            } catch (RasterFormatException rfe) {
                getLogger().error("Failed to get screenshot of interaction.");
                getLogger().throwable(rfe);
            }
        }
        return screenshot;
    }
}
