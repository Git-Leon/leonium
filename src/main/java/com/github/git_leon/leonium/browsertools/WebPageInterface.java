package com.github.git_leon.leonium.browsertools;

import com.github.git_leon.leonium.ReflectionUtils;
import com.github.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import org.openqa.selenium.By;

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

    BrowserHandlerInterface getBrowserHandler();

    void navigateTo();
}
