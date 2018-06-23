package com.git_leon.leonium.browsertools.browserhandler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author leon on 5/22/18.
 */
public interface BrowserWaitInterface {
    /**
     * wait for address bar's url to match one of the specified urls
     *
     * @param partUrls the urls to check against
     * @return true if url contains at least one of the specified urls
     */
    boolean forUrlToContain(String... partUrls);


    /**
     * wait for page-load-state to be 'complete'
     *
     * @return true if page's load state was 'complete' within specified wait-time
     */
    boolean forPageLoad();

    /**
     * wait for page-load-state to be `desiredState`
     *
     * @param desiredState desired state of page
     * @return true if page's load state was desired state within specified wait-time
     */
    boolean forPageState(String desiredState);

    /**
     * wait for element to be enabled
     *
     * @param by        selector used to query for element on DOM
     * @param isEnabled desired enabledness
     * @return element if enabled state matches desired enabledness within specified wait-time
     */// TODO - Move to SelectorWaitInterface
    WebElement forEnabled(By by, boolean isEnabled);


    /**
     * wait for element to be visible
     *
     * @param by selector used to query for element on DOM
     * @return element if visible within specified wait-time
     */// TODO - Move to SelectorWaitInterface
    WebElement forVisibility(By by);

    /**
     * wait for element to be invisible
     *
     * @param by selector used to query for element on DOM
     */// TODO - Move to SelectorWaitInterface
    void forInvisibility(By by);

    /**
     * wait for element to be clickable
     *
     * @param by selector used to query for element on DOM
     * @return element if clickable within specified wait-time
     */// TODO - Move to SelectorWaitInterface
    WebElement forClickability(By by);


    /**
     * wait for element to be present
     *
     * @param by selector used to query for element on DOM
     * @return element if present within specified wait-time
     */// TODO - Move to SelectorWaitInterface
    WebElement forPresence(By by);

    /**
     * wait for element to not be stale
     *
     * @param by selector used to query for element on DOM
     * @return element if not stale within specified wait-time
     */// TODO - Move to SelectorWaitInterface
    WebElement forNotStale(By by);

    /**
     * wait for alert to be present
     *
     * @return element if alert is present within specified wait-time
     */// TODO - Move to SelectorWaitInterface
    boolean forAlert();

    /**
     * wait for all specified elements to be visible
     *
     * @param by selector used to query elements on DOM
     * @return List of queried elements
     */// TODO - Move to SelectorWaitInterface
    List<WebElement> forVisibilities(By by);

    /**
     * get result-set of specified by-selector; wait for presence of all elements in result set
     *
     * @param by selector used to query for element on DOM
     * @return List of queried elements
     */// TODO - Move to SelectorWaitInterface
    List<WebElement> forPresences(By by);

    /**
     * Query WebElement using selector; ensure element can have keys sent to it
     *
     * @param by selector used to query element on DOM
     * @return respective WebElement
     */// TODO - Move to SelectorWaitInterface
    WebElement forKeyable(By by);

    /**
     * @param by             selector used to query element on DOM
     * @param waitConditions variable number of string representations of wait conditions
     * @return respective browserHandler element
     */// TODO - Move to SelectorWaitInterface
    WebElement forConditions(By by, SelectorWaitCondition... waitConditions);
}
