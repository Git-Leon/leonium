package com.git_leon.selenium.tools.browsertools.browserhandler;


import com.git_leon.selenium.tools.TimeUtils;
import com.git_leon.selenium.tools.logging.LoggerHandler;
import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BrowserWait {
    private final WebDriver driver;
    private final LoggerHandler loggerHandler;

    public BrowserWait(WebDriver driver, LoggerHandler loggerHandler) {
        this.driver = driver;
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
        long t0 = System.currentTimeMillis();
        WebElement we = forPresence(by, waitSeconds);
        if (we != null && isEnabled(we) != isEnabled) {
            until(TimeUtils.remainingTime(waitSeconds, t0), BrowserWaitConditions.elementEnabledness(by, isEnabled));
        }
        return forPresence(by, TimeUtils.remainingTime(waitSeconds, t0));
    }


    /**
     * wait for element to be visible
     *
     * @param by          selector used to query for element on DOM
     * @param waitSeconds the maximum wait-time in seconds
     * @return element if visible within specified wait-time
     */
    public WebElement forVisibility(By by, int waitSeconds) {
        long t0 = System.currentTimeMillis();
        WebElement we = forPresence(by, waitSeconds);
        if (we != null && !we.isDisplayed()) {
            until(TimeUtils.remainingTime(waitSeconds, t0), ExpectedConditions.visibilityOfElementLocated(by));
        }
        return we;
    }

    /**
     * wait for element to be invisible
     *
     * @param by          selector used to query for element on DOM
     * @param waitSeconds the maximum wait-time in seconds
     * @return element if invisible within specified wait-time
     */
    public void forInvisibility(By by, int waitSeconds) {
        until(waitSeconds, ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * wait for element to be clickable
     *
     * @param by          selector used to query for element on DOM
     * @param waitSeconds the maximum wait-time in seconds
     * @return element if clickable within specified wait-time
     */
    public WebElement forClickability(By by, int waitSeconds) {
        long t0 = System.currentTimeMillis();
        until(waitSeconds, ExpectedConditions.elementToBeClickable(by));
        return forPresence(by, TimeUtils.remainingTime(waitSeconds, t0));
    }


    /**
     * wait for element to be present
     *
     * @param by          selector used to query for element on DOM
     * @param waitSeconds the maximum wait-time in seconds
     * @return element if present within specified wait-time
     */
    public WebElement forPresence(By by, int waitSeconds) {
        try {
            return driver.findElement(by);
        } catch (NoSuchElementException nsee) {
            until(waitSeconds, ExpectedConditions.presenceOfElementLocated(by));
            return driver.findElement(by);
        }
    }

    /**
     * wait for element to not be stale
     *
     * @param by          selector used to query for element on DOM
     * @param waitSeconds the maximum wait-time in seconds
     * @return element if not stale within specified wait-time
     */
    public WebElement forNotStale(By by, int waitSeconds) {
        long t0 = System.currentTimeMillis();
        WebElement we = forPresence(by, waitSeconds);
        try {
            we.getText();
        } catch (StaleElementReferenceException stere) {
            until(TimeUtils.remainingTime(waitSeconds, t0), ExpectedConditions.not(ExpectedConditions.stalenessOf(we)));
        }
        return we;
    }

    /**
     * wait for alert to be present
     *
     * @param waitSeconds the maximum wait-time in seconds
     * @return element if alert is present within specified wait-time
     */
    public boolean forAlert(int waitSeconds) {
        return until(waitSeconds, ExpectedConditions.alertIsPresent());
    }

    /**
     * wait for address bar's url to match one of the specified urls
     *
     * @param waitSeconds the maximum wait-time in seconds
     * @param partUrls    the urls to check against
     * @return true if url contains at least one of the specified urls
     */
    public boolean forUrlToContain(int waitSeconds, String... partUrls) {
        loggerHandler.info(String.format("Current url is '%s'", driver.getCurrentUrl()));
        return until(waitSeconds, BrowserWaitConditions.urlContains(partUrls));
    }

    /**
     * wait for all specified elements to be visible
     *
     * @param waitSeconds the maximum wait-time in seconds
     * @param by          selector used to query elements on DOM
     * @return List of queried elements
     */
    public List<WebElement> forVisibilities(int waitSeconds, By by) {
        long t0 = System.currentTimeMillis();
        List<WebElement> webElements = forPresences(waitSeconds, by);
        int remainingTime = TimeUtils.remainingTime(waitSeconds, t0);
        until(remainingTime, ExpectedConditions.visibilityOfAllElements(webElements));
        return webElements;
    }

    /**
     * get result-set of specified by-selector; wait for presence of all elements in result set
     *
     * @param waitSeconds the maximum wait-time in seconds
     * @param by          selector used to query for element on DOM
     * @return List of queried elements
     */
    public List<WebElement> forPresences(int waitSeconds, By by) {
        until(waitSeconds, ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return driver.findElements(by);
    }

    /**
     * wait for page-load-state to be 'complete'
     *
     * @param waitSeconds the maximum wait-time in seconds
     * @return true if page's load state was 'complete' within specified wait-time
     */
    public boolean forPageLoad(int waitSeconds) {
        return forPageState(waitSeconds, "complete");
    }

    /**
     * wait for page-load-state to be `desiredState`
     * 
     * @param waitSeconds  the maximum wait-time in seconds
     * @param desiredState desired state of page
     * @return true if page's load state was desired state within specified wait-time
     */
    public boolean forPageState(int waitSeconds, String desiredState) {
        String currentState = ((JavascriptExecutor) driver).executeScript("return document.readyState").toString();
        ExpectedCondition<Boolean> condition = BrowserWaitConditions.pageState(desiredState);
        loggerHandler.info("Waiting for webpage load-state to be [ %s ]", desiredState);

        if (currentState.equals(desiredState)) {
            return true;
        } else {
            return condition.apply(driver).booleanValue() ? true : until(waitSeconds, condition);
        }
    }

    /**
     * Query WebElement using selector; ensure element can have keys sent to it
     *
     * @param by          selector used to query element on DOM
     * @param waitSeconds the maximum wait-time in seconds
     * @return respective WebElement
     */ // TODO - Migrate logic to BrowserWaitConditions
    public WebElement forKeyable(By by, int waitSeconds) {
        boolean isKeyable = false;
        long t0 = System.currentTimeMillis();

        WebElement we = forPresence(by, waitSeconds);

        try {
            we.sendKeys(" ", Keys.BACK_SPACE);
            isKeyable = true;
        } catch (Exception e) {
            String details = "Waiting for element to be keyable [ %s ]";
            loggerHandler.info(details, we.toString());
            while (!isKeyable && TimeUtils.timeRemains(waitSeconds, t0)) {
                try {
                    we.sendKeys(" ", Keys.BACK_SPACE);
                    isKeyable = true;
                    break;
                } catch (Exception ee) {
                    continue;
                }
            }
            loggerHandler.warn("[ %s ] %s keyable.", we.toString(), isKeyable ? "is" : "is not");
        }
        return we;
    }

    /**
     * @param by             selector used to query element on DOM
     * @param waitSeconds    the maximum wait-time in seconds
     * @param waitConditions variable number of string representations of wait conditions
     * @return respective browserHandler element
     */
    public WebElement forConditions(By by, int waitSeconds, String... waitConditions) {
        WebElement we = null;
        int remainingTime = -1;
        long t0 = System.currentTimeMillis();
        for (String condition : waitConditions) {
            remainingTime = TimeUtils.remainingTime(waitSeconds, t0);
            condition = condition.toLowerCase();
            // TODO - Replace with enum
            if (condition.contains("invis")) {
                forInvisibility(by, remainingTime);
            } else if (condition.contains("visib")) {
                we = forVisibility(by, remainingTime);
            } else if (condition.contains("click")) {
                we = forClickability(by, remainingTime);
            } else if (condition.contains("enab")) {
                we = forEnabled(by, remainingTime, true);
            } else if (condition.contains("disab")) {
                we = forEnabled(by, remainingTime, false);
            } else if (condition.contains("pres")) {
                we = forPresence(by, remainingTime);
            } else if (condition.contains("stale")) {
                we = forNotStale(by, remainingTime);
            }
        }
        return we = we != null ? we : forPresence(by, remainingTime);
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
        boolean outcome = false;
        if (waitSeconds > 0) {
            loggerHandler.info("Waiting for %s", condition.toString());
            WebDriverWait wait = new WebDriverWait(driver, waitSeconds);
            try {
                wait.until(ExpectedConditions.refreshed((ExpectedCondition<T>) condition));
            } catch (ClassCastException cce) {
                wait.until(condition);
            }
            outcome = true;
        }
        return outcome;
    }
}