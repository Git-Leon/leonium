package com.git_leon.leonium.browsertools;

import com.git_leon.leonium.ReflectionUtils;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author leonhunter
 * @created 04/27/2020 - 8:42 PM
 */
public interface WebPageInterface {
    default By[] getDeclaredBys() {
        return ReflectionUtils
                .getFieldValues(this, By.class)
                .stream()
                .toArray(By[]::new);
    }

    default void highlightElements() {
        getBrowserHandler().highlightElements(getDeclaredBys(), "yellow");
    }

    BrowserHandler getBrowserHandler();

    void navigateTo();
}
