package com.github.git_leon.leonium.browsertools;

import com.github.git_leon.leonium.ReflectionUtils;
import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;

import java.util.Collection;

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

    default Collection<Cookie> getAllCookies() {
        return getBrowserHandler().getCookies();
    }

    BrowserHandlerInterface getBrowserHandler();

    void navigateTo();
}
