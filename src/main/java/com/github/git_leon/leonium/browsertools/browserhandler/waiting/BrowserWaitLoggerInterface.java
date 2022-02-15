package com.github.git_leon.leonium.browsertools.browserhandler.waiting;

import com.github.git_leon.logging.FunctionExecutionLoggerInterface;
import com.github.git_leon.logging.SimpleLoggerInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public interface BrowserWaitLoggerInterface extends BrowserWaitInterface, SimpleLoggerInterface {
    SimpleLoggerInterface getLogger();

    BrowserWaitInterface getWait();

    static String formatMessage(String s, Object... o) {
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
    @Override
    default WebElement forEnabled(By by, boolean isEnabled) {
        String logMessage = "%s to be enabled";
        logMessage = formatMessage(logMessage, by);
        FunctionExecutionLoggerInterface functionExecutionLogger = this::getLogger;
        return functionExecutionLogger.logAndInvoke(getWait()::forEnabled, by, isEnabled, logMessage);
    }

    /**
     * wait for element to be visible
     *
     * @param by selector used to query for element on DOM
     * @return element if visible within specified wait-time
     */
    @Override
    default WebElement forVisibility(By by) {
        String logMessage = "%s to be visible";
        logMessage = formatMessage(logMessage, by);
        FunctionExecutionLoggerInterface functionExecutionLogger = this::getLogger;
        return functionExecutionLogger.logAndInvoke(getWait()::forVisibility, by, logMessage);
    }

    /**
     * wait for element to be invisible
     *
     * @param by selector used to query for element on DOM
     * @return element if invisible within specified wait-time
     */
    @Override
    default void forInvisibility(By by) {
        String logMessage = "%s to be invisible";
        logMessage = formatMessage(logMessage, by);
        FunctionExecutionLoggerInterface functionExecutionLogger = this::getLogger;
        functionExecutionLogger.consumeAndLog(getWait()::forInvisibility, by, logMessage);
    }

    /**
     * wait for element to be clickable
     *
     * @param by selector used to query for element on DOM
     * @return element if clickable within specified wait-time
     */
    @Override
    default WebElement forClickability(By by) {
        String logMessage = "%s to be clickable";
        logMessage = formatMessage(logMessage, by);
        FunctionExecutionLoggerInterface functionExecutionLogger = this::getLogger;
        return functionExecutionLogger.logAndInvoke(getWait()::forClickability, by, logMessage);
    }


    /**
     * wait for element to be present
     *
     * @param by selector used to query for element on DOM
     * @return element if present within specified wait-time
     */
    @Override
    default WebElement forPresence(By by) {
        String logMessage = "%s to be present";
        logMessage = formatMessage(logMessage, by);
        FunctionExecutionLoggerInterface functionExecutionLogger = this::getLogger;
        return functionExecutionLogger.logAndInvoke(getWait()::forPresence, by, logMessage);
    }

    /**
     * wait for element to not be stale
     *
     * @param by selector used to query for element on DOM
     * @return element if not stale within specified wait-time
     */
    @Override
    default WebElement forNotStale(By by) {
        String logMessage = "%s to not be stale";
        logMessage = formatMessage(logMessage, by);
        FunctionExecutionLoggerInterface functionExecutionLogger = this::getLogger;
        return functionExecutionLogger.logAndInvoke(getWait()::forNotStale, by, logMessage);
    }

    /**
     * wait for alert to be present
     *
     * @return element if alert is present within specified wait-time
     */
    @Override
    default boolean forAlert() {
        String logMessage = "an alert to be present";
        logMessage = formatMessage(logMessage);
        FunctionExecutionLoggerInterface functionExecutionLogger = this::getLogger;
        return functionExecutionLogger.logAndInvoke(getWait()::forAlert, logMessage);
    }

    /**
     * wait for address bar's url to match one of the specified urls
     *
     * @param partUrls the urls to check against
     * @return true if url contains at least one of the specified urls
     */
    @Override
    default boolean forUrlToContain(String... partUrls) {
        String logMessage = "the url to contain any of the following: %s";
        logMessage = formatMessage(logMessage, Arrays.toString(partUrls));
        FunctionExecutionLoggerInterface functionExecutionLogger = this::getLogger;
        return functionExecutionLogger.logAndInvoke(getWait()::forUrlToContain, partUrls, logMessage);
    }

    /**
     * wait for all specified elements to be visible
     *
     * @param by selector used to query elements on DOM
     * @return List of queried elements
     */
    @Override
    default List<WebElement> forVisibilities(By by) {
        String logMessage = "visibilities of all elements selected by [ %s ]";
        logMessage = formatMessage(logMessage, by);
        FunctionExecutionLoggerInterface functionExecutionLogger = this::getLogger;
        return functionExecutionLogger.logAndInvoke(getWait()::forVisibilities, by, logMessage);
    }

    /**
     * get result-set of specified by-selector; wait for presence of all elements in result set
     *
     * @param by selector used to query for element on DOM
     * @return List of queried elements
     */
    @Override
    default List<WebElement> forPresences(By by) {
        String logMessage = "presences of all elements selected by [ %s ]";
        logMessage = formatMessage(logMessage, by);
        FunctionExecutionLoggerInterface functionExecutionLogger = this::getLogger;
        return functionExecutionLogger.logAndInvoke(getWait()::forPresences, by, logMessage);
    }

    /**
     * wait for page-load-state to be 'complete'
     *
     * @return true if page's load state was 'complete' within specified wait-time
     */
    @Override
    default boolean forPageLoad() {
        String logMessage = "page to load";
        logMessage = formatMessage(logMessage);
        FunctionExecutionLoggerInterface functionExecutionLogger = this::getLogger;
        return functionExecutionLogger.logAndInvoke(getWait()::forPageLoad, logMessage);
    }

    /**
     * wait for page-load-state to be `desiredState`
     *
     * @param desiredState desired state of page
     * @return true if page's load state was desired state within specified wait-time
     */
    @Override
    default boolean forPageState(String desiredState) {
        String logMessage = "page state to be [ %s ]";
        logMessage = formatMessage(logMessage, desiredState);
        FunctionExecutionLoggerInterface functionExecutionLogger = this::getLogger;
        return functionExecutionLogger.logAndInvoke(getWait()::forPageState, desiredState, logMessage);
    }

    /**
     * Query WebElement using selector; ensure element can have keys sent to it
     *
     * @param by selector used to query element on DOM
     * @return respective WebElement
     */
    @Override
    default WebElement forKeyable(By by) {
        String logMessage = "[ %s ] to become keyable";
        logMessage = formatMessage(logMessage, by);
        FunctionExecutionLoggerInterface functionExecutionLogger = this::getLogger;
        return functionExecutionLogger.logAndInvoke(getWait()::forKeyable, by, logMessage);
    }

    /**
     * @param by             selector used to query element on DOM
     * @param waitConditions variable number of string representations of wait conditions
     * @return respective browserHandler element
     */
    @Override
    default WebElement forConditions(By by, SelectorWaitCondition... waitConditions) {
        String logMessage = "Selector\n\t\t[ %s ]\n\t\tto suffice each of the following conditions: %s";
        logMessage = formatMessage(logMessage, by, Arrays.toString(waitConditions));
        FunctionExecutionLoggerInterface functionExecutionLogger = this::getLogger;
        return functionExecutionLogger.logAndInvoke(getWait()::forConditions, by, waitConditions, logMessage);
    }

    @Override
    default void log(Level level, String s, Object... objects) {
        FunctionExecutionLoggerInterface functionExecutionLogger = this::getLogger;
        functionExecutionLogger.log(level, s, objects);
    }

    @Override
    default void enable() {
        FunctionExecutionLoggerInterface functionExecutionLogger = this::getLogger;
        functionExecutionLogger.enable();
    }

    @Override
    default void disable() {
        FunctionExecutionLoggerInterface functionExecutionLogger = this::getLogger;
        functionExecutionLogger.disable();
    }

    @Override
    default boolean isEnabled() {
        FunctionExecutionLoggerInterface functionExecutionLogger = this::getLogger;
        return functionExecutionLogger.isEnabled();
    }
}
