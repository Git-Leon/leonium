package com.git_leon.leonium.browsertools.browserhandler;



import com.github.git_leon.logging.FunctionExecutionTimeLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class BrowserWaitLogger implements BrowserWaitInterface {
    private final BrowserWait wait;
    private final FunctionExecutionTimeLogger logger;

    public BrowserWaitLogger(WebDriver driver, int waitSeconds) {
        this.wait = new BrowserWait(driver, waitSeconds);

        String className = driver.getClass().getSimpleName();
        String hexDecaVal = Integer.toString(driver.hashCode(), 16);
        this.logger = new FunctionExecutionTimeLogger(Logger.getLogger(className + "@" + hexDecaVal));
    }

    private String formatMessage(String s, Object... o) {
        String logMessagePrefix = "\n\nWaiting for ";
        return String.format(logMessagePrefix + s, o);
    }

    /**
     * wait for element to be enabled
     *
     * @param by        selector used to query for element on DOM
     * @param isEnabled desired enabledness
     * @return element if enabled state matches desired enabledness within specified wait-time
     */
    public WebElement forEnabled(By by, boolean isEnabled) {
        String logMessage = "%s to be enabled";
        logMessage = formatMessage(logMessage, by);
        return logger.invokeAndLog(wait::forEnabled, by, isEnabled, logMessage);
    }

    /**
     * wait for element to be visible
     *
     * @param by selector used to query for element on DOM
     * @return element if visible within specified wait-time
     */
    public WebElement forVisibility(By by) {
        String logMessage = "%s to be visible";
        logMessage = formatMessage(logMessage, by);
        return logger.invokeAndLog(wait::forVisibility, by, logMessage);
    }

    /**
     * wait for element to be invisible
     *
     * @param by selector used to query for element on DOM
     * @return element if invisible within specified wait-time
     */
    public void forInvisibility(By by) {
        String logMessage = "%s to be invisible";
        logMessage = formatMessage(logMessage, by);
        logger.consumeAndLog(wait::forInvisibility, by, logMessage);
    }

    /**
     * wait for element to be clickable
     *
     * @param by selector used to query for element on DOM
     * @return element if clickable within specified wait-time
     */
    public WebElement forClickability(By by) {
        String logMessage = "%s to be clickable";
        logMessage = formatMessage(logMessage, by);
        return logger.invokeAndLog(wait::forClickability, by, logMessage);
    }


    /**
     * wait for element to be present
     *
     * @param by selector used to query for element on DOM
     * @return element if present within specified wait-time
     */
    public WebElement forPresence(By by) {
        String logMessage = "%s to be present";
        logMessage = formatMessage(logMessage, by);
        return logger.invokeAndLog(wait::forPresence, by, logMessage);
    }

    /**
     * wait for element to not be stale
     *
     * @param by selector used to query for element on DOM
     * @return element if not stale within specified wait-time
     */
    public WebElement forNotStale(By by) {
        String logMessage = "%s to not be stale";
        logMessage = formatMessage(logMessage, by);
        return logger.invokeAndLog(wait::forNotStale, by, logMessage);
    }

    /**
     * wait for alert to be present
     *
     * @return element if alert is present within specified wait-time
     */
    public boolean forAlert() {
        String logMessage = "an alert to be present";
        logMessage = formatMessage(logMessage);
        return logger.invokeAndLog(wait::forAlert, logMessage);
    }

    /**
     * wait for address bar's url to match one of the specified urls
     *
     * @param partUrls the urls to check against
     * @return true if url contains at least one of the specified urls
     */
    public boolean forUrlToContain(String... partUrls) {
        String logMessage = "the url to contain any of the following: %s";
        logMessage = formatMessage(logMessage, Arrays.toString(partUrls));
        return logger.invokeAndLog(wait::forUrlToContain, partUrls, logMessage);
    }

    /**
     * wait for all specified elements to be visible
     *
     * @param by selector used to query elements on DOM
     * @return List of queried elements
     */
    public List<WebElement> forVisibilities(By by) {
        String logMessage = "visibilities of all elements selected by [ %s ]";
        logMessage = formatMessage(logMessage, by);
        return logger.invokeAndLog(wait::forVisibilities, by, logMessage);
    }

    /**
     * get result-set of specified by-selector; wait for presence of all elements in result set
     *
     * @param by selector used to query for element on DOM
     * @return List of queried elements
     */
    public List<WebElement> forPresences(By by) {
        String logMessage = "presences of all elements selected by [ %s ]";
        logMessage = formatMessage(logMessage, by);
        return logger.invokeAndLog(wait::forPresences, by, logMessage);
    }

    /**
     * wait for page-load-state to be 'complete'
     *
     * @return true if page's load state was 'complete' within specified wait-time
     */
    public boolean forPageLoad() {
        String logMessage = "page to load";
        logMessage = formatMessage(logMessage);
        return logger.invokeAndLog(wait::forPageLoad, logMessage);
    }

    /**
     * wait for page-load-state to be `desiredState`
     *
     * @param desiredState desired state of page
     * @return true if page's load state was desired state within specified wait-time
     */
    public boolean forPageState(String desiredState) {
        String logMessage = "page state to be [ %s ]";
        logMessage = formatMessage(logMessage, desiredState);
        return logger.invokeAndLog(wait::forPageState, desiredState, logMessage);
    }

    /**
     * Query WebElement using selector; ensure element can have keys sent to it
     *
     * @param by selector used to query element on DOM
     * @return respective WebElement
     */
    public WebElement forKeyable(By by) {
        String logMessage = "[ %s ] to become keyable";
        logMessage = formatMessage(logMessage, by);
        return logger.invokeAndLog(wait::forKeyable, by, logMessage);
    }

    /**
     * @param by             selector used to query element on DOM
     * @param waitConditions variable number of string representations of wait conditions
     * @return respective browserHandler element
     */
    public WebElement forConditions(By by, SelectorWaitCondition... waitConditions) {
        String logMessage = "Selector\n\t\t[ %s ]\n\t\tto suffice each of the following conditions: %s";
        logMessage = formatMessage(logMessage, by, Arrays.toString(waitConditions));
        return logger.invokeAndLog(wait::forConditions, by, waitConditions, logMessage);
    }
}