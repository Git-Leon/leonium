package com.git_leon.selenium.tools.browsertools.browserhandler;


import com.git_leon.selenium.tools.TimeUtils;
import com.git_leon.selenium.tools.logging.LoggerHandler;
import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BrowserWaitLogger {
    private final LoggerHandler loggerHandler;
    private final BrowserWait wait;

    public BrowserWaitLogger(WebDriver driver, LoggerHandler loggerHandler) {
        this.loggerHandler = loggerHandler;
        this.wait = new BrowserWait(driver);
    }

    public BrowserWaitLogger(BrowserWait wait, LoggerHandler loggerHandler) {
        this.wait = wait;
        this.loggerHandler = loggerHandler;
    }

    /**
     * check if element is enabled & handle potential exception
     *
     * @param we element to check enabledness of
     * @return true if element is enabled
     */
    private boolean isEnabled(WebElement we) {
        try {
            return we.isEnabled();
        } catch (WebDriverException e) {
            return false;
        }
    }

    /**
     * check if element is displaed & handle potential exception
     *
     * @param we element to check display of
     * @return true if element is display
     */
    private boolean isDisplayed(WebElement we) {
        try {
            return we.isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }

    /**
     * wait for element to be enabled
     *
     * @param by          selector used to query for element on DOM
     * @param waitSeconds the maximum wait-time in seconds
     * @param isEnabled   desired enabledness
     * @return element if enabled state matches desired enabledness within specified wait-time
     */
    public WebElement forEnabled(By by, int waitSeconds, boolean isEnabled) {
        return wait.forEnabled(by, waitSeconds, isEnabled);
    }


    /**
     * wait for element to be visible
     *
     * @param by          selector used to query for element on DOM
     * @param waitSeconds the maximum wait-time in seconds
     * @return element if visible within specified wait-time
     */
    public WebElement forVisibility(By by, int waitSeconds) {
        return wait.forVisibility(by, waitSeconds);
    }

    /**
     * wait for element to be invisible
     *
     * @param by          selector used to query for element on DOM
     * @param waitSeconds the maximum wait-time in seconds
     * @return element if invisible within specified wait-time
     */
    public void forInvisibility(By by, int waitSeconds) {
        wait.forInvisibility(by, waitSeconds);
    }

    /**
     * wait for element to be clickable
     *
     * @param by          selector used to query for element on DOM
     * @param waitSeconds the maximum wait-time in seconds
     * @return element if clickable within specified wait-time
     */
    public WebElement forClickability(By by, int waitSeconds) {
        return wait.forClickability(by, waitSeconds);
    }


    /**
     * wait for element to be present
     *
     * @param by          selector used to query for element on DOM
     * @param waitSeconds the maximum wait-time in seconds
     * @return element if present within specified wait-time
     */
    public WebElement forPresence(By by, int waitSeconds) {
        return wait.forPresence(by, waitSeconds);
    }

    /**
     * wait for element to not be stale
     *
     * @param by          selector used to query for element on DOM
     * @param waitSeconds the maximum wait-time in seconds
     * @return element if not stale within specified wait-time
     */
    public WebElement forNotStale(By by, int waitSeconds) {
        return wait.forNotStale(by, waitSeconds);
    }

    /**
     * wait for alert to be present
     *
     * @param waitSeconds the maximum wait-time in seconds
     * @return element if alert is present within specified wait-time
     */
    public boolean forAlert(int waitSeconds) {
        return wait.forAlert(waitSeconds);
    }

    /**
     * wait for address bar's url to match one of the specified urls
     *
     * @param waitSeconds the maximum wait-time in seconds
     * @param partUrls    the urls to check against
     * @return true if url contains at least one of the specified urls
     */
    public boolean forUrlToContain(int waitSeconds, String... partUrls) {
        return wait.forUrlToContain(waitSeconds, partUrls);
    }

    /**
     * wait for all specified elements to be visible
     *
     * @param waitSeconds the maximum wait-time in seconds
     * @param by          selector used to query elements on DOM
     * @return List of queried elements
     */
    public List<WebElement> forVisibilities(int waitSeconds, By by) {
        return wait.forVisibilities(waitSeconds, by);
    }

    /**
     * get result-set of specified by-selector; wait for presence of all elements in result set
     *
     * @param waitSeconds the maximum wait-time in seconds
     * @param by          selector used to query for element on DOM
     * @return List of queried elements
     */
    public List<WebElement> forPresences(int waitSeconds, By by) {
        return wait.forPresences(waitSeconds, by);
    }

    /**
     * wait for page-load-state to be 'complete'
     *
     * @param waitSeconds the maximum wait-time in seconds
     * @return true if page's load state was 'complete' within specified wait-time
     */
    public boolean forPageLoad(int waitSeconds) {
        return wait.forPageLoad(waitSeconds);
    }

    /**
     * wait for page-load-state to be `desiredState`
     * 
     * @param waitSeconds  the maximum wait-time in seconds
     * @param desiredState desired state of page
     * @return true if page's load state was desired state within specified wait-time
     */
    public boolean forPageState(int waitSeconds, String desiredState) {
        return wait.forPageState(waitSeconds, desiredState);
    }

    /**
     * Query WebElement using selector; ensure element can have keys sent to it
     *
     * @param by          selector used to query element on DOM
     * @param waitSeconds the maximum wait-time in seconds
     * @return respective WebElement
     */ // TODO - Migrate logic to BrowserWaitConditions
    public WebElement forKeyable(By by, int waitSeconds) {
        return wait.forKeyable(by, waitSeconds);
    }

    /**
     * @param by             selector used to query element on DOM
     * @param waitSeconds    the maximum wait-time in seconds
     * @param waitConditions variable number of string representations of wait conditions
     * @return respective browserHandler element
     */
    public WebElement forConditions(By by, int waitSeconds, String... waitConditions) {
        return wait.forConditions(by, waitSeconds, waitConditions);
    }

    /**
     * wait for specified condition to return true
     *
     * @param waitSeconds the maximum wait-time in seconds
     * @param condition   condition to wait for
     * @param <T>
     * @return true if condition is true within specified wait seconds
     */
    private <T> boolean until(int waitSeconds, Function<WebDriver, T> condition) {
        return wait.until(waitSeconds, condition);
    }
}