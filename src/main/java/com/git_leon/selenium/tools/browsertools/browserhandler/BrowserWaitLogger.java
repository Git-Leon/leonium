package com.git_leon.selenium.tools.browsertools.browserhandler;


import com.git_leon.selenium.tools.TimeUtils;
import com.git_leon.selenium.tools.logging.LoggerHandler;
import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BrowserWaitLogger implements BrowserWaitInterface {
    private final BrowserWait wait;
    private final LoggerHandler logger;

    public BrowserWaitLogger(WebDriver driver, int waitSeconds) {
        this.wait = new BrowserWait(driver, waitSeconds);

        String className = driver.getClass().getSimpleName();
        String hexDecaVal = Integer.toString(driver.hashCode(), 16);
        this.logger = new LoggerHandler(className + "@" + hexDecaVal);
    }

    private <FirstArgType, SecondArgType, ReturnType> ReturnType invokeAndLog(
            BiFunction<FirstArgType, SecondArgType, ReturnType> forCondition,
            FirstArgType firstArg, SecondArgType secondArg, String logMessage) {
        String waitMessage = "\n\nWaiting for " + logMessage;
        String timeElapsedLog = "\tExecution time: %s seconds.";
        String resultValLog = "\tResulted in %s";

        logger.info(waitMessage);

        long t0 = System.currentTimeMillis();
        ReturnType returnValue = forCondition.apply(firstArg, secondArg);
        double timeElapsed = TimeUtils.getElapsedTime(t0);

        logger.info(resultValLog, returnValue);
        logger.info(timeElapsedLog, timeElapsed);

        return returnValue;
    }


    private <ArgType, ReturnType> ReturnType invokeAndLog(Function<ArgType, ReturnType> forCondition, ArgType arg, String logMessage) {
        String waitMessage = "\n\nWaiting for " + logMessage;
        String timeElapsedLog = "\tExecution time: %s seconds.";
        String resultValLog = "\tResulted in %s";

        logger.info(waitMessage);

        long t0 = System.currentTimeMillis();
        ReturnType returnValue = forCondition.apply(arg);
        double timeElapsed = TimeUtils.getElapsedTime(t0);

        logger.info(resultValLog, returnValue);
        logger.info(timeElapsedLog, timeElapsed);

        return returnValue;
    }

    private <ArgType> void consumeAndLog(Consumer<ArgType> forCondition, ArgType argument, String logMessage) {
        String waitMessage = "\n\nWaiting for " + logMessage;
        String timeElapsedLog = "\tExecution time: %s seconds.";

        logger.info(waitMessage);

        long t0 = System.currentTimeMillis();
        forCondition.accept(argument);
        double timeElapsed = TimeUtils.getElapsedTime(t0);

        logger.info(timeElapsedLog, timeElapsed);
    }


    private <ReturnType> ReturnType invokeAndLog(Supplier<ReturnType> forCondition, String logMessage) {
        String waitMessage = "\n\nWaiting for " + logMessage;
        String timeElapsedLog = "\tExecution time: %s seconds.";
        String resultValLog = "\tResulted in %s";

        logger.info(waitMessage);

        long t0 = System.currentTimeMillis();
        ReturnType returnValue = forCondition.get();
        double timeElapsed = TimeUtils.getElapsedTime(t0);

        logger.info(resultValLog, returnValue);
        logger.info(timeElapsedLog, timeElapsed);

        return returnValue;
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
        logMessage = String.format(logMessage, by);
        return invokeAndLog(wait::forEnabled, by, isEnabled, logMessage);
    }

    /**
     * wait for element to be visible
     *
     * @param by selector used to query for element on DOM
     * @return element if visible within specified wait-time
     */
    public WebElement forVisibility(By by) {
        String logMessage = "%s to be visible";
        logMessage = String.format(logMessage, by);
        return invokeAndLog(wait::forVisibility, by, logMessage);
    }

    /**
     * wait for element to be invisible
     *
     * @param by selector used to query for element on DOM
     * @return element if invisible within specified wait-time
     */
    public void forInvisibility(By by) {
        String logMessage = "%s to be invisible";
        logMessage = String.format(logMessage, by);
        consumeAndLog(wait::forInvisibility, by, logMessage);
    }

    /**
     * wait for element to be clickable
     *
     * @param by selector used to query for element on DOM
     * @return element if clickable within specified wait-time
     */
    public WebElement forClickability(By by) {
        String logMessage = "%s to be clickable";
        logMessage = String.format(logMessage, by);
        return invokeAndLog(wait::forClickability, by, logMessage);
    }


    /**
     * wait for element to be present
     *
     * @param by selector used to query for element on DOM
     * @return element if present within specified wait-time
     */
    public WebElement forPresence(By by) {
        String logMessage = "%s to be present";
        logMessage = String.format(logMessage, by);
        return invokeAndLog(wait::forPresence, by, logMessage);
    }

    /**
     * wait for element to not be stale
     *
     * @param by selector used to query for element on DOM
     * @return element if not stale within specified wait-time
     */
    public WebElement forNotStale(By by) {
        String logMessage = "%s to not be stale";
        logMessage = String.format(logMessage, by);
        return invokeAndLog(wait::forNotStale, by, logMessage);
    }

    /**
     * wait for alert to be present
     *
     * @return element if alert is present within specified wait-time
     */
    public boolean forAlert() {
        String logMessage = "an alert to be present";
        logMessage = String.format(logMessage);
        return invokeAndLog(wait::forAlert, logMessage);
    }

    /**
     * wait for address bar's url to match one of the specified urls
     *
     * @param partUrls the urls to check against
     * @return true if url contains at least one of the specified urls
     */
    public boolean forUrlToContain(String... partUrls) {
        String logMessage = "the url to contain any of the following: %s";
        logMessage = String.format(logMessage, Arrays.toString(partUrls));
        return invokeAndLog(wait::forUrlToContain, partUrls, logMessage);
    }

    /**
     * wait for all specified elements to be visible
     *
     * @param by selector used to query elements on DOM
     * @return List of queried elements
     */
    public List<WebElement> forVisibilities(By by) {
        String logMessage = "visibilities of all elements selected by [ %s ]";
        logMessage = String.format(logMessage, by);
        return invokeAndLog(wait::forVisibilities, by, logMessage);
    }

    /**
     * get result-set of specified by-selector; wait for presence of all elements in result set
     *
     * @param by selector used to query for element on DOM
     * @return List of queried elements
     */
    public List<WebElement> forPresences(By by) {
        String logMessage = "presences of all elements selected by [ %s ]";
        logMessage = String.format(logMessage, by);
        return invokeAndLog(wait::forPresences, by, logMessage);
    }

    /**
     * wait for page-load-state to be 'complete'
     *
     * @return true if page's load state was 'complete' within specified wait-time
     */
    public boolean forPageLoad() {
        String logMessage = "page to load";
        logMessage = String.format(logMessage);
        return invokeAndLog(wait::forPageLoad, logMessage);
    }

    /**
     * wait for page-load-state to be `desiredState`
     *
     * @param desiredState desired state of page
     * @return true if page's load state was desired state within specified wait-time
     */
    public boolean forPageState(String desiredState) {
        String logMessage = "page state to be [ %s ]";
        logMessage = String.format(logMessage);
        return invokeAndLog(wait::forPageState, desiredState, logMessage);
    }

    /**
     * Query WebElement using selector; ensure element can have keys sent to it
     *
     * @param by selector used to query element on DOM
     * @return respective WebElement
     */
    public WebElement forKeyable(By by) {
        String logMessage = "[ %s ] to become keyable";
        logMessage = String.format(logMessage);
        return invokeAndLog(wait::forKeyable, by, logMessage);
    }

    /**
     * @param by             selector used to query element on DOM
     * @param waitConditions variable number of string representations of wait conditions
     * @return respective browserHandler element
     */
    public WebElement forConditions(By by, SelectorWaitCondition... waitConditions) {
        String logMessage = "Selector\n\t\t[ %s ]\n\t\tto suffice each of the following conditions: %s";
        logMessage = String.format(logMessage, by, Arrays.toString(waitConditions));
        return invokeAndLog(wait::forConditions, by, waitConditions, logMessage);
    }
}