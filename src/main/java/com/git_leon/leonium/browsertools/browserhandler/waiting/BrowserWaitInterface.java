package com.git_leon.leonium.browsertools.browserhandler.waiting;

import com.git_leon.leonium.TimeUtils;
import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * @author leon on 5/22/18.
 */
public interface BrowserWaitInterface {

    int getWaitSeconds();

    WebDriver getDriver();

    /**
     * wait for element to be enabled
     *
     * @param by        selector used to query for element on DOM
     * @param isEnabled desired enabledness
     * @return element if enabled state matches desired enabledness within specified wait-time
     */
    default WebElement forEnabled(By by, boolean isEnabled) {
        long t0 = System.currentTimeMillis();
        WebElement we = forPresence(by);
        if (we != null && isEnabled(we) != isEnabled) {
            int remainingTime = TimeUtils.remainingTime(getWaitSeconds(), t0);
            until(remainingTime, ExpectedBrowserCondition.elementEnabledness(by, isEnabled));
        }
        return forPresence(by);
    }

    /**
     * wait for element to be visible
     *
     * @param by selector used to query for element on DOM
     * @return element if visible within specified wait-time
     */
    default WebElement forVisibility(By by) {
        long t0 = System.currentTimeMillis();
        WebElement we = forPresence(by);
        if (we != null && !we.isDisplayed()) {
            int remainingTime = TimeUtils.remainingTime(getWaitSeconds(), t0);
            until(remainingTime, ExpectedConditions.visibilityOfElementLocated(by));
        }
        return we;
    }

    /**
     * wait for element to be invisible
     *
     * @param by selector used to query for element on DOM
     * @return element if invisible within specified wait-time
     */
    default void forInvisibility(By by) {
        until(getWaitSeconds(), ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * wait for element to be clickable
     *
     * @param by selector used to query for element on DOM
     * @return element if clickable within specified wait-time
     */
    default WebElement forClickability(By by) {
        until(getWaitSeconds(), ExpectedConditions.elementToBeClickable(by));
        return forPresence(by);
    }

    /**
     * wait for element to be present
     *
     * @param by selector used to query for element on DOM
     * @return element if present within specified wait-time
     */
    default WebElement forPresence(By by) {
        try {
            return getDriver().findElement(by);
        } catch (NoSuchElementException nsee) {
            until(getWaitSeconds(), ExpectedConditions.presenceOfElementLocated(by));
            return getDriver().findElement(by);
        }
    }

    /**
     * wait for element to not be stale
     *
     * @param by selector used to query for element on DOM
     * @return element if not stale within specified wait-time
     */
    default WebElement forNotStale(By by) {
        long t0 = System.currentTimeMillis();
        WebElement we = forPresence(by);
        try {
            we.getText();
        } catch (StaleElementReferenceException stere) {
            int remainingTime = TimeUtils.remainingTime(getWaitSeconds(), t0);
            until(remainingTime, ExpectedConditions.not(ExpectedConditions.stalenessOf(we)));
        }
        return we;
    }

    /**
     * wait for alert to be present
     *
     * @return element if alert is present within specified wait-time
     */
    default boolean forAlert() {
        return until(getWaitSeconds(), ExpectedConditions.alertIsPresent());
    }

    /**
     * wait for address bar's url to match one of the specified urls
     *
     * @param partUrls the urls to check against
     * @return true if url contains at least one of the specified urls
     */
    default boolean forUrlToContain(String... partUrls) {
        return until(getWaitSeconds(), ExpectedBrowserCondition.urlContains(partUrls));
    }

    /**
     * wait for all specified elements to be visible
     *
     * @param by selector used to query elements on DOM
     * @return List of queried elements
     */
    default List<WebElement> forVisibilities(By by) {
        long t0 = System.currentTimeMillis();
        List<WebElement> webElements = forPresences(by);
        int remainingTime = TimeUtils.remainingTime(getWaitSeconds(), t0);
        until(remainingTime, ExpectedConditions.visibilityOfAllElements(webElements));
        return webElements;
    }

    /**
     * get result-set of specified by-selector; wait for presence of all elements in result set
     *
     * @param by selector used to query for element on DOM
     * @return List of queried elements
     */
    default List<WebElement> forPresences(By by) {
        until(getWaitSeconds(), ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return getDriver().findElements(by);
    }

    /**
     * wait for page-load-state to be 'complete'
     *
     * @return true if page's load state was 'complete' within specified wait-time
     */
    default boolean forPageLoad() {
        return forPageState("complete");
    }

    /**
     * wait for page-load-state to be `desiredState`
     *
     * @param desiredState desired state of page
     * @return true if page's load state was desired state within specified wait-time
     */
    default boolean forPageState(String desiredState) {
        JavascriptExecutor jse = ((JavascriptExecutor) getDriver());
        Object returnVal = jse.executeScript("return document.readyState");
        String currentState = returnVal.toString();
        ExpectedCondition<Boolean> condition = ExpectedBrowserCondition.pageState(desiredState);

        if (currentState.equals(desiredState)) {
            return true;
        } else {
            return condition.apply(getDriver()).booleanValue() ? true : until(getWaitSeconds(), condition);
        }
    }

    /**
     * Query WebElement using selector; ensure element can have keys sent to it
     *
     * @param by selector used to query element on DOM
     * @return respective WebElement
     */ // TODO - Migrate logic to ExpectedBrowserCondition
    default WebElement forKeyable(By by) {
        boolean isKeyable = false;
        long t0 = System.currentTimeMillis();

        WebElement we = forPresence(by);

        try {
            we.sendKeys(" ", Keys.BACK_SPACE);
            isKeyable = true;
        } catch (Exception e) {
            while (!isKeyable && TimeUtils.timeRemains(getWaitSeconds(), t0)) {
                try {
                    we.sendKeys(" ", Keys.BACK_SPACE);
                    isKeyable = true;
                    break;
                } catch (Exception ee) {
                    continue;
                }
            }
        }
        return we;
    }

    /**
     * @param by             selector used to query element on DOM
     * @param waitConditions variable number of string representations of wait conditions
     * @return respective browserHandler element
     */
    default WebElement forConditions(By by, SelectorWaitCondition... waitConditions) {
        for (SelectorWaitCondition condition : waitConditions) {
            condition.waitFor(by, getDriver()); // TODO - Make a decision
        }
        return forPresence(by);
    }


    /**
     * wait for specified condition to return true
     *
     * @param condition condition to wait for
     * @param <T>
     * @return true if condition is true within specified wait seconds
     */
    default <T> boolean until(int waitSeconds, Function<WebDriver, T> condition) {
        boolean outcome = false;
        if (getWaitSeconds() > 0) {
            WebDriverWait wait = new WebDriverWait(getDriver(), getWaitSeconds());
            try {
                wait.until(ExpectedConditions.refreshed((ExpectedCondition<T>) condition));
            } catch (ClassCastException cce) {
                wait.until(condition);
            }
            outcome = true;
        }
        return outcome;
    }


    /**
     * check if element is enabled & handle potential exception
     *
     * @param we element to check enabledness of
     * @return true if element is enabled
     */
    default boolean isEnabled(WebElement we) {
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
    default boolean isDisplayed(WebElement we) {
        try {
            return we.isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }
}
